##  SmartFix Email Edge
Email Validator for SmartFix API. Detect disposable mail,subdomain, fix typos and verify MX.  Get it and read more on RapidAPI! https://rapidapi.com/christiandamato487/api/smartfix-email-edge
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
A high-performance email verification API running on the Cloudflare Global Edge Network. SmartFix validates adresses via live DNS/MX checks, flagging disposable "burner" emails (even those hiding behind subdomains), and suggesting real-time typo corrections.
Built for developers who need low-latency validation for client-side forms and scalable parallel processing (up to 50 emails per request) for backend bulk cleaning.

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


# SmartFix Email Validation API

Fast, edge-deployed email validation. Checks syntax, MX records, disposable domains, typos, and role-based accounts in a single request 


---

## Authentication

Every request (except `/ping`) must include this header:

| Header | Value |
|---|---|
| `X-RapidAPI-Proxy-Secret` | Your API secret, provided on subscription |

Requests without a valid secret return `401 Unauthorized`.

---

## Endpoints


---

### `POST /validate`

Validates a single email address.

**Request body**
```json
{ "email": "jhon@gmial.com" }
```

**Response**
```json
{
  "email": "jhon@gmial.com",
  "valid": false,
  "score": 40,
  "risk_level": "medium",
  "reason": "suspect_typo",
  "message": "Double check this address, it might contain a typo. Did you mean jhon@gmail.com?",
  "did_you_mean": "jhon@gmail.com",
  "details": {
    "syntax_valid": true,
    "mx_valid": true,
    "is_disposable": false,
    "is_freemail": false,
    "is_suspicious": true,
    "is_role_account": false
  },
  "execution_time_ms": 143
}
```

---

### `POST /validate-bulk`

Validates up to **100 email addresses** in a single call. Ideal for cleaning an existing contact list before a campaign.

**Request body**
```json
{
  "emails": ["user1@gmail.com", "test@mailinator.com", "jhon@gmial.com"]
}
```

**Response**
```json
{
  "summary": {
    "total": 3,
    "valid": 1,
    "invalid": 2,
    "disposable": 1,
    "suspicious_typo": 1
  },
  "results": [ /* one object per email, same shape as /validate */ ]
}
```

---

## Response fields explained

| Field | Type | Meaning |
|---|---|---|
| `email` | string | The address that was checked (trimmed, lowercased) |
| `valid` | boolean | Strict technical verdict: the domain can receive mail **and** it's not a disposable address |
| `score` | number (0–100) | Confidence score. Higher is better |
| `risk_level` | string | `low` (score ≥ 70), `medium` (40–69), `high` (< 40) — use this for quick decisioning without reading the raw score |
| `reason` | string | Machine-readable code explaining the verdict (see table below) |
| `message` | string | Human-readable message, ready to display directly in your signup/checkout form |
| `did_you_mean` | string or null | Suggested correction if a likely typo was detected, otherwise `null` |
| `details.syntax_valid` | boolean | Passes RFC-style format validation |
| `details.mx_valid` | boolean | The domain has valid mail servers (MX records) |
| `details.is_disposable` | boolean | Known temporary/throwaway email provider (e.g. Mailinator, Guerrilla Mail) |
| `details.is_freemail` | boolean | Well-known free consumer provider (Gmail, Yahoo, Outlook, etc.) |
| `details.is_suspicious` | boolean | A likely typo was detected in the domain |
| `details.is_role_account` | boolean | Generic business mailbox (`info@`, `support@`, `admin@`, etc.) rather than a personal one |
| `execution_time_ms` | number | Server-side processing time for this request |

### Reason codes

| Code | Meaning |
|---|---|
| `accept` | Clean, valid address |
| `accept_role_account` | Valid, but it's a generic business address, not a personal one |
| `suspect_typo` | Domain looks like a typo of a well-known provider |
| `reject_format` | Doesn't follow valid email syntax |
| `reject_too_long` | Exceeds the maximum allowed length (254 characters total, 64 for the part before `@`) |
| `reject_invalid_domain` | Domain has no valid mail servers |
| `reject_disposable` | Known disposable/temporary email provider |

---

## What this API does *not* do

To set expectations clearly: this API checks that a domain **can receive mail** (MX + disposable + typo detection). It does **not** perform a live SMTP handshake to confirm that one specific mailbox exists — this keeps every check fast, cheap, and free of the false positives that greylisting and catch-all domains cause on "deep verification" tools. For most signup-form and list-cleaning use cases, this is the check that actually reduces bounce rates in practice, since mistyped domains are the single largest cause of failed delivery.

---

## Rate limits

| Limit | Value |
|---|---|
| Requests per IP | 30 per minute |
| Emails per bulk request | 100 max |

Exceeding the limit returns `429 Too Many Requests`.

---

## Error responses

| Status | Meaning |
|---|---|
| `400` | Malformed JSON, or missing/invalid `email`/`emails` field |
| `401` | Missing or invalid `X-RapidAPI-Proxy-Secret` header |
| `404` | Unknown endpoint |
| `429` | Rate limit exceeded |
| `500` | Unexpected server error |

---

## Quick start (curl)

```bash
curl -X POST https://your-worker-domain.workers.dev/validate \
  -H "Content-Type: application/json" \
  -H "X-RapidAPI-Proxy-Secret: YOUR_SECRET" \
  -d '{"email": "someone@example.com"}'
```

```bash
curl -X POST https://your-worker-domain.workers.dev/validate-bulk \
  -H "Content-Type: application/json" \
  -H "X-RapidAPI-Proxy-Secret: YOUR_SECRET" \
  -d '{"emails": ["a@gmail.com", "b@mailinator.com"]}'
```

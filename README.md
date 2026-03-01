##  SmartFix Email Edge
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Email Validator for SmartFix API. Detect disposable mail,subdomain, fix typos and verify MX.  Get it and read more on RapidAPI! Feedback welcome.
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
A high-performance email verification API running on the Cloudflare Global Edge Network. SmartFix validates adresses via live DNS/MX checks, flagging disposable "burner" emails (even those hiding behind subdomains), and suggesting real-time typo corrections.
Built for developers who need low-latency validation for client-side forms and scalable parallel processing (up to 50 emails per request) for backend bulk cleaning.

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

###  Why SmartFix?
**Smart Conversion Logic** : <br>
SmartFix differentiates between fraudulent entries and accidental typo. If an address has a typo  but the domain is technically active. The system provide a suspect_typo flag and a did_you_mean suggestion.<br>

**You Hold the Key**: Since the domain is valid and not a disposable provider, we give you the flexibility to accept the lead or prompt the user for a real-time correction. <br>

**Hygiene vs. Conversion**: You choose the balance. Whether you want a strict accepts or a frictionless signup, we provide the metadata to power your logic. <br>

**Stop Ghost Lead**s: Automatically reject disposable/burner emails (like Mailinator) that ruin your sender reputation and inflate your marketing costs.<br>

**Reliable Disposable Detection**: SmartFix leverages a constantly synchronized and highly curated engine to identify temporary domains. By intercepting even the most recent burner providers, we ensure your lead quality remains uncompromised and your deliverability stays peak

**Edge Performance**: Powered by Cloudflare, processing requests at the server closest to your user for zero-lag integration.

**B2B Intelligence**: Instantly detect role-based accounts (info@, admin@)<br>

**High-Availability DNS**: Using multi-provider DNS-over-HTTPS strategy  to ensure MX lookups are performed with 99.9% reliability, even if one provider experiences downtime.
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
###  Key Features
* **Syntax Check:** Fast-fail regex check to filter out malformed strings before they hit your database
* **Typo Correction:** Advanced algorithm to catch "fat-finger" errors (e.g., `gmil.com` → `gmail.com`).
* **MX Record Lookup:** Real-time verification via DNS-over-HTTPS  to ensure the domain can receive mail.
* **Disposable & Burner Shield:** Automatically blocks temporary email providers using a dynamically updated global list <br>
 Bi-Hourly Intelligence Sync:  The system syncs with global databases every 2 hours  to catch desposable domains and stealth burner domains. <br>
* **Role Detection (B2B):** Identifies generic accounts like `info@`, `admin@`, or `sales@` for better lead qualification..
* **Confidence Scoring:** Returns a 0-100 score based on multiple signals, allowing you to set your own threshold for user registration. <br>
* **Freemail Identification**: Detects if the address belongs to a major free provider (Gmail, Outlook, Yahoo, iCloud, etc.), helping you distinguish between personal and professional leads.
* **Parallel Bulk Processing**: Validate up to 50 email addresses in a single request. The system uses an asynchronous parallel engine, delivering results nearly as fast as a single validation
* **Instant Single Validation** : Ideal for client-side forms and real-time verification
* **Subdomain Detection**: Extracts the root domain from any subdomain to accurately catch and flag temporary/disposable emails.

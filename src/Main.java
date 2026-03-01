import java.net.http.*;
import java.net.URI;

public class Main {
    public static void main(String[] args) throws Exception {
/**
 * SmartFix Email Edge - Java Example
 * * This example demonstrates how to perform bulk email validation using 
 * the SmartFix Email Edge API on RapidAPI.
 * * Features:
 * - Real-time updates every 2 hours (Disposable database)
 * - Cloudflare-powered Edge network for low latency
 * - Typo detection & MX record verification
 * - and more...
 * - TRY IT FOR FREE:
 * - You can get a FREE basic plan key to test all features directly on RapidAPI:
 * - Get your API Key: https://rapidapi.com/christiandamato487/api/smartfix-email-edge
 * * Feedbacks welcome :)
 */
      // 1. Prepare the JSON payload with a list of emails to validate.
        String json = """ 
                {
                "emails": [
                "mario.rossi@gmail.com",
                "support@apple.com",
                "test.user@outlook.com",
                "admin@libero.it",
                "user123@gmil.com",
                "info@yahoo.com",
                "fake.email@non-esisto-veramente.it",
                "temporary@10minutemail.com",
                "sales@fastwebnet.it",
                "luca.verdi@hotmail.it",
                "contact@tiscali.it",
                "webmaster@aruba.it",
                "job@protonmail.com",
                "marketing@virgilio.it",
                "wrong.domain@gmal.com",
                "customer.care@amazon.com",
                "student@university.edu",
                "dev@github.com",
                "billing@alice.it",
                "anonymous@guerrillamail.com",
                "hr@azienda-fittizia.com",
                "press@repubblica.it",
                "hello@icloud.com",
                "francesco@me.com",
                "staff@yandex.ru",
                "help@wanadoo.fr",
                "boss@live.com",
                "ceo@google.com",
                "office@pec.it",
                "postmaster@tim.it",
                "valid.user@aol.com",
                "old.account@zoho.com",
                "typo@hotmal.com",
                "service@bluehost.com",
                "no-reply@netflix.com",
                "noreply@spotify.com",
                "random.person@uol.com.br",
                "check@mailinator.com",
                "verify@yopmail.com",
                "account@btinternet.com",
                "info@gmx.de",
                "test@web.de",
                "support@orange.fr",
                "admin@qq.com",
                "user@sina.com",
                "test.typo@outlok.com",
                "feedback@bol.com.br",
                "security@facebook.com",
                "manager@linkedin.com",
                "last.test@gmail.com"
  ]
}
        """;
               
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()

                .uri(URI.create("https://smartfix-email-edge.p.rapidapi.com/validate"))
                .header("x-rapidapi-key", "REPLACE_WITH_YOUR_ACTUAL_KEY")
                .header("x-rapidapi-host", "smartfix-email-edge.p.rapidapi.com")
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();


        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body()
                .replace("{", "{\n  ")
                .replace("}", "\n}")
                .replace(",", ",\n  ")
                .replace("[", "[\n  ")
                .replace("]", "\n]"));
    }
}

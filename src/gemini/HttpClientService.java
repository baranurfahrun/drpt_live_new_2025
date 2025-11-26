package gemini;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.net.URI;

public class HttpClientService {
    private final HttpClient httpClient;

    public HttpClientService() {
        this.httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(GeminiConfig.getTimeoutSeconds()))
            .build();
    }

    public String post(String url, String body) throws Exception {
        // Tambahkan API key sebagai query parameter
        String urlWithKey = url + "?key=" + GeminiConfig.getApiKey();

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(urlWithKey))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(body))
            .timeout(Duration.ofSeconds(GeminiConfig.getTimeoutSeconds()))
            .build();

        HttpResponse<String> response = httpClient.send(request,
            HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("API request failed with status code: "
                + response.statusCode() + " and body: " + response.body());
        }

        return response.body();
    }

    public String get(String url) throws Exception {
        // Tambahkan API key sebagai query parameter
        String urlWithKey = url + "?key=" + GeminiConfig.getApiKey();

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(urlWithKey))
            .header("Content-Type", "application/json")
            .GET()
            .timeout(Duration.ofSeconds(GeminiConfig.getTimeoutSeconds()))
            .build();

        HttpResponse<String> response = httpClient.send(request,
            HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("API request failed with status code: "
                + response.statusCode() + " and body: " + response.body());
        }

        return response.body();
    }

}
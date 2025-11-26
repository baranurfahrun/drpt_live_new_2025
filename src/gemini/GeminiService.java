package gemini;

//import gemini.GeminiConfig;
//import gemini.GeminiRequest;
//import gemini.GeminiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GeminiService {
    private final HttpClientService httpClient;
    private final ObjectMapper objectMapper;

    public GeminiService() {
        this.httpClient = new HttpClientService();
        this.objectMapper = new ObjectMapper();
    }

    public String generateResponse(String prompt) {
        try {
            GeminiRequest request = new GeminiRequest(prompt);
            String requestJson = objectMapper.writeValueAsString(request);

            String responseJson = httpClient.post(GeminiConfig.getApiUrl(), requestJson);
            GeminiResponse response = objectMapper.readValue(responseJson, GeminiResponse.class);

            String generatedText = response.getFirstResponseText();
            if (generatedText == null) {
                throw new RuntimeException("No response generated from the API");
            }

            return generatedText;
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate response: " + e.getMessage(), e);
        }
    }

    public String listAvailableModels() {
        try {
            String listUrl = "https://generativelanguage.googleapis.com/v1beta/models";
            String responseJson = httpClient.get(listUrl);
            return responseJson;
        } catch (Exception e) {
            throw new RuntimeException("Failed to list models: " + e.getMessage(), e);
        }
    }
}
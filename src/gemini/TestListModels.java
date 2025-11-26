package gemini;

public class TestListModels {
    public static void main(String[] args) {
        try {
            System.out.println("Testing Google Gemini API...");
            System.out.println("API Key: " + GeminiConfig.getApiKey().substring(0, 10) + "...");
            System.out.println("\nListing available models:\n");

            GeminiService service = new GeminiService();
            String models = service.listAvailableModels();

            System.out.println(models);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

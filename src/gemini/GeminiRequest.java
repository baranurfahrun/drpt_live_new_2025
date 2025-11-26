package gemini;

public class GeminiRequest {
    private Content[] contents;

    public GeminiRequest(String text) {
        this.contents = new Content[]{
            new Content(new Part[]{new Part(text)})
        };
    }

    public Content[] getContents() {
        return contents;
    }

    public static class Content {
        private Part[] parts;

        public Content(Part[] parts) {
            this.parts = parts;
        }

        public Part[] getParts() {
            return parts;
        }
    }

    public static class Part {
        private String text;

        public Part(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }
}
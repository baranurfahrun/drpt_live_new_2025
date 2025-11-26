package gemini;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeminiResponse {
    public Candidate[] candidates;
    public PromptFeedback promptFeedback;

    public static class Candidate {
        public Content content;
        public String finishReason;
        public int index;
        public safetyRatings[] safetyRatings;
        public double avgLogprobs;
        public CitationMetadata citationMetadata;
        
        public Content getContent() {
            return content;
        }
    }

    public static class Content {
        public Part[] parts;
        public String role;

        public Part[] getParts() {
            return parts;
        }
    }

    public static class Part {
        public String text;

        public String getText() {
            return text;
        }
    }

    public static class PromptFeedback {
        public safetyRatings[] safetyRatings;
    }

    public static class safetyRatings {
        public safetyRatings[] safetyRatings;
        public String category;
        public String probability;
    }

    public Candidate[] getCandidates() {
        return candidates;
    }

    public String getFirstResponseText() {
        if (candidates != null && candidates.length > 0 
            && candidates[0].content != null 
            && candidates[0].content.parts != null 
            && candidates[0].content.parts.length > 0) {
            return candidates[0].content.parts[0].text;
        }
        return null;
    }

    private static class CitationMetadata {
        public citationSources[] citationSources;

        public citationSources[] getMetadata() {
            return citationSources;
        }
    }

    private static class citationSources {
        public int startIndex;
        public int endIndex;
        public String uri;
        public String license;
    }
}
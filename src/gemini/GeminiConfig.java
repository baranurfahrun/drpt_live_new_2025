package gemini;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GeminiConfig {
    private static final Properties properties = loadProperties();
    
    private static Properties loadProperties() {
        Properties props = new Properties();
        try (InputStream input = GeminiConfig.class.getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new RuntimeException("Unable to find application.properties");
            }
            props.load(input);
        } catch (IOException ex) {
            throw new RuntimeException("Error loading application.properties", ex);
        }
        return props;
    }
    
    public static String getApiKey() {
        return properties.getProperty("gemini.api.key");
    }
    
    public static String getApiUrl() {
        return properties.getProperty("gemini.api.url");
    }
    
    public static String getApiUploadUrl() {
        return properties.getProperty("gemini.api.upload.url");
    }
    
    public static int getTimeoutSeconds() {
        return Integer.parseInt(properties.getProperty("gemini.timeout.seconds", "30"));
    }
    
    public static String getApiKeyCloud() {
        return properties.getProperty("api.key");
    }
    public static String getProjectId() {
        return properties.getProperty("project.id");
    }
}
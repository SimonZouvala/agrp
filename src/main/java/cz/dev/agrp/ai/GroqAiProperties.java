package cz.dev.agrp.ai;

import lombok.Getter;
import lombok.Setter;

/**
 * Configuration properties for Groq AI integration.
 */
@Setter
@Getter
public class GroqAiProperties {
    /**
     * API key for authentication
     */
    private String key;
    /**
     * Base URL for the Groq AI service
     */
    private String url;
    /**
     * Model to be used for AI operations
     */
    private String model;


}

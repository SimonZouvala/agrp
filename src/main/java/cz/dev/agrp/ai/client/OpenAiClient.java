package cz.dev.agrp.ai.client;

import java.io.IOException;
/**
 * Client interface for interacting with OpenAI services.
 */
public interface OpenAiClient {

    /**
     * Generates a daily summary based on the provided message.
     *
     * @param message The input message for summary generation.
     * @return The generated daily summary.
     * @throws IOException If an I/O error occurs during the process.
     */
    String generateDailySummary(String message) throws IOException;
}

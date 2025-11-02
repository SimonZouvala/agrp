package cz.dev.agrp.ai.client;

public class OpenAiClientMock implements OpenAiClient {

    @Override
    public String generateDailySummary(String message) {
        return "Every messages are dead, Dave.";
    }
}

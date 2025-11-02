package cz.dev.agrp.ai.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.dev.agrp.ai.GroqAiProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class OpenAiClientImpl implements OpenAiClient {

    private final GroqAiProperties groqAiProperties;

    private final OkHttpClient client;
    private final ObjectMapper objectMapper;

    @Override
    public String generateDailySummary(String message) throws IOException {

        List<Map<String, String>> messages = List.of(
                Map.of("role", "user", "content", message)
        );

        log.info("Generating daily suggestions for messages: {}", messages);
        log.info("model: {}", groqAiProperties.getModel());

        Map<String, Object> payload = Map.of(
                "model", groqAiProperties.getModel(),
                "messages", messages
        );

        String json = null;
        try {
            json = objectMapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        Request request = new Request.Builder()
                .url(groqAiProperties.getUrl())
                .addHeader("Authorization", "Bearer " + groqAiProperties.getKey())
                .addHeader("Content-Type", "application/json")
                .post(RequestBody.create(MediaType.get("application/json"), json))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response: " + response.code() + " " + response.body().string());
            }
            return response.body().string();
        }
    }
}

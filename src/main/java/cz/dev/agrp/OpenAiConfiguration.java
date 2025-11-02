package cz.dev.agrp;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.dev.agrp.ai.GroqAiProperties;
import cz.dev.agrp.ai.client.OpenAiClient;
import cz.dev.agrp.ai.client.OpenAiClientImpl;
import cz.dev.agrp.ai.client.OpenAiClientMock;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class OpenAiConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "openai.api")
    public GroqAiProperties groqApiProperties() {
        return new GroqAiProperties();
    }

    @Bean
    @ConditionalOnProperty(prefix = "openai.api", name = "mock", havingValue = "false", matchIfMissing = true)
    public OpenAiClient OpenAiClientImpl() {
        return new OpenAiClientImpl(groqApiProperties(), new OkHttpClient(), new ObjectMapper());
    }

    @Bean
    @ConditionalOnProperty(prefix = "openai.api", name = "mock", havingValue = "true")
    public OpenAiClient OpenAiClientMock() {
        return new OpenAiClientMock();
    }

}

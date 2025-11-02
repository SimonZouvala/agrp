package cz.dev.agrp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class OnePostApplication {
    public static void main(String[] args) {
        log.info("Starting OnePostApplication");
        SpringApplication.run(OnePostApplication.class, args);
    }
}
package cz.dev.agrp;

import cz.dev.agrp.dto.DailySummaryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/agrp")
@RequiredArgsConstructor
public class OnePostApi {

    private final OpenPostService openPostService;


    @PostMapping("/daily-summary")
    public String getDailySummary(@RequestBody DailySummaryRequest messagesJson) throws IOException {
        return openPostService.generateDailySummary(messagesJson);
    }
}

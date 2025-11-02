package cz.dev.agrp.dto;

import lombok.Data;

import java.util.List;
/**
 * Dummy DTO representing a request for daily summary containing a list of messages.
 */
@Data
public class DailySummaryRequest {
    private List<MessageDto> messages;
}

package cz.dev.agrp.dto;

import lombok.Data;

import java.time.LocalDate;
/**
 * Dummy DTO representing a message with subject, read status, and deadline.
 */
@Data
public class MessageDto {
    private String subject;
    private boolean read;
    private LocalDate deadline;
}

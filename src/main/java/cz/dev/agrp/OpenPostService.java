package cz.dev.agrp;

import cz.dev.agrp.ai.client.OpenAiClient;
import cz.dev.agrp.dto.DailySummaryRequest;
import cz.dev.agrp.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
/**
 * Service class responsible for generating daily summaries using OpenAiClient.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OpenPostService {

    private final OpenAiClient openAiService;

    /**
     * Generates a daily summary based on the provided request.
     *
     * @param request The request containing messages to summarize.
     * @return The generated daily summary.
     * @throws IOException If an I/O error occurs during the process.
     */
    public String generateDailySummary(DailySummaryRequest request) throws IOException {
        log.info("Generating daily summary for {} messages", request.getMessages().size());

        StringBuilder sb = new StringBuilder("Jsi virtuální asistent pro službu, která zjednoduší každodenní práci s datovými zprávami.\n" +
                "Na základě těchto zpráv vytvoř krátké, přirozené shrnutí v češtině:\n" +
                "- kolik zpráv je nepřečtených\n" +
                "- kolik vyžaduje akci\n" +
                "- doporučení, co udělat dnes, aby zítra byl klid.\n" +
                "Napiš to ve stylu Holly ze seriálu Červený trpaslík. Chtěl bych to mít podobně jako začátek každé epizody. " +
                "Souhrn co se stalo a pak na závěr nějaký nepodstatný detail o posádce nebo lodi \n" +
                "Zprávy:\n");
        
        for (MessageDto msg : request.getMessages()) {
            sb.append("- ")
                    .append(msg.getSubject())
                    .append(msg.isRead() ? " (přečtená)" : " (nepřečtená)");
            if (msg.getDeadline() != null)
                sb.append(", termín: ").append(msg.getDeadline());
            sb.append("\n");
        }

        return openAiService.generateDailySummary(sb.toString());
    }
}

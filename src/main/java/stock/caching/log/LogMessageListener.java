package stock.caching.log;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import stock.caching.log.dto.StockSearchLogSaveForm;
import stock.caching.log.service.SearchLogService;

@Component
@RequiredArgsConstructor
@Slf4j
public class LogMessageListener {

    private final SearchLogService searchLogService;

    @KafkaListener(topics = "stock", groupId = "log")
    public void listenStockCache(String text) {
        log.info("listenStockCache: {}", text);
        String code = text.split(",")[0];
        String member = text.split(",")[1];
        searchLogService.save(StockSearchLogSaveForm.builder()
            .code(code)
            .member(member)
            .build());
    }

}

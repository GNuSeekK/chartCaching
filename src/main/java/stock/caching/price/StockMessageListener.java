package stock.caching.price;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import stock.caching.price.exception.CacheLogicInProgressException;
import stock.caching.price.service.CacheStockPriceService;

@Component
@RequiredArgsConstructor
@Slf4j
public class StockMessageListener {

    private final CacheStockPriceService cacheStockPriceService;

    @KafkaListener(topics = "stock", groupId = "caching")
    public void listenStockCache(String text) {
        String code = text.split(",")[0];
        try {
            cacheStockPriceService.saveStockPriceListDto(code);
        } catch (CacheLogicInProgressException e) {
            log.info("CacheLogicInProgressException: {}", e.getMessage());
        }
    }

    @KafkaListener(topics = "stock-cache-priority", groupId = "caching")
    public void changePriority(String priority) {
        log.info("changePriority: {}", priority);
        cacheStockPriceService.changePriority(Integer.parseInt(priority));
    }
}

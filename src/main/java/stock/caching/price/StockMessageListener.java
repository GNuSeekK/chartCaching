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

    @KafkaListener(topics = "stock")
    public void listenStockCache(String code) {
        try {
            cacheStockPriceService.saveStockPriceListDto(code);
        } catch (CacheLogicInProgressException e) {
            log.info("CacheLogicInProgressException: {}", e.getMessage());
        }
    }

    @KafkaListener(topics = "stock-cache-priority")
    public void changePriority(String priority) {
        log.info("changePriority: {}", priority);
        cacheStockPriceService.changePriority(Integer.parseInt(priority));
    }
}

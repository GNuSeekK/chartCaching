package stock.caching.price.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import stock.caching.price.dto.StockPriceDto;

@Getter
@Builder
@AllArgsConstructor
public class CacheStockPrice {

    private final Integer open;
    private final Integer high;
    private final Integer low;
    private final Integer close;
    private final Integer volume;
    private final Long date;

    public CacheStockPrice(StockPriceDto stockPriceDto) {
        this.open = stockPriceDto.getOpen();
        this.high = stockPriceDto.getHigh();
        this.low = stockPriceDto.getLow();
        this.close = stockPriceDto.getClose();
        this.volume = stockPriceDto.getVolume();
        this.date = stockPriceDto.getDate().toEpochDay();
    }

}

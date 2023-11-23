package stock.caching.price.dto;

import java.time.LocalDate;
import lombok.Getter;
import stock.caching.domain.StockPrice;

@Getter
public class StockPriceDto {

    private final Integer open;
    private final Integer high;
    private final Integer low;
    private final Integer close;
    private final Integer volume;
    private final LocalDate date;


    public StockPriceDto(StockPrice stockPrice) {
        this.open = stockPrice.getOpen();
        this.high = stockPrice.getHigh();
        this.low = stockPrice.getLow();
        this.close = stockPrice.getClose();
        this.volume = stockPrice.getVolume();
        this.date = stockPrice.getDate();
    }
}

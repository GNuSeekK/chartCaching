package stock.caching.price.exception;

public class LockedCacheStockPriceException extends RuntimeException {

    public LockedCacheStockPriceException() {
        super("캐시가 잠겨있습니다.");
    }

}

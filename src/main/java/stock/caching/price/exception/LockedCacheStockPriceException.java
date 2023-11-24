package stock.caching.price.exception;

public class LockedCacheStockPriceException extends CacheLogicInProgressException {

    public LockedCacheStockPriceException() {
        super("캐시가 잠겨있습니다.");
    }

}

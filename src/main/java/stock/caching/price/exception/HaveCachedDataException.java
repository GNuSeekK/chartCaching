package stock.caching.price.exception;

public class HaveCachedDataException extends CacheLogicInProgressException {

    public HaveCachedDataException() {
        super("이미 캐시된 데이터가 있습니다.");
    }

}

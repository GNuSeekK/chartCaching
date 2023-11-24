package stock.caching.price.exception;

public class CacheLogicInProgressException extends RuntimeException {

    public CacheLogicInProgressException() {
        super("캐시 로직이 진행중입니다.");
    }

    public CacheLogicInProgressException(String message) {
        super(message);
    }
}

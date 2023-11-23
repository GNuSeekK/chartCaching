package stock.caching.price.exception;

public class NoStockPriceDataException extends RuntimeException {

    public NoStockPriceDataException() {
        super("해당 주식의 가격 정보가 없습니다.");
    }
}

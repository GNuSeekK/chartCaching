package stock.caching.log.dto;

import java.time.LocalDate;
import lombok.Builder;
import stock.caching.domain.SearchLog;
import stock.caching.domain.SearchLogId;

@Builder
public class StockSearchLogSaveForm {

    private final String code;
    private final String member;


    public SearchLog toEntity() {
        return SearchLog.builder()
            .code(code)
            .id(makeLogId(member))
            .build();
    }

    private SearchLogId makeLogId(String member) {
        return new SearchLogId(member);
    }

}

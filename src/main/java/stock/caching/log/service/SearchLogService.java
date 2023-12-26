package stock.caching.log.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import stock.caching.log.dto.StockSearchLogSaveForm;
import stock.caching.log.repository.SearchLogRepository;

@Service
@RequiredArgsConstructor
public class SearchLogService {

    private final SearchLogRepository searchLogRepository;

    public void save(StockSearchLogSaveForm form) {
        searchLogRepository.save(form.toEntity());
    }

}

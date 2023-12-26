package stock.caching.log.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stock.caching.domain.SearchLog;

public interface SearchLogRepository extends JpaRepository<SearchLog, Long> {

}

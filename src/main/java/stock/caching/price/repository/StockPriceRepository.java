package stock.caching.price.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import stock.caching.domain.StockPrice;

public interface StockPriceRepository extends JpaRepository<StockPrice, Long> {

    @Query("select sp from StockPrice sp where sp.id.code = :code")
    Optional<List<StockPrice>> findAll(@Param("code") String code);

}

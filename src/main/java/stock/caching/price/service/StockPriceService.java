package stock.caching.price.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stock.caching.price.dto.StockPriceDto;
import stock.caching.price.dto.StockPriceListDto;
import stock.caching.price.exception.NoStockPriceDataException;
import stock.caching.price.repository.StockPriceRepository;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StockPriceService {

    private final StockPriceRepository stockPriceRepository;
    public StockPriceListDto getStockPriceListDto(String code) {
        List<StockPriceDto> stockPriceDtos = stockPriceRepository.findAll(code)
            .orElseThrow(NoStockPriceDataException::new)
            .stream()
            .map(StockPriceDto::new)
            .collect(Collectors.toList());
        return StockPriceListDto.builder()
            .stockCode(code)
            .stockPriceDtoList(stockPriceDtos)
            .build();
    }

}

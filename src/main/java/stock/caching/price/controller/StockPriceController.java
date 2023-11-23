package stock.caching.price.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import stock.caching.price.service.CacheStockPriceService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stockPrice")
public class StockPriceController {

    private final CacheStockPriceService cacheStockPriceService;

    @GetMapping
    public String cache(@RequestParam String code) {
        cacheStockPriceService.saveStockPriceListDto(code);
        return "success";
    }
}

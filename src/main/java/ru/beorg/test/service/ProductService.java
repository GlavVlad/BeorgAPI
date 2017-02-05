package ru.beorg.test.service;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.beorg.test.dto.PriceDto;
import ru.beorg.test.entity.Product;
import ru.beorg.test.exception.PriceException;
import ru.beorg.test.feign.Prices;
import ru.beorg.test.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Created by Vlad on 05.02.2017.
 */
@Service
public class ProductService {

    private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    @Value("${percent}")
    private Integer percent;

    @Value("${prices.url}")
    private String url;

    @Autowired
    private ProductRepository repository;

    public Product save(Product product) {
        return repository.saveAndFlush(product);
    }

    public Optional<Product> findOneByName(String name) {
        return repository.findOneByName(name);
    }

    public void checkPrice(BigDecimal price, BigDecimal average) {
        if (!isInRange(price, average)) {
            throw new PriceException(PriceException.Code.PRICE_NOT_IN_RANGE);
        }
    }

    private boolean isInRange(BigDecimal price, BigDecimal average) {
        return BigDecimal.ONE.subtract(new BigDecimal(percent).divide(ONE_HUNDRED)).multiply(average).compareTo(price) == -1
                && BigDecimal.ONE.add(new BigDecimal(percent).divide(ONE_HUNDRED)).multiply(average).compareTo(price) == 1;
    }

    public BigDecimal getAverage(Long productId) {
        Prices prices = Feign.builder()
                .decoder(new JacksonDecoder())
                .target(Prices.class, url);
        return prices.average(productId);
    }

    public void addPrice(PriceDto dto) {
        Prices prices = Feign.builder()
                .encoder(new JacksonEncoder())
                .target(Prices.class, url);
        prices.add(dto);
    }
}

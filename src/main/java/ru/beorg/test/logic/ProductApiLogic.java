package ru.beorg.test.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.beorg.test.dto.PriceDto;
import ru.beorg.test.dto.ProductPriceDto;
import ru.beorg.test.entity.Product;
import ru.beorg.test.service.ProductService;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Created by Vlad on 05.02.2017.
 */
@Service
public class ProductApiLogic {

    @Autowired
    private ProductService service;

    public void add(ProductPriceDto dto) {
        Optional<Product> product = service.findOneByName(dto.getName());

        product.ifPresent(p -> {
            BigDecimal average = service.getAverage(p.getId());
            if (!average.equals(BigDecimal.ZERO)) {
                service.checkPrice(dto.getPrice(), average);
            }
            PriceDto priceDto = new PriceDto();
            priceDto.setPrice(dto.getPrice());
            priceDto.setProductId(p.getId());
            service.addPrice(priceDto);
        });

        if (!product.isPresent()) {
            Product newProduct = new Product();
            newProduct.setName(dto.getName());
            Product productDB = service.save(newProduct);
            PriceDto priceDto = new PriceDto();
            priceDto.setPrice(dto.getPrice());
            priceDto.setProductId(productDB.getId());
            service.addPrice(priceDto);
        }
    }
}

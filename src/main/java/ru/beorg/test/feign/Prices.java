package ru.beorg.test.feign;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import ru.beorg.test.dto.PriceDto;

import java.math.BigDecimal;

/**
 * Created by Vlad on 05.02.2017.
 */
public interface Prices {

    @RequestLine("GET /average/{productId}")
    BigDecimal average(@Param("productId") Long productId);

    @RequestLine("POST /add")
    @Headers("Content-Type: application/json")
    void add(PriceDto dto);
}

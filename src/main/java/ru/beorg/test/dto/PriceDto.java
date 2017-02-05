package ru.beorg.test.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by Vlad on 05.02.2017.
 */
@Data
public class PriceDto {

    @NotNull
    private Long productId;
    @NotNull
    private BigDecimal price;
}

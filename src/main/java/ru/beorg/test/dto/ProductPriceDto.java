package ru.beorg.test.dto;

import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.NotEmpty;

import java.math.BigDecimal;

/**
 * Created by Vlad on 05.02.2017.
 */
@Data
public class ProductPriceDto {

    @NotEmpty
    private String name;
    @NonNull
    private BigDecimal price;
}

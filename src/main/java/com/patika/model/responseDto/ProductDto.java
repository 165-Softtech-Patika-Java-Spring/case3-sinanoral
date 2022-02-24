package com.patika.model.responseDto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {
    private String name;
    private BigDecimal price;
}

package com.example;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public class Clothe {
    private int ID;
    private String name; 
    private BigDecimal price;
    private short quantity;
    private String description;

    public BigDecimal discaunt(String percent) {
        return this.price.subtract(this.price.multiply(new BigDecimal(percent).divide(new BigDecimal(100))));
    }
}

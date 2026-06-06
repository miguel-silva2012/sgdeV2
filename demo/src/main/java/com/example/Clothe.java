package com.example;

import java.math.BigDecimal;

public class Clothe {
    private BigDecimal price;
    private String name; 
    private int ID;

    public Clothe(BigDecimal price, String name, int ID) {
        this.name = name;
        this.price = price;
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal discaunt(String percent) {
        return this.price.subtract(this.price.multiply(new BigDecimal(percent).divide(new BigDecimal(100))));
    }
}

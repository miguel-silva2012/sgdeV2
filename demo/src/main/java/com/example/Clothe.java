package com.example;

import java.math.BigDecimal;

public class Clothe {
    public BigDecimal price;
    public String name; 
    public int ID;

    public Clothe(String price, String name, int ID) {
        this.ID = ID;
        this.name = name;
        this.price = new BigDecimal(price);
    }

    public BigDecimal discaunt(String percent) {
        return this.price.subtract(this.price.multiply(new BigDecimal(percent).divide(new BigDecimal(100))));
    }
}

package com.example;

import java.math.BigDecimal;

public class Clothe {
    private BigDecimal price;
    private short quantity;
    private String description;
    private String name; 
    private int ID;

    public Clothe(int ID, String name, BigDecimal price, short quantity, String description) {
        this.name = name;
        this.price = price;
        this.ID = ID;
        this.quantity = quantity;
        this.description = description;
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

    public short getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal discaunt(String percent) {
        return this.price.subtract(this.price.multiply(new BigDecimal(percent).divide(new BigDecimal(100))));
    }
}

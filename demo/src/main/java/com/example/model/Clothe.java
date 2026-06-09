package com.example.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public class Clothe {
    private short ID;
    private String name; 
    private BigDecimal price;
    private short quantity;
    private String description;
}

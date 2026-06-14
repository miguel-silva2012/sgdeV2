package com.example;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public class ClotheDAO {
    private String name; 
    private BigDecimal price;
    private short quantity;
    private String description;
}

package com.local.service.data.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class InventoryEntity {

    private Integer plu;
    private String supplier;
    private String name;
    private Integer inStock;
    private Float price;
    private String currency;
}

package com.increff.employee.model.Forms;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProductForm {
    @NotNull
    private String barcode;
    private String brand;
    private String category;

   @NotNull
    private String name;

    @NotNull
    private Double mrp;
}
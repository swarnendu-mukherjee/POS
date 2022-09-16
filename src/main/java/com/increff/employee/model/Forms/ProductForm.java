package com.increff.employee.model.Forms;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductForm {
    @NotNull
    private String barcode;
    private int brandCategory;
    @NotNull
    private String name;
    private double mrp;
}
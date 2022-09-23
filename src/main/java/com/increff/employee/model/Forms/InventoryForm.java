package com.increff.employee.model.Forms;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class InventoryForm {

    @NotNull
    public Long id;

    @NotNull
    public Long quantity;
}

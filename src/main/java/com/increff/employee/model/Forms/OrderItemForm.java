package com.increff.employee.model.Forms;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemForm {

    private Long orderId;
    private String barcode;
    private Long quantity;
}

package com.increff.employee.model.Data;

import com.increff.employee.model.Forms.OrderItemForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemData extends OrderItemForm {

    private Long id;
    private Long orderId;
    private Long productId;
    private Double sellingPrice;
}

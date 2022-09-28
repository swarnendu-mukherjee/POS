package com.increff.employee.Dto.Helper;

import com.increff.employee.model.Data.OrderData;
import com.increff.employee.model.Data.OrderItemData;
import com.increff.employee.pojo.OrderItemPojo;

public class OrderItemHelper {

    public static OrderItemData convertToData(OrderItemPojo pojo) {
        OrderItemData orderItemData = new OrderItemData();
        orderItemData.setId(pojo.getId());
        orderItemData.setOrderId(pojo.getOrderId());
        orderItemData.setProductId(pojo.getProductId());
        orderItemData.setQuantity(pojo.getQuantity());
        orderItemData.setSellingPrice(pojo.getSellingPrice());
        return orderItemData;
    }
}

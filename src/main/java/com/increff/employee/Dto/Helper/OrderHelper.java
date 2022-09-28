package com.increff.employee.Dto.Helper;

import com.increff.employee.model.Data.OrderData;
import com.increff.employee.pojo.OrderPojo;

import java.util.ArrayList;
import java.util.List;

public class OrderHelper {

    public static List<OrderData> convertPojoToData(List<OrderPojo> orderPojoList){
        List<OrderData>orderDataList=new ArrayList<>();
        for(OrderPojo orderPojo:orderPojoList){
            OrderData orderData=new OrderData();
            orderData.setId(orderPojo.getId());
            orderData.setTime(orderData.getTime());
            orderDataList.add(orderData);
        }
        return orderDataList;
    }
    public static OrderData convertToData(OrderPojo orderPojo){
        OrderData orderData=new OrderData();
        orderData.setId(orderPojo.getId());
        orderData.setTime(orderData.getTime());
        return orderData;
    }
}

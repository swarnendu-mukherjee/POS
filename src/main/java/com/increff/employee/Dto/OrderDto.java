package com.increff.employee.Dto;

import com.increff.employee.Dto.Helper.OrderHelper;
import com.increff.employee.Dto.Helper.OrderItemHelper;
import com.increff.employee.Exception.ApiException;
import com.increff.employee.model.Data.OrderData;
import com.increff.employee.model.Data.OrderItemData;
import com.increff.employee.pojo.InventoryPojo;
import com.increff.employee.pojo.OrderItemPojo;
import com.increff.employee.service.InventoryService;
import com.increff.employee.service.OrderItemService;
import com.increff.employee.service.OrderService;
import com.increff.employee.service.ProductService;
import org.hibernate.type.OrderedSetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDto {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private ProductService productService;

    @Autowired
    private InventoryService inventoryService;


    public OrderData createOrder(){
        return OrderHelper.convertToData(orderService.createOrder());
    }

    public void deleteOrder(Long id){
        orderItemService.deleteForOrderId(id);
        orderService.deleteOrder(id);
    }

    @Transactional(rollbackFor = ApiException.class)
    public void placeOrder(Long id){
        List<OrderItemPojo> orderItemPojoList=orderItemService.getOrderItemsByOrderId(id);
        inventoryService.placeOrder(orderItemPojoList);
        orderService.placeOrder(id);
    }
    public List<OrderData> getAll(){
        return OrderHelper.convertPojoToData(orderService.getAll());
    }
    public OrderData getById(Long id){
        return OrderHelper.convertToData(orderService.getById(id));
    }
//    public void generateInvoice(Long id,List<OrderItemData> orderItemList){orderService.generateInvoice(id,orderItemList);}


}

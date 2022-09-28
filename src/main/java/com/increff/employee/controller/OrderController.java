package com.increff.employee.controller;

import com.increff.employee.Dto.Helper.OrderItemHelper;
import com.increff.employee.Dto.OrderDto;
import com.increff.employee.model.Data.OrderData;
import com.increff.employee.model.Data.OrderItemData;
import com.increff.employee.pojo.OrderItemPojo;
import com.increff.employee.service.OrderItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api
@RestController
@RequestMapping("/pos/orders")
public class OrderController {

    @Autowired
    private OrderDto orderDto;

    @Autowired
    private OrderItemService orderItemService;

    @ApiOperation("Create an order")
    @RequestMapping(value = "",method=RequestMethod.POST)
    public OrderData createOrder(){
        return orderDto.createOrder();
    }

    @ApiOperation("Delete an order")
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public void deleteOrder(@PathVariable  Long id){
        orderDto.deleteOrder(id);
    }

    @ApiOperation("Place the Order")
    @RequestMapping(value = "/place/{id}", method = RequestMethod.POST)
    public void placeOrder(@PathVariable Long id){
        orderDto.placeOrder(id);
    }

    @ApiOperation("Get all the orders")
    @RequestMapping(value = "",method = RequestMethod.GET)
    public List<OrderData> getAll(){
        return orderDto.getAll();
    }

    @ApiOperation("Get the order by ID")
    @RequestMapping(value ="/{id}", method=RequestMethod.GET)
    public OrderData getById(@PathVariable Long id){
        return orderDto.getById(id);
    }

//    public void generateInvoice(@PathVariable Long orderId){
//        List<OrderItemData> orderItemList = new ArrayList<>();
//        for(OrderItemPojo pojo :orderItemService.getByOrderId(orderId)){
//            orderItemList.add(OrderItemHelper.convertToData(pojo));
//        }
//        orderDto.generateInvoice(orderId,orderItemList);
//    }

}

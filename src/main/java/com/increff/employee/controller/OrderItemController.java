package com.increff.employee.controller;

import com.increff.employee.Dto.OrderItemDto;
import com.increff.employee.Exception.ApiException;
import com.increff.employee.model.Data.OrderItemData;
import com.increff.employee.model.Forms.OrderItemForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping(path="/cart")
public class OrderItemController {

    @Autowired
    private OrderItemDto orderItemDto;

    @ApiOperation("Add a product to cart")
    @RequestMapping(path="",method=RequestMethod.POST)
    public void addToCart(@RequestBody OrderItemForm orderItemForm){
        orderItemDto.addToCart(orderItemForm);
    }

    @ApiOperation("To delete a product from the cart")
    @RequestMapping(value="",method=RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        orderItemDto.delete(id);
    }

    @ApiOperation("Getting Order items by order id")
    @RequestMapping(value="/{id}",method=RequestMethod.GET)
    public void getOrderItemsByOrderId(Long id){
        orderItemDto.getOrderItemsByOrderId(id);
    }

    @ApiOperation("Get all the cart items")
    @RequestMapping(path="",method=RequestMethod.GET)
    public List<OrderItemData> getTheItems() {
        return orderItemDto.getAll();
    }

   @ApiOperation("Update the cart with it's id")
   @RequestMapping(path="",method=RequestMethod.PUT)
   public void updateCart(Long id,OrderItemForm orderItemForm){
       orderItemDto.updateCart(id,orderItemForm);
   }

}

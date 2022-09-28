package com.increff.employee.Dto;

import com.increff.employee.Dto.Helper.OrderItemHelper;
import com.increff.employee.Dto.Helper.ProductHelper;
import com.increff.employee.Exception.ApiException;
import com.increff.employee.model.Data.OrderItemData;
import com.increff.employee.model.Forms.OrderItemForm;
import com.increff.employee.pojo.OrderItemPojo;
import com.increff.employee.pojo.ProductPojo;
import com.increff.employee.service.InventoryService;
import com.increff.employee.service.OrderItemService;
import com.increff.employee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderItemDto {

    @Autowired
    private OrderItemService orderItemService;


    @Autowired
    private ProductService productService;

    @Autowired
    private InventoryService inventoryService;

    private boolean validate(OrderItemForm form) throws ApiException {
        if(form==null)
            throw new ApiException("Input is null, please do enter input");
        else if (form.getBarcode().isEmpty())
            throw new ApiException("Invalid input: Entered barcode is empty");
        else if (form.getQuantity()<=0)
            throw new ApiException("Invalid input: Quantity cannot be less than 1");
        return true;
    }

    public void addToCart(OrderItemForm orderItemForm){
        ProductPojo productPojo= productService.getByBarcode(orderItemForm.getBarcode());
        //Long quantity=inventoryService.getById(productPojo.getId()).getQuantity();
        validate(orderItemForm);
        //  TODO convert form to pojo in helper class
        OrderItemPojo orderItemPojo=new OrderItemPojo();
        orderItemPojo.setOrderId(orderItemForm.getOrderId());
        orderItemPojo.setQuantity(orderItemForm.getQuantity());
        orderItemPojo.setProductId(orderItemPojo.getProductId());
        orderItemPojo.setSellingPrice(productPojo.getMrp());

        orderItemService.addToCart(orderItemPojo);
    }

    public void delete(Long id){
        orderItemService.delete(id);
    }
    public List<OrderItemData>getOrderItemsByOrderId(Long id){
        List<OrderItemData> orderItemList = new ArrayList<>();
        for(OrderItemPojo pojo: orderItemService.getOrderItemsByOrderId(id)) {
            OrderItemData itemData = OrderItemHelper.convertToData(pojo);
            itemData.setBarcode(productService.getByID(pojo.getProductId()).getBarcode());
            orderItemList.add(itemData);
        }
        return orderItemList;
    }

    public List<OrderItemData> getAll(){
        List<OrderItemData> orderItemList = new ArrayList<>();
//        TODO bulk convert orderitemdata
        for(OrderItemPojo pojo: orderItemService.getAll()) {
            OrderItemData itemData = OrderItemHelper.convertToData(pojo);
            itemData.setBarcode(productService.getByID(pojo.getProductId()).getBarcode());
            orderItemList.add(itemData);
        }
        return orderItemList;
    }

    public void updateCart(Long id,OrderItemForm orderItemForm){
        OrderItemPojo orderItemPojo=new OrderItemPojo();

        orderItemPojo.setQuantity(orderItemForm.getQuantity());
        orderItemService.update(id,orderItemPojo);
    }

}

package com.increff.employee.service;

import com.increff.employee.Dao.OrderItemDao;
import com.increff.employee.Dto.OrderItemDto;
import com.increff.employee.Exception.ApiException;
import com.increff.employee.model.Forms.OrderItemForm;
import com.increff.employee.pojo.InventoryPojo;
import com.increff.employee.pojo.OrderItemPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemDao orderItemDao;

    @Transactional(rollbackFor = ApiException.class)
    public void addToCart(OrderItemPojo orderItemPojo){
        OrderItemPojo orderItemPojo1=orderItemDao.getByProductOrderId(orderItemPojo.getProductId(),orderItemPojo.getOrderId());
        if(orderItemPojo1!=null){
            throw new ApiException("Already exists in your cart, please update the quantity");
        }
        orderItemDao.add(orderItemPojo);
    }

    @Transactional(rollbackFor = ApiException.class)
    public void delete(Long id){

        OrderItemPojo orderItemPojo=orderItemDao.getById(id);
        if(Objects.isNull(orderItemPojo)){
            throw new ApiException("No items exists");
        }
        orderItemDao.delete(orderItemPojo);
    }

    @Transactional(readOnly = true)
    public List<OrderItemPojo> getAll(){
       return orderItemDao.getAll();
    }

    @Transactional(readOnly = true)
    public List<OrderItemPojo>getOrderItemsByOrderId(Long id){
        List<OrderItemPojo> orderItemPojoList=orderItemDao.getOrderItemsByOrderId(id);
        if(orderItemPojoList.size()==0){
            throw new ApiException("No items available in your cart");
        }
        return orderItemPojoList;
    }


    @Transactional
    public void update(Long ID, OrderItemPojo orderItemPojo){
        OrderItemPojo existingOrderItemPojo=orderItemDao.getByProductOrderId(orderItemPojo.getProductId(),orderItemPojo.getOrderId());
        if(existingOrderItemPojo==null){
            throw new ApiException("No item exists to update");
        }
        existingOrderItemPojo.setQuantity(orderItemPojo.getQuantity());
        orderItemDao.update();
    }

    public void deleteForOrderId(Long id) {
        List<OrderItemPojo> orderItemPojoList = getOrderItemsByOrderId(id);
        orderItemPojoList.forEach(x-> delete(x.getId()));
    }
}

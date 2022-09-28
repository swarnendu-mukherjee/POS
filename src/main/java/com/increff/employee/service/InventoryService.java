package com.increff.employee.service;

import com.increff.employee.Dao.InventoryDao;
import com.increff.employee.Exception.ApiException;
import com.increff.employee.model.Forms.InventoryForm;
import com.increff.employee.pojo.InventoryPojo;
import com.increff.employee.pojo.OrderItemPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryDao inventoryDao;

    @Transactional(rollbackFor=ApiException.class)
    public void update(Long id, Long quantity){
        InventoryPojo inventoryPojo=getById(id);
        if(id==null){
            throw new ApiException("ID shouldn't be null");
        }
        else if(quantity==null){
            throw new ApiException("quantity should not be null");
        }
        else if(inventoryPojo==null){
            throw new ApiException("This ID is not present in this database");
        }
        else if(inventoryPojo.getQuantity()==quantity){
            throw new ApiException("No Update needed");
        }
        else{
            inventoryPojo.setQuantity(quantity);
            inventoryDao.update(id,quantity);
        }
    }
    @Transactional(readOnly = true)
    public List<InventoryPojo> getAll(){
        return inventoryDao.getAll();
    }

    @Transactional(readOnly = true)
    public InventoryPojo getById(Long id){
        if(id==null){
            throw new ApiException("ID shouldn't be null");
        }
        InventoryPojo inventoryPojo=inventoryDao.getById(id);
        if(inventoryPojo==null){
            throw new ApiException("This id is not present in the database");
        }
        return inventoryPojo;
    }

    @Transactional(rollbackFor=ApiException.class)
    public void addList(List<InventoryPojo>inventoryPojoList){
        for(InventoryPojo inventoryPojo:inventoryPojoList){
            InventoryPojo inventoryPojo1=getById(inventoryPojo.getId());
            if(inventoryPojo1!=null){
                throw new ApiException("It's already present in the database");
            }
            Long id=inventoryPojo.getId();
            Long quantity=inventoryPojo.getQuantity();
            if(id==null){
                throw new ApiException("ID shouldn't be null");
            }
            else if(quantity==null){
                throw new ApiException("quantity should not be null");
            }
            inventoryDao.add(inventoryPojo);
        }
    }


    @Transactional(rollbackFor = ApiException.class)
    public void placeOrder(List<OrderItemPojo> orderItemPojoList) {
        for(OrderItemPojo orderItemPojo: orderItemPojoList){
            InventoryPojo inventoryPojo = getById(orderItemPojo.getProductId());
            if(inventoryPojo.getQuantity()<orderItemPojo.getQuantity())
                throw new ApiException(String.format("Only {} quantity present in inventory", inventoryPojo.getQuantity()));
            update(inventoryPojo.getId(), inventoryPojo.getQuantity()- orderItemPojo.getQuantity());
        }
    }
}

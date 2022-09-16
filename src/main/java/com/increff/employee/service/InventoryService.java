package com.increff.employee.service;

import com.increff.employee.Dao.InventoryDao;
import com.increff.employee.Exception.ApiException;
import com.increff.employee.model.Forms.InventoryForm;
import com.increff.employee.pojo.InventoryPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryDao inventoryDao;

    public void update(int id, int quantity){
        InventoryPojo inventoryPojo=getById(id);
        if(inventoryPojo==null){
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
    public List<InventoryPojo> getAll(){
        return inventoryDao.getAll();
    }
    public InventoryPojo getById(int id){
        return inventoryDao.getById(id);
    }
    public void addList(List<InventoryPojo>inventoryPojoList){
        for(InventoryPojo inventoryPojo:inventoryPojoList){
            InventoryPojo inventoryPojo1=getById(inventoryPojo.getId());
            if(inventoryPojo1!=null){
                throw new ApiException("It's already present in the database");
            }
            inventoryDao.add(inventoryPojo);
        }
    }


}

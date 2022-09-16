package com.increff.employee.Dto;

import com.increff.employee.Dto.Helper.InventoryHelper;
import com.increff.employee.model.Forms.InventoryForm;
import com.increff.employee.pojo.InventoryPojo;
import com.increff.employee.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Component
public class InventoryDto {

    @Autowired
    private InventoryService inventoryService;

    public void update(int id, int quantity) {
        inventoryService.update(id, quantity);
    }
    public List<InventoryForm> getAll(){
        return InventoryHelper.convertPojoToForm(inventoryService.getAll());
    }
    public InventoryForm getById(int id){
        return InventoryHelper.convertPojoToFormSingle(inventoryService.getById(id));
    }
    public void addList(List<InventoryForm>inventoryFormList){
        inventoryService.addList(InventoryHelper.convertFormToPojo(inventoryFormList));
    }

}

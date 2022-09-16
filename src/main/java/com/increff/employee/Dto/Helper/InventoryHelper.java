package com.increff.employee.Dto.Helper;

import com.increff.employee.model.Forms.InventoryForm;
import com.increff.employee.pojo.InventoryPojo;

import java.util.ArrayList;
import java.util.List;

public class InventoryHelper {

    public static List<InventoryPojo> convertFormToPojo(List<InventoryForm>inventoryForms){
        List<InventoryPojo> inventoryPojoList=new ArrayList<>();

        for(InventoryForm inventoryForm:inventoryForms){
            InventoryPojo inventoryPojo=new InventoryPojo();
            inventoryPojo.setId(inventoryForm.getId());
            inventoryPojoList.add(inventoryPojo);
        }
        return inventoryPojoList;
    }

    public static List<InventoryForm> convertPojoToForm(List<InventoryPojo> inventoryPojoList){

        List<InventoryForm> inventoryForms=new ArrayList<>();
        for(InventoryPojo inventoryPojo: inventoryPojoList){
            InventoryForm inventoryForm=new InventoryForm();
            inventoryForm.setId(inventoryPojo.getId());
            inventoryForm.setQuantity(inventoryPojo.getQuantity());
            inventoryForms.add(inventoryForm);
        }
        return inventoryForms;
    }

    public static InventoryForm convertPojoToFormSingle(InventoryPojo inventoryPojo){
        InventoryForm inventoryForm=new InventoryForm();
        inventoryForm.setId(inventoryPojo.getId());
        inventoryForm.setQuantity(inventoryPojo.getQuantity());
        return inventoryForm;
    }


}

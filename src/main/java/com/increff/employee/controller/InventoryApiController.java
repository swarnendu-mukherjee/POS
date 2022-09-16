package com.increff.employee.controller;

import com.increff.employee.Dto.InventoryDto;
import com.increff.employee.model.Forms.InventoryForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pos/inventory")
@Api
public class InventoryApiController {


    @Autowired
    private InventoryDto inventoryDto;


    @ApiOperation("Update the inventory")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable int id, @RequestBody int quantity){inventoryDto.update(id,quantity);}


    @ApiOperation("get all the inventory")
    @RequestMapping(value="",method =RequestMethod.GET )
    public List<InventoryForm> getAll(){return inventoryDto.getAll();}

    @ApiOperation("Getting inventory by id")
    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public InventoryForm getById(@PathVariable int id){ return inventoryDto.getById(id);}


    @ApiOperation("Add a List")
    @RequestMapping(value="", method=RequestMethod.POST)
    public void addList(@RequestBody List<InventoryForm>inventoryForm){
        inventoryDto.addList(inventoryForm);

    }



}

package com.increff.employee.controller;

import com.increff.employee.Dto.BrandDto;
import com.increff.employee.model.Data.BrandData;
import com.increff.employee.model.Forms.BrandForm;
import com.increff.employee.pojo.BrandPojo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping(value="/pos/brands")
public class BrandApiController {

    @Autowired
    private BrandDto brandDto;

    @ApiOperation(value = "get all brand category pairs")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<BrandData> getAllBrands() {
        return brandDto.getAll();
    }

    @ApiOperation(value = "add a brand category pair")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public void add(@RequestBody List<BrandForm> brandFormList) {
        brandDto.add(brandFormList);
    }

    @ApiOperation(value = "get by an id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BrandData getByID(@PathVariable Long id) {return brandDto.getByID(id);}


    @ApiOperation(value = "delete a brand category pair by id")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        brandDto.delete(id);
    }

    @ApiOperation(value = "update a brand category pair by id")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable Long id, @RequestBody BrandForm brandForm) {
        brandDto.update(id, brandForm);
    }


}

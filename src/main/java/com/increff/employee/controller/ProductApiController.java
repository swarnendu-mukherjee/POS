package com.increff.employee.controller;

import com.increff.employee.Dto.ProductDto;
import com.increff.employee.model.Data.ProductData;
import com.increff.employee.model.Forms.BrandForm;
import com.increff.employee.model.Forms.ProductForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping("/pos/products/")
public class ProductApiController {

    @Autowired
    private ProductDto productDto;

    @ApiOperation("The list of all products")
    @RequestMapping(value="",method= RequestMethod.GET)
    public List<ProductData> getAll(){
        return productDto.getAll();
    }

    @ApiOperation("Add a product")
    @RequestMapping(value="",method=RequestMethod.POST)
    public void add(@RequestBody List<ProductForm>productFormList)
    {
        productDto.add(productFormList);
    }

    @ApiOperation("Delete a Product")
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public void delete(@PathVariable int id)
    {
        productDto.delete(id);
    }

    @ApiOperation(value = "update a  category pair by id")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable int id, @RequestBody ProductForm productForm) {
        productDto.update(id, productForm);
    }

}

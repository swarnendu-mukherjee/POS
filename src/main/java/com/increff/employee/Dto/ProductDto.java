package com.increff.employee.Dto;


import com.increff.employee.Dto.Helper.BrandHelper;
import com.increff.employee.Dto.Helper.ProductHelper;
import com.increff.employee.model.Data.BrandData;
import com.increff.employee.model.Data.ProductData;
import com.increff.employee.model.Forms.BrandForm;
import com.increff.employee.model.Forms.ProductForm;
import com.increff.employee.pojo.BrandPojo;
import com.increff.employee.pojo.ProductPojo;
import com.increff.employee.service.BrandService;
import com.increff.employee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDto {


    //private ProductHelper productHelper;
    @Autowired
    private ProductService productService;

    public List<ProductData> getAll() {
        return ProductHelper.ConvertPojoToData(productService.getAll());
    }

    public void add(List<ProductForm> productFormList) {
        List<ProductPojo> productPojoList = new ArrayList<>();
        for (ProductForm productForm : productFormList) {
            //BrandHelper.Normalize(brandForm);
            ProductPojo productPojo = ProductHelper.ConvertFormToPojo(productForm);
            productPojoList.add(productPojo);
        }
        productService.add(productPojoList);
    }

    public void delete(int id) {
        productService.delete(id);
    }

    public void update(int id, ProductForm productForm) {
        //BrandHelper.Normalize(brandForm);
        ProductPojo productPojo = ProductHelper.ConvertFormToPojo(productForm);
        productService.update(id, productPojo);
    }

}

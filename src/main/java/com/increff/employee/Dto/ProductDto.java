package com.increff.employee.Dto;


import com.increff.employee.Dto.Helper.BrandHelper;
import com.increff.employee.Dto.Helper.ProductHelper;
import com.increff.employee.Exception.ApiException;
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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductDto {


    //private ProductHelper productHelper;
    @Autowired
    private ProductService productService;

    @Autowired
    private BrandService brandService;

    public List<ProductData> getAll() {
        List<ProductPojo> productPojoList=productService.getAll();
        return ProductHelper.ConvertPojoToData(productPojoList,getByProductPojo(productPojoList));
    }
    public ProductData getById(Long id){
        ProductPojo productPojo = productService.getByID(id);
        BrandPojo brandPojo=brandService.getByID(productPojo.getBrandCategory());
        return ProductHelper.convertPojoToData(productPojo, brandPojo);
    }

    public void add(List<ProductForm> productFormList) {
        List<ProductPojo> productPojoList = new ArrayList<>();
        for (ProductForm productForm : productFormList) {
            //BrandHelper.Normalize(brandForm);
            BrandPojo brandPojo=brandService.getByNameAndCategory(productForm.getBrand(),productForm.getCategory());
            if(brandPojo==null){
                throw new ApiException("Brand is not present in the database");
            }
            long id=brandPojo.getId();
            ProductPojo productPojo = ProductHelper.ConvertFormToPojo(productForm,id);
            productPojoList.add(productPojo);
        }
        productService.add(productPojoList);
    }

    public void delete(Long id) {
        productService.delete(id);
    }

    public void update(Long id, ProductForm productForm) {
        //BrandHelper.Normalize(brandForm);

        BrandPojo brandPojo=brandService.getByNameAndCategory(productForm.getBrand(),productForm.getCategory());
        if(brandPojo==null){
            throw new ApiException("This brand is absent in the database, so update can not be done");
        }
        Long brandID=brandPojo.getId();
        ProductPojo productPojo = ProductHelper.ConvertFormToPojo(productForm,brandID);
        productPojo.setId(id);
        productService.update(id, productPojo);
    }
    public List<BrandPojo> getByProductPojo(List<ProductPojo> productPojoList){
        List<BrandPojo> brandPojoList=new ArrayList<>();
        for(ProductPojo productPojo: productPojoList){
            brandPojoList.add(brandService.getByID(productPojo.getBrandCategory()));
        }
        return brandPojoList;
    }

}

package com.increff.employee.Dto.Helper;

import com.increff.employee.model.Data.BrandData;
import com.increff.employee.model.Data.ProductData;
import com.increff.employee.model.Forms.BrandForm;
import com.increff.employee.model.Forms.ProductForm;
import com.increff.employee.pojo.BrandPojo;
import com.increff.employee.pojo.ProductPojo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


public class ProductHelper {

public static List<ProductData> ConvertPojoToData(List<ProductPojo> productPojo1){

    List<ProductData> productDataList=new ArrayList<>();
    for(ProductPojo productPojo:productPojo1){
        ProductData productData=new ProductData();
        productData.setId(productPojo.getId());
        productData.setBarcode(productPojo.getBarcode());
        productData.setMrp(productPojo.getMrp());
        productData.setName(productPojo.getName());
        productData.setBrandCategory(productPojo.getBrand_category());
        productDataList.add(productData);
    }
    return productDataList;
}

public static ProductPojo ConvertFormToPojo(ProductForm productForm){

    ProductPojo productPojo=new ProductPojo();
    productPojo.setBarcode(productForm.getBarcode());
    productPojo.setName(productForm.getName());
    productPojo.setMrp(productForm.getMrp());
    productPojo.setBrand_category(productForm.getBrandCategory());
    return productPojo;

}



}
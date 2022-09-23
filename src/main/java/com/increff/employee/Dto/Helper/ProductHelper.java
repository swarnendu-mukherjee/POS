package com.increff.employee.Dto.Helper;

import com.increff.employee.model.Data.BrandData;
import com.increff.employee.model.Data.ProductData;
import com.increff.employee.model.Forms.BrandForm;
import com.increff.employee.model.Forms.ProductForm;
import com.increff.employee.pojo.BrandPojo;
import com.increff.employee.pojo.ProductPojo;
import com.increff.employee.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


public class ProductHelper {

    public static List<ProductData> ConvertPojoToData(List<ProductPojo> productPojo1, List<BrandPojo> brandPojoList){

    List<ProductData> productDataList=new ArrayList<>();
    int i=0;
    for(ProductPojo productPojo:productPojo1){
        BrandPojo brandPojo=brandPojoList.get(i++);
        ProductData productData=new ProductData();
        productData.setId(productPojo.getId());
        productData.setBarcode(productPojo.getBarcode());
        productData.setMrp(productPojo.getMrp());
        productData.setName(productPojo.getName());
        productData.setBrand(brandPojo.getBrandName());
        productData.setCategory(brandPojo.getCategoryName());
        productDataList.add(productData);
    }
    return productDataList;
}

public static ProductPojo ConvertFormToPojo(ProductForm productForm, Long id){

    ProductPojo productPojo=new ProductPojo();
    productPojo.setBarcode(productForm.getBarcode());
    productPojo.setName(productForm.getName());
    productPojo.setMrp(productForm.getMrp());
    productPojo.setBrandCategory(id);
    return productPojo;

}
public static ProductData convertPojoToData(ProductPojo productPojo, BrandPojo brandPojo){

    ProductData productData=new ProductData();


    productData.setBarcode(productPojo.getBarcode());
    productData.setName(productPojo.getName());
    productData.setMrp(productPojo.getMrp());
    productData.setId(productPojo.getId());
    productData.setBrand(brandPojo.getBrandName());
    productData.setCategory(brandPojo.getCategoryName());

    return productData;
}


}
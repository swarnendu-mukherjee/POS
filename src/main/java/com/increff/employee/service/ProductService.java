package com.increff.employee.service;

import com.increff.employee.Exception.ApiException;
import com.increff.employee.Dao.ProductDao;
import com.increff.employee.pojo.BrandPojo;
import com.increff.employee.pojo.ProductPojo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    public BrandService brandService;

    @Transactional(rollbackFor = ApiException.class)
    public void add(List<ProductPojo> productPojoList) {

        for (ProductPojo productPojo : productPojoList) {
            ProductPojo productPojo1 = getByParameters(productPojo);
            BrandPojo brandPojo = brandService.getByID(productPojo.getBrandCategory());
            if (productPojo1 != null) {
                throw new ApiException("Already present in the database");
            }
            else if(productPojo.getMrp()<=0){
                throw new ApiException("MRP can't be zero or negative");
            }
            else if(productPojo.getMrp().toString().length()==0){
                throw new ApiException("MRP can't be empty");
            }
            else if(brandPojo==null){
                throw new ApiException("Brand is not present in the database");
            }
            else if(productPojo.getName().length()==0 || productPojo.getBarcode().length()==0){
                throw new ApiException("Product name or barcode cant be negative");
            }
            productDao.add(productPojo);
        }
    }

    public List<ProductPojo> getAll() {
        return productDao.getAll();
    }

    @Transactional(rollbackFor = ApiException.class)
    public void delete(Long id) {
        ProductPojo productPojo = getByID(id);
        if (productPojo == null) {
            throw new ApiException("Not present in the database");
        }
        productDao.delete(id);
    }

    @Transactional(rollbackFor = ApiException.class)
    public void update(Long id, ProductPojo productPojo) {
        ProductPojo productPojo1 = getByID(id);
        //brandService.getByID(productPojo.getBrandCategory());

        if (productPojo1 == null) {
            throw new ApiException("Not present in the database");
        } else if (productPojo1.getBrandCategory() == productPojo.getBrandCategory() && productPojo1.getMrp() == productPojo.getMrp() && productPojo1.getBarcode() == productPojo.getBarcode() && productPojo1.getName() == productPojo.getName()) {
            throw new ApiException("No update needed");
        }else if(productPojo.getMrp()==null || productPojo.getMrp().toString().length()==0){
            throw new ApiException("MRP can't be empty");
        } else if(productPojo.getMrp()<0){
            throw new ApiException("MRP can't be be negative");
        }else if(productPojo.getName().length()==0){
            throw new ApiException("The Name cannot be null");
        } else {
            productPojo1.setId(productPojo.getId());
            productPojo1.setBrandCategory(productPojo.getBrandCategory());
            productPojo1.setBarcode(productPojo.getBarcode());
            productPojo1.setMrp(productPojo.getMrp());
            productPojo1.setName(productPojo.getName());
            productDao.update(id, productPojo1);
        }
    }

    public ProductPojo getByID(Long id) {
       return productDao.getByID(id);
    }

    public ProductPojo getByParameters(ProductPojo productPojo) {
        ProductPojo productPojo1 = productDao.getByParameters(productPojo);
        return productPojo1;
    }


}
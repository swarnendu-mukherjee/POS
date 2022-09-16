package com.increff.employee.service;

import com.increff.employee.Exception.ApiException;
import com.increff.employee.Dao.ProductDao;
import com.increff.employee.pojo.BrandPojo;
import com.increff.employee.pojo.ProductPojo;
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
            BrandPojo brandPojo = brandService.getByID(productPojo.getBrand_category());
            if (productPojo1 != null) {
                throw new ApiException("Already present in the database");
            }
            productDao.add(productPojo);
        }
    }

    public List<ProductPojo> getAll() {
        return productDao.getAll();
    }

    @Transactional(rollbackFor = ApiException.class)
    public void delete(int id) {
        ProductPojo productPojo = getByID(id);
        if (productPojo == null) {
            throw new ApiException("Not present in the database");
        }
        productDao.delete(id);
    }

    @Transactional(rollbackFor = ApiException.class)
    public void update(int id, ProductPojo productPojo) {
        ProductPojo productPojo1 = getByID(id);
        brandService.getByID(productPojo.getBrand_category());

        if (productPojo1 == null) {
            throw new ApiException("Not present in the database");
        } else if (productPojo1.getBrand_category() == productPojo.getBrand_category() && productPojo1.getMrp() == productPojo.getMrp() && productPojo1.getBarcode() == productPojo.getBarcode() && productPojo1.getName() == productPojo.getName()) {
            throw new ApiException("No update needed");
        } else {
            productPojo.setId(productPojo1.getId());
            productPojo.setBrand_category(productPojo1.getBrand_category());
            productPojo.setBarcode(productPojo1.getBarcode());
            productPojo.setMrp(productPojo1.getMrp());
            productPojo.setName(productPojo1.getName());
            productDao.update(id, productPojo);
        }
    }

    public ProductPojo getByID(int id) {
        ProductPojo productPojo = productDao.getByID(id);
        return productPojo;
    }

    public ProductPojo getByParameters(ProductPojo productPojo) {
        ProductPojo productPojo1 = productDao.getByParameters(productPojo);
        return productPojo1;
    }


}

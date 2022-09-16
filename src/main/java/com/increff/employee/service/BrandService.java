package com.increff.employee.service;

import com.increff.employee.Exception.ApiException;
import com.increff.employee.Dao.BrandDao;
import com.increff.employee.pojo.BrandPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BrandService {

    @Autowired
    private BrandDao brandDao;

    @Transactional(readOnly = true)
    public List<BrandPojo> getAll()
    {
        return brandDao.getAll();
    }

    @Transactional(rollbackFor=ApiException.class)
    public void add(List<BrandPojo> BrandPojoList)
    {
        //List<BrandPojo> brandPojoList=new ArrayList<>();
        for(BrandPojo brandPojo:BrandPojoList)
        {
            BrandPojo brandpojo=getByNameAndCategory(brandPojo.getBrandName(),brandPojo.getCategoryName());
            if(brandpojo!=null){
                throw new ApiException("Already present in the database");
            }
            brandDao.add(brandPojo);
        }
    }
    @Transactional(rollbackFor=ApiException.class)
    public void delete(int id)
    {
        getByID(id);
        brandDao.delete(id);
    }
    @Transactional(rollbackFor=ApiException.class)
    public void update(int id, BrandPojo input)
    {
        BrandPojo brandpojo=getByNameAndCategory(input.getBrandName(), input.getCategoryName());

        if(brandpojo==null){
            throw new ApiException("Id not present in the database");
        }

        brandpojo.setId(id);
        brandpojo.setBrandName(input.getBrandName());
        brandpojo.setCategoryName(input.getCategoryName());
        brandDao.update(brandpojo);
    }
    public BrandPojo getByID(int id)
    {
        BrandPojo brandPojo = brandDao.getByID(id);
        if(brandPojo==null){
            throw new ApiException("This ID is not present in the Database");
        }
        return brandPojo;
    }

    public BrandPojo getByNameAndCategory(String BrandName, String CategoryName)
    {
        BrandPojo brandPojo=brandDao.getByNameAndCategory(BrandName,CategoryName);
        return brandPojo;
    }

}

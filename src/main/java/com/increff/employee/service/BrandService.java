package com.increff.employee.service;

import com.increff.employee.Exception.ApiException;
import com.increff.employee.Dao.BrandDao;
import com.increff.employee.pojo.BrandPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

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
            else if(brandPojo.getCategoryName().length()==0 || brandPojo.getBrandName().length()==0){
                throw new ApiException("Brand or Category should not be null");
            }
            brandDao.add(brandPojo);
        }
    }
    @Transactional(rollbackFor=ApiException.class)
    public void delete(Long id)
    {
        getByID(id);
        brandDao.delete(id);
    }
    @Transactional(rollbackFor=ApiException.class)
    public void update(Long id, BrandPojo input)
    {
        BrandPojo brandpojo=getByID(id);
        if(brandpojo==null){
            throw new ApiException("Id not present in the database");
        }
        else if(input.getBrandName().length()==0 || input.getCategoryName().length()==0){
            throw new ApiException("Brand can not be updated with null input");
        }
        //brandpojo.setId(id);
        brandpojo.setBrandName(input.getBrandName());
        brandpojo.setCategoryName(input.getCategoryName());
        brandDao.update(brandpojo);
    }
    public BrandPojo getByID(Long id)
    {
        BrandPojo brandPojo = brandDao.getByID(id);
        if(Objects.isNull(brandPojo)){
            throw new ApiException("This ID is not present in the Database");
        }/*****/
        return brandPojo;
    }

    public BrandPojo getByNameAndCategory(String BrandName, String CategoryName)
    {
        BrandPojo brandPojo=brandDao.getByNameAndCategory(BrandName,CategoryName);
        return brandPojo;
    }

}

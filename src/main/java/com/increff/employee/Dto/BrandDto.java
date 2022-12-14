package com.increff.employee.Dto;


import com.increff.employee.Dto.Helper.BrandHelper;
import com.increff.employee.model.Data.BrandData;
import com.increff.employee.model.Forms.BrandForm;
import com.increff.employee.pojo.BrandPojo;
import com.increff.employee.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;


@Service
public class BrandDto {

    @Autowired
    private BrandService brandservice;

    public List<BrandData> getAll() {
        return BrandHelper.ConvertPojoToData(brandservice.getAll());
    }
    public BrandData getByID(Long id) {return BrandHelper.ConvertPojoToDataSingle(brandservice.getByID(id));}

    public void add(List<BrandForm> brandFormList) {
        brandservice.add(BrandHelper.convertToBrandPojos(brandFormList));
    }

    public void delete(Long id) {
        brandservice.delete(id);
    }

    public void update(Long id, BrandForm brandForm) {
        //BrandHelper.Normalize(brandForm);
        BrandPojo brandPojo = BrandHelper.ConvertFormToPojo(brandForm);
        brandservice.update(id, brandPojo);
    }

}

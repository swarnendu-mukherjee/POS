package com.increff.employee.Dto.Helper;

import com.increff.employee.model.Data.BrandData;
import com.increff.employee.model.Forms.BrandForm;
import com.increff.employee.pojo.BrandPojo;

import java.util.ArrayList;
import java.util.List;


public class BrandHelper {
    public static List<BrandData> ConvertPojoToData(List<BrandPojo> brandPojoList)
    {
        List<BrandData>brandDataList=new ArrayList<>();
        for(BrandPojo brandPojo:brandPojoList){

            BrandData brandData=new BrandData();
            brandData.setId(brandPojo.getId());
            brandData.setBrandName(brandPojo.getBrandName());
            brandData.setCategoryName(brandPojo.getCategoryName());
        }
        return brandDataList;
    }
    public static BrandPojo ConvertFormToPojo(BrandForm brandForm)
    {
        BrandPojo brandPojo=new BrandPojo();
        brandPojo.setBrandName(brandForm.getBrandName());
        brandPojo.setCategoryName(brandForm.getCategoryName());
        return brandPojo;
    }

    public static List<BrandPojo> convertToBrandPojos(List<BrandForm> brandFormList){
        List<BrandPojo> BrandPojoList=new ArrayList<>();
        for(BrandForm brandForm:brandFormList)
        {
            //BrandHelper.Normalize(brandForm);
            BrandPojo brandPojo=ConvertFormToPojo(brandForm);
            BrandPojoList.add(brandPojo);
        }
        return BrandPojoList;
    }

}

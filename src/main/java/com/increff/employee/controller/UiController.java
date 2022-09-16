package com.increff.employee.controller;


import com.increff.employee.model.InfoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UiController {

    @Value("${app.baseUrl}")
    private String baseUrl;

    @Autowired
    private InfoData info;

    @RequestMapping(value = "/ui/home")
    public ModelAndView home() {return mav("home.html");}

    @RequestMapping(value = "/ui/brand")
    public ModelAndView brand() {
        return mav("brand.html");
    }

    @RequestMapping(value = "/ui/product")
    public ModelAndView product() {
        return mav("product.html");
    }

    @RequestMapping(value = "/ui/order")
    public ModelAndView order() {
        return mav("orders.html");
    }

    @RequestMapping(value = "/ui/inventory")
    public ModelAndView inventory() {
        return mav("inventory.html");
    }

    @RequestMapping(value = "/ui/report")
    public ModelAndView report() {
        return mav("report.html");
    }

    @RequestMapping(value = "/ui/cart")
    public ModelAndView cart() {
        return mav("cart.html");
    }



    private ModelAndView mav(String page) {

        info.setEmail("");
        ModelAndView mav = new ModelAndView(page);
        mav.addObject("info", new InfoData());
        mav.addObject("baseUrl", baseUrl);
        return mav;
    }

}

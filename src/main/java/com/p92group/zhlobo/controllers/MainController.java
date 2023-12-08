package com.p92group.zhlobo.controllers;

import com.p92group.zhlobo.repos.CouriersRepo;
import com.p92group.zhlobo.repos.CustomersRepo;
import com.p92group.zhlobo.repos.ProductRepo;
import com.p92group.zhlobo.repos.SellerRepo;
import com.p92group.zhlobo.models.Product;
import com.p92group.zhlobo.models.ProductStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;

@Controller
public class MainController {
    @Autowired
    private CouriersRepo couriersRepo;

    @Autowired
    private CustomersRepo customersRepo;

    @Autowired
    private SellerRepo sellerRepo;

    @Autowired
    private ProductRepo productRepo;


    @GetMapping({"/", "/index"})
    public String index(){
        return "index";
    }

    @GetMapping("/addStartData")
    public String anyData(){

        productRepo.save(new Product("banana", ProductStatus.in_stock, new BigDecimal(10), null, null));

        return "redirect:index";
    }

}

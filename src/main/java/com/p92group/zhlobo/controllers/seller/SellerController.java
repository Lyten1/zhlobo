package com.p92group.zhlobo.controllers.seller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/seller")
public class SellerController {
    @GetMapping({"", "/", "/index"})
    public String index(){
        return "seller/index";
    }
}

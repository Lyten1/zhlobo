package com.p92group.zhlobo.controllers.client;

import com.p92group.zhlobo.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private SellerService sellerService;

    @GetMapping
    public String defaultView(Model model) {
        model.addAttribute("topSellers", sellerService.getAll());

        return "client/index";
    }


}

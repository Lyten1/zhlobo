package com.p92group.zhlobo.controllers.client;

import com.p92group.zhlobo.models.*;
import com.p92group.zhlobo.repos.CustomersRepo;
import com.p92group.zhlobo.services.BucketService;
import com.p92group.zhlobo.services.OrderService;
import com.p92group.zhlobo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientConlrollerOrders {


    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomersRepo customersRepo;
    @Autowired
    private UserService userService;


    @GetMapping("/orders")
    public String orderProductsView(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long current_id =  userService.getUserByUsername(authentication.getName()).getId();
        List<ProductQuantity> productQuantity = orderService.getProductsByCustomerId(current_id);
        model.addAttribute("productsQuantities", productQuantity);
        Customer customer = customersRepo.findById(current_id).orElse(null);
        Order order = orderService.createOrder(customer, productQuantity);
        model.addAttribute("order", order);
        BigDecimal sum = BigDecimal.ZERO;
        for (ProductQuantity pQ: productQuantity) {
            BigDecimal BD = new BigDecimal(pQ.getQuantity()).multiply(pQ.getProduct().getPrice());
            sum = sum.add(BD);
        }
        model.addAttribute("sumOrder", sum);
        return "client/forms-view/view-orders";
    }

    @PostMapping("/orders/add")
    public String cartAddProducts(@ModelAttribute Bucket bucket){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long currentUserId =  userService.getUserByUsername(authentication.getName()).getId();
        orderService.save(currentUserId);
        return "redirect:../../client/orders";
    }





}

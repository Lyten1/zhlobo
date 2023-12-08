package com.p92group.zhlobo.controllers.client;

import com.p92group.zhlobo.models.Customer;
import com.p92group.zhlobo.models.Product;
import com.p92group.zhlobo.models.ProductQuantity;
import com.p92group.zhlobo.models.UserInterface;
import com.p92group.zhlobo.repos.BucketRepo;
import com.p92group.zhlobo.repos.ProductQuantityRepo;
import com.p92group.zhlobo.repos.ProductRepo;
import com.p92group.zhlobo.services.BucketService;
import com.p92group.zhlobo.services.ProductQuantityService;
import com.p92group.zhlobo.services.ProductService;
import com.p92group.zhlobo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.nio.FloatBuffer;
import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientConlrollerBucket {


    @Autowired
    private BucketService bucketService;
    @Autowired
    private UserService userService;


    @GetMapping("/bucket")
    public String cartProductsView(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long current_id =  userService.getUserByUsername(authentication.getName()).getId();
        List<ProductQuantity> productQuantity = bucketService.getProductsByCustomerId(current_id);
        model.addAttribute("productsQuantities", productQuantity);
        BigDecimal sum = BigDecimal.ZERO;
        for (ProductQuantity pQ: productQuantity) {
            BigDecimal BD = new BigDecimal(pQ.getQuantity()).multiply(pQ.getProduct().getPrice());
            sum = sum.add(BD);
        }
        model.addAttribute("sumOrder", sum);
        return "client/forms-view/view-cart";
    }

    @PostMapping("/bucket/add")
    public String cartAddProducts(@ModelAttribute Product product){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long currentUserId =  userService.getUserByUsername(authentication.getName()).getId();
        bucketService.save(product, currentUserId);
        return "redirect:../../client/products";
    }

    @GetMapping("/bucket/add-one/{id}")
    public String cartAddOneProduct(@PathVariable Long id, Model model) {
        bucketService.addOne(id);
        var s = cartProductsView(model);
        return "redirect:../../../client/bucket";
    }

    @GetMapping("/bucket/sub-one/{id}")
    public String cartSubOneProduct(@PathVariable Long id, Model model) {
        bucketService.subOne(id);
        var s = cartProductsView(model);
        return "redirect:../../../client/bucket";
    }






}

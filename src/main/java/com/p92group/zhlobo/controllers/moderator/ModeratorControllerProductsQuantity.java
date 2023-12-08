package com.p92group.zhlobo.controllers.moderator;

import com.p92group.zhlobo.repos.ProductQuantityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/moderator")
public class ModeratorControllerProductsQuantity {
    @Autowired
    private ProductQuantityRepo productQuantityRepo;

    @GetMapping("/products-quantity")
    public String productsQuantityView(Model model){
        model.addAttribute("productsQuantity", productQuantityRepo.findAll());

        return "moderator/forms-view/view-products-quantity";
    }

//    @GetMapping("/products-quantity/add")
//    public String productsQuantityAdd(Model model) {
//        return "moderator/forms-add/add-product";
//    }
//
//    @PostMapping("/products-quantity/add")
//    public String productsQuantityCreate(@ModelAttribute ProductQuantity productQuantity) {
//        productQuantityRepo.save(productQuantity);
//
//        return "redirect:/moderator/products-quantity";
//    }
//
//    @GetMapping("/products-quantity/edit/{id}")
//    public String productsQuantityEdit(@PathVariable Long id, Model model) {
//        ProductQuantity productQuantity = productQuantityRepo.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid Image ID: " + id));
//
//        model.addAttribute("productQuantity", productQuantity);
//
//        return "moderator/forms-edit/edit-product-quantity";
//    }
//
//    @PostMapping("/products-quantity/edit")
//    public String productsQuantityUpdate(@ModelAttribute ProductQuantity productQuantity) {
//        productQuantityRepo.save(productQuantity);
//
//        return "redirect:/moderator/products-quantity";
//    }
}

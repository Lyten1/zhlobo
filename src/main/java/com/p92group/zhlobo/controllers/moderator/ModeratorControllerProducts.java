package com.p92group.zhlobo.controllers.moderator;

import com.p92group.zhlobo.models.Product;
import com.p92group.zhlobo.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/moderator")
public class ModeratorControllerProducts {
    @Autowired
    private ProductRepo productRepo;

    @GetMapping("/products")
    public String productsView(Model model){
        model.addAttribute("products", productRepo.findAll());

        return "moderator/forms-view/view-products";
    }

    @GetMapping("/products/add")
    public String productsAdd(Model model) {
        return "moderator/forms-add/add-product";
    }

    @PostMapping("/products/add")
    public String productsCreate(@ModelAttribute Product product) {
        productRepo.save(product);

        return "redirect:/moderator/products";
    }

    @GetMapping("/products/edit/{id}")
    public String productsEdit(@PathVariable Long id, Model model) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Image ID: " + id));

        model.addAttribute("product", product);

        return "moderator/forms-edit/edit-product";
    }

    @PostMapping("/products/edit")
    public String productsUpdate(@ModelAttribute Product product) {
        productRepo.save(product);

        return "redirect:/moderator/products";
    }
}

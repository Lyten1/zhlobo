package com.p92group.zhlobo.controllers.client;

import com.p92group.zhlobo.models.Product;
import com.p92group.zhlobo.repos.ProductRepo;
import com.p92group.zhlobo.services.ProductService;
import com.p92group.zhlobo.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/client")
public class ClientControllerProducts {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private SellerService sellerService;

    @GetMapping("/sellers/{id}")
    public String sellerProducts(Model model, @PathVariable Long id, @RequestParam(value = "prodName", required = false) String name){
        List<Product> products = productRepo.findAll().stream().filter(x -> Objects.equals(x.getSeller().getId(), id)).toList();

        Product product = new Product();
        model.addAttribute("product", product);

        model.addAttribute("products", products);

        return "client/forms-view/view-seller-products";
    }

    @GetMapping("/products")
    public String productsView(Model model, @RequestParam(value = "prodName", required = false) String name){
        Product product = new Product();
        model.addAttribute("product", product);
        if (name != null && !name.isEmpty()) {
            System.out.println(name);
            model.addAttribute("products", productRepo.findByName("%" + name + "%"));
        } else {
            model.addAttribute("products", productRepo.findAll());
        }

        return "client/forms-view/view-products";
    }

    @GetMapping("/products/choose/{id}")
    public String productPage(@PathVariable Long id, Model model) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Product ID: " + id));

        model.addAttribute("product", product);

        return "client/forms-view/view-one-product";
    }

    @PostMapping("/products/list")
    public String productsUpdate(@ModelAttribute Product product) {


        return "redirect:/client/products";
    }

}

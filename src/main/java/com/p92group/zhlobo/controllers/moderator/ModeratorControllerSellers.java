package com.p92group.zhlobo.controllers.moderator;


import com.p92group.zhlobo.models.Balance;
import com.p92group.zhlobo.models.Seller;
import com.p92group.zhlobo.repos.BalanceRepo;
import com.p92group.zhlobo.repos.SellerRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/moderator")
public class ModeratorControllerSellers {
    private final SellerRepo sellerRepo;
    private final BalanceRepo balanceRepo;

    public ModeratorControllerSellers(SellerRepo sellerRepo, BalanceRepo balanceRepo) {
        this.sellerRepo = sellerRepo;
        this.balanceRepo = balanceRepo;
    }

    @GetMapping("/sellers")
    public String sellersView(Model model){
        model.addAttribute("sellers", sellerRepo.findAll());

        return "moderator/forms-view/view-sellers";
    }

    @GetMapping("/sellers/add")
    public String sellersAdd() {
        return "moderator/forms-add/add-seller";
    }

    @PostMapping("/sellers/add")
    public String sellersCreate(@ModelAttribute Seller seller) {
        Balance newBalance = new Balance();
        balanceRepo.save(newBalance);

        seller.setBalance(newBalance);
        sellerRepo.save(seller);

        return "redirect:/moderator/sellers";
    }

    @GetMapping("/sellers/edit/{id}")
    public String sellersEdit(@PathVariable Long id, Model model) {
        Seller seller = sellerRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Image ID: " + id));

        model.addAttribute("seller", seller);
//        model.addAttribute("balances", balanceRepo.findAll());

        return "moderator/forms-edit/edit-seller";
    }

    @PostMapping("/sellers/edit")
    public String sellersUpdate(@ModelAttribute Seller seller) {
        sellerRepo.save(seller);

        return "redirect:/moderator/sellers";
    }
}

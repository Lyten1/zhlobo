package com.p92group.zhlobo.controllers.moderator;

import com.p92group.zhlobo.models.Balance;
import com.p92group.zhlobo.repos.BalanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/moderator")
public class ModeratorControllerBalances {

    //TODO try catch exception
    // tests
    // findByStatus
    // findByValueFilter

    @Autowired
    private BalanceRepo balanceRepo;
    
    @GetMapping("/balances")
    public String balancesView(Model model){
        model.addAttribute("balances", balanceRepo.findAll());

        return "moderator/forms-view/view-balances";
    }

    @GetMapping("/balances/add")
    public String balancesAdd() {
        return "moderator/forms-add/add-balance";
    }

    @PostMapping("/balances/add")
    public String balancesCreate(@ModelAttribute Balance balance) {
        balanceRepo.save(balance);

        return "redirect:/moderator/balances";
    }

    @GetMapping("/balances/edit/{id}")
    public String balancesEdit(@PathVariable Long id, Model model) {
        Balance balance = balanceRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Image ID: " + id));

        model.addAttribute("balance", balance);

        return "moderator/forms-edit/edit-balance";
    }

    @PostMapping("/balances/edit")
    public String balancesUpdate(@ModelAttribute Balance balance) {
        balanceRepo.save(balance);

        return "redirect:/moderator/balances";
    }
}

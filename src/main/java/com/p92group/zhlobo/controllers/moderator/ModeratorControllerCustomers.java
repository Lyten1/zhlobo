package com.p92group.zhlobo.controllers.moderator;


import com.p92group.zhlobo.models.Balance;
import com.p92group.zhlobo.models.Customer;
import com.p92group.zhlobo.repos.BalanceRepo;
import com.p92group.zhlobo.repos.CustomersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/moderator")
public class ModeratorControllerCustomers {
    @Autowired
    private CustomersRepo customerRepo;
    @Autowired
    private BalanceRepo balanceRepo;

    @GetMapping("/customers")
    public String customersView(Model model){
        model.addAttribute("customers", customerRepo.findAll());

        return "moderator/forms-view/view-customers";
    }

    @GetMapping("/customers/add")
    public String customersAdd() {
        return "moderator/forms-add/add-customer";
    }

    @PostMapping("/customers/add")
    public String customersCreate(@ModelAttribute Customer customer) {
        Balance newBalance = new Balance();
        balanceRepo.save(newBalance);

        customer.setBalance(newBalance);
        customerRepo.save(customer);

        return "redirect:/moderator/customers";
    }

    @GetMapping("/customers/edit/{id}")
    public String customersEdit(@PathVariable Long id, Model model) {
        Customer customer = customerRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Image ID: " + id));

        model.addAttribute("customer", customer);
//        model.addAttribute("balances", balanceRepo.findAll());

        return "moderator/forms-edit/edit-customer";
    }

    @PostMapping("/customers/edit")
    public String customersUpdate(@ModelAttribute Customer customer) {
        customerRepo.save(customer);

        return "redirect:/moderator/customers";
    }
}

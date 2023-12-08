package com.p92group.zhlobo.services;

import com.p92group.zhlobo.models.Balance;
import com.p92group.zhlobo.models.Customer;
import com.p92group.zhlobo.models.Seller;
import com.p92group.zhlobo.repos.BalanceRepo;
import com.p92group.zhlobo.repos.CustomersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomersRepo customersRepo;
    @Autowired
    private BalanceService balanceService;

    public void save(Customer customer) {
        if (customer.getBalance() == null){
            Balance balance = new Balance();
            balanceService.save(balance);
            customer.setBalance(balance);
        }

        customer.setPassword(passwordEncoder.encode(customer.getPassword()));

        customersRepo.saveAndFlush(customer);
    }

    public List<Customer> getAll() {
        return customersRepo.findAll();
    }

    public Customer getById(Long id) {
        return customersRepo.findById(id).orElse(null);
    }

    public void delete(String idIn){
        Customer customer = customersRepo.findById(Long.parseLong(idIn)).orElse(null);
        if(customer != null) {
            customersRepo.delete(customer);
        }
    }
}

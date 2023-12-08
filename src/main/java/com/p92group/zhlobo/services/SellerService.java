package com.p92group.zhlobo.services;

import com.p92group.zhlobo.models.Balance;
import com.p92group.zhlobo.models.Product;
import com.p92group.zhlobo.models.Seller;
import com.p92group.zhlobo.repos.ProductRepo;
import com.p92group.zhlobo.repos.SellerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SellerRepo sellerRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private BalanceService balanceService;

    public void save(Seller seller) {
        if (seller.getBalance() == null){
            Balance balance = new Balance();
            balanceService.save(balance);
            seller.setBalance(balance);
        }

        seller.setPassword(passwordEncoder.encode(seller.getPassword()));

        sellerRepo.saveAndFlush(seller);
    }

    public List<Seller> getAll() {
        return sellerRepo.findAll();
    }

    public Seller getById(Long id) {
        return sellerRepo.findById(id).orElse(null);
    }

    public void delete(String idIn) {
        Seller seller = sellerRepo.findById(Long.parseLong(idIn)).orElse(null);
        if (seller != null) {
            sellerRepo.delete(seller);
        }
    }

    public void saveWithProducts(Seller seller, List<Product> products) {
        save(seller);

        products.forEach(product -> product.setSeller(seller));

        productRepo.saveAllAndFlush(products);
    }
}

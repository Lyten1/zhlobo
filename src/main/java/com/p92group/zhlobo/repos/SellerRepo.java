package com.p92group.zhlobo.repos;

import com.p92group.zhlobo.models.Customer;
import com.p92group.zhlobo.models.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepo extends JpaRepository<Seller, Long> {
    Seller findByEmail(String email);
}

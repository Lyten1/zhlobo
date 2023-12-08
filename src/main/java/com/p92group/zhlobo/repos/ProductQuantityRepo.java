package com.p92group.zhlobo.repos;

import com.p92group.zhlobo.models.ProductQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductQuantityRepo extends JpaRepository<ProductQuantity, Long> {
    List<ProductQuantity> findByProduct_Id(Long productId);
}

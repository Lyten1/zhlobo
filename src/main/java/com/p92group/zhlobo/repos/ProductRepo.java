package com.p92group.zhlobo.repos;


import com.p92group.zhlobo.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    @Query(value = "SELECT p FROM Product p WHERE LOWER(p.title) LIKE LOWER(:titlePrefix)")
    List<Product> findByName(@Param("titlePrefix") String name);


}

package com.p92group.zhlobo.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Data
@Entity
@Table(name = "products")
public class Product {
    public Product() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_status")
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus = ProductStatus.in_stock;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "price")
    private BigDecimal price = BigDecimal.ZERO;

    @ManyToOne
    private Seller seller;

    @ManyToOne
    private Image image;

    public Product(String title, ProductStatus productStatus, BigDecimal price, Seller seller, Image image) {
        this.productStatus = productStatus;
        this.title = title;
        this.price = price;
        this.seller = seller;
        this.image = image;
    }
}

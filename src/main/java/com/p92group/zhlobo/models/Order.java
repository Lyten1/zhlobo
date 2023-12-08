package com.p92group.zhlobo.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "order_time")
    private LocalDateTime orderTime;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Courier courier;

    @ManyToMany
    @JoinTable(name = "orders_products_quantity",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_quantity_id"))
    private List<ProductQuantity> productQuantity;


    public Order() {
    }

    public Long getId() {
        return id;
    }

    public Order(Customer customer, List<ProductQuantity> productQuantity) {
        this.customer = customer;
        this.productQuantity = productQuantity;
    }

    public List<ProductQuantity> getProductQuantity() {
        return productQuantity;
    }
}

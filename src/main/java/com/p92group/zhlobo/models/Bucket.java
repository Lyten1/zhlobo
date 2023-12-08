package com.p92group.zhlobo.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@Entity
@Table(name = "bucket")
public class Bucket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    @ManyToMany
    @JoinTable(name = "bucket_products_quantity",
            joinColumns = @JoinColumn(name = "bucket_id"),
            inverseJoinColumns = @JoinColumn(name = "product_quantity_id"))
    private List<ProductQuantity> productQuantity;

    public Bucket() {
    }

    public Bucket(Customer customer, List<ProductQuantity> productQuantity) {
        this.customer = customer;
        this.productQuantity = productQuantity;
    }

    public List<ProductQuantity> getProductQuantity() {
        return productQuantity;
    }
}

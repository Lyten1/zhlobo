package com.p92group.zhlobo.services;

import com.p92group.zhlobo.models.*;
import com.p92group.zhlobo.repos.BucketRepo;
import com.p92group.zhlobo.repos.CustomersRepo;
import com.p92group.zhlobo.repos.ProductQuantityRepo;
import com.p92group.zhlobo.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BucketService {
    @Autowired
    private BucketRepo bucketRepo;

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CustomersRepo customersRepo;
    @Autowired
    private ProductQuantityRepo productQuantityRepo;

    public void save(Bucket bucket) {
        bucketRepo.saveAndFlush(bucket);
    }

    public Bucket getByUserId(Long user_id) {
        return bucketRepo.findById(user_id).orElse(null);
    }
    public List<ProductQuantity> getProductsByCustomerId(Long customerId) {
        List<Bucket> buckets = bucketRepo.findByCustomer_Id(customerId);
        List<ProductQuantity> productsQuantities = new ArrayList<>();

        for (Bucket bucket : buckets) {
            List<ProductQuantity> bucketProductsQuantities = bucket.getProductQuantity();
            productsQuantities.addAll(bucketProductsQuantities);
        }

        return productsQuantities;
    }

    public void save(Product product, Long currentUserId){
         Product product1 = productRepo.findById(product.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Product ID: " + product.getId()));
        Customer customer = customersRepo.findById(currentUserId).orElse(null);
        ProductQuantity productQuantity = new ProductQuantity(1, product);
        List<ProductQuantity> pQ = new ArrayList<>();
        pQ.add(productQuantity);
        productQuantityRepo.saveAndFlush(productQuantity);

        Bucket bucket = new Bucket(customer, pQ);
        bucketRepo.saveAndFlush(bucket);
    }

    public void addOne(Long pQId){
        ProductQuantity productQuantity = productQuantityRepo.findById(pQId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Product ID: " + pQId));
        productQuantity.setQuantity(productQuantity.getQuantity() + 1);
        productQuantityRepo.saveAndFlush(productQuantity);

    }

    public void subOne(Long pQId){
        ProductQuantity productQuantity = productQuantityRepo.findById(pQId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Product ID: " + pQId));
        productQuantity.setQuantity(productQuantity.getQuantity() > 1 ? productQuantity.getQuantity() - 1 : productQuantity.getQuantity());
        productQuantityRepo.saveAndFlush(productQuantity);

    }

}

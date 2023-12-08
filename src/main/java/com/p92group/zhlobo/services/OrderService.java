package com.p92group.zhlobo.services;

import com.p92group.zhlobo.models.*;
import com.p92group.zhlobo.repos.*;
import org.hibernate.grammars.hql.HqlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private BucketRepo bucketRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CustomersRepo customersRepo;
    @Autowired
    private ProductQuantityRepo productQuantityRepo;

    public Order createOrder(Customer customer, List<ProductQuantity> productQuantityList){
        return new Order(customer, productQuantityList);
    }

    public void save(Order order) {
        orderRepo.saveAndFlush(order);
    }

    public Order getByUserId(Long user_id) {
        return orderRepo.findById(user_id).orElse(null);
    }
    public List<ProductQuantity> getProductsByCustomerId(Long customerId) {
        List<Order> orders = orderRepo.findByCustomer_Id(customerId);
        List<ProductQuantity> productsQuantities = new ArrayList<>();

        for (Order order : orders) {
            List<ProductQuantity> orderProductsQuantities = order.getProductQuantity();
            productsQuantities.addAll(orderProductsQuantities);
        }

        return productsQuantities;
    }

    public void save(Long currentUserId){
        Customer customer = customersRepo.findById(currentUserId).orElse(null);
        List<Bucket> buckets = bucketRepo.findByCustomer_Id(customer.getId());
        List<ProductQuantity> productQuantities = new ArrayList<>();
        for (Bucket bucket : buckets){
            productQuantities.add(bucket.getProductQuantity().get(0));
            bucketRepo.delete(bucket);
        }

        Order order = new Order(customer, productQuantities);
        orderRepo.saveAndFlush(order);
    }



}

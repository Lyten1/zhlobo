package com.p92group.zhlobo.repos;

import com.p92group.zhlobo.models.Bucket;
import com.p92group.zhlobo.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

    List<Order> findByCustomer_Id(Long customerId);
}

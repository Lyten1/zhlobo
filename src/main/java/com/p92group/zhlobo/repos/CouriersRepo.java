package com.p92group.zhlobo.repos;

import com.p92group.zhlobo.models.Courier;
import com.p92group.zhlobo.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouriersRepo extends JpaRepository<Courier, Long> {
    Courier findByEmail(String email);
}

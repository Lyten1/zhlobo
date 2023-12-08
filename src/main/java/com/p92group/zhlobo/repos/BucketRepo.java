package com.p92group.zhlobo.repos;


import com.p92group.zhlobo.models.Balance;
import com.p92group.zhlobo.models.Bucket;
import com.p92group.zhlobo.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BucketRepo extends JpaRepository<Bucket, Long> {
    List<Bucket> findByCustomer_Id(Long customerId);

    @Override
    <S extends Bucket> S saveAndFlush(S entity);

    @Override
    void delete(Bucket entity);
}

package com.p92group.zhlobo.repos;

import com.p92group.zhlobo.models.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BalanceRepo extends JpaRepository<Balance, Long> {


    @Query("select b from Balance b where b.id = :id")
    Optional<Balance> findById(@Param("id") Long aLong);




}

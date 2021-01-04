package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Recharge;

public interface RechargeRepository extends JpaRepository<Recharge,Long> {

@Query("Select r.purchasedDate from Recharge r Join r.account a where a.id = :id")
public LocalDate PurchesedDate(@Param("id") Long id);

@Query("SELECT e FROM Recharge e WHERE e.purchasedDate BETWEEN :startDate AND :endDate")
public List<Recharge> findAllRechargesInPeriod(@Param("startDate") LocalDate startDate,@Param("endDate") LocalDate endDate);

@Query("SELECT e FROM Recharge e WHERE e.purchasedDate BETWEEN :startDate AND :endDate order by e.purchasedDate")
public int countRechargesInPeriod(LocalDate startDate, LocalDate endDate);

}

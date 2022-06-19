package com.tushaar.mainassignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tushaar.mainassignment.models.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}

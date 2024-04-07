package com.boccigaria.testecsite.repository;

import org.springframework.data.repository.CrudRepository;

import com.boccigaria.testecsite.repository.entity.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Integer> {

}

package com.boccigaria.testecsite.repository;

import org.springframework.data.repository.CrudRepository;

import com.boccigaria.testecsite.repository.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}

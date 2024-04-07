package com.boccigaria.testecsite.repository;

import org.springframework.data.repository.CrudRepository;

import com.boccigaria.testecsite.repository.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Integer> {

}

package com.boccigaria.testecsite.repository;

import org.springframework.data.repository.CrudRepository;

import com.boccigaria.testecsite.repository.entity.OrderDetail;
import com.boccigaria.testecsite.repository.entity.key.OrderDetailPKey;

public interface OrderDetailRepository extends CrudRepository<OrderDetail, OrderDetailPKey> {

}

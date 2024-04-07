package com.boccigaria.testecsite.repository;

import org.springframework.data.repository.CrudRepository;

import com.boccigaria.testecsite.repository.entity.Item;

public interface ItemRepository extends CrudRepository<Item, String> {

}

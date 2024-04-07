package com.boccigaria.testecsite.repository;

import org.springframework.data.repository.CrudRepository;

import com.boccigaria.testecsite.repository.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, String> {

}

package com.boccigaria.testecsite.repository;

import org.springframework.data.repository.CrudRepository;

import com.boccigaria.testecsite.repository.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}

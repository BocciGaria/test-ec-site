package com.boccigaria.testecsite.repository;

import org.springframework.data.repository.CrudRepository;

import com.boccigaria.testecsite.repository.entity.Course;

public interface CourseRepository extends CrudRepository<Course, String> {

}

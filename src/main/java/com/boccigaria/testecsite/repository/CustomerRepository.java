package com.boccigaria.testecsite.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.boccigaria.testecsite.repository.entity.Customer;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>, JpaSpecificationExecutor<Customer> {

    static Specification<Customer> matchNameAddressEmailTel(String name, String address, String email, String tel) {
        if (name == null || address == null || address == null || tel == null) return null;
        return new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.and(
                    cb.equal(root.get("name"), name),
                    cb.equal(root.get("address"), address),
                    cb.equal(root.get("email"), email),
                    cb.equal(root.get("tel"), tel)
                );
            }
        };
    }
}

package com.boccigaria.testecsite.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "customers",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "name_address_email_tel_unique",
            columnNames = {"name", "address", "email", "tel"}
        ),
    }
)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private Integer tel;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name == "" ? null : name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address == "" ? null : address;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email == "" ? null : email;
    }
    public Integer getTel() {
        return tel;
    }
    public void setTel(Integer tel) {
        this.tel = tel;
    }
}

package com.boccigaria.testecsite;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "user",
    uniqueConstraints = {
        @UniqueConstraint(name = "name_unique", columnNames = {"name"}),
        @UniqueConstraint(name = "email_unique", columnNames = {"email"}),
    }
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 60)
    private String name;
    @Column(nullable = false, length = 255)
    private String email;

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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email == "" ? null : email;
    }
}

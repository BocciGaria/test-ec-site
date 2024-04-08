package com.boccigaria.testecsite.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "categories",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"}),
    }
)
public class Category {
    @Id
    private String id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Float tax;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id == "" ? null : id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name == "" ? null : name;
    }
    public Float getTax() {
        return tax;
    }
    public void setTax(Float tax) {
        this.tax = tax;
    }

}

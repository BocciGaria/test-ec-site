package com.boccigaria.testecsite.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "items",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"}),
    }
)
public class Item {
    @Id
    private String id;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Category category;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer price;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id == "" ? null : id;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name == "" ? null : name;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }


}

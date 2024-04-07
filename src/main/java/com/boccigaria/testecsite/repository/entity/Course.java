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
    name = "courses",
    uniqueConstraints = {
        @UniqueConstraint(name = "name_unique", columnNames = {"name"}),
    }
)
public class Course {
    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "item", nullable = false)
    private Item item;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer quantity;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id == "" ? null : id;
    }
    public Item getItem() {
        return item;
    }
    public void setItem(Item item) {
        this.item = item;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name == "" ? null : name;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

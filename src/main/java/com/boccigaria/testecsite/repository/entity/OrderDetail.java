package com.boccigaria.testecsite.repository.entity;

import com.boccigaria.testecsite.repository.entity.key.OrderDetailPKey;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(
    name = "order_details"
)
@IdClass(OrderDetailPKey.class)
public class OrderDetail {
    @Id
    @ManyToOne
    @JoinColumn
    private Order order;
    @Id
    private Integer detail_no;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Course course;

    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
    public Integer getDetailNo() {
        return detail_no;
    }
    public void setDetailNo(Integer detail_no) {
        this.detail_no = detail_no;
    }
    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }
}

package com.boccigaria.testecsite.repository.entity;

import com.boccigaria.testecsite.repository.entity.key.CourseDetailPKey;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(
    name = "course_details"
)
@IdClass(CourseDetailPKey.class)
public class CourseDetail {

    @Id
    @ManyToOne
    @JoinColumn
    private Course course;
    @Id
    private Integer detailNo;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Item item;
    @Column(nullable = false)
    private Integer quantity;

    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }
    public Integer getDetailNo() {
        return detailNo;
    }
    public void setDetailNo(Integer detailNo) {
        this.detailNo = detailNo;
    }
    public Item getItem() {
        return item;
    }
    public void setItem(Item item) {
        this.item = item;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}

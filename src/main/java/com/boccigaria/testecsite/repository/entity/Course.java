package com.boccigaria.testecsite.repository.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "courses",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"}),
    }
)
public class Course {
    @Id
    private String id;
    @Column(nullable = false)
    private String name;

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

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    private Set<CourseDetail> courseDetails;

    public Set<CourseDetail> getCourseDetails() {
        return courseDetails;
    }
    public void setCourseDetails(Set<CourseDetail> courseDetails) {
        this.courseDetails = courseDetails;
    }

    /**
     * Get the total price with tax for the course
     * @return price(rounded)
     */
    public Integer getTotalPrice() {
        Integer result = 0;
        for (CourseDetail d : courseDetails) {
            result += d.getItem().getPrice();
            result += Math.round(d.getItem().getPrice() * d.getItem().getCategory().getTax());
        }
        return result;
    }
}

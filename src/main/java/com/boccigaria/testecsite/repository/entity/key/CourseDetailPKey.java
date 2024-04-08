package com.boccigaria.testecsite.repository.entity.key;

import java.io.Serializable;

import com.boccigaria.testecsite.repository.entity.Course;

public class CourseDetailPKey implements Serializable {
    private Course course;
    private Integer detailNo;

    public CourseDetailPKey() {

    }
    public CourseDetailPKey(Course course, Integer detailNo) {
        this.course = course;
        this.detailNo = detailNo;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj instanceof CourseDetailPKey == false) return false;
        CourseDetailPKey other = (CourseDetailPKey)obj;
        if (course.getId() != other.course.getId() || detailNo != other.detailNo) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return (course.getId() + detailNo).hashCode();
    }
}

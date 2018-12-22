package com.cn.sleep.study.example.shigongwen.model.course;

import com.cn.sleep.study.example.shigongwen.base.BaseModel;
import org.hibernate.annotations.Formula;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class CourseInfo extends BaseModel {

    private String schoolYear;
    private Integer term;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @Formula("(SELECT CS.COURSE_NAME FROM COURSE CS WHERE CS.ID = COURSE_ID)")
    private String courseName;

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}

package com.cn.sleep.study.example.shigongwen.model.course;

import com.cn.sleep.study.example.shigongwen.base.BaseModel;

import javax.persistence.Entity;

@Entity
public class Course extends BaseModel {
    private String courseName;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}

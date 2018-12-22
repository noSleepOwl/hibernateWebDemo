package com.cn.sleep.study.example.shigongwen.model.room;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
// 设置类的标记
@DiscriminatorValue("Classroom")
public class Classroom extends Room {
    private String courseName;
    private String className;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}

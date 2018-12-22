package com.cn.sleep.study.example.shigongwen.model.video;

import javax.persistence.Entity;

/**
 * 课程视频资源
 */
@Entity
public class CourseVideo extends Video {
    private String courseId;
    private String courseName;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}

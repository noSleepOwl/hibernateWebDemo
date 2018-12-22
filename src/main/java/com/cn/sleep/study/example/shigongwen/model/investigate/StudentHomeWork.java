package com.cn.sleep.study.example.shigongwen.model.investigate;

import jdk.nashorn.internal.ir.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * 通过配置子查询进行模型的映射
 */
@Entity
@Immutable
@Subselect(value = "SELECT ST.ID AS STUDENT_ID , WK.CONTENT AS CONTENT , WK.CREATE_TIME AS TIME ,WK.ID AS ID " +
        "FROM  HOME_WORK WK INNER JOIN STUDENT ST ON ST.ID = WK.STUDENT_ID ")
// 此处为表名称 关联的表
@Synchronize({"student", "home_work"})
public class StudentHomeWork {
    @Id
    private String id;  // 必须指定id
    private String studentId;
    private String content;
    private Date time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}

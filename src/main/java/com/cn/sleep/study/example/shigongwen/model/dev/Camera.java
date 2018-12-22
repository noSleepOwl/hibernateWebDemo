package com.cn.sleep.study.example.shigongwen.model.dev;

import javax.persistence.Entity;

@Entity
public class Camera extends Dev {
    private String type;  // 类型 学生 老师 vga
    private String position; // 位置

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}

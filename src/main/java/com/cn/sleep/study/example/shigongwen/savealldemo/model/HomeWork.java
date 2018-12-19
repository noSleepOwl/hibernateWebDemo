package com.cn.sleep.study.example.shigongwen.savealldemo.model;

import com.cn.sleep.study.example.shigongwen.base.BaseModel;

import javax.persistence.Entity;

@Entity
public class HomeWork extends BaseModel {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

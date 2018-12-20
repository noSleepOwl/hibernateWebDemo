package com.cn.sleep.study.example.shigongwen.model;

import com.cn.sleep.study.example.shigongwen.base.BaseModel;

import javax.persistence.Entity;

@Entity
public class Teacher extends BaseModel {
    private String name;
    private Integer age;
    private String code;

}

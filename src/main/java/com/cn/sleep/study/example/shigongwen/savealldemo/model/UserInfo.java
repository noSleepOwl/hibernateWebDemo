package com.cn.sleep.study.example.shigongwen.savealldemo.model;

import javax.persistence.Embeddable;

/**
 * 用户信息
 */
@Embeddable
public class UserInfo {
    private String name;
    private Integer age;
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

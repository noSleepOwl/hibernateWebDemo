package com.cn.sleep.study.example.shigongwen.model;

import com.cn.sleep.study.example.shigongwen.base.BaseModel;
import com.cn.sleep.study.example.shigongwen.hibernatepowerfilter.Const;
import org.hibernate.annotations.Filter;

import javax.persistence.Entity;

@Entity
@Filter(name = Const.USER_FILTER, condition = Const.HOME_WORK_CONDITION)
public class HomeWork extends BaseModel {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

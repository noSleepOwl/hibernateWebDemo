package com.cn.sleep.study.example.shigongwen.model;

import com.cn.sleep.study.example.shigongwen.base.BaseModel;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student extends BaseModel {

    private UserInfo userInfo;
    /**
     * CascadeType.PERSIST 级联更新
     * CascadeType.REMOVE 级联删除配置
     */
    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name = "student_id")
    private List<HomeWork> homeWorkList = new ArrayList<>();


    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public List<HomeWork> getHomeWorkList() {
        return homeWorkList;
    }

    public void setHomeWorkList(List<HomeWork> homeWorkList) {
        this.homeWorkList = homeWorkList;
    }
}

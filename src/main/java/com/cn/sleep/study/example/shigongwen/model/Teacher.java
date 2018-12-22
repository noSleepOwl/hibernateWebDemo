package com.cn.sleep.study.example.shigongwen.model;

import com.cn.sleep.study.example.shigongwen.base.BaseModel;

import javax.persistence.*;

@Entity
// 可以重写 BaseModel 类中的属性在数据库中映射的字段名称
@AttributeOverrides({
        @AttributeOverride(name = "createTime", column = @Column(name = "in_school_time"))
})
public class Teacher extends BaseModel {
    // 属性重写 将组件中的 属性 在当前实体中数据库映射的名称进行重写
    @AttributeOverrides({
            // 将UserInfo 中的name 属性 在数据库中重写为 teacher_name
            @AttributeOverride(name = "name"
                    , column = @Column(name = "teacher_name", length = 35)),
            // 将UserInfo 中的code属性 在数据库中重写为 teacher_code
            @AttributeOverride(name = "code"
                    , column = @Column(name = "teacher_code"))
    })
    private UserInfo userInfo;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}

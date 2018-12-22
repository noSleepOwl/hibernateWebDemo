package com.cn.sleep.study.example.shigongwen.model.immutable;

import com.cn.sleep.study.example.shigongwen.base.BaseModel;
import jdk.nashorn.internal.ir.annotations.Immutable;

import javax.persistence.Entity;

@Entity
// 该注解将使实体不可变 , 无法进行更新 ,并且 hibernate 对此实例的查询操作会有一些优化
@Immutable
public class Config extends BaseModel {
    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

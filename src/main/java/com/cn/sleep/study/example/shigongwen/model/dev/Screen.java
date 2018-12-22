package com.cn.sleep.study.example.shigongwen.model.dev;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
// 指定关联外键,在查询的时候将使用该字段进行join
@PrimaryKeyJoinColumn(name = "SCREEN_ID")
public class Screen extends Dev {
    private String type; // 分屏 主屏
    private String brand; // 品牌

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}

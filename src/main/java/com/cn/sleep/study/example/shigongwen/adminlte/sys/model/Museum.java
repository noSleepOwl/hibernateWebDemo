package com.cn.sleep.study.example.shigongwen.adminlte.sys.model;

import com.cn.sleep.study.example.shigongwen.adminlte.sys.model.enums.Type;
import com.cn.sleep.study.example.shigongwen.base.BaseModel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
public class Museum extends BaseModel {
    // 类型
    @Enumerated
    private Type type;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_Id")
    private List<Museum> subMuseum = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "parent_Id")
    private Museum parent;
    // 名称
    private String name;
    // 图标
    private String icon;
    // 连接地址
    private String href;
    // 最小化后图标
    private String pullRightIcon;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
    public List<Museum> getSubMuseum() {
        return subMuseum;
    }
    public void setSubMuseum(List<Museum> subMuseum) {
        this.subMuseum = subMuseum;
    }
    public Museum getParent() {
        return parent;
    }
    @JsonBackReference
    public void setParent(Museum parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getPullRightIcon() {
        return pullRightIcon;
    }

    public void setPullRightIcon(String pullRightIcon) {
        this.pullRightIcon = pullRightIcon;
    }
}

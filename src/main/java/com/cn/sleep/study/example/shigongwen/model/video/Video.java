package com.cn.sleep.study.example.shigongwen.model.video;

import com.cn.sleep.study.example.shigongwen.base.BaseModel;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * 每个带有联合的具体类使用一个表
 * 此类代表视频资源
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)// 该注解表示,将直接继承该类的所有实体映射到一张表中去
public abstract class Video extends BaseModel {
    private String sourceName;
    private String sourceUrl;

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }
}

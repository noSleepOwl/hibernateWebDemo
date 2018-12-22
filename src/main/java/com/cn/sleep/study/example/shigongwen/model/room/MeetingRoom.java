package com.cn.sleep.study.example.shigongwen.model.room;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
// 设置类的标记
@DiscriminatorValue("MeetingRoom")
public class MeetingRoom extends Room {
    private String meetingType;

    public String getMeetingType() {
        return meetingType;
    }

    public void setMeetingType(String meetingType) {
        this.meetingType = meetingType;
    }
}

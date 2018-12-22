package com.cn.sleep.study.example.shigongwen.model.video;

import javax.persistence.Entity;

/**
 * 任务视频资源
 */
@Entity
public class MissionVideo extends Video {
    private String missionId;
    private String missionName;

    public String getMissionId() {
        return missionId;
    }

    public void setMissionId(String missionId) {
        this.missionId = missionId;
    }

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }
}

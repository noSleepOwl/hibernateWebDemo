package com.cn.sleep.study.example.shigongwen.model.room;

import com.cn.sleep.study.example.shigongwen.base.BaseModel;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// 所有子类放在一张表里面,该注解来告诉jap
// 使用哪个字段来标记 子类的名称
@DiscriminatorColumn(name = "BD_TYPE")
public abstract class Room extends BaseModel {

    private String roomName;
    private String roomCode;

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }
}

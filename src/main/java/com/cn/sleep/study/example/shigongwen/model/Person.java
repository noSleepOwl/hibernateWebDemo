package com.cn.sleep.study.example.shigongwen.model;

import javax.persistence.*;

@Entity
@Table(name = "person_table")
/* 指定第二个表的表名为person_detail*/
@SecondaryTable(name = "person_detail"
        , pkJoinColumns = @PrimaryKeyJoinColumn(name = "person_id"))
public class Person {
    //实体类的标识属性
    @Id /* 用于修饰标识属性 */
    /* 指定该主键列的主键生成策略 */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /* @Column指定该Field映射的列信息，此处指定了列名、长度 */
    @Column(name = "person_name", length = 50)
    private String name;
    /* 指定将该列放入新数据表（person_detail）内 */
    @Column(table = "person_detail", name = "email")
    private String email;
    /* 指定将该列放入新数据表（person_detail）内 */
    @Column(table = "person_detail", name = "phone")
    private String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
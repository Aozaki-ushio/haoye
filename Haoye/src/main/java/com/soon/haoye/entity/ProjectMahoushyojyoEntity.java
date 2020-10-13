package com.soon.haoye.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "project_mahoushyojyo", schema = "HaoyeFirst", catalog = "")
public class ProjectMahoushyojyoEntity {
    private String uid;
    private String user;
    private String password;
    private Integer isSelled;
    private String list3X;
    private String list4X;
    private Date createTime;
    private Date selledTime;
    private String number;
    private String price;

    @Basic
    @Column(name="price")
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Basic
    @Column(name="number")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Id
    @Column(name = "uid")
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "user")
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "is_selled")
    public Integer getIsSelled() {
        return isSelled;
    }

    public void setIsSelled(Integer isSelled) {
        this.isSelled = isSelled;
    }

    @Basic
    @Column(name = "list3x")
    public String getList3X() {
        return list3X;
    }

    public void setList3X(String list3X) {
        this.list3X = list3X;
    }

    @Basic
    @Column(name = "list4x")
    public String getList4X() {
        return list4X;
    }

    public void setList4X(String list4X) {
        this.list4X = list4X;
    }

    @Basic
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "selled_time")
    public Date getSelledTime() {
        return selledTime;
    }

    public void setSelledTime(Date selledTime) {
        this.selledTime = selledTime;
    }

}

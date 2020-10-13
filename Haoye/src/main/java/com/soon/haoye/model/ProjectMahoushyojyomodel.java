package com.soon.haoye.model;


import java.sql.Date;

public class ProjectMahoushyojyomodel {
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
    private int numberofsame;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIsSelled() {
        return isSelled;
    }

    public void setIsSelled(Integer isSelled) {
        this.isSelled = isSelled;
    }

    public String getList3X() {
        return list3X;
    }

    public void setList3X(String list3X) {
        this.list3X = list3X;
    }

    public String getList4X() {
        return list4X;
    }

    public void setList4X(String list4X) {
        this.list4X = list4X;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getSelledTime() {
        return selledTime;
    }

    public void setSelledTime(Date selledTime) {
        this.selledTime = selledTime;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getNumberofsame() {
        return numberofsame;
    }

    public void setNumberofsame(int numberofsame) {
        this.numberofsame = numberofsame;
    }
}

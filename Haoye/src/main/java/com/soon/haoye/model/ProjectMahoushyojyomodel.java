package com.soon.haoye.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.sql.Date;

@Data
@JsonIgnoreProperties({"uid","user","password","price","createTime","selledTime","numberOfsame"})
public class ProjectMahoushyojyomodel {

    private int uid;
    private String user;
    private String password;
    private String bind_mailbox;
    private String mail_password;
    private Integer isSelled;
    private String number;
    private String price;
    private String listOf4X;
    private String listOfMemoryCrystalls;
    private Date createTime;
    private Date selledTime;
    private int numberOfsame;//计数器

}

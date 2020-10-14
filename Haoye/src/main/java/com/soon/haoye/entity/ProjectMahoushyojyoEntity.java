package com.soon.haoye.entity;


import lombok.Data;

import javax.naming.Name;
import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "projectmahoushyojyo", schema = "HaoyeFirst", catalog = "")
public class ProjectMahoushyojyoEntity {

    @Id
    @Column(name = "uid")
    private String uid;
    @Column(name = "user")
    private String user;
    @Column(name = "password")
    private String password;
    @Column(name = "is_selled")
    private Integer isSelled;
    @Column(name = "number")
    private String number;
    @Column(name = "price")
    private String price;
    @Column(name = "list_of_4X")
    private String listOf4X;
    @Column(name = "list_of_memory_crystalls")
    private String listOfMemoryCrystalls;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "selled_time")
    private Date selledTime;

}

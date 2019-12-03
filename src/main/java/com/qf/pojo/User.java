package com.qf.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
@Data
@Component
//用户管理信息表
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int uid;
    private int phone;//手机
    private  String pass;
    private String photo;//头像
    private String uname;//姓名
    private String sex;//性别
    private String city;//城市
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birth;//生日
    private  String edustatus;//教育状态
    private String weixin;//微信
    private int worstatus;//兼职状态
    private int money;//钱包
    private int score;//信誉积分
}

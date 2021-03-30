package com.example.api.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 配送
 */
@Data
@Entity
@NoArgsConstructor
public class Distribution {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    //司机id
    private String did;

    //车辆id
    private String vid;

    //司机
    private String driver;

    //车牌号
    private String number;

    //客户电话
    private String phone;

    //客户地址
    private String address;

    //加急处理
    private boolean urgent;

    private String care;

    private String time;

    private Integer status;

}

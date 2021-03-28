package com.example.api.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 车辆
 */
@Data
@Entity
@NoArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    //车牌号
    private String number;

    //车辆类型
    private String type;

    //是否正在运输
    private boolean driving;

    private String createAt;

}

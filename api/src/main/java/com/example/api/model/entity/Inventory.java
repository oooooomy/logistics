package com.example.api.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 库存
 */
@Data
@Entity
@NoArgsConstructor
public class Inventory {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    //仓库id
    private String wid;

    //商品信息id
    private String cid;

    //商品名
    private String name;

    //所在位置区域
    private String location;

    //数量
    private Integer count;

}

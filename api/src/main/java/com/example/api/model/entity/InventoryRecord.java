package com.example.api.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 库存记录 出库 入库
 */
@Data
@Entity
@NoArgsConstructor
public class InventoryRecord {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    //商品名
    private String name;

    //仓库id
    private String wid;

    //商品信息id
    private String cid;

    private Integer count;

    //-1:出库 +1:入库
    private Integer type;

    //描述信息
    private String description;

    private String createAt;

}

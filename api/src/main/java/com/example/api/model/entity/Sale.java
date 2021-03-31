package com.example.api.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 销售
 */
@Data
@Entity
@NoArgsConstructor
public class Sale {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String company;

    private String number;

    private String commodity;

    private String count;

    private double price;

    private String phone;

    private String description;

    private boolean pay;

    private String createAt;

}

package com.example.api.service;

import com.example.api.model.entity.Warehouse;

import java.util.List;

public interface WarehouseService {

    Warehouse save(Warehouse warehouse);

    List<Warehouse> findAll();

    void delete(String id);

}

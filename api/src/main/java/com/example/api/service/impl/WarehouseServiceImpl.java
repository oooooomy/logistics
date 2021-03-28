package com.example.api.service.impl;

import com.example.api.model.entity.Warehouse;
import com.example.api.repository.WareHouseRepository;
import com.example.api.service.WarehouseService;
import com.example.api.utils.DataTimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Resource
    private WareHouseRepository wareHouseRepository;

    @Override
    public Warehouse save(Warehouse warehouse) {
        warehouse.setCreateAt(DataTimeUtil.getNowTimeString());
        return wareHouseRepository.save(warehouse);
    }

    @Override
    public List<Warehouse> findAll() {
        return wareHouseRepository.findAll();
    }

    @Override
    public void delete(String id) {
        wareHouseRepository.deleteById(id);
    }

}

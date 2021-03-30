package com.example.api.service.impl;

import com.example.api.model.entity.Inventory;
import com.example.api.repository.InventoryRepository;
import com.example.api.service.InventoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Resource
    private InventoryRepository inventoryRepository;

    @Override
    public Inventory save(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public List<Inventory> findByCommodityId(String cid) {
        return inventoryRepository.findAllByCid(cid);
    }

    @Override
    public List<Inventory> findByWarehouseId(String wid) {
        return inventoryRepository.findAllByWid(wid);
    }

}

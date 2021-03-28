package com.example.api.controller;

import com.example.api.model.entity.Warehouse;
import com.example.api.service.WarehouseService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController {

    @Resource
    private WarehouseService warehouseService;

    @PostMapping("/save")
    public Warehouse save(@RequestBody Warehouse warehouse){
        return warehouseService.save(warehouse);
    }

    @GetMapping("/findAll")
    public List<Warehouse> findAll() {
        return warehouseService.findAll();
    }

    @DeleteMapping("/delete")
    public void delete(String id){
        warehouseService.delete(id);
    }

}

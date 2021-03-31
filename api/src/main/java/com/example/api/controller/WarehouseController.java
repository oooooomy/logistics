package com.example.api.controller;

import com.example.api.model.entity.Warehouse;
import com.example.api.service.WarehouseService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/warehouse")
@PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN' ,'ROLE_WAREHOUSE')")
public class WarehouseController {

    @Resource
    private WarehouseService warehouseService;

    @PostMapping("")
    public Warehouse save(@RequestBody Warehouse warehouse) {
        return warehouseService.save(warehouse);
    }

    @GetMapping("")
    public List<Warehouse> findAll() {
        return warehouseService.findAll();
    }

    @DeleteMapping("")
    public void delete(String id) {
        warehouseService.delete(id);
    }

}

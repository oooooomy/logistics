package com.example.api.controller;

import com.example.api.annotation.DisableBaseResponse;
import com.example.api.model.entity.Vehicle;
import com.example.api.service.VehicleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {

    @Resource
    private VehicleService vehicleService;

    @PostMapping("")
    public Vehicle save(@RequestBody Vehicle vehicle) {
        return vehicleService.save(vehicle);
    }

    @GetMapping("")
    public List<Vehicle> findAll() {
        return vehicleService.findAll();
    }

    @GetMapping("/{id}")
    public Vehicle findById(@PathVariable String id) {
        return vehicleService.findById(id);
    }

    @DeleteMapping("")
    public void delete(String id) {
        vehicleService.delete(id);
    }

}

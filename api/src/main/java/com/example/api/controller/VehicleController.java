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

    @PostMapping("/save")
    public Vehicle save(@RequestBody Vehicle vehicle){
        return vehicleService.save(vehicle);
    }

    @GetMapping("/findAll")
    public List<Vehicle> findAll(){
        return vehicleService.findAll();
    }

    @GetMapping("/findById")
    public Vehicle findById(String id){
        return vehicleService.findById(id);
    }

    @DeleteMapping("/delete")
    public void delete(String id){
        vehicleService.delete(id);
    }

}

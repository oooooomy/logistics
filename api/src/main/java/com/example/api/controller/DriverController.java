package com.example.api.controller;

import com.example.api.model.entity.Driver;
import com.example.api.service.DriverService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/driver")
public class DriverController {

    @Resource
    private DriverService driverService;

    @PostMapping("/save")
    public Driver save(@RequestBody Driver driver) {
        return driverService.save(driver);
    }

    @GetMapping("/findAll")
    public List<Driver> findAll() {
        return driverService.findAll();
    }

    @GetMapping("/findById")
    public Driver findById(String id) {
        return driverService.findById(id);
    }

    @DeleteMapping("/delete")
    public void delete(String id) {
        driverService.delete(id);
    }

}

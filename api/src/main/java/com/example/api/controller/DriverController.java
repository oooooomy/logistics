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

    @PostMapping("")
    public Driver save(@RequestBody Driver driver) {
        return driverService.save(driver);
    }

    @GetMapping("")
    public List<Driver> findAll() {
        return driverService.findAll();
    }

    @GetMapping("/{id}")
    public Driver findById(@PathVariable String id) {
        return driverService.findById(id);
    }

    @DeleteMapping("")
    public void delete(String id) {
        driverService.delete(id);
    }

}

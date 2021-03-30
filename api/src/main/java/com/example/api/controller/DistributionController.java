package com.example.api.controller;

import com.example.api.model.entity.Distribution;
import com.example.api.repository.DriverRepository;
import com.example.api.repository.VehicleRepository;
import com.example.api.service.DistributionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/distribution")
public class DistributionController {

    @Resource
    private DistributionService distributionService;

    @Resource
    private DriverRepository driverRepository;

    @Resource
    private VehicleRepository vehicleRepository;

    @PostMapping("")
    public Distribution save(@RequestBody Distribution distribution) throws Exception {
        return distributionService.save(distribution);
    }

    @GetMapping("")
    public List<Distribution> findAll() {
        return distributionService.findAll();
    }

    @GetMapping("can")
    public Map<String, Object> can() {
        Map<String, Object> map = new HashMap<>();
        map.put("drivers", driverRepository.findAll());
        map.put("vehicles", vehicleRepository.findAll());
        return map;
    }

}

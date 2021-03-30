package com.example.api.service.impl;

import com.example.api.model.entity.Distribution;
import com.example.api.model.entity.Driver;
import com.example.api.model.entity.Vehicle;
import com.example.api.repository.DistributionRepository;
import com.example.api.repository.DriverRepository;
import com.example.api.repository.VehicleRepository;
import com.example.api.service.DistributionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class DistributionServiceImpl implements DistributionService {

    @Resource
    private DistributionRepository distributionRepository;

    @Resource
    private DriverRepository driverRepository;

    @Resource
    private VehicleRepository vehicleRepository;

    @Override
    public Distribution save(Distribution distribution) throws Exception {
        Optional<Driver> driver = driverRepository.findById(distribution.getDid());
        Optional<Vehicle> vehicle = vehicleRepository.findById(distribution.getVid());
        if (driver.isEmpty() || vehicle.isEmpty()) throw new Exception("请求参数错误");
        if (driver.get().isDriving() || vehicle.get().isDriving()) throw new Exception("司机或货车状态不可用");
        driverRepository.updateDriving(true, distribution.getDid());
        vehicleRepository.updateDriving(true, distribution.getVid());
        return distributionRepository.save(distribution);
    }

    @Override
    public List<Distribution> findAll() {
        return distributionRepository.findAll();
    }

}

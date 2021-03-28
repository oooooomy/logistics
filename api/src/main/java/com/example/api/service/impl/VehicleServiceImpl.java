package com.example.api.service.impl;

import com.example.api.model.entity.Vehicle;
import com.example.api.repository.VehicleRepository;
import com.example.api.service.VehicleService;
import com.example.api.utils.DataTimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Resource
    private VehicleRepository vehicleRepository;

    @Override
    public Vehicle save(Vehicle vehicle) {
        vehicle.setCreateAt(DataTimeUtil.getNowTimeString());
        return vehicleRepository.save(vehicle);
    }

    @Override
    public void update(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    @Override
    public void delete(String id) {
        vehicleRepository.deleteById(id);
    }

    @Override
    public Vehicle findById(String id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    @Override
    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

}

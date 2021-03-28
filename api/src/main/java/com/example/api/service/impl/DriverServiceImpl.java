package com.example.api.service.impl;

import com.example.api.model.entity.Driver;
import com.example.api.repository.DriverRepository;
import com.example.api.service.DriverService;
import com.example.api.utils.DataTimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

    @Resource
    private DriverRepository driverRepository;

    @Override
    public Driver save(Driver driver) {
        driver.setCreateAt(DataTimeUtil.getNowTimeString());
        return driverRepository.save(driver);
    }

    @Override
    public void update(Driver driver) {
        driver.setUpdateAt(DataTimeUtil.getNowTimeString());
        driverRepository.save(driver);
    }

    @Override
    public void delete(String id) {
        driverRepository.deleteById(id);
    }

    @Override
    public Driver findById(String id) {
        return driverRepository.findById(id).orElse(null);
    }

    @Override
    public List<Driver> findAll() {
        return driverRepository.findAll();
    }

}

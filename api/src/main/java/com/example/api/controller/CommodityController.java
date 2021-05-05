package com.example.api.controller;

import com.example.api.model.entity.Commodity;
import com.example.api.service.CommodityService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/commodity")
@PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN' ,'ROLE_COMMODITY')")
public class CommodityController {

    @Resource
    private CommodityService commodityService;

    @PostMapping("")
    public Commodity save(@RequestBody Commodity commodity) {
        return commodityService.save(commodity);
    }

    @DeleteMapping("")
    public void delete(String id) {
        commodityService.delete(id);
    }

    @PutMapping("")
    public void update(@RequestBody Commodity commodity) {
        commodityService.update(commodity);
    }

    @GetMapping("")
    public List<Commodity> findAll() {
        return commodityService.findAll();
    }

    @GetMapping("/search/{name}")
    public List<Commodity> findByLikeName(@PathVariable String name) {
        return commodityService.findAllByLikeName(name);
    }

    @GetMapping("/{id}")
    public Commodity findById(@PathVariable String id) {
        return commodityService.findById(id);
    }

}

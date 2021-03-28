package com.example.api.controller;

import com.example.api.model.entity.Commodity;
import com.example.api.service.CommodityService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/commodity")
public class CommodityController {

    @Resource
    private CommodityService commodityService;

    @PostMapping("/save")
    public Commodity save(@RequestBody Commodity commodity) {
        return commodityService.save(commodity);
    }

    @DeleteMapping("/delete")
    public void delete(String id){
        commodityService.delete(id);
    }

    @PutMapping("/update")
    public void update(@RequestBody Commodity commodity){
        commodityService.update(commodity);
    }

    @GetMapping("/findAll")
    public List<Commodity> findAll(){
        return commodityService.findAll();
    }

    @GetMapping("/findById")
    public Commodity findById(String id){
        return commodityService.findById(id);
    }

}

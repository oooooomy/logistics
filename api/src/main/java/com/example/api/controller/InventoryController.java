package com.example.api.controller;

import com.example.api.model.entity.Inventory;
import com.example.api.model.entity.InventoryRecord;
import com.example.api.model.vo.CommodityChartVo;
import com.example.api.service.InventoryRecordService;
import com.example.api.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Resource
    private InventoryService inventoryService;

    @Resource
    private InventoryRecordService recordService;

    @GetMapping("")
    public List<Inventory> findAll() {
        return inventoryService.findAll();
    }

    @GetMapping("analyze")
    public List<CommodityChartVo> analyze(Integer type) {
        return recordService.analyzeCommodity(type);
    }

    //指定仓库id
    //查询某个仓库的库存情况
    @GetMapping("/warehouse/{id}")
    public List<Inventory> findByWarehouse(@PathVariable String id) {
        return inventoryService.findByWarehouseId(id);
    }

    //指定商品id
    //查询某个商品在所有仓库的库存
    @GetMapping("/commodity/{id}")
    public List<Inventory> findByCommodity(@PathVariable String id) {
        return inventoryService.findByCommodityId(id);
    }

    //指定仓库id
    //查询某个仓库库内商品的出库入库记录
    @GetMapping("/record/warehouse/{id}")
    public List<InventoryRecord> findRecordByWarehouse(@PathVariable String id) {
        return recordService.findAllByWarehouseId(id);
    }

    //指定商品id
    //查询某个商品在所有仓库出库入库记录
    @GetMapping("/record/commodity/{id}")
    public List<InventoryRecord> findRecordByCommodity(@PathVariable String id) {
        return recordService.findAllByCommodityId(id);
    }

    @PostMapping("/in")
    public InventoryRecord in(@RequestBody InventoryRecord record) {
        return recordService.in(record);
    }

    @PostMapping("/out")
    public InventoryRecord out(@RequestBody InventoryRecord record) throws Exception {
        return recordService.out(record);
    }


}

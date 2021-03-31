package com.example.api.service.impl;

import com.example.api.model.entity.Inventory;
import com.example.api.model.entity.InventoryRecord;
import com.example.api.model.vo.CommodityChartVo;
import com.example.api.repository.InventoryRecordRepository;
import com.example.api.repository.InventoryRepository;
import com.example.api.service.InventoryRecordService;
import com.example.api.utils.DataTimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InventoryRecordServiceImpl implements InventoryRecordService {

    @Resource
    private InventoryRepository inventoryRepository;

    @Resource
    private InventoryRecordRepository recordRepository;

    @Override
    public List<CommodityChartVo> analyzeCommodity(Integer type) {
        List<CommodityChartVo> result = new ArrayList<>();
        List<InventoryRecord> all = recordRepository.findAllByType(type);
        Map<String, Integer> map = new HashMap<>();
        for (InventoryRecord r : all) {
            if (map.containsKey(r.getName())) {
                map.put(r.getName(), map.get(r.getName()) + r.getCount());
            } else {
                map.put(r.getName(), r.getCount());
            }
        }
        for (String key : map.keySet()) {
            result.add(new CommodityChartVo(map.get(key), key));
        }
        return result;
    }

    @Override
    public List<InventoryRecord> findAllByWarehouseId(String wid) {
        return recordRepository.findAllByWid(wid);
    }

    @Override
    public List<InventoryRecord> findAllByCommodityId(String cid) {
        return recordRepository.findAllByCid(cid);
    }

    @Override
    public InventoryRecord out(InventoryRecord record) throws Exception {
        //查找当前商品在该仓库的库存
        Inventory inventory = inventoryRepository.findByWidAndCid(record.getWid(), record.getCid());
        //查询结果为空
        if (inventory == null) throw new Exception("仓库内不存在该商品");
        //比较库存
        if (inventory.getCount() < record.getCount()) throw new Exception("出库失败，库存数量不足");
        inventory.setCount(inventory.getCount() - record.getCount());
        inventoryRepository.save(inventory);
        record.setCreateAt(DataTimeUtil.getNowTimeString());
        record.setType(-1);
        return recordRepository.save(record);
    }

    @Override
    public InventoryRecord in(InventoryRecord record) {
        //查找当前商品在该仓库的库存
        Inventory inventory = inventoryRepository.findByWidAndCid(record.getWid(), record.getCid());
        //查询结果为空
        if (inventory == null) {
            //新建该商品库存信息
            inventory = new Inventory();
            inventory.setCid(record.getCid());
            inventory.setWid(record.getWid());
            inventory.setCount(0);
            inventory.setName(record.getName());
        }
        inventory.setCount(inventory.getCount() + record.getCount());
        inventoryRepository.save(inventory);
        record.setCreateAt(DataTimeUtil.getNowTimeString());
        record.setType(+1);
        return recordRepository.save(record);
    }

}

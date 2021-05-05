package com.example.api.service;

import com.example.api.model.entity.InventoryRecord;
import com.example.api.model.vo.CommodityChartVo;

import java.util.List;

public interface InventoryRecordService {

    //出入库排行统计
    List<CommodityChartVo> analyzeCommodity(Integer type);

    List<InventoryRecord> findAllByWarehouseId(String wid);

    List<InventoryRecord> findAllByCommodityId(String cid);

    //出库
    InventoryRecord out(InventoryRecord record) throws Exception;

    //入库
    InventoryRecord in(InventoryRecord record) throws Exception;

}

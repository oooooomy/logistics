package com.example.api.service;

import com.example.api.model.entity.InventoryRecord;

import java.util.List;

public interface InventoryRecordService {

    List<InventoryRecord> findAllByWarehouseId(String wid);

    List<InventoryRecord> findAllByCommodityId(String cid);

    //出库
    InventoryRecord out(InventoryRecord record) throws Exception;

    //入库
    InventoryRecord in(InventoryRecord record);

}

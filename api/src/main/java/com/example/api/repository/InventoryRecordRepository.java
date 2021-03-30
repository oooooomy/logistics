package com.example.api.repository;

import com.example.api.model.entity.InventoryRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRecordRepository extends JpaRepository<InventoryRecord, String> {

    List<InventoryRecord> findAllByWid(String wid);

    List<InventoryRecord> findAllByCid(String cid);

}

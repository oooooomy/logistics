package com.example.api.repository;

import com.example.api.model.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, String> {

    @Modifying
    @Transactional
    @Query("update Driver d set d.driving = ?1 where d.id = ?2")
    void updateDriving(boolean driving, String id);

    List<Driver> findAllByDriving(boolean driving);

}

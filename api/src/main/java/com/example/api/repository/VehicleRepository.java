package com.example.api.repository;

import com.example.api.model.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {

    @Modifying
    @Transactional
    @Query("update Vehicle v set v.driving = ?1 where v.id = ?2")
    void updateDriving(boolean driving, String id);

    List<Vehicle> findAllByDriving(boolean driving);

}

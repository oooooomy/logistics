package com.example.api.repository;

import com.example.api.model.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, String> {

    List<Sale> findAllByCompanyLike(String name);

}

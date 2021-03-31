package com.example.api.service;

import com.example.api.model.entity.Sale;

import java.util.List;

public interface SaleService {

    Sale save(Sale sale);

    List<Sale> findAll();

}

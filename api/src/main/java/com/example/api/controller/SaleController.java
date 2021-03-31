package com.example.api.controller;

import com.example.api.model.entity.Sale;
import com.example.api.service.SaleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/sale")
@PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN' ,'ROLE_SALE')")
public class SaleController {

    @Resource
    private SaleService saleService;

    @PostMapping("")
    public Sale save(@RequestBody Sale sale) {
        return saleService.save(sale);
    }

    @GetMapping("")
    public List<Sale> findAll() {
        return saleService.findAll();
    }

}

package com.example.api.controller;

import com.example.api.model.entity.Employee;
import com.example.api.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;

    @GetMapping("")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable String id) {
        return employeeService.findById(id);
    }

    @PostMapping("")
    public Employee save(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @PutMapping("")
    public void update(@RequestBody Employee employee) {
        employeeService.update(employee);
    }

    @DeleteMapping("")
    public void delete(String id) {
        employeeService.delete(id);
    }

}

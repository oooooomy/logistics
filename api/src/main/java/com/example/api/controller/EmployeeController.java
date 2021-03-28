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

    @GetMapping("/findAll")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/findById")
    public Employee findById(String id) {
        return employeeService.findById(id);
    }

    @PostMapping("/save")
    public Employee save(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @PutMapping("/update")
    public void update(@RequestBody Employee employee) {
        employeeService.update(employee);
    }

    @DeleteMapping("/delete")
    public void delete(String id){
        employeeService.delete(id);
    }

}

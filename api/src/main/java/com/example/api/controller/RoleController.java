package com.example.api.controller;

import com.alibaba.fastjson.JSON;
import com.example.api.model.enums.Role;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @GetMapping("")
    public List<String> list() {
        List<String> roles = new ArrayList<>();
        roles.add(JSON.toJSONString(Role.FIND));
        roles.add(JSON.toJSONString(Role.WAREHOUSE));
        return roles;
    }

}

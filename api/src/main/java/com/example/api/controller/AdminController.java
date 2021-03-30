package com.example.api.controller;

import com.example.api.model.dto.LoginDto;
import com.example.api.model.entity.Admin;
import com.example.api.service.AdminService;
import com.example.api.utils.JwtTokenUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    @PostMapping("")
    public Admin save(@RequestBody Admin admin) {
        return adminService.save(admin);
    }

    @PostMapping("/login")
    public Map<String, Object> loginByEmail(String type, @RequestBody LoginDto dto) throws Exception {
        Map<String, Object> map = new HashMap<>();
        Admin admin = type.equals("email") ? adminService.loginByEmail(dto) : adminService.loginByPassword(dto);
        String token = adminService.createToken(admin,
                dto.isRemember() ? JwtTokenUtil.REMEMBER_EXPIRATION_TIME : JwtTokenUtil.EXPIRATION_TIME);
        map.put("admin", admin);
        map.put("token", token);
        return map;
    }

    @GetMapping("/sendEmail")
    public void sendEmail(String email) throws Exception {
        adminService.sendEmail(email);
    }

}

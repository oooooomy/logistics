package com.example.api.controller;

import com.example.api.model.dto.LoginDto;
import com.example.api.model.entity.Admin;
import com.example.api.service.AdminService;
import com.example.api.utils.JwtTokenUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    @PostMapping("/save")
    public Admin save(@RequestBody Admin admin){
        return adminService.save(admin);
    }

    @GetMapping("/test")
    @PreAuthorize("hasAnyRole('')")
    public Map<String, String> test(HttpServletResponse response) {
        Map<String, String> map = new HashMap<>();
        map.put("token", "s");
        response.setHeader(JwtTokenUtil.TOKEN_HEADER, UUID.randomUUID().toString());
        return map;
    }

    @PostMapping("/login/username")
    public Map<String, Object> loginByUsername(@RequestBody LoginDto dto) throws Exception {
        Map<String, Object> map = new HashMap<>();
        Admin admin = adminService.loginByUsername(dto);
        String token = adminService.createToken(admin,
                dto.isRemember() ? JwtTokenUtil.REMEMBER_EXPIRATION_TIME : JwtTokenUtil.EXPIRATION_TIME);
        map.put("admin", admin);
        map.put("token", token);
        return map;
    }

    @PostMapping("/login/email")
    public Map<String, Object> loginByEmail(@RequestBody LoginDto dto) throws Exception {
        Map<String, Object> map = new HashMap<>();
        Admin admin = adminService.loginByEmail(dto);
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

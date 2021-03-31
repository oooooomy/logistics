package com.example.api.service;

import com.example.api.model.dto.LoginDto;
import com.example.api.model.entity.Admin;

import java.util.List;

public interface AdminService {

    Admin save(Admin admin) throws Exception;

    Admin findById(String id);

    void sendEmail(String email) throws Exception;

    Admin loginByPassword(LoginDto dto) throws Exception;

    Admin loginByEmail(LoginDto dto) throws Exception;

    List<Admin> findAll();

    //生成token
    String createToken(Admin admin, long exp);

    void delete(String id);
}

package com.example.api.model.dto;

import lombok.Data;

/**
 * 登录传输数据
 */
@Data
public class LoginDto {

    private String username;

    private String email;

    private String password;

    private String code;

    private boolean remember;

}

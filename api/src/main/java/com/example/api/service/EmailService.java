package com.example.api.service;

import org.springframework.mail.MailException;

public interface EmailService {

    //发送验证码
    void sendVerificationCode(String email) throws MailException;

    //检验验证码
    boolean checkVerificationCode(String email, String code);

}

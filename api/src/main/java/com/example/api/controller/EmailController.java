package com.example.api.controller;

import com.example.api.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Resource
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @GetMapping("/send")
    public String send() {
        SimpleMailMessage message = new SimpleMailMessage();
        //发件人
        message.setFrom(from);
        //收件人
        message.setTo("1729677089@qq.com");
        message.setSubject("验证码");
        message.setText("你的验证码为:  " + RandomUtil.next() + "  十五分钟内有效");
        try {
            mailSender.send(message);
            return "发送普通邮件成功";
        } catch (MailException e) {
            e.printStackTrace();
            return "邮件方失败";
        }
    }

}

package com.example.api;

import com.example.api.model.entity.Admin;
import com.example.api.model.enums.Role;
import com.example.api.service.AdminService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    @Resource
    private AdminService adminService;

    @Bean
    public CommandLineRunner commandLineRunner() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Admin admin = new Admin();
                admin.setEmail("1729677089@qq.com");
                admin.setPassword("000000");
                admin.setRoles(Role.ROLES);
                adminService.save(admin);
            }
        };
    }


}

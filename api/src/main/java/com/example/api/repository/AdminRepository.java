package com.example.api.repository;

import com.example.api.model.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {

    Admin findAdminByUsernameAndPassword(String username, String password);

    Admin findAdminByEmail(String email);

}

package com.example.api.model.entity;

import com.example.api.model.enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 管理员
 */
@Data
@Entity
@NoArgsConstructor
public class Admin {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String password;

    private String email;

    private String roles;

    private String createAt;

    public void setRoles(Role[] roles) {
        StringBuilder builder = new StringBuilder();
        for (Role role : roles) {
            builder.append(role.getValue()).append(";");
        }
        this.roles = builder.toString();
    }

}

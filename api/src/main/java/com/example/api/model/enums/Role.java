package com.example.api.model.enums;

import lombok.Getter;

@Getter
public enum Role {

    FIND("ROLE_FIND_ALL", "查找"),

    WAREHOUSE("WAREHOUSE", "仓库");

    private final String value;

    private final String description;

    Role(String value, String description) {
        this.value = value;
        this.description = description;
    }

}

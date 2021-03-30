package com.example.api.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Role {

    FIND("ROLE_FIND_ALL", "查找"),

    WAREHOUSE("WAREHOUSE", "仓库");

    private final String value;

    private final String description;

    public static final Role[] ROLES = {FIND, WAREHOUSE};

    Role(String value, String description) {
        this.value = value;
        this.description = description;
    }

}

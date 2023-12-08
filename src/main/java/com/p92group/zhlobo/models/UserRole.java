package com.p92group.zhlobo.models;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ROLE_MODERATOR,
    ROLE_CUSTOMER,
    ROLE_SELLER,
    ROLE_COURIER;

    @Override
    public String getAuthority() {
        return name();
    }
}
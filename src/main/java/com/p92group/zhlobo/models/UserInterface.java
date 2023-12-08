package com.p92group.zhlobo.models;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserInterface extends UserDetails {
    Long getId();
    void setId(Long id);
    String getEmail();
    void setEmail(String login);
    String getPassword();
    void setPassword(String password);
}

package com.p92group.zhlobo.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.Remove;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
@Data
@Entity
@Table(name = "couriers")
public class Courier implements UserInterface {
    private static final Collection<? extends GrantedAuthority> role = Collections.singleton(UserRole.ROLE_COURIER);


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fullname", nullable = false)
    private String fullname;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "courier_type")
    @Enumerated(EnumType.STRING)
    private CourierType courierType;

    @ManyToOne
    private City city;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Balance balance;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

//    @OneToMany
//    @JoinTable(name = "orders")
//    private List<Order> orders;
}

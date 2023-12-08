package com.p92group.zhlobo.models;

import jakarta.persistence.*;
import lombok.Data;
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
@Table(name = "sellers")
public class Seller implements UserInterface {
    private static final Collection<? extends GrantedAuthority> role = Collections.singleton(UserRole.ROLE_SELLER);


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

    @Column(name = "address")
    private String address;

    @Column(name = "company_name")
    private String companyName;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Balance balance;

    @ManyToOne
    private City city;

    @ManyToOne
    private Image image;

    @Column(name = "seller_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private SellerType sellerType;

    public Seller(){

    }
    public Seller(String fullname, String email, String phoneNumber, String password, String address, String companyName, Image image, SellerType sellerType, City city) {
        this.fullname = fullname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.address = address;
        this.companyName = companyName;
        this.image = image;
        this.sellerType = sellerType;
        this.city = city;
    }

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
}

package com.p92group.zhlobo.services;

import com.p92group.zhlobo.models.UserInterface;
import com.p92group.zhlobo.repos.CouriersRepo;
import com.p92group.zhlobo.repos.CustomersRepo;
import com.p92group.zhlobo.repos.ModeratorRepo;
import com.p92group.zhlobo.repos.SellerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private SellerRepo sellerRepo;
    @Autowired
    private CustomersRepo customersRepo;
    @Autowired
    private CouriersRepo couriersRepo;
    @Autowired
    private ModeratorRepo moderatorRepo;

    public UserDetails loadUserByUsername(String email) {
        UserInterface userUnit = findByUsername(email);

        return new org.springframework.security.core.userdetails.User(
                userUnit.getUsername(),
                userUnit.getPassword(),
                userUnit.getAuthorities()
        );
    }

    public UserInterface getUserByUsername(String email) {
        UserInterface userUnit = findByUsername(email);
        return userUnit;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return List.of(new SimpleGrantedAuthority(role));
    }

    public UserInterface findByUsername(String email) {
        UserInterface userUnit = null;

        userUnit = moderatorRepo.findByEmail(email);
        if (userUnit == null)
            userUnit = sellerRepo.findByEmail(email);
        if (userUnit == null)
            userUnit = customersRepo.findByEmail(email);
        if (userUnit == null)
            userUnit = couriersRepo.findByEmail(email);

        return userUnit;
    }
}

package com.p92group.zhlobo.services;


import com.p92group.zhlobo.models.Balance;
import com.p92group.zhlobo.models.Customer;
import com.p92group.zhlobo.models.Moderator;
import com.p92group.zhlobo.repos.ModeratorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModeratorService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ModeratorRepo moderatorRepo;
    
    public void save(Moderator moderator) {
        moderator.setPassword(passwordEncoder.encode(moderator.getPassword()));

        moderatorRepo.saveAndFlush(moderator);
    }

    public List<Moderator> getAll() {
        return moderatorRepo.findAll();
    }

    public Moderator getById(Long id) {
        return moderatorRepo.findById(id).orElse(null);
    }

    public void delete(String idIn){
        Moderator moderator = moderatorRepo.findById(Long.parseLong(idIn)).orElse(null);
        if(moderator != null) {
            moderatorRepo.delete(moderator);
        }
    }
}

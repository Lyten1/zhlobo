package com.p92group.zhlobo.services;

import com.p92group.zhlobo.models.Balance;
import com.p92group.zhlobo.repos.BalanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BalanceService{

    @Autowired
    private BalanceRepo balanceRepo;

    public void save(Balance balance) {
        balanceRepo.saveAndFlush(balance);
    }

    public List<Balance> getAll() {
        return balanceRepo.findAll();
    }

    public Balance getById(Long id) {
        return balanceRepo.findById(id).orElse(null);
    }


    public void update(String idIn, Balance balance) {
        Balance newBalance = balanceRepo.findById(Long.parseLong(idIn)).orElse(null);
        if(newBalance != null) {
            System.out.println("sa");
            if (!newBalance.equals(balance)) {
                newBalance.updateData(balance);
                balanceRepo.saveAndFlush(newBalance);
            }
        }
    }

    public void delete(String idIn){
        Balance balance = balanceRepo.findById(Long.parseLong(idIn)).orElse(null);
        if(balance != null) {
            balanceRepo.delete(balance);
        }
    }


}


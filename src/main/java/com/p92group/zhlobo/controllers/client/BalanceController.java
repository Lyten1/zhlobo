package com.p92group.zhlobo.controllers.client;

import com.p92group.zhlobo.models.Balance;
import com.p92group.zhlobo.services.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@Controller
@RequestMapping("/balance")
public class BalanceController {



    @Autowired
    private BalanceService balanceService;

    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Balance balance){
        balanceService.save(balance);
    }

    @GetMapping(value = "/get/{request_data}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.FOUND)
    public Balance getById(@PathVariable("request_data") String requestData){
        return  balanceService.getById(Long.parseLong(requestData));
    }

    @GetMapping(value = "/get/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.FOUND)
    public List<Balance> getAll(){
        return balanceService.getAll();
    }

    @PatchMapping(value = "/update/{request_data}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("request_data") String requestData, @RequestBody Balance new_balance){
        balanceService.update(requestData, new_balance);
    }

    @DeleteMapping (value = "/delete/{request_data}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("request_data") String requestData){
        balanceService.delete(requestData);
    }

}
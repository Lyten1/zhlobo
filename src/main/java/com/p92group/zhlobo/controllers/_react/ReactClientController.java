package com.p92group.zhlobo.controllers._react;

import com.p92group.zhlobo.models.Seller;
import com.p92group.zhlobo.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController
@RequestMapping("/react")
@CrossOrigin(origins = "http://localhost:3000")
public class ReactClientController {
    @Autowired
    private SellerService sellerService;

    @GetMapping
    public ResponseEntity<List<Seller>> getAllSellers(){
        return new ResponseEntity<List<Seller>>(sellerService.getAll(), HttpStatus.OK);
    }
}

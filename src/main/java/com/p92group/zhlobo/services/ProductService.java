package com.p92group.zhlobo.services;

import com.p92group.zhlobo.models.Image;
import com.p92group.zhlobo.models.Product;
import com.p92group.zhlobo.repos.ImageRepo;
import com.p92group.zhlobo.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;
//    @Autowired
//    private ImageRepo imageRepo;
    @Autowired
    private ImageService imageService;

    public Product smartCreate(String title, String price, String imageUrl){
        Product product = new Product();

        product.setTitle(title);
        product.setPrice(new BigDecimal(price.replace(',', '.')));
//        Image image = new Image();
//        image.setUrl(imageUrl);
//        product.setImage(image);
//        imageRepo.saveAndFlush(image);
        product.setImage(imageService.smartSave(imageUrl));

        return product;
    }
}

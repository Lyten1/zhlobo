package com.p92group.zhlobo.services;

import com.p92group.zhlobo.models.Image;
import com.p92group.zhlobo.repos.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ImageService {
    @Autowired
    private ImageRepo imageRepo;

    public Image smartSave(String url){
        var imageFound = imageRepo.findAll().stream().filter(x -> Objects.equals(x.getUrl(), url)).toList();

        if (imageFound.isEmpty()){
            Image image = new Image();

            image.setUrl(url);

            imageRepo.saveAndFlush(image);

            return image;
        }

        return imageFound.get(0);
    }


}

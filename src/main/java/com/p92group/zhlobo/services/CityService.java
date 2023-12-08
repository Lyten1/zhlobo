package com.p92group.zhlobo.services;

import com.p92group.zhlobo.models.City;
import com.p92group.zhlobo.models.Image;
import com.p92group.zhlobo.repos.CityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CityService {
    @Autowired
    private CityRepo cityRepo;

    public City smartSave(String cityTitle){
        var cityFound = cityRepo.findAll().stream().filter(x -> Objects.equals(x.getTitle(), cityTitle)).toList();

        if (cityFound.isEmpty()){
            City city = new City();

            city.setTitle(cityTitle);

            cityRepo.saveAndFlush(city);

            return city;
        }

        return cityFound.get(0);
    }
}

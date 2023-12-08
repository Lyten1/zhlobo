package com.p92group.zhlobo.controllers.moderator;

import com.p92group.zhlobo.models.City;
import com.p92group.zhlobo.repos.CityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/moderator")
public class ModeratorControllerCities {
    @Autowired
    private CityRepo cityRepo;

    @GetMapping("/cities")
    public String citiesView(Model model) {
        model.addAttribute("cities", cityRepo.findAll());

        return "moderator/forms-view/view-cities";
    }

    @GetMapping("/cities/add")
    public String citiesAdd() {
        return "moderator/forms-add/add-city";
    }

    @PostMapping("/cities/add")
    public String citiesCreate(@ModelAttribute City city) {
        cityRepo.save(city);

        return "redirect:/moderator/cities";
    }

    @GetMapping("/cities/edit/{id}")
    public String citiesEdit(@PathVariable Long id, Model model) {
        City city = cityRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid City ID: " + id));

        model.addAttribute("city", city);

        return "moderator/forms-edit/edit-city";
    }

    @PostMapping("/cities/edit")
    public String citiesUpdate(@ModelAttribute City city) {
        cityRepo.save(city);

        return "redirect:/moderator/cities";
    }

}

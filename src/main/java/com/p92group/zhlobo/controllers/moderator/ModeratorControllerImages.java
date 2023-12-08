package com.p92group.zhlobo.controllers.moderator;

import com.p92group.zhlobo.models.Image;
import com.p92group.zhlobo.repos.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/moderator")
public class ModeratorControllerImages {
    @Autowired
    private ImageRepo imageRepo;

    @GetMapping("/images")
    public String imagesView(Model model) {
        model.addAttribute("images", imageRepo.findAll());

        return "moderator/forms-view/view-images";
    }

    @GetMapping("/images/add")
    public String imagesAdd(){
        return "moderator/forms-add/add-image";
    }

    @PostMapping("/images/add")
    public String imagesCreate(@ModelAttribute Image image){
        imageRepo.save(image);

        return "redirect:/moderator/images";
    }

    @GetMapping("/images/edit/{id}")
    public String imagesEdit(@PathVariable Long id, Model model) {
        Image image = imageRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Image ID: " + id));

        model.addAttribute("image", image);

        return "moderator/forms-edit/edit-image";
    }

    @PostMapping("/images/edit")
    public String imagesUpdate(@ModelAttribute Image image) {
        imageRepo.save(image);

        return "redirect:/moderator/images";
    }
}

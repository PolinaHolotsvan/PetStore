package com.example.petstore.controllers;

import com.example.petstore.models.speciesModels.SpeciesCreateModel;
import com.example.petstore.models.speciesModels.SpeciesUpdateModel;
import com.example.petstore.services.SpeciesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/species")
public class SpeciesController {
    public final SpeciesService speciesService;

    public SpeciesController(SpeciesService speciesService) {
        this.speciesService = speciesService;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam UUID id) {
        speciesService.delete(id);
        return "redirect:/species/getAll";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute SpeciesUpdateModel model) {
        speciesService.update(model);
        return "redirect:/species/getAll";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute SpeciesCreateModel model) {
        speciesService.create(model);
        return "redirect:/species/getAll";
    }

    @GetMapping("/showAddForm")
    public String showAddForm(Model page){
        page.addAttribute("species", new SpeciesCreateModel());
        return "SpeciesPages/SpeciesCreatePage";
    }

    @GetMapping("/showUpdateForm")
    public String showUpdateForm(Model page, @RequestParam UUID id){
        SpeciesUpdateModel model=new SpeciesUpdateModel();
        model.setId(id);
        page.addAttribute("species", model);
        return "SpeciesPages/SpeciesUpdatePage";
    }


    @GetMapping("/getAll")
    public String getAll(Model page) {
        page.addAttribute("species", speciesService.getAll());
        return "SpeciesPages/SpeciesViewPage";
    }
}

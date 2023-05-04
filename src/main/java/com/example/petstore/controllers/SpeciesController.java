package com.example.petstore.controllers;

import com.example.petstore.models.speciesModels.SpeciesCreateModel;
import com.example.petstore.models.speciesModels.SpeciesUpdateModel;
import com.example.petstore.services.SpeciesService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
    public String update(@ModelAttribute("species") @Valid SpeciesUpdateModel model, BindingResult result) {
        String err = speciesService.isUnique2(model.getName(), model.getId());
        if (!err.isEmpty()) {
            var error = new FieldError("model", "name", err);
            result.addError(error);
        }
        if(result.hasErrors()){
            return "SpeciesPages/SpeciesUpdatePage";
        }
        speciesService.update(model);
        return "redirect:/species/getAll";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("species") @Valid SpeciesCreateModel model, BindingResult result) {
        String err = speciesService.isUnique(model.getName());
        if (!err.isEmpty()) {
            var error = new FieldError("model", "name", err);
            result.addError(error);
        }
        if(result.hasErrors()){
            return "SpeciesPages/SpeciesUpdatePage";
        }
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

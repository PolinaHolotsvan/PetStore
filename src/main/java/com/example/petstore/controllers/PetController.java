package com.example.petstore.controllers;

import com.example.petstore.models.petModels.PetCreateModel;
import com.example.petstore.models.petModels.PetUpdateModel;
import com.example.petstore.services.PetService;
import com.example.petstore.services.PetStoreService;
import com.example.petstore.services.SpeciesService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/pet")
public class PetController {
    private final PetService petService;
    private final PetStoreService petStoreService;
    private final SpeciesService speciesService;

    public PetController(PetService petService, PetStoreService petStoreService, SpeciesService speciesService) {
        this.petService = petService;
        this.petStoreService = petStoreService;
        this.speciesService = speciesService;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam UUID id) {
        petService.delete(id);
        return "redirect:/pet/getAll";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("pet") @Valid PetUpdateModel model, BindingResult result) {
        if(result.hasErrors()){
            return "PetPages/PetUpdatePage";
        }
        petService.update(model);
        return "redirect:/pet/getAll";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("pet") @Valid PetCreateModel model, BindingResult result) {
        if(result.hasErrors()){
            return "PetPages/PetCreatePage";
        }
        petService.create(model);
        return "redirect:/pet/getAll";
    }

    @GetMapping("/showAddForm")
    public String showAddForm(Model page){
        page.addAttribute("pet", new PetCreateModel());
        page.addAttribute("petStores", petStoreService.getAll());
        page.addAttribute("species", speciesService.getAll());
        return "PetPages/PetCreatePage";
    }

    @GetMapping("/showUpdateForm")
    public String showUpdateForm(Model page, @RequestParam UUID id){
        PetUpdateModel model=new PetUpdateModel();
        model.setId(id);
        page.addAttribute("pet", model);
        return "PetPages/PetUpdatePage";
    }

    @GetMapping("/getAll")
    public String getAll(Model page) {
        page.addAttribute("pets", petService.getAll());
        return "PetPages/PetViewPage";
    }

}

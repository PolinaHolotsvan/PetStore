package com.example.petstore.controllers;

import com.example.petstore.models.petStoreModels.PetStoreCreateModel;
import com.example.petstore.models.petStoreModels.PetStoreUpdateModel;
import com.example.petstore.services.DirectorService;
import com.example.petstore.services.PetStoreService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/petStore")
public class PetStoreController {
    private final PetStoreService petStoreService;
    private final DirectorService directorService;

    public PetStoreController(PetStoreService petStoreService, DirectorService directorService) {
        this.petStoreService = petStoreService;
        this.directorService = directorService;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("petStore") @Valid PetStoreCreateModel model, BindingResult result, Model page) {
        String err = petStoreService.isUnique(model.getName());
        if (!err.isEmpty()) {
            var error = new FieldError("model", "name", err);
            result.addError(error);
        }
        if(result.hasErrors()){
            page.addAttribute("directors", directorService.getAllFree());
            return "PetStorePages/PetStoreCreatePage";
        }
        petStoreService.create(model);
        return "redirect:/petStore/getAll";
    }

    @GetMapping("/getAll")
    public String getAll(Model page) {
        page.addAttribute("petStores", petStoreService.getAll());
        return "PetStorePages/PetStoreViewPage";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam UUID id) {
        petStoreService.delete(id);
        return "redirect:/petStore/getAll";
    }

    @GetMapping("/showAddForm")
    public String showAddForm(Model page){
        page.addAttribute("petStore", new PetStoreCreateModel());
        page.addAttribute("directors", directorService.getAllFree());
        return "PetStorePages/PetStoreCreatePage";
    }

    @GetMapping("/showUpdateForm")
    public String showUpdateForm(Model page, @RequestParam UUID id){
        PetStoreUpdateModel model=new PetStoreUpdateModel();
        model.setId(id);
        page.addAttribute("petStore", model);
        return "PetStorePages/PetStoreUpdatePage";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("petStore") @Valid PetStoreUpdateModel model, BindingResult result) {
        String err = petStoreService.isUnique2(model.getName(), model.getId());
        if (!err.isEmpty()) {
            var error = new FieldError("model", "name", err);
            result.addError(error);
        }
        if(result.hasErrors()){
            return "PetStorePages/PetStoreUpdatePage";
        }
        petStoreService.update(model);
        return "redirect:/petStore/getAll";
    }
}

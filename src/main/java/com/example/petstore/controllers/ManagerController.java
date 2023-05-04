package com.example.petstore.controllers;

import com.example.petstore.models.managerModels.ManagerCreateModel;
import com.example.petstore.models.managerModels.ManagerUpdateModel;
import com.example.petstore.services.ManagerService;
import com.example.petstore.services.PetStoreService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/manager")
public class ManagerController {
    private final PetStoreService petStoreService;
    private final ManagerService managerService;

    public ManagerController(PetStoreService petStoreService, ManagerService managerService) {
        this.petStoreService = petStoreService;
        this.managerService = managerService;
    }


    @PostMapping("/create")
    public String create(@ModelAttribute("manager") @Valid ManagerCreateModel model, BindingResult result, Model page) {
        String err = managerService.isUnique(model.getName());
        if (!err.isEmpty()) {
            var error = new FieldError("model", "name", err);
            result.addError(error);
        }
        if(result.hasErrors()){
            page.addAttribute("petStores", petStoreService.getAll());
            return "ManagerPages/ManagerCreatePage";
        }
        managerService.create(model);
        return "redirect:/manager/getAll";
    }

    @GetMapping("/getAll")
    public String getAll(Model page) {
        page.addAttribute("managers", managerService.getAll());
        return "ManagerPages/ManagerViewPage";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam UUID id) {
        managerService.delete(id);
        return "redirect:/manager/getAll";
    }

    @GetMapping("/showAddForm")
    public String showAddForm(Model page){
        page.addAttribute("manager", new ManagerCreateModel());
        page.addAttribute("petStores", petStoreService.getAll());
        return "ManagerPages/ManagerCreatePage";
    }

    @GetMapping("/showUpdateForm")
    public String showUpdateForm(Model page, @RequestParam UUID id){
        ManagerUpdateModel model=new ManagerUpdateModel();
        model.setId(id);
        page.addAttribute("manager", model);
        return "ManagerPages/ManagerUpdatePage";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("manager") @Valid ManagerUpdateModel model, BindingResult result) {
        String err = managerService.isUnique2(model.getName(), model.getId());
        if (!err.isEmpty()) {
            var error = new FieldError("model", "name", err);
            result.addError(error);
        }
        if(result.hasErrors()){
            return "ManagerPages/ManagerUpdatePage";
        }
        managerService.update(model);
        return "redirect:/manager/getAll";
    }

}

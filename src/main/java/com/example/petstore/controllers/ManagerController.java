package com.example.petstore.controllers;

import com.example.petstore.models.managerModels.ManagerCreateModel;
import com.example.petstore.models.managerModels.ManagerUpdateModel;
import com.example.petstore.repositories.ManagerRepository;
import com.example.petstore.repositories.PetStoreRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/manager")
public class ManagerController {
    private final PetStoreRepository petStoreRepository;
    private final ManagerRepository managerRepository;

    public ManagerController(PetStoreRepository petStoreRepository, ManagerRepository managerRepository) {
        this.petStoreRepository = petStoreRepository;
        this.managerRepository = managerRepository;
    }


    @PostMapping("/create")
    public String create(@ModelAttribute ManagerCreateModel model) {
        managerRepository.create(model);
        return "redirect:/manager/getAll";
    }

    @GetMapping("/getAll")
    public String getAll(Model page) {
        page.addAttribute("managers", managerRepository.getAll());
        return "ManagerPages/ManagerViewPage";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam UUID id) {
        managerRepository.delete(id);
        return "redirect:/manager/getAll";
    }

    @GetMapping("/showAddForm")
    public String showAddForm(Model page){
        page.addAttribute("manager", new ManagerCreateModel());
        page.addAttribute("petStores", petStoreRepository.getAll());
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
    public String update(@ModelAttribute ManagerUpdateModel model) {
        managerRepository.update(model);
        return "redirect:/manager/getAll";
    }

}

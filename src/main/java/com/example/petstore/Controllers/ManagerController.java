package com.example.petstore.Controllers;

import com.example.petstore.Models.ManagerModels.ManagerCreateModel;
import com.example.petstore.Models.ManagerModels.ManagerUpdateModel;
import com.example.petstore.Services.ManagerService;
import com.example.petstore.Services.PetStoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String create(@ModelAttribute ManagerCreateModel model) {
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
    public String update(@ModelAttribute ManagerUpdateModel model) {
        managerService.update(model);
        return "redirect:/manager/getAll";
    }

}

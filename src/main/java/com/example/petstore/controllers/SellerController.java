package com.example.petstore.controllers;

import com.example.petstore.models.sellerModels.SellerCreateModel;
import com.example.petstore.models.sellerModels.SellerUpdateModel;
import com.example.petstore.repositories.PetStoreRepository;
import com.example.petstore.repositories.SellerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/seller")
public class SellerController {
    public final SellerRepository sellerRepository;
    public final PetStoreRepository petStoreRepository;


    public SellerController(SellerRepository sellerRepository, PetStoreRepository petStoreRepository) {
        this.sellerRepository = sellerRepository;
        this.petStoreRepository = petStoreRepository;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam UUID id) {
        sellerRepository.delete(id);
        return "redirect:/seller/getAll";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute SellerUpdateModel model) {
        sellerRepository.update(model);
        return "redirect:/seller/getAll";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute SellerCreateModel model) {
        sellerRepository.create(model);
        return "redirect:/seller/getAll";
    }

    @GetMapping("/showAddForm")
    public String showAddForm(Model page){
        page.addAttribute("seller", new SellerCreateModel());
        return "SellerPages/SellerCreatePage";
    }

    @GetMapping("/showUpdateForm")
    public String showUpdateForm(Model page, @RequestParam UUID id){
        SellerUpdateModel model=new SellerUpdateModel();
        model.setId(id);
        page.addAttribute("seller", model);
        return "SellerPages/SellerUpdatePage";
    }


    @GetMapping("/getAll")
    public String getAll(Model page) {
        page.addAttribute("sellers", sellerRepository.getAll());
        return "SellerPages/SellerViewPage";
    }


    @GetMapping("/showEmploymentFormCreate")
    public String showEmploymentFormCreate(Model page, @RequestParam UUID id){
        page.addAttribute("sellerId", id);
        page.addAttribute("petStoreId", UUID.randomUUID());
        page.addAttribute("petStores", petStoreRepository.getAllUnemployed(id));
        return "SellerPages/EmploymentFormCreate";
    }

    @GetMapping("/showEmploymentFormDelete")
    public String showEmploymentFormDelete(Model page, @RequestParam UUID id){
        page.addAttribute("sellerId", id);
        page.addAttribute("petStoreId", UUID.randomUUID());
        page.addAttribute("petStores", petStoreRepository.getAllEmployed(id));
        return "SellerPages/EmploymentFormDelete";
    }

    @GetMapping("/removeSellerFromPetStore")
    public String removeSellerFromPetStore(@RequestParam UUID petStoreId, @RequestParam UUID sellerId) {
        sellerRepository.removeSellerFromPetStore(petStoreId, sellerId);
        return "redirect:/seller/getAll";
    }

    @GetMapping("/assignSellerToPetStore")
    public String assignSellerToPetStore(@RequestParam UUID petStoreId, @RequestParam UUID sellerId) {
        sellerRepository.assignSellerToPetStore(petStoreId, sellerId);
        return "redirect:/seller/getAll";
    }

}

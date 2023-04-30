package com.example.petstore.Controllers;

import com.example.petstore.Models.SellerModels.SellerCreateModel;
import com.example.petstore.Models.SellerModels.SellerUpdateModel;
import com.example.petstore.Models.SpeciesModels.SpeciesCreateModel;
import com.example.petstore.Models.SpeciesModels.SpeciesUpdateModel;
import com.example.petstore.Services.PetStoreService;
import com.example.petstore.Services.SellerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/seller")
public class SellerController {
    public final SellerService sellerService;
    public final PetStoreService petStoreService;


    public SellerController(SellerService sellerService, PetStoreService petStoreService) {
        this.sellerService = sellerService;
        this.petStoreService = petStoreService;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam UUID id) {
        sellerService.delete(id);
        return "redirect:/seller/getAll";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute SellerUpdateModel model) {
        sellerService.update(model);
        return "redirect:/seller/getAll";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute SellerCreateModel model) {
        sellerService.create(model);
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
        page.addAttribute("sellers", sellerService.getAll());
        return "SellerPages/SellerViewPage";
    }
}

package com.example.petstore.controllers;

import com.example.petstore.models.goodsModels.GoodsCreateModel;
import com.example.petstore.models.goodsModels.GoodsUpdateModel;
import com.example.petstore.services.GoodsService;
import com.example.petstore.services.PetStoreService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/goods")
public class GoodsController {
    private final PetStoreService petStoreService;
    private final GoodsService goodsService;

    public GoodsController(PetStoreService petStoreService, GoodsService goodsService) {
        this.petStoreService = petStoreService;
        this.goodsService = goodsService;
    }


    @PostMapping("/create")
    public String create(@ModelAttribute("goods") @Valid GoodsCreateModel model, BindingResult result, Model page) {
        if(result.hasErrors()){
            page.addAttribute("petStores", petStoreService.getAll());
            return "GoodsPages/GoodsCreatePage";
        }
        goodsService.create(model);
        return "redirect:/goods/getAll";
    }

    @GetMapping("/getAll")
    public String getAll(Model page) {
        page.addAttribute("goods", goodsService.getAll());
        return "GoodsPages/GoodsViewPage";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam UUID id) {
        goodsService.delete(id);
        return "redirect:/goods/getAll";
    }

    @GetMapping("/showAddForm")
    public String showAddForm(Model page){
        page.addAttribute("goods", new GoodsCreateModel());
        page.addAttribute("petStores", petStoreService.getAll());
        return "GoodsPages/GoodsCreatePage";
    }

    @GetMapping("/showUpdateForm")
    public String showUpdateForm(Model page, @RequestParam UUID id){
        GoodsUpdateModel model=new GoodsUpdateModel();
        model.setId(id);
        page.addAttribute("goods", model);
        return "GoodsPages/GoodsUpdatePage";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("goods") @Valid GoodsUpdateModel model, BindingResult result) {
        if(result.hasErrors()){
            return "GoodsPages/GoodsUpdatePage";
        }
        goodsService.update(model);
        return "redirect:/goods/getAll";
    }
}

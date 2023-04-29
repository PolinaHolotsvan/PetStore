package com.example.petstore.Controllers;

import com.example.petstore.Models.GoodsModels.GoodsCreateModel;
import com.example.petstore.Models.GoodsModels.GoodsUpdateModel;
import com.example.petstore.Models.PetStoreModels.PetStoreCreateModel;
import com.example.petstore.Services.GoodsService;
import com.example.petstore.Services.PetStoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String create(@ModelAttribute GoodsCreateModel model) {
        goodsService.create(model);
        return "redirect:/goods/getAll";
    }

    @GetMapping("/getAll")
    public String getAll(Model page) {
        page.addAttribute("goods", goodsService.getAll());
        return "GoodsPages/GoodsViewPage";
    }

    /*@GetMapping("/getById")
    public PetStoreViewModel getById(@RequestParam UUID id) {
        EntityManager em = entityManagerFactory.createEntityManager();

        PetStore petStore = em.find(PetStore.class, id);
        PetStoreViewModel model = modelMapper.map(petStore, PetStoreViewModel.class);

        model.convertPets(petStore.getPets());
        model.convertGoods(petStore.getGoods());
        model.convertManagers(petStore.getManagers());
        model.convertSellers(petStore.getSellers());
        model.convertDirector(petStore.getDirector());

        return model;
    }*/

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
    public String update(@ModelAttribute GoodsUpdateModel model) {
        goodsService.update(model);
        return "redirect:/goods/getAll";
    }
}

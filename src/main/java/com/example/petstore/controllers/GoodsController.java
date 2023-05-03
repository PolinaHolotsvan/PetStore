package com.example.petstore.controllers;

import com.example.petstore.models.goodsModels.GoodsCreateModel;
import com.example.petstore.models.goodsModels.GoodsUpdateModel;
import com.example.petstore.repositories.GoodsRepository;
import com.example.petstore.repositories.PetStoreRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/goods")
public class GoodsController {
    private final PetStoreRepository petStoreRepository;
    private final GoodsRepository goodsRepository;

    public GoodsController(PetStoreRepository petStoreRepository, GoodsRepository goodsRepository) {
        this.petStoreRepository = petStoreRepository;
        this.goodsRepository = goodsRepository;
    }


    @PostMapping("/create")
    public String create(@ModelAttribute GoodsCreateModel model) {
        goodsRepository.create(model);
        return "redirect:/goods/getAll";
    }

    @GetMapping("/getAll")
    public String getAll(Model page) {
        page.addAttribute("goods", goodsRepository.getAll());
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
        goodsRepository.delete(id);
        return "redirect:/goods/getAll";
    }

    @GetMapping("/showAddForm")
    public String showAddForm(Model page){
        page.addAttribute("goods", new GoodsCreateModel());
        page.addAttribute("petStores", petStoreRepository.getAll());
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
        goodsRepository.update(model);
        return "redirect:/goods/getAll";
    }
}

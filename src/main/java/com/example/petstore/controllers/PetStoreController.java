package com.example.petstore.controllers;

import com.example.petstore.models.petStoreModels.PetStoreCreateModel;
import com.example.petstore.models.petStoreModels.PetStoreUpdateModel;
import com.example.petstore.repositories.DirectorRepository;
import com.example.petstore.repositories.PetStoreRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/petStore")
public class PetStoreController {
    private final PetStoreRepository petStoreRepository;
    private final DirectorRepository directorRepository;

    public PetStoreController(PetStoreRepository petStoreRepository, DirectorRepository directorRepository) {
        this.petStoreRepository = petStoreRepository;
        this.directorRepository = directorRepository;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute PetStoreCreateModel model) {
        petStoreRepository.create(model);
        return "redirect:/petStore/getAll";
    }

    @GetMapping("/getAll")
    public String getAll(Model page) {
        page.addAttribute("petStores", petStoreRepository.getAll());
        return "PetStorePages/PetStoreViewPage";
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
        petStoreRepository.delete(id);
        return "redirect:/petStore/getAll";
    }

    @GetMapping("/showAddForm")
    public String showAddForm(Model page){
        page.addAttribute("petStore", new PetStoreCreateModel());
        page.addAttribute("directors", directorRepository.getAllFree());
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
    public String update(@ModelAttribute PetStoreUpdateModel model) {
        petStoreRepository.update(model);
        return "redirect:/petStore/getAll";
    }
}

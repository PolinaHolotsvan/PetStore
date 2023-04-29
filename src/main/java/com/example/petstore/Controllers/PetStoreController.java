package com.example.petstore.Controllers;

import com.example.petstore.Entities.PetStore;
import com.example.petstore.Models.DirectorModels.DirectorUpdateModel;
import com.example.petstore.Models.PetStoreModels.PetStoreCreateModel;
import com.example.petstore.Models.PetStoreModels.PetStoreUpdateModel;
import com.example.petstore.Services.DirectorService;
import com.example.petstore.Services.PetStoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String create(@ModelAttribute PetStoreCreateModel model) {
        petStoreService.create(model);
        return "redirect:/petStore/getAll";
    }

    @GetMapping("/getAll")
    public String getAll(Model page) {
        page.addAttribute("petStores", petStoreService.getAll());
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
    public String update(@ModelAttribute PetStoreUpdateModel model) {
        petStoreService.update(model);
        return "redirect:/petStore/getAll";
    }
}

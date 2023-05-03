package com.example.petstore.controllers;

import com.example.petstore.models.petModels.PetCreateModel;
import com.example.petstore.models.petModels.PetUpdateModel;
import com.example.petstore.repositories.PetRepository;
import com.example.petstore.repositories.PetStoreRepository;
import com.example.petstore.repositories.SpeciesRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/pet")
public class PetController {
    private final PetRepository petRepository;
    private final PetStoreRepository petStoreRepository;
    private final SpeciesRepository speciesRepository;

    public PetController(PetRepository petRepository, PetStoreRepository petStoreRepository, SpeciesRepository speciesRepository) {
        this.petRepository = petRepository;
        this.petStoreRepository = petStoreRepository;
        this.speciesRepository = speciesRepository;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam UUID id) {
        petRepository.delete(id);
        return "redirect:/pet/getAll";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute PetUpdateModel model) {
        petRepository.update(model);
        return "redirect:/pet/getAll";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute PetCreateModel model) {
        petRepository.create(model);
        return "redirect:/pet/getAll";
    }

    @GetMapping("/showAddForm")
    public String showAddForm(Model page){
        page.addAttribute("pet", new PetCreateModel());
        page.addAttribute("petStores", petStoreRepository.getAll());
        page.addAttribute("species", speciesRepository.getAll());
        return "PetPages/PetCreatePage";
    }

    @GetMapping("/showUpdateForm")
    public String showUpdateForm(Model page, @RequestParam UUID id){
        PetUpdateModel model=new PetUpdateModel();
        model.setId(id);
        page.addAttribute("pet", model);
        return "PetPages/PetUpdatePage";
    }

    @GetMapping("/getAll")
    public String getAll(Model page) {
        page.addAttribute("pets", petRepository.getAll());
        return "PetPages/PetViewPage";
    }

    /*@GetMapping("/getById")
    public PetViewModel getById(@RequestParam UUID id) {
        EntityManager em = entityManagerFactory.createEntityManager();

        Pet pet = em.find(Pet.class, id);
        PetViewModel model = modelMapper.map(pet, PetViewModel.class);

        model.convertSpecies(pet.getSpecies());
        model.convertPetStore(pet.getPetStore());

        return model;
    }*/

}

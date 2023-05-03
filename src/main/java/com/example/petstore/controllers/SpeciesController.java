package com.example.petstore.controllers;

import com.example.petstore.models.speciesModels.SpeciesCreateModel;
import com.example.petstore.models.speciesModels.SpeciesUpdateModel;
import com.example.petstore.repositories.SpeciesRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/species")
public class SpeciesController {
    public final SpeciesRepository speciesRepository;

    public SpeciesController(SpeciesRepository speciesRepository) {
        this.speciesRepository = speciesRepository;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam UUID id) {
        speciesRepository.delete(id);
        return "redirect:/species/getAll";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute SpeciesUpdateModel model) {
        speciesRepository.update(model);
        return "redirect:/species/getAll";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute SpeciesCreateModel model) {
        speciesRepository.create(model);
        return "redirect:/species/getAll";
    }

    @GetMapping("/showAddForm")
    public String showAddForm(Model page){
        page.addAttribute("species", new SpeciesCreateModel());
        return "SpeciesPages/SpeciesCreatePage";
    }

    @GetMapping("/showUpdateForm")
    public String showUpdateForm(Model page, @RequestParam UUID id){
        SpeciesUpdateModel model=new SpeciesUpdateModel();
        model.setId(id);
        page.addAttribute("species", model);
        return "SpeciesPages/SpeciesUpdatePage";
    }


    @GetMapping("/getAll")
    public String getAll(Model page) {
        page.addAttribute("species", speciesRepository.getAll());
        return "SpeciesPages/SpeciesViewPage";
    }

    /*@GetMapping("/getById")
    public SpeciesViewModel getById(@RequestParam UUID id) {
        EntityManager em = entityManagerFactory.createEntityManager();

        Species species = em.find(Species.class, id);
        SpeciesViewModel model = modelMapper.map(species, SpeciesViewModel.class);
        model.convertPets(species.getPets());

        return model;
    }*/
}

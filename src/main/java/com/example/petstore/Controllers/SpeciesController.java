package com.example.petstore.Controllers;

import com.example.petstore.Entities.Pet;
import com.example.petstore.Entities.Species;
import com.example.petstore.Models.DirectorModels.DirectorCreateModel;
import com.example.petstore.Models.DirectorModels.DirectorUpdateModel;
import com.example.petstore.Models.SpeciesModels.SpeciesCreateModel;
import com.example.petstore.Models.SpeciesModels.SpeciesUpdateModel;
import com.example.petstore.Services.SpeciesService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/species")
public class SpeciesController {
    public final SpeciesService speciesService;

    public SpeciesController(SpeciesService speciesService) {
        this.speciesService = speciesService;
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam UUID id) {
        speciesService.delete(id);
        return "redirect:/species/getAll";
    }

    @PutMapping("/update")
    public String update(@ModelAttribute SpeciesUpdateModel model) {
        speciesService.update(model);
        return "redirect:/species/getAll";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute SpeciesCreateModel model) {
        speciesService.create(model);
        return "redirect:/species/getAll";
    }

    @GetMapping("/showAddForm")
    public String showAddForm(Model page){
        page.addAttribute("species", new DirectorCreateModel());
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
        page.addAttribute("directors", speciesService.getAll());
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

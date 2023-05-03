package com.example.petstore.controllers;

import com.example.petstore.models.directorModels.DirectorCreateModel;
import com.example.petstore.models.directorModels.DirectorUpdateModel;
import com.example.petstore.repositories.DirectorRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/director")
public class DirectorController {
    private final DirectorRepository directorRepository;

    public DirectorController(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam UUID id) {
        directorRepository.delete(id);
        return "redirect:/director/getAll";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute DirectorUpdateModel model) {
        directorRepository.update(model);
        return "redirect:/director/getAll";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute @Valid DirectorCreateModel directorCreateModel, BindingResult result) {
        if(result.hasErrors()){
            return "DirectorPages/DirectorCreatePage";
        }
        directorRepository.create(directorCreateModel);
        return "redirect:/director/getAll";
    }

    @GetMapping("/showAddForm")
    public String showAddForm(Model page){
        page.addAttribute("directorCreateModel", new DirectorCreateModel());
        return "DirectorPages/DirectorCreatePage";
    }

    @GetMapping("/showUpdateForm")
    public String showUpdateForm(Model page, @RequestParam UUID id){
        DirectorUpdateModel model=new DirectorUpdateModel();
        model.setId(id);
        page.addAttribute("director", model);
        return "DirectorPages/DirectorUpdatePage";
    }


    @GetMapping("/getAll")
    public String getAll(Model page) {
        page.addAttribute("directors", directorRepository.getAll());
        return "DirectorPages/DirectorViewPage";
    }

    /*@GetMapping("/getById")
    public DirectorViewModel getById(@RequestParam UUID id) {
        EntityManager em = entityManagerFactory.createEntityManager();

        Director director = em.find(Director.class, id);
        DirectorViewModel model = modelMapper.map(director, DirectorViewModel.class);
        if (director.getPetStore() != null)
            model.convertPetStore(director.getPetStore());

        return model;
    }*/
}

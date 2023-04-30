package com.example.petstore.Controllers;

import com.example.petstore.Models.DirectorModels.DirectorCreateModel;
import com.example.petstore.Models.DirectorModels.DirectorUpdateModel;
import com.example.petstore.Services.DirectorService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@Controller
@RequestMapping("/director")
public class DirectorController {
    private final DirectorService directorService;

    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam UUID id) {
        directorService.delete(id);
        return "redirect:/director/getAll";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute DirectorUpdateModel model) {
        directorService.update(model);
        return "redirect:/director/getAll";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute @Valid DirectorCreateModel directorCreateModel, BindingResult result) {
        if(result.hasErrors()){
            return "DirectorPages/DirectorCreatePage";
        }
        directorService.create(directorCreateModel);
        return "redirect:/director/getAll";
    }

    @GetMapping("/showAddForm")
    public String showAddForm(Model page){
        page.addAttribute("directorCreateModel",  new @Valid DirectorCreateModel());
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
        page.addAttribute("directors", directorService.getAll());
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

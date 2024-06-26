package com.example.petstore.controllers;

import com.example.petstore.models.directorModels.DirectorCreateModel;
import com.example.petstore.models.directorModels.DirectorUpdateModel;
import com.example.petstore.services.DirectorService;
import com.example.petstore.services.ExcelGenerator;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public String update(@ModelAttribute("director") @Valid DirectorUpdateModel model, BindingResult result) {
        String err = directorService.isUnique2(model.getName(), model.getId());
        if (!err.isEmpty()) {
            var error = new FieldError("model", "name", err);
            result.addError(error);
        }
        if(result.hasErrors()){
            return "DirectorPages/DirectorUpdatePage";
        }
        directorService.update(model);
        return "redirect:/director/getAll";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute @Valid DirectorCreateModel directorCreateModel, BindingResult result) {
        String err = directorService.isUnique(directorCreateModel.getName());
        if (!err.isEmpty()) {
            var error = new FieldError("directorCreateModel", "name", err);
            result.addError(error);
        }
        if(result.hasErrors()){
            return "DirectorPages/DirectorCreatePage";
        }
        directorService.create(directorCreateModel);
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
        page.addAttribute("directors", directorService.getAll());
        if(!directorService.getAll().isEmpty()){
            page.addAttribute("Male", directorService.getStatistics()[0]);
            page.addAttribute("Female", directorService.getStatistics()[1]);
            page.addAttribute("Nonbinary", directorService.getStatistics()[2]);
        }
        else {
            page.addAttribute("Male", 0);
            page.addAttribute("Female", 0);
            page.addAttribute("Nonbinary", 0);
        }
        return "DirectorPages/DirectorViewPage";
    }

    @GetMapping("/generate")
    public String generate(HttpServletResponse response) throws IOException {
        response.setContentType("application/xlsx");
        String headerKey = "Content-Disposition";
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerValue = "attachment; filename=xlsx_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        ExcelGenerator excelGenerator=new ExcelGenerator(directorService.getAll());
        excelGenerator.generate(response);
        return "DirectorPages/DirectorViewPage";
    }
}

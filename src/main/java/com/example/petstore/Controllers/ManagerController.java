package com.example.petstore.Controllers;

import com.example.petstore.Entities.Manager;
import com.example.petstore.Entities.PetStore;
import com.example.petstore.Models.ManagerModels.ManagerCreateModel;
import com.example.petstore.Models.ManagerModels.ManagerUpdateModel;
import com.example.petstore.Models.ManagerModels.ManagerViewModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    @PersistenceUnit(name = "Entities")
    private EntityManagerFactory entityManagerFactory;

    private final ModelMapper modelMapper;

    public ManagerController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @DeleteMapping("/delete")
    public void delete(@RequestParam UUID id){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Manager manager = em.find(Manager.class, id);
        PetStore petStore = em.find(PetStore.class, manager.getPetStore().getId());
        petStore.removeManager(manager);

        em.remove(manager);
        em.merge(petStore);
        em.getTransaction().commit();
    }

    @PutMapping("/update")
    public void update(@RequestBody ManagerUpdateModel model){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Manager manager = em.find(Manager.class, model.getId());
        PetStore petStore = manager.getPetStore();
        manager = modelMapper.map(model, Manager.class);
        manager.setPetStore(petStore);

        em.merge(manager);
        em.getTransaction().commit();
    }

    @PostMapping("/create")
    public void create(@RequestBody ManagerCreateModel model){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Manager manager = modelMapper.map(model,Manager.class);
        manager.setId(UUID.randomUUID());

        PetStore petStore = em.find(PetStore.class, model.getPetStoreId());
        petStore.addManager(manager);

        em.merge(petStore);
        em.getTransaction().commit();
    }

    @GetMapping("/getAll")
    public List<ManagerViewModel> getAll(){
        EntityManager em = entityManagerFactory.createEntityManager();

        List<Manager> managers = em.createQuery("from Manager ").getResultList();
        List<ManagerViewModel> models = new ArrayList<>();

        for (Manager manager : managers) {
            ManagerViewModel model = modelMapper.map(manager, ManagerViewModel.class);
            model.convertPetStore(manager.getPetStore());
            models.add(model);
        }

        return models;
    }

    @GetMapping("/getById")
    public ManagerViewModel getById(@RequestParam UUID id){
        EntityManager em = entityManagerFactory.createEntityManager();

        Manager manager = em.find(Manager.class, id);
        ManagerViewModel model = modelMapper.map(manager, ManagerViewModel.class);
        model.convertPetStore(manager.getPetStore());

        return model;
    }
}

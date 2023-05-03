package com.example.petstore.repositories;

import com.example.petstore.entities.Manager;
import com.example.petstore.entities.PetStore;
import com.example.petstore.models.managerModels.ManagerCreateModel;
import com.example.petstore.models.managerModels.ManagerUpdateModel;
import com.example.petstore.models.managerModels.ManagerViewModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class ManagerRepository {
    private final ModelMapper modelMapper;
    @PersistenceUnit(name = "Entities")
    private EntityManagerFactory entityManagerFactory;

    public ManagerRepository(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public void delete(UUID id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Manager manager = em.find(Manager.class, id);
        PetStore petStore = em.find(PetStore.class, manager.getPetStore().getId());
        petStore.removeManager(manager);

        em.remove(manager);
        em.merge(petStore);
        em.getTransaction().commit();
        em.close();
    }

    public void update(ManagerUpdateModel model) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Manager manager = em.find(Manager.class, model.getId());
        PetStore petStore = manager.getPetStore();
        manager = modelMapper.map(model, Manager.class);
        manager.setPetStore(petStore);

        em.merge(manager);
        em.getTransaction().commit();
        em.close();
    }

    public void create(ManagerCreateModel model) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Manager manager = modelMapper.map(model, Manager.class);
        manager.setId(UUID.randomUUID());

        PetStore petStore = em.find(PetStore.class, model.getPetStoreId());
        petStore.addManager(manager);

        em.merge(petStore);
        em.getTransaction().commit();
        em.close();
    }

    public List<ManagerViewModel> getAll() {
        EntityManager em = entityManagerFactory.createEntityManager();

        List<Manager> managers = em.createQuery("from Manager ").getResultList();
        List<ManagerViewModel> models = new ArrayList<>();

        for (Manager manager : managers) {
            ManagerViewModel model = modelMapper.map(manager, ManagerViewModel.class);
            model.convertPetStore(manager.getPetStore());
            models.add(model);
        }
        em.close();

        return models;
    }

    public ManagerViewModel getById(UUID id) {
        EntityManager em = entityManagerFactory.createEntityManager();

        Manager manager = em.find(Manager.class, id);
        ManagerViewModel model = modelMapper.map(manager, ManagerViewModel.class);
        model.convertPetStore(manager.getPetStore());
        em.close();

        return model;
    }
}

package com.example.petstore.Controllers;

import com.example.petstore.Entities.*;
import com.example.petstore.Models.SellerModels.SellerCreateModel;
import com.example.petstore.Models.SellerModels.SellerUpdateModel;
import com.example.petstore.Models.SellerModels.SellerViewModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @PersistenceUnit(name = "Entities")
    private EntityManagerFactory entityManagerFactory;

    private final ModelMapper modelMapper;

    public SellerController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam UUID id){
        EntityManager em=entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Seller seller=em.find(Seller.class, id);

        for (int i=0; i<seller.getPetStores().size(); i++) {
            PetStore petStore = seller.getPetStores().get(i);
            petStore.removeSellerFromPetStore(seller);
            em.merge(petStore);
        }

        em.remove(seller);
        em.getTransaction().commit();
    }

    @PutMapping("/update")
    public void update(@RequestBody SellerUpdateModel model){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Seller seller = em.find(Seller.class, model.getId());
        List<PetStore> petStores=seller.getPetStores();

        seller = modelMapper.map(model, Seller.class);
        seller.setPetStores(petStores);

        em.merge(seller);
        em.getTransaction().commit();
    }

    @PostMapping("/create")
    public void create(@RequestBody SellerCreateModel model){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Seller seller = modelMapper.map(model, Seller.class);
        seller.setId(UUID.randomUUID());

        em.persist(seller);
        em.getTransaction().commit();
    }

    @GetMapping("/getAll")
    public List<SellerViewModel> getAll(){
        EntityManager em = entityManagerFactory.createEntityManager();

        List<Seller> sellers = em.createQuery("from Seller").getResultList();
        List<SellerViewModel> models = new ArrayList<>();

        for (Seller seller : sellers) {
            SellerViewModel model = modelMapper.map(seller, SellerViewModel.class);
            model.convertPets(seller.getPetStores());
            models.add(model);
        }

        return models;
    }

    @GetMapping("/getById")
    public SellerViewModel getById(@RequestParam UUID id){
        EntityManager em = entityManagerFactory.createEntityManager();

        Seller seller = em.find(Seller.class, id);
        SellerViewModel model = modelMapper.map(seller, SellerViewModel.class);
        model.convertPets(seller.getPetStores());

        return model;
    }

    @DeleteMapping("/removeSellerFromPetStore")
    public void removeSellerFromPetStore(@RequestParam UUID petStoreId, @RequestParam UUID sellerId){
        EntityManager em=entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Seller seller=em.find(Seller.class, sellerId);
        PetStore petStore =em.find(PetStore.class, petStoreId);
        petStore.removeSellerFromPetStore(seller);

        em.merge(seller);
        em.merge(petStore);
        em.getTransaction().commit();
    }

    @PostMapping("/assignSellerToPetStore")
    public void assignSellerToPetStore(@RequestParam UUID petStoreId, @RequestParam UUID sellerId){
        EntityManager em=entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        PetStore petStore=em.find(PetStore.class, petStoreId);
        Seller seller=em.find(Seller.class, sellerId);
        petStore.assignSellerToPetStore(seller);

        em.merge(seller);
        em.merge(petStore);
        em.getTransaction().commit();
    }
}

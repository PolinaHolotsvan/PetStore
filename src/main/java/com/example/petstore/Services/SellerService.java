package com.example.petstore.Services;

import com.example.petstore.Entities.PetStore;
import com.example.petstore.Entities.Seller;
import com.example.petstore.Models.SellerModels.SellerCreateModel;
import com.example.petstore.Models.SellerModels.SellerUpdateModel;
import com.example.petstore.Models.SellerModels.SellerViewModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SellerService {
    private final ModelMapper modelMapper;
    @PersistenceUnit(name = "Entities")
    private EntityManagerFactory entityManagerFactory;

    public SellerService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public void delete(UUID id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Seller seller = em.find(Seller.class, id);

        for (int i = 0; i < seller.getPetStores().size(); i++) {
            PetStore petStore = seller.getPetStores().get(i);
            petStore.removeSellerFromPetStore(seller);
            em.merge(petStore);
        }

        em.remove(seller);
        em.getTransaction().commit();
        em.close();
    }

    public void update(SellerUpdateModel model) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Seller seller = em.find(Seller.class, model.getId());
        List<PetStore> petStores = seller.getPetStores();

        seller = modelMapper.map(model, Seller.class);
        seller.setPetStores(petStores);

        em.merge(seller);
        em.getTransaction().commit();
        em.close();
    }

    public void create(SellerCreateModel model) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Seller seller = modelMapper.map(model, Seller.class);
        seller.setId(UUID.randomUUID());

        em.persist(seller);
        em.getTransaction().commit();
        em.close();
    }

    public List<SellerViewModel> getAll() {
        EntityManager em = entityManagerFactory.createEntityManager();

        List<Seller> sellers = em.createQuery("from Seller").getResultList();
        List<SellerViewModel> models = new ArrayList<>();

        for (Seller seller : sellers) {
            SellerViewModel model = modelMapper.map(seller, SellerViewModel.class);
            model.convertPets(seller.getPetStores());
            models.add(model);
        }
        em.close();

        return models;
    }

    public SellerViewModel getById(UUID id) {
        EntityManager em = entityManagerFactory.createEntityManager();

        Seller seller = em.find(Seller.class, id);
        SellerViewModel model = modelMapper.map(seller, SellerViewModel.class);
        model.convertPets(seller.getPetStores());
        em.close();

        return model;
    }

    public void removeSellerFromPetStore(UUID petStoreId, UUID sellerId) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Seller seller = em.find(Seller.class, sellerId);
        PetStore petStore = em.find(PetStore.class, petStoreId);
        petStore.removeSellerFromPetStore(seller);

        em.merge(seller);
        em.merge(petStore);
        em.getTransaction().commit();
    }

    public void assignSellerToPetStore(UUID petStoreId, UUID sellerId) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        PetStore petStore = em.find(PetStore.class, petStoreId);
        Seller seller = em.find(Seller.class, sellerId);
        petStore.assignSellerToPetStore(seller);

        em.merge(seller);
        em.merge(petStore);
        em.getTransaction().commit();
    }
}

package com.example.petstore.services;

import com.example.petstore.entities.PetStore;
import com.example.petstore.entities.Seller;
import com.example.petstore.models.petStoreModels.PetStoreViewModel;
import com.example.petstore.models.sellerModels.SellerCreateModel;
import com.example.petstore.models.sellerModels.SellerUpdateModel;
import com.example.petstore.models.sellerModels.SellerViewModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
@Service

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

    public String isUnique(String name){
        List<SellerViewModel> list= this.getAll();
        for(SellerViewModel sellerViewModel: list){
            if(Objects.equals(sellerViewModel.getName(), name)) return "You can not use the same name twice";
        }
        return "";
    }

    public String isUnique2(String name, UUID id){
        List<SellerViewModel> list= this.getAll();
        for(SellerViewModel sellerViewModel: list){
            if((sellerViewModel.getId()!=id) && sellerViewModel.getName()==name) return "You can not use the same name twice";
        }
        return "";
    }
}

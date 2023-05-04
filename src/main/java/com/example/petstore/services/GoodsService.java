package com.example.petstore.services;

import com.example.petstore.entities.Goods;
import com.example.petstore.entities.PetStore;
import com.example.petstore.models.goodsModels.GoodsCreateModel;
import com.example.petstore.models.goodsModels.GoodsUpdateModel;
import com.example.petstore.models.goodsModels.GoodsViewModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class GoodsService {
    @PersistenceUnit(name = "Entities")
    private EntityManagerFactory entityManagerFactory;

    private final ModelMapper modelMapper;

    public GoodsService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public void delete(UUID id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Goods goods = em.find(Goods.class, id);
        PetStore petStore = em.find(PetStore.class, goods.getPetStore().getId());
        petStore.removeGoods(goods);

        em.remove(goods);
        em.merge(petStore);
        em.getTransaction().commit();
        em.close();
    }

    public void update(GoodsUpdateModel model) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Goods goods = em.find(Goods.class, model.getId());
        PetStore petStore = goods.getPetStore();
        goods = modelMapper.map(model, Goods.class);
        goods.setPetStore(petStore);

        em.merge(goods);
        em.getTransaction().commit();
        em.close();
    }

    public void create(GoodsCreateModel model) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Goods goods = modelMapper.map(model, Goods.class);
        goods.setId(UUID.randomUUID());

        PetStore petStore = em.find(PetStore.class, model.getPetStoreId());
        petStore.addGoods(goods);

        em.merge(petStore);
        em.getTransaction().commit();
        em.close();
    }

    public List<GoodsViewModel> getAll() {
        EntityManager em = entityManagerFactory.createEntityManager();

        List<Goods> goods = em.createQuery("from Goods").getResultList();
        List<GoodsViewModel> models = new ArrayList<>();

        for (Goods goods1 : goods) {
            GoodsViewModel model = modelMapper.map(goods1, GoodsViewModel.class);
            model.convertPetStore(goods1.getPetStore());
            models.add(model);
        }
        em.close();

        return models;
    }

    public GoodsViewModel getById(UUID id) {
        EntityManager em = entityManagerFactory.createEntityManager();

        Goods goods = em.find(Goods.class, id);
        GoodsViewModel model = modelMapper.map(goods, GoodsViewModel.class);
        model.convertPetStore(goods.getPetStore());

        em.close();

        return model;
    }
}

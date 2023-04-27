package com.example.petstore.Controllers;

import com.example.petstore.Entities.Goods;
import com.example.petstore.Entities.PetStore;
import com.example.petstore.Models.GoodsModels.GoodsCreateModel;
import com.example.petstore.Models.GoodsModels.GoodsUpdateModel;
import com.example.petstore.Models.GoodsModels.GoodsViewModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @PersistenceUnit(name = "Entities")
    private EntityManagerFactory entityManagerFactory;

    private final ModelMapper modelMapper;

    public GoodsController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam UUID id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Goods goods = em.find(Goods.class, id);
        PetStore petStore = em.find(PetStore.class, goods.getPetStore().getId());
        petStore.removeGoods(goods);

        em.remove(goods);
        em.merge(petStore);
        em.getTransaction().commit();
    }

    @PutMapping("/update")
    public void update(@RequestBody GoodsUpdateModel model) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Goods goods = em.find(Goods.class, model.getId());
        PetStore petStore = goods.getPetStore();
        goods = modelMapper.map(model, Goods.class);
        goods.setPetStore(petStore);

        em.merge(goods);
        em.getTransaction().commit();
    }

    @PostMapping("/create")
    public void create(@RequestBody GoodsCreateModel model) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Goods goods = modelMapper.map(model, Goods.class);
        goods.setId(UUID.randomUUID());

        PetStore petStore = em.find(PetStore.class, model.getPetStoreId());
        petStore.addGoods(goods);

        em.merge(petStore);
        em.getTransaction().commit();
    }

    @GetMapping("/getAll")
    public List<GoodsViewModel> getAll() {
        EntityManager em = entityManagerFactory.createEntityManager();

        List<Goods> goods = em.createQuery("from Goods").getResultList();
        List<GoodsViewModel> models = new ArrayList<>();

        for (Goods goods1 : goods) {
            GoodsViewModel model = modelMapper.map(goods1, GoodsViewModel.class);
            model.convertPetStore(goods1.getPetStore());
            models.add(model);
        }

        return models;
    }

    @GetMapping("/getById")
    public GoodsViewModel getById(@RequestParam UUID id) {
        EntityManager em = entityManagerFactory.createEntityManager();

        Goods goods = em.find(Goods.class, id);
        GoodsViewModel model = modelMapper.map(goods, GoodsViewModel.class);
        model.convertPetStore(goods.getPetStore());

        return model;
    }
}

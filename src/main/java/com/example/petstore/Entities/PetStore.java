package com.example.petstore.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Entity
@Data
public class PetStore {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID Id;
    private String Name;
    private int Rating;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })

    @JoinTable(name = "EmploymentContract",
            joinColumns = { @JoinColumn(name = "PetStoreId") },
            inverseJoinColumns = { @JoinColumn(name = "SellerId") })
    private Set<Seller> Sellers = new HashSet<>();

    @OneToMany(mappedBy = "PetStore", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Goods> Goods=new ArrayList<>();

    @OneToMany(mappedBy = "PetStore", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pet> Pets=new ArrayList<>();

    @OneToMany(mappedBy = "PetStore", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Manager> Managers=new ArrayList<>();


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DirectorId", referencedColumnName = "Id")
    private Director Director;

    public void assignSellerToPetStore(Seller seller){
        Sellers.add(seller);
        seller.getPetStores().add(this);
    }
    public void removeSellerFromPetStore(Seller seller){
        Sellers.remove(seller);
        seller.getPetStores().remove(this);
    }

    public void addGoods(Goods goods) {
        Goods.add(goods);
        goods.setPetStore(this);
    }
    public void removeGoods(Goods goods) {
        Goods.remove(goods);
        goods.setPetStore(null);
    }

    public void addManager(Manager manager) {
        Managers.add(manager);
        manager.setPetStore(this);
    }
    public void removeManager(Manager manager) {
        Managers.remove(manager);
        manager.setPetStore(null);
    }

    public void addPet(Pet pet) {
        Pets.add(pet);
        pet.setPetStore(this);
    }
    public void removePet(Pet pet) {
        Pets.remove(pet);
        pet.setPetStore(null);
    }

    public void setDirector(Director director) {
        Director = director;
        director.setPetStore(this);
    }
    public void removeDirector(Director director) {
        Director = null;
        director.setPetStore(null);
    }

}

package com.example.petstore.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Entity
@Getter
@Setter
public class PetStore {
    @Id
    private UUID Id;
    private String Name;
    private double Rating;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.ALL
    })

    @JoinTable(name = "EmploymentContract",
            joinColumns = { @JoinColumn(name = "PetStoreId") },
            inverseJoinColumns = { @JoinColumn(name = "SellerId") })
    private List<Seller> Sellers = new ArrayList<>();

    @OneToMany(mappedBy = "PetStore", cascade = {
            CascadeType.ALL
    }, orphanRemoval = true)
    private List<Goods> Goods=new ArrayList<>();

    @OneToMany(mappedBy = "PetStore", cascade = {
            CascadeType.ALL
    }, orphanRemoval = true)
    private List<Pet> Pets=new ArrayList<>();

    @OneToMany(mappedBy = "PetStore", cascade = {
            CascadeType.ALL
    }, orphanRemoval = true)
    private List<Manager> Managers=new ArrayList<>();


    @OneToOne
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

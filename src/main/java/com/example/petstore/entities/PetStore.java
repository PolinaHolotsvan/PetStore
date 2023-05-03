package com.example.petstore.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Entity
@Getter
@Setter
public class PetStore {
    @Id
    private UUID id;
    private String name;
    private double rating;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.ALL
    })

    @JoinTable(name = "EmploymentContract",
            joinColumns = { @JoinColumn(name = "PetStoreId") },
            inverseJoinColumns = { @JoinColumn(name = "SellerId") })
    private List<Seller> sellers = new ArrayList<>();

    @OneToMany(mappedBy = "petStore", cascade = {
            CascadeType.ALL
    }, orphanRemoval = true)
    private List<Goods> goods =new ArrayList<>();

    @OneToMany(mappedBy = "petStore", cascade = {
            CascadeType.ALL
    }, orphanRemoval = true)
    private List<Pet> pets =new ArrayList<>();

    @OneToMany(mappedBy = "petStore", cascade = {
            CascadeType.ALL
    }, orphanRemoval = true)
    private List<Manager> managers =new ArrayList<>();


    @OneToOne
    @JoinColumn(name = "DirectorId", referencedColumnName = "id")
    private Director director;

    public void assignSellerToPetStore(Seller seller){
        sellers.add(seller);
        seller.getPetStores().add(this);
    }
    public void removeSellerFromPetStore(Seller seller){
        sellers.remove(seller);
        seller.getPetStores().remove(this);
    }

    public void addGoods(Goods goods) {
        this.goods.add(goods);
        goods.setPetStore(this);
    }
    public void removeGoods(Goods goods) {
        this.goods.remove(goods);
        goods.setPetStore(null);
    }

    public void addManager(Manager manager) {
        managers.add(manager);
        manager.setPetStore(this);
    }
    public void removeManager(Manager manager) {
        managers.remove(manager);
        manager.setPetStore(null);
    }

    public void addPet(Pet pet) {
        pets.add(pet);
        pet.setPetStore(this);
    }
    public void removePet(Pet pet) {
        pets.remove(pet);
        pet.setPetStore(null);
    }

    public void setDirector(Director director) {
        this.director = director;
        director.setPetStore(this);
    }
    public void removeDirector(Director director) {
        this.director = null;
        director.setPetStore(null);
    }

}

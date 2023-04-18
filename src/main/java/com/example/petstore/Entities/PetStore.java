package com.example.petstore.Entities;

import jakarta.persistence.*;

import java.util.*;

@Entity
public class PetStore {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID Id;
    public String Name;
    public int Rating;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })

    @JoinTable(name = "EmploymentContract",
            joinColumns = { @JoinColumn(name = "PetStoreId") },
            inverseJoinColumns = { @JoinColumn(name = "SellerId") })
    public Set<Seller> Sellers = new HashSet<>();

    @OneToMany(mappedBy = "PetStore", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Goods> Goods=new ArrayList<>();

    @OneToMany(mappedBy = "PetStore", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Pet> Pets=new ArrayList<>();

    @OneToMany(mappedBy = "PetStore", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Manager> Managers=new ArrayList<>();


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DirectorId", referencedColumnName = "Id")
    public Director Director;

    public PetStore(UUID id, String name, int rating, Set<Seller> sellers, List<Goods> goods, List<Pet> pets, List<Manager> managers, Director director) {
        Id = id;
        Name = name;
        Rating = rating;
        Sellers = sellers;
        Goods = goods;
        Pets = pets;
        Managers = managers;
        Director = director;
    }

    public PetStore() {
    }

    public void assignSellerToPetStore(Seller seller){
        Sellers.add(seller);
        seller.PetStores.add(this);
    }
    public void removeSellerFromPetStore(Seller seller){
        Sellers.remove(seller);
        seller.PetStores.remove(this);
    }

    public void addGoods(Goods goods) {
        Goods.add(goods);
        goods.PetStore=this;
    }
    public void removeGoods(Goods goods) {
        Goods.remove(goods);
        goods.PetStore=null;
    }

    public void addManager(Manager manager) {
        Managers.add(manager);
        manager.PetStore=this;
    }
    public void removeManager(Manager manager) {
        Managers.remove(manager);
        manager.PetStore=null;
    }

    public void addPet(Pet pet) {
        Pets.add(pet);
        pet.PetStore=this;
    }
    public void removePet(Pet pet) {
        Pets.remove(pet);
        pet.PetStore=null;
    }

    public void setDirector(Director director) {
        Director = director;
        director.PetStore=this;
    }
    public void removeDirector(Director director) {
        Director = null;
        director.PetStore=null;
    }

}

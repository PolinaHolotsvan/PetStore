package com.example.petstore.Controllers;

import com.example.petstore.Entities.PetStore;
import com.example.petstore.Entities.Seller;
import com.example.petstore.Models.SellerModels.SellerCreateModel;
import com.example.petstore.Models.SellerModels.SellerUpdateModel;
import com.example.petstore.Models.SellerModels.SellerViewModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/seller")
public class SellerController {

}

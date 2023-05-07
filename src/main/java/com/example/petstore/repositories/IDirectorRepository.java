package com.example.petstore.repositories;

import com.example.petstore.entities.Director;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IDirectorRepository extends JpaRepository<Director, UUID> {
}

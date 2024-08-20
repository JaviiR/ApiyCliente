package com.example.API.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.API.model.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona,Integer>{
    
    
}

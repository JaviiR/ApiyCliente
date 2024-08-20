package com.example.API.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.API.model.Cliente;
import com.example.API.model.Persona;

@Repository
public interface ClienteRepository  extends JpaRepository<Cliente,Integer>{
    

    public boolean existsByPersona(Persona persona);
}

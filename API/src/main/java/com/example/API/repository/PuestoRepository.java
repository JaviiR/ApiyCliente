package com.example.API.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.API.model.Puesto;

@Repository
public interface PuestoRepository extends JpaRepository<Puesto,Integer>{
    
}

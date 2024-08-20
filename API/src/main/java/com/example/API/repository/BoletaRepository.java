package com.example.API.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.API.model.Boleta;

@Repository
public interface BoletaRepository  extends JpaRepository<Boleta,Integer>{
    
}

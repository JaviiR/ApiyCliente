package com.example.API.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.API.model.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta,Integer>{
    
}

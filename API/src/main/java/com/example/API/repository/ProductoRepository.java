package com.example.API.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.API.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Integer>{
    
}

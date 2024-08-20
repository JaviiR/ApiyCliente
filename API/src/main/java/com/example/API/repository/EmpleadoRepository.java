package com.example.API.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.API.model.Empleado;
import com.example.API.model.Persona;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado,Integer>{

    boolean existsByPersona(Persona persona);
}

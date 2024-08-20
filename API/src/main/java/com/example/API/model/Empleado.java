package com.example.API.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Empleado {
    @Id
    @Column(length = 11)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "salario_final")
    private double salario_final;

    @Column(name = "fecha_ingreso",nullable = false)
    private LocalDateTime fecha_ingreso;

    @Column(name = "fecha_retiro")
    private LocalDateTime fecha_retiro;

    
    @OneToOne
    @JoinColumn(name="id_persona", unique = true,nullable = false)
    private Persona persona;

    
    

    @ManyToOne
    @JoinColumn(name="id_puesto",nullable = false)
    private Puesto puesto;
    
}

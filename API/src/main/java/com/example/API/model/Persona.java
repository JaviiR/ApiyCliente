package com.example.API.model;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 11)
    private int id;

    @Column(length = 20,nullable = false)
    private String tipo_documento;

    @Column(length = 30,nullable = false)
    private Long num_documento;

    @Column(length = 30,nullable = false)
    private String nombres;

    @Column(length = 30,nullable = false)
    private String apellidos;

    @Column(length = 35)
    private String correo;

    @Column(length = 15)
    private String telefono;

    @Column(length = 50)
    private String direccion;

    @Column(nullable = false)
    private LocalDateTime fecha_creacion;
    
    @Column(nullable = false)
    private LocalDateTime fecha_actualizacion;

    
}

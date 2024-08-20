package com.example.API.model;

import java.time.LocalDateTime;

import com.example.API.enums.EstadoClienteEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @Column(length = 11)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 30,nullable = false)
    private String usuario;
    @Column(length = 200,nullable = false)
    private String contraseña;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoClienteEnum estado;
    @Column(nullable = false)
    private LocalDateTime fecha_creacion;
    @OneToOne
    @JoinColumn(name = "id_persona", unique=true,nullable = false)
    private Persona persona;

    
}



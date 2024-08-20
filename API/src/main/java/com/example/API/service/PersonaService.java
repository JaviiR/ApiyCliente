package com.example.API.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.API.model.Persona;
import com.example.API.repository.PersonaRepository;

@Service
public class PersonaService {
    @Autowired
    private PersonaRepository personaRepo;

    //---------------------------OBTENCIÓN DE DATOS-----------------------------
    public List<Persona> obtenerTodasLasPersonas() {
        return personaRepo.findAll();
    }

    public List<Persona> obtenerPersonasConIds(List<Integer> listaIds){
        return personaRepo.findAllById(listaIds);
    }

    public Persona obtenerPersona(int id){
        return personaRepo.findById(id).get();
    }


    //--------------------------------------------------------------------------
    //-----------------------------CREACIÓN DE REGISTROS------------------------
    public Persona agregarPersona(Persona persona){
        return personaRepo.save(persona);
    }

    public List<Persona> agregarPersonas(List<Persona> listaPersonas){
        return personaRepo.saveAll(listaPersonas);
    }

    //--------------------------------------------------------------------------
    //---------------------------ACTUALIZACIÓN DE REGISTROS---------------------
    public Persona actualizarPersona(Persona personaActualizada){
        Persona personaBD=personaRepo.findById(personaActualizada.getId()).get();
        personaBD.setNombres(personaActualizada.getNombres());
        personaBD.setApellidos(personaActualizada.getApellidos());
        personaBD.setCorreo(personaActualizada.getCorreo());
        personaBD.setDireccion(personaActualizada.getDireccion());
        personaBD.setTelefono(personaActualizada.getTelefono());
        personaBD.setFecha_actualizacion(personaActualizada.getFecha_actualizacion());
        personaBD.setTipo_documento(personaActualizada.getTipo_documento());
        personaBD.setNum_documento(personaActualizada.getNum_documento());
        return personaRepo.save(personaBD);
    }

    public List<Persona> actualizarPersonas(List<Persona> personasActulizadas){
        //lista que tendra las personas de la BD actualizadas
        List<Persona> listaActualizados=new ArrayList<>();
        for(Persona p:personasActulizadas){
            Persona personaBD=personaRepo.findById(p.getId()).get();
            personaBD.setNombres(p.getNombres());
            personaBD.setApellidos(p.getApellidos());
            personaBD.setCorreo(p.getCorreo());
            personaBD.setDireccion(p.getDireccion());
            personaBD.setTelefono(p.getTelefono());
            personaBD.setFecha_actualizacion(p.getFecha_actualizacion());
            personaBD.setTipo_documento(p.getTipo_documento());
            personaBD.setNum_documento(p.getNum_documento());
            listaActualizados.add(personaBD);
        }
        return personaRepo.saveAll(listaActualizados);
    }
    //---------------------------ELIMINACIÓN DE DATOS------------------------
    public void borrarPersona(Persona persona){
        personaRepo.delete(persona);
    }

    public void borrarPersonaPorId(int id){
        personaRepo.deleteById(id);
    }

    public void borrarPersonas(List<Persona> listaPersonas){
        personaRepo.deleteAll(listaPersonas);
    }

    public void borrarPersonasPorIds(List<Integer> ids){
        personaRepo.deleteAllById(ids);
    }

    public boolean validarPersonaId(int id){
        return personaRepo.existsById(id);
    }
}

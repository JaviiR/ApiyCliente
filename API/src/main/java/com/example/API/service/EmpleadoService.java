package com.example.API.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import com.example.API.model.Empleado;
import com.example.API.model.Persona;
import com.example.API.repository.EmpleadoRepository;
import com.example.API.repository.PersonaRepository;

@Service
public class EmpleadoService {
    @Autowired
    private EmpleadoRepository empleadoRepo;
    @Autowired
    private PersonaRepository personaRepo;

    // ---------------------------OBTENCIÓN DE DATOS-----------------------------
    public List<Empleado> obtenerTodasLosEmpleados() {
        return empleadoRepo.findAll();
    }

    public List<Empleado> obtenerEmpleadosConIds(List<Integer> listaIds) {
        return empleadoRepo.findAllById(listaIds);
    }

    public Empleado obtenerEmpleado(int id) {
        return empleadoRepo.findById(id).get();
    }

    // --------------------------------------------------------------------------
    // -----------------------------CREACIÓN DE REGISTROS------------------------
    public Empleado agregarEmpleado(Empleado Empleado) {
        return empleadoRepo.save(Empleado);
    }

    public List<Empleado> agregarEmpleados(List<Empleado> listaEmpleados) {
        return empleadoRepo.saveAll(listaEmpleados);
    }

    // --------------------------------------------------------------------------
    // ---------------------------ACTUALIZACIÓN DE REGISTROS---------------------
    public Empleado actualizarEmpleado(Empleado EmpleadoActualizada) {
        Empleado EmpleadoBD = empleadoRepo.findById(EmpleadoActualizada.getId()).get();
        EmpleadoBD.setPuesto(EmpleadoActualizada.getPuesto());
        EmpleadoBD.setFecha_retiro(EmpleadoActualizada.getFecha_retiro());
        EmpleadoBD.setSalario_final(EmpleadoActualizada.getSalario_final());
        return empleadoRepo.save(EmpleadoBD);
    }

    public List<Empleado> actualizarEmpleados(List<Empleado> EmpleadosActulizadas) {
        // lista que tendra las Empleados de la BD actualizadas
        List<Empleado> listaActualizados = new ArrayList<>();
        for (Empleado p : EmpleadosActulizadas) {
            Empleado EmpleadoBD = empleadoRepo.findById(p.getId()).get();
            EmpleadoBD.setPersona(p.getPersona());
            EmpleadoBD.setPuesto(p.getPuesto());
            EmpleadoBD.setFecha_retiro(p.getFecha_retiro());
            EmpleadoBD.setSalario_final(p.getSalario_final());
            listaActualizados.add(EmpleadoBD);
        }
        return empleadoRepo.saveAll(listaActualizados);
    }

    // ---------------------------ELIMINACIÓN DE DATOS------------------------
    public void borrarEmpleado(Empleado Empleado) {
        empleadoRepo.delete(Empleado);
    }

    public void borrarEmpleadoPorId(int id) {
        empleadoRepo.deleteById(id);
    }

    public void borrarEmpleados(List<Empleado> listaEmpleados) {
        empleadoRepo.deleteAll(listaEmpleados);
    }

    public void borrarEmpleadosPorIds(List<Integer> ids) {
        empleadoRepo.deleteAllById(ids);
    }

    public boolean validarEmpleado(int id){
        return empleadoRepo.existsById(id);
    }

    public boolean validarPersona(int personaId){
        Persona persona=personaRepo.findById(personaId).get();
        return empleadoRepo.existsByPersona(persona);
    }
}

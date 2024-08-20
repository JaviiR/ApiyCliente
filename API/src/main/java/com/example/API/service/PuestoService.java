package com.example.API.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import com.example.API.model.Puesto;
import com.example.API.repository.PuestoRepository;

@Service
public class PuestoService {

    @Autowired
    private PuestoRepository puestoRepository;

    // ---------------------------OBTENCIÓN DE DATOS-----------------------------
    public List<Puesto> obtenerTodasLosPuestos() {
        return puestoRepository.findAll();
    }

    public List<Puesto> obtenerPuestosConIds(List<Integer> listaIds) {
        return puestoRepository.findAllById(listaIds);
    }

    public Puesto obtenerPuesto(int id) {
        return puestoRepository.findById(id).get();
    }

    // --------------------------------------------------------------------------
    // -----------------------------CREACIÓN DE REGISTROS------------------------
    public Puesto agregarPuesto(Puesto Puesto) {
        return puestoRepository.save(Puesto);
    }

    public List<Puesto> agregarPuestos(List<Puesto> listaPuestos) {
        return puestoRepository.saveAll(listaPuestos);
    }

    // --------------------------------------------------------------------------
    // ---------------------------ACTUALIZACIÓN DE REGISTROS---------------------
    public Puesto actualizarPuesto(Puesto PuestoActualizada) {
        Puesto PuestoBD = puestoRepository.findById(PuestoActualizada.getId()).get();
        PuestoBD.setFecha_modificacion(PuestoActualizada.getFecha_modificacion());
        PuestoBD.setHora_ingreso(PuestoActualizada.getHora_ingreso());
        PuestoBD.setHora_salida(PuestoActualizada.getHora_salida());
        PuestoBD.setNombre(PuestoActualizada.getNombre());
        PuestoBD.setSalario_base(PuestoActualizada.getSalario_base());
        return puestoRepository.save(PuestoBD);
    }

    public List<Puesto> actualizarPuestos(List<Puesto> PuestosActulizadas) {
        // lista que tendra las Puestos de la BD actualizadas
        List<Puesto> listaActualizados = new ArrayList<>();
        for (Puesto p : PuestosActulizadas) {
            Puesto PuestoBD = puestoRepository.findById(p.getId()).get();
            PuestoBD.setFecha_modificacion(p.getFecha_modificacion());
            PuestoBD.setHora_ingreso(p.getHora_ingreso());
            PuestoBD.setHora_salida(p.getHora_salida());
            PuestoBD.setNombre(p.getNombre());
            PuestoBD.setSalario_base(p.getSalario_base());
            listaActualizados.add(PuestoBD);
        }
        return puestoRepository.saveAll(listaActualizados);
    }

    // ---------------------------ELIMINACIÓN DE DATOS------------------------
    public void borrarPuesto(Puesto Puesto) {
        puestoRepository.delete(Puesto);
    }

    public void borrarPuestoPorId(int id) {
        puestoRepository.deleteById(id);
    }

    public void borrarPuestos(List<Puesto> listaPuestos) {
        puestoRepository.deleteAll(listaPuestos);
    }

    public void borrarPuestosPorIds(List<Integer> ids) {
        puestoRepository.deleteAllById(ids);
    }

    public boolean validarPuesto(int id){
        return puestoRepository.existsById(id);
    }
}

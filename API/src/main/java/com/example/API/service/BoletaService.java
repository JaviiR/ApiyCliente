package com.example.API.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.API.model.Boleta;
import com.example.API.repository.BoletaRepository;


@Service
public class BoletaService {
@Autowired
    private BoletaRepository boletaRepository;

    // ---------------------------OBTENCIÓN DE DATOS-----------------------------
    public List<Boleta> obtenerTodasLosBoletas() {
        return boletaRepository.findAll();
    }

    public List<Boleta> obtenerBoletasConIds(List<Integer> listaIds) {
        return boletaRepository.findAllById(listaIds);
    }

    public Boleta obtenerBoleta(int id){
            return boletaRepository.findById(id).get();    
    }

    // --------------------------------------------------------------------------
    // -----------------------------CREACIÓN DE REGISTROS------------------------
    public Boleta agregarBoleta(Boleta Boleta) {
        return boletaRepository.save(Boleta);
    }

    public List<Boleta> agregarBoletas(List<Boleta> listaBoletas) {
        return boletaRepository.saveAll(listaBoletas);
    }

    // --------------------------------------------------------------------------
    // ---------------------------ACTUALIZACIÓN DE REGISTROS---------------------
    public Boleta actualizarBoleta(Boleta BoletaActualizada) {
        Boleta BoletaBD = boletaRepository.findById(BoletaActualizada.getId()).get();
        BoletaBD.setTotal(BoletaActualizada.getTotal());
        BoletaBD.setVenta(BoletaActualizada.getVenta());
        BoletaBD.setFecha(BoletaActualizada.getFecha());
        return boletaRepository.save(BoletaBD);
    }

    public List<Boleta> actualizarBoletas(List<Boleta> BoletasActulizadas) {
        // lista que tendra las Boletas de la BD actualizadas
        List<Boleta> listaActualizados = new ArrayList<>();
        for (Boleta p : BoletasActulizadas) {
            Boleta BoletaBD = boletaRepository.findById(p.getId()).get();
            BoletaBD.setTotal(p.getTotal());
        BoletaBD.setVenta(p.getVenta());
        BoletaBD.setFecha(p.getFecha());
            listaActualizados.add(BoletaBD);
        }
        return boletaRepository.saveAll(listaActualizados);
    }

    // ---------------------------ELIMINACIÓN DE DATOS------------------------
    public void borrarBoleta(Boleta Boleta) {
        boletaRepository.delete(Boleta);
    }

    public void borrarBoletaPorId(int id) {
        boletaRepository.deleteById(id);
    }

    public void borrarBoletas(List<Boleta> listaBoletas) {
        boletaRepository.deleteAll(listaBoletas);
    }

    public void borrarBoletasPorIds(List<Integer> ids) {
        boletaRepository.deleteAllById(ids);
    }

    public boolean validarBoletaId(int id){
        return boletaRepository.existsById(id);
    }
    
}

package com.example.API.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.API.model.Factura;
import com.example.API.repository.FacturaRepository;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository FacturaRepository;

    // ---------------------------OBTENCIÓN DE DATOS-----------------------------
    public List<Factura> obtenerTodasLosFacturas() {
        return FacturaRepository.findAll();
    }

    public List<Factura> obtenerFacturasConIds(List<Integer> listaIds) {
        return FacturaRepository.findAllById(listaIds);
    }

    public Factura obtenerFactura(int id) {
        return FacturaRepository.findById(id).get();
    }

    // --------------------------------------------------------------------------
    // -----------------------------CREACIÓN DE REGISTROS------------------------
    public Factura agregarFactura(Factura Factura) {
        return FacturaRepository.save(Factura);
    }

    public List<Factura> agregarFacturas(List<Factura> listaFacturas) {
        return FacturaRepository.saveAll(listaFacturas);
    }

    // --------------------------------------------------------------------------
    // ---------------------------ACTUALIZACIÓN DE REGISTROS---------------------
    public Factura actualizarFactura(Factura FacturaActualizada) {
        Factura FacturaBD = FacturaRepository.findById(FacturaActualizada.getId()).get();
        FacturaBD.setTotal(FacturaActualizada.getTotal());
        FacturaBD.setVenta(FacturaActualizada.getVenta());
        FacturaBD.setFecha(FacturaActualizada.getFecha());
        return FacturaRepository.save(FacturaBD);
    }

    public List<Factura> actualizarFacturas(List<Factura> FacturasActulizadas) {
        // lista que tendra las Facturas de la BD actualizadas
        List<Factura> listaActualizados = new ArrayList<>();
        for (Factura p : FacturasActulizadas) {
            Factura FacturaBD = FacturaRepository.findById(p.getId()).get();
            FacturaBD.setTotal(p.getTotal());
        FacturaBD.setVenta(p.getVenta());
        FacturaBD.setFecha(p.getFecha());
            listaActualizados.add(FacturaBD);
        }
        return FacturaRepository.saveAll(listaActualizados);
    }

    // ---------------------------ELIMINACIÓN DE DATOS------------------------
    public void borrarFactura(Factura Factura) {
        FacturaRepository.delete(Factura);
    }

    public void borrarFacturaPorId(int id) {
        FacturaRepository.deleteById(id);
    }

    public void borrarFacturas(List<Factura> listaFacturas) {
        FacturaRepository.deleteAll(listaFacturas);
    }

    public void borrarFacturasPorIds(List<Integer> ids) {
        FacturaRepository.deleteAllById(ids);
    }
}

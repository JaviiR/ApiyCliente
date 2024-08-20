package com.example.API.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.API.model.Venta;
import com.example.API.repository.VentaRepository;
import java.util.List;
import java.util.ArrayList;

@Service
public class VentaService {
    @Autowired
    private VentaRepository VentaRepository;

    // ---------------------------OBTENCIÓN DE DATOS-----------------------------
    public List<Venta> obtenerTodasLosVentas() {
        return VentaRepository.findAll();
    }

    public List<Venta> obtenerVentasConIds(List<Integer> listaIds) {
        return VentaRepository.findAllById(listaIds);
    }

    public Venta obtenerVenta(int id) {
        return VentaRepository.findById(id).get();
    }

    // --------------------------------------------------------------------------
    // -----------------------------CREACIÓN DE REGISTROS------------------------
    public Venta agregarVenta(Venta Venta) {
        return VentaRepository.save(Venta);
    }

    public List<Venta> agregarVentas(List<Venta> listaVentas) {
        return VentaRepository.saveAll(listaVentas);
    }

    // --------------------------------------------------------------------------
    // ---------------------------ACTUALIZACIÓN DE REGISTROS---------------------
    public Venta actualizarVenta(Venta VentaActualizada) {
        Venta VentaBD = VentaRepository.findById(VentaActualizada.getId()).get();
        VentaBD.setEstado(VentaActualizada.getEstado());
        VentaBD.setMetodo_pago(VentaActualizada.getMetodo_pago());
        VentaBD.setMonto(VentaActualizada.getMonto());
        return VentaRepository.save(VentaBD);
    }

    public List<Venta> actualizarVentas(List<Venta> VentasActulizadas) {
        // lista que tendra las Ventas de la BD actualizadas
        List<Venta> listaActualizados = new ArrayList<>();
        for (Venta p : VentasActulizadas) {
            Venta VentaBD = VentaRepository.findById(p.getId()).get();
            VentaBD.setEstado(p.getEstado());
            VentaBD.setMetodo_pago(p.getMetodo_pago());
            VentaBD.setMonto(p.getMonto());
            listaActualizados.add(VentaBD);
        }
        return VentaRepository.saveAll(listaActualizados);
    }

    // ---------------------------ELIMINACIÓN DE DATOS------------------------
    public void borrarVenta(Venta Venta) {
        VentaRepository.delete(Venta);
    }

    public void borrarVentaPorId(int id) {
        VentaRepository.deleteById(id);
    }

    public void borrarVentas(List<Venta> listaVentas) {
        VentaRepository.deleteAll(listaVentas);
    }

    public void borrarVentasPorIds(List<Integer> ids) {
        VentaRepository.deleteAllById(ids);
    }
}

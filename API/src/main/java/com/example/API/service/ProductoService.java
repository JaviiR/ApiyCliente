package com.example.API.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import com.example.API.model.Producto;
import com.example.API.repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository ProductoRepository;

    // ---------------------------OBTENCIÓN DE DATOS-----------------------------
    public List<Producto> obtenerTodasLosProductos() {
        return ProductoRepository.findAll();
    }

    public List<Producto> obtenerProductosConIds(List<Integer> listaIds) {
        return ProductoRepository.findAllById(listaIds);
    }

    public Producto obtenerProducto(int id) {
        return ProductoRepository.findById(id).get();
    }

    // --------------------------------------------------------------------------
    // -----------------------------CREACIÓN DE REGISTROS------------------------
    public Producto agregarProducto(Producto Producto) {
        return ProductoRepository.save(Producto);
    }

    public List<Producto> agregarProductos(List<Producto> listaProductos) {
        return ProductoRepository.saveAll(listaProductos);
    }

    // --------------------------------------------------------------------------
    // ---------------------------ACTUALIZACIÓN DE REGISTROS---------------------
    public Producto actualizarProducto(Producto ProductoActualizada) {
        Producto ProductoBD = ProductoRepository.findById(ProductoActualizada.getId()).get();
        ProductoBD.setCategoria(ProductoActualizada.getCategoria());
        ProductoBD.setCodigo(ProductoActualizada.getCodigo());
        ProductoBD.setDescripcion(ProductoActualizada.getDescripcion());
        ProductoBD.setFecha_modificacion(ProductoActualizada.getFecha_modificacion());
        ProductoBD.setNombre(ProductoActualizada.getNombre());
        ProductoBD.setPrecio(ProductoActualizada.getPrecio());
        ProductoBD.setStock(ProductoActualizada.getStock());
        return ProductoRepository.save(ProductoBD);
    }

    public List<Producto> actualizarProductos(List<Producto> ProductosActulizadas) {
        // lista que tendra las Productos de la BD actualizadas
        List<Producto> listaActualizados = new ArrayList<>();
        for (Producto p : ProductosActulizadas) {
            Producto ProductoBD = ProductoRepository.findById(p.getId()).get();
            ProductoBD.setCategoria(p.getCategoria());
            ProductoBD.setCodigo(p.getCodigo());
            ProductoBD.setDescripcion(p.getDescripcion());
            ProductoBD.setFecha_modificacion(p.getFecha_modificacion());
            ProductoBD.setNombre(p.getNombre());
            ProductoBD.setPrecio(p.getPrecio());
            ProductoBD.setStock(p.getStock());
            listaActualizados.add(ProductoBD);
        }
        return ProductoRepository.saveAll(listaActualizados);
    }

    // ---------------------------ELIMINACIÓN DE DATOS------------------------
    public void borrarProducto(Producto Producto) {
        ProductoRepository.delete(Producto);
    }

    public void borrarProductoPorId(int id) {
        ProductoRepository.deleteById(id);
    }

    public void borrarProductos(List<Producto> listaProductos) {
        ProductoRepository.deleteAll(listaProductos);
    }

    public void borrarProductosPorIds(List<Integer> ids) {
        ProductoRepository.deleteAllById(ids);
    }

}

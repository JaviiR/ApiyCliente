package com.example.API.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.example.API.model.Boleta;
import com.example.API.model.Persona;
import com.example.API.model.Venta;
import com.example.API.service.BoletaService;
import com.example.API.utility.ParsearFecha;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.stream.Collectors;
import java.util.NoSuchElementException;

@RestController
public class BoletaController {
    @Autowired
    private BoletaService boletaService;

    @CrossOrigin(origins = "*")
    @GetMapping("/boletas")
    public ResponseEntity<?> obtenerBoletas() {
        try {
            List<Boleta> listaBoletas = boletaService.obtenerTodasLosBoletas();
            if (listaBoletas.size() == 0) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO HAY REGISTROS!!");
            }
            return ResponseEntity.ok(listaBoletas);    
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR INTERNO DEL SERVIDOR");
        }
        
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/boletasConIds")
    public ResponseEntity<?> obtenerBoletas(@Valid @RequestBody(required = false) Object ids) {

        try {
            // si el objeto ingresado no es una lista dara error y retornara el catch
            List<Integer> NuevaLista = (List<Integer>) ids;

            List<Integer> listaIdsValidos = NuevaLista.stream().filter(id -> id instanceof Integer)
                    .map(id -> (Integer) id).collect(Collectors.toList());
            if (listaIdsValidos.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("IDS NO VALIDOS!!");
            }
            List<Boleta> listaBoletas = boletaService.obtenerBoletasConIds(listaIdsValidos);
            if (listaBoletas.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("IDS NO ENCONTRADOS!!");
            }
            return ResponseEntity.ok(listaBoletas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR INTERNO DEL SERVIDOR");
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/boleta")
    public ResponseEntity<?> obtenerBoleta(@RequestHeader Object id) {
        try {
            // si el objeto no es integer dara un error y lo enviare la catch
            Integer idParseado = Integer.parseInt(id.toString());
            Boleta boleta = boletaService.obtenerBoleta(idParseado);
            return ResponseEntity.ok(boleta);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Boleta no encontrada");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR INTERNO DEL SERVIDOR");
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/boleta")
    public ResponseEntity<?> eliminarBoletaPorId(@RequestHeader Object id) {
        try {
            // si el objeto no es integer dara un error y lo enviara al catch
            Integer idParseado = Integer.parseInt(id.toString());
            System.out.println(boletaService.validarBoletaId(idParseado));
            if (!boletaService.validarBoletaId(idParseado)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Boleta no encontrada");
            }
            boletaService.borrarBoletaPorId(idParseado);
            return ResponseEntity.ok().body("Boleta eliminada satisfactoriamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR INTERNO DEL SERVIDOR");
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/boleta")
    public ResponseEntity<?> nuevaBoleta(@RequestBody Object boleta){
        try {
            LinkedHashMap datos = (LinkedHashMap) boleta;
            Boleta boletaNueva = new Boleta();
            boletaNueva.setFecha(ParsearFecha.parsearString(datos.get("fecha").toString()));
            boletaNueva.setNumero_boleta(Integer.parseInt(datos.get("numero_boleta").toString()));
            boletaNueva.setTotal(Double.parseDouble(datos.get("total").toString()));
            boletaNueva.setVenta((Venta)datos.get("venta"));
            boletaService.agregarBoleta(boletaNueva);
            return ResponseEntity.ok().body("Boleta agregada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR INTERNO DEL SERVIDOR");
        }
    }


    @CrossOrigin(origins = "*")
    @PutMapping("/boleta")
    public ResponseEntity<?> actualizarBoleta(@RequestBody Object boleta){
        try {
            LinkedHashMap datos = (LinkedHashMap) boleta;
            Boleta boletaNueva = new Boleta();
            boletaNueva.setId(Integer.parseInt(datos.get("id").toString()));
            if(!boletaService.validarBoletaId(boletaNueva.getId())){
                return ResponseEntity.badRequest().body("Boleta no existe");
            }
            boletaNueva.setTotal(Double.parseDouble(datos.get("total").toString()));
            boletaNueva.setVenta((Venta)datos.get("venta"));
            boletaService.actualizarBoleta(boletaNueva);
            return ResponseEntity.ok().body("Boleta actualizada correctamente");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR INTERNO DEL SERVIDOR");
        }
    }

}

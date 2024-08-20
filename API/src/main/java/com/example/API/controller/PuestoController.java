package com.example.API.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.API.model.Puesto;
import com.example.API.service.PuestoService;
import com.example.API.utility.ParsearFecha;
import com.example.API.utility.Parsearhora;

@RestController
public class PuestoController {
    @Autowired
    private PuestoService puestoService;

    @CrossOrigin(origins = "*")
    @GetMapping("/puestos")
    public ResponseEntity<?> obtenerPuestos(){
        try {
            List<Puesto> puestos=puestoService.obtenerTodasLosPuestos();
            if(puestos.size()==0){
                return ResponseEntity.badRequest().body("No existen puestos!");
            }
            return ResponseEntity.ok().body(puestos);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }


    @CrossOrigin(origins = "*")
    @GetMapping("/puestosConIds")
    public ResponseEntity<?> obtenerPuestosConIds(@RequestBody Object ids){
        try {
            ArrayList<Integer> listaIds=(ArrayList<Integer>)ids;
            List<Puesto> puestos=puestoService.obtenerPuestosConIds(listaIds);
            if(puestos.size()==0){
                return ResponseEntity.badRequest().body("No existen puestos!");
            }
            return ResponseEntity.ok().body(puestos);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }


    @CrossOrigin(origins = "*")
    @PostMapping("/puesto")
    public ResponseEntity<?> nuevoPuesto(@RequestBody Object puesto){
        try {
            LinkedHashMap listaDatosNuevos=(LinkedHashMap)puesto;
            Puesto puestoNuevo=new Puesto();
            puestoNuevo.setNombre(listaDatosNuevos.get("nombre").toString());
            puestoNuevo.setSalario_base(Double.parseDouble(listaDatosNuevos.get("salario_base").toString()));
            puestoNuevo.setFecha_creacion(ParsearFecha.parsearString(listaDatosNuevos.get("fecha_creacion").toString()));
            puestoNuevo.setFecha_modificacion(ParsearFecha.parsearString(listaDatosNuevos.get("fecha_modificacion").toString()));
            puestoNuevo.setHora_ingreso(Parsearhora.parsearString(listaDatosNuevos.get("hora_ingreso").toString()));
            puestoNuevo.setHora_salida(Parsearhora.parsearString(listaDatosNuevos.get("hora_salida").toString()));
            puestoService.agregarPuesto(puestoNuevo);
            return ResponseEntity.ok().body("Puesto agregado");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @CrossOrigin(origins ="*")
    @DeleteMapping("/puesto")
    public ResponseEntity<?> borrarPuesto(@RequestHeader Object id){
        try {
            Integer idParseado=(Integer)id;
            if(!puestoService.validarPuesto(idParseado)){
                return ResponseEntity.badRequest().body("puesto no existe");
            }
            puestoService.borrarPuestoPorId(idParseado);
            return ResponseEntity.ok().body("Puesto Eliminado");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}

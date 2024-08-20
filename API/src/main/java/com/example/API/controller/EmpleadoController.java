package com.example.API.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.API.model.Empleado;
import com.example.API.model.Persona;
import com.example.API.model.Puesto;
import com.example.API.service.EmpleadoService;
import com.example.API.service.PersonaService;
import com.example.API.service.PuestoService;
import com.example.API.utility.ParsearFecha;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;
    @Autowired
    private PersonaService personaService;
    @Autowired
    private PuestoService puestoService;

    @CrossOrigin(origins = "*")
    @GetMapping("/empleados")
    public ResponseEntity<?> listaEmpleados() {
        try {
            List<Empleado> listaEmpleados=empleadoService.obtenerTodasLosEmpleados();
            if(listaEmpleados.size()==0){
                return ResponseEntity.badRequest().body("No existen empleados");
            }
            return ResponseEntity.ok().body(listaEmpleados);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR INTERNO DEL SERVIDOR");
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/empleadosConIds")
    public ResponseEntity<?> listaEmpleadosConIds(@RequestBody(required = false) Object ids) throws Exception{
        try {
            List<Integer> listaIds=(List<Integer>)ids;
            List<Empleado> listaEmpleados=empleadoService.obtenerEmpleadosConIds(listaIds);
            if(listaEmpleados.size()==0){
                return ResponseEntity.badRequest().body("No existen empleados");
            }
            return ResponseEntity.ok().body(listaEmpleados);
        } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/empleado")
    public ResponseEntity<?> obtenerEmpleado(@RequestHeader Object id){
        try {
            System.out.println(id.getClass());
            Integer idParseado=Integer.parseInt(id.toString());
            if(!empleadoService.validarEmpleado(idParseado)){
                return ResponseEntity.badRequest().body("ERROR!: el empleado no existe");
            }
            return ResponseEntity.ok().body(empleadoService.obtenerEmpleado(idParseado));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/empleado")
    public ResponseEntity<?> agregarEmpleado(@RequestBody Object empleado){
        try {
            LinkedHashMap lista=(LinkedHashMap)empleado;
            Empleado empleadoNuevo=new Empleado();
            LinkedHashMap datosPersonaEntrante=(LinkedHashMap)lista.get("persona");
            LinkedHashMap datosPuestoEntrante=(LinkedHashMap)lista.get("puesto");
            if(empleadoService.validarPersona(Integer.parseInt(datosPersonaEntrante.get("id").toString()))){
                return ResponseEntity.badRequest().body("ERROR!: El empleado ya tiene un puesto en la empresa");
            }
            Persona persona=personaService.obtenerPersona(Integer.parseInt(datosPersonaEntrante.get("id").toString()));
            Puesto puesto =puestoService.obtenerPuesto(Integer.parseInt(datosPuestoEntrante.get("id").toString()));
            
            empleadoNuevo.setPersona(persona);
            empleadoNuevo.setPuesto(puesto);
            empleadoNuevo.setSalario_final(Double.parseDouble(lista.get("salario_final").toString()));
            empleadoNuevo.setFecha_ingreso(ParsearFecha.parsearString(lista.get("fecha_ingreso").toString()));
            empleadoService.agregarEmpleado(empleadoNuevo);
            return ResponseEntity.ok().body("empleado agregador correctamente");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getLocalizedMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/empleado")
    public ResponseEntity<?> actualizarEmpleado(@RequestBody Object empleado){
        try {
            LinkedHashMap lista=(LinkedHashMap)empleado;
            Empleado empleadoActualizado=new Empleado();
            empleadoActualizado.setId(Integer.parseInt(lista.get("id").toString()));
            if(!empleadoService.validarEmpleado(empleadoActualizado.getId())){
                return ResponseEntity.badRequest().body("El empleado no existe");
            }
            LinkedHashMap datosPuestoEntrante=(LinkedHashMap)lista.get("puesto");
            Puesto puesto =puestoService.obtenerPuesto(Integer.parseInt(datosPuestoEntrante.get("id").toString()));
            empleadoActualizado.setPuesto(puesto);
            empleadoActualizado.setSalario_final(Double.parseDouble(lista.get("salario_final").toString()));
            empleadoActualizado.setFecha_ingreso(ParsearFecha.parsearString(lista.get("fecha_ingreso").toString()));
            empleadoService.actualizarEmpleado(empleadoActualizado);
            return ResponseEntity.ok().body("empleado actualizado correctamente");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getLocalizedMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/empleado")
    public ResponseEntity<?> eliminarEmpleado(@RequestBody Object empleado){
        try {
            LinkedHashMap lista=(LinkedHashMap)empleado;
            Integer idParseado=Integer.parseInt(lista.get("id").toString());
            if(!empleadoService.validarEmpleado(idParseado)){
                return ResponseEntity.badRequest().body("ERROR!: el empleado no existe");
            }
            empleadoService.borrarEmpleadoPorId(idParseado);
            return ResponseEntity.ok().body("empleado eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
    
    
}

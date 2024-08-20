package com.example.API.controller;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.API.enums.EstadoClienteEnum;
import com.example.API.model.Cliente;
import com.example.API.model.Persona;
import com.example.API.service.ClienteService;
import com.example.API.service.PersonaService;
import com.example.API.utility.ParsearFecha;

@RestController
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private PersonaService personaService;
    
    @CrossOrigin(origins = "*")
    @GetMapping("/clientes")
    public ResponseEntity<?> listaClientes() {
        try {
            List<Cliente> listaClientes=clienteService.obtenerTodasLosClientes();
            if(listaClientes.size()==0){
                return ResponseEntity.badRequest().body("No existen clientes");
            }
            return ResponseEntity.ok().body(listaClientes);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR INTERNO DEL SERVIDOR");
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/clientesConIds")
    public ResponseEntity<?> listaClientesConIds(@RequestBody(required = false) Object ids) throws Exception{
        try {
            List<Integer> listaIds=(List<Integer>)ids;
            List<Cliente> listaClientes=clienteService.obtenerClientesConIds(listaIds);
            if(listaClientes.size()==0){
                return ResponseEntity.badRequest().body("No existen clientes");
            }
            return ResponseEntity.ok().body(listaClientes);
        } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/cliente")
    public ResponseEntity<?> obtenercliente(@RequestHeader Object id){
        try {
            Integer idParseado=Integer.parseInt(id.toString());
            if(!clienteService.validarCliente(idParseado)){
                return ResponseEntity.badRequest().body("ERROR!: el cliente no existe");
            }
            return ResponseEntity.ok().body(clienteService.obtenerCliente(idParseado));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/cliente")
    public ResponseEntity<?> agregarcliente(@RequestBody Object cliente){
        try {
            LinkedHashMap lista=(LinkedHashMap)cliente;
            Cliente clienteNuevo=new Cliente();
            LinkedHashMap datosPersonaEntrante=(LinkedHashMap)lista.get("persona");
            if(clienteService.validarPersona(Integer.parseInt(datosPersonaEntrante.get("id").toString()))){
                return ResponseEntity.badRequest().body("ERROR!: El cliente ya esta registrado en el servidor");
            }
            Persona persona=personaService.obtenerPersona(Integer.parseInt(datosPersonaEntrante.get("id").toString()));
            clienteNuevo.setPersona(persona);
            clienteNuevo.setFecha_creacion(ParsearFecha.parsearString(lista.get("fecha_creacion").toString()));
            String estadoString=lista.get("estado").toString();
            EstadoClienteEnum estado=EstadoClienteEnum.ACTIVO;
            switch(estadoString){
                case "ACTIVO":
                estado=EstadoClienteEnum.ACTIVO;
                break;
                case "INACTIVO":
                estado=EstadoClienteEnum.INACTIVO;
                break;
                case "SUSPENDIDO":
                estado=EstadoClienteEnum.SUSPENDIDO;
                break;
                default:
                estado=EstadoClienteEnum.ACTIVO;
                break;
            }
            clienteNuevo.setEstado(estado);
            clienteNuevo.setUsuario(lista.get("usuario").toString());
            clienteNuevo.setContraseña(lista.get("contraseña").toString());
            clienteService.agregarCliente(clienteNuevo);
            return ResponseEntity.ok().body("cliente agregador correctamente");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getLocalizedMessage());
        }
    }

    /*@CrossOrigin(origins = "*")
    @PutMapping("/cliente")
    public ResponseEntity<?> actualizarcliente(@RequestBody Object cliente){
        try {
            LinkedHashMap lista=(LinkedHashMap)cliente;
            Cliente clienteActualizado=new Cliente();
            clienteActualizado.setId(Integer.parseInt(lista.get("id").toString()));
            if(!clienteService.validarcliente(clienteActualizado.getId())){
                return ResponseEntity.badRequest().body("El cliente no existe");
            }
            LinkedHashMap datosPuestoEntrante=(LinkedHashMap)lista.get("puesto");
            Puesto puesto =puestoService.obtenerPuesto(Integer.parseInt(datosPuestoEntrante.get("id").toString()));
            clienteActualizado.setPuesto(puesto);
            clienteActualizado.setSalario_final(Double.parseDouble(lista.get("salario_final").toString()));
            clienteActualizado.setFecha_ingreso(ParsearFecha.parsearString(lista.get("fecha_ingreso").toString()));
            clienteService.actualizarcliente(clienteActualizado);
            return ResponseEntity.ok().body("cliente actualizado correctamente");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getLocalizedMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/cliente")
    public ResponseEntity<?> eliminarcliente(@RequestBody Object cliente){
        try {
            LinkedHashMap lista=(LinkedHashMap)cliente;
            Integer idParseado=Integer.parseInt(lista.get("id").toString());
            if(!clienteService.validarCliente(idParseado)){
                return ResponseEntity.badRequest().body("ERROR!: el cliente no existe");
            }
            clienteService.borrarClientePorId(idParseado);
            return ResponseEntity.ok().body("cliente eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }*/
}

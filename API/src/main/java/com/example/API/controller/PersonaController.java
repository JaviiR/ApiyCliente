package com.example.API.controller;

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
import java.util.stream.Collectors;
import java.util.LinkedHashMap;
import com.example.API.model.Boleta;
import com.example.API.model.Persona;
import com.example.API.service.PersonaService;
import com.example.API.utility.ParsearFecha;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import jakarta.validation.Valid;

@RestController
public class PersonaController {
    @Autowired
    private PersonaService personaService;

    // obtener todas las personas
    @CrossOrigin(origins = "*")
    @GetMapping("/personas")
    public ResponseEntity<?> obtenerPersonas() {
    List<Persona> listaPersonas = personaService.obtenerTodasLasPersonas();

    if (listaPersonas.size() == 0) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO HAY REGISTROS!!");
                                    }
                                    
        return ResponseEntity.ok(listaPersonas);
                                    
    }




    // obtener todas las personas filtradas por id
    @CrossOrigin(origins = "*")
    @GetMapping("/personasConIds")
    public ResponseEntity<?> obtenerPersonasConIds(@RequestBody(required = false) Object ids) {
        try {
            // si el objeto ingresado no es una lista dara error y retornara el catch
            List<Integer> listaParseada = (List<Integer>) ids;
            List<Integer> listaIdsValidos = listaParseada.stream()
                    .filter(id -> id instanceof Integer)
                    .map(id -> (Integer) id)
                    .collect(Collectors.toList());

            if (listaIdsValidos.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("LOS IDS DEBEN SER ENTEROS!!");
            }
            List<Persona> listaPersonas = personaService.obtenerPersonasConIds(listaIdsValidos);
            if (listaPersonas.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("IDS NO ENCONTRADOS!!");
            }
            return ResponseEntity.ok(listaPersonas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR interno del servidor");

        }

    }

    // agregar persona
    @CrossOrigin(origins = "*")
    @PostMapping("/persona")
    public ResponseEntity<?> nuevaPersona(@RequestBody Object persona) {
        try {
            LinkedHashMap datos = (LinkedHashMap) persona;
            Persona personaNueva = new Persona();
            personaNueva.setNombres(datos.get("nombres").toString());
            personaNueva.setApellidos(datos.get("apellidos").toString());
            personaNueva.setCorreo(datos.get("correo").toString());
            personaNueva.setDireccion(datos.get("direccion").toString());
            personaNueva.setNum_documento(Long.parseLong(datos.get("num_documento").toString()));
            personaNueva.setTelefono(datos.get("telefono").toString());
            personaNueva.setTipo_documento(datos.get("tipo_documento").toString());
            personaNueva.setFecha_actualizacion(ParsearFecha.parsearString(datos.get("fecha_actualizacion").toString()));
            personaNueva.setFecha_creacion(ParsearFecha.parsearString(datos.get("fecha_creacion").toString()));
            personaService.agregarPersona(personaNueva);
            return ResponseEntity.ok().body("Persona agregada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("DATOS INVALIDOS");
        }
    }

    // ACtualizar persona
    @CrossOrigin(origins = "*")
    @PutMapping("/persona")
    public ResponseEntity<?> actualizarPersona(@RequestBody Object persona) {
        try {
            LinkedHashMap datos = (LinkedHashMap) persona;
            Persona personaNueva = new Persona();
            personaNueva.setId(Integer.parseInt(datos.get("id").toString()));
            if (!personaService.validarPersonaId(personaNueva.getId())) {
                return ResponseEntity.badRequest().body("Persona no existe");
            }
            personaNueva.setNombres(datos.get("nombres").toString());
            personaNueva.setApellidos(datos.get("apellidos").toString());
            personaNueva.setCorreo(datos.get("correo").toString());
            personaNueva.setDireccion(datos.get("direccion").toString());
            personaNueva.setNum_documento(Long.parseLong(datos.get("num_documento").toString()));
            personaNueva.setTelefono(datos.get("telefono").toString());
            personaNueva.setTipo_documento(datos.get("tipo_documento").toString());
            personaNueva.setFecha_actualizacion(ParsearFecha.parsearString(datos.get("fecha_actualizacion").toString()));
            personaNueva.setFecha_creacion(ParsearFecha.parsearString(datos.get("fecha_creacion").toString()));
            personaService.agregarPersona(personaNueva);
            return ResponseEntity.ok().body("Persona actualizada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR INTERNO DEL SERVIDOR");
        }
    }

    // Eliminar persona
    @CrossOrigin(origins = "*")
    @DeleteMapping("/persona")
    public ResponseEntity<?> eliminarPersona(@RequestHeader Object id) {
        try {
            Integer idParseado = Integer.parseInt(id.toString());
            if (!personaService.validarPersonaId(idParseado)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Persona no existe");
            } else {
                personaService.borrarPersonaPorId(idParseado);
                return ResponseEntity.ok().body("persona eliminada");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR INTERNO DEL SERVIDOR");
        }
    }

}

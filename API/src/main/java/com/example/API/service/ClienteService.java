package com.example.API.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.example.API.model.Cliente;
import com.example.API.model.Persona;
import com.example.API.repository.ClienteRepository;
import com.example.API.repository.PersonaRepository;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PersonaRepository personaRepository;

    // ---------------------------OBTENCIÓN DE DATOS-----------------------------
    public List<Cliente> obtenerTodasLosClientes() {
        return clienteRepository.findAll();
    }

    public List<Cliente> obtenerClientesConIds(List<Integer> listaIds) {
        return clienteRepository.findAllById(listaIds);
    }

    public Cliente obtenerCliente(int id) {
        return clienteRepository.findById(id).get();
    }

    // --------------------------------------------------------------------------
    // -----------------------------CREACIÓN DE REGISTROS------------------------
    public Cliente agregarCliente(Cliente Cliente) {
        return clienteRepository.save(Cliente);
    }

    public List<Cliente> agregarClientes(List<Cliente> listaClientes) {
        return clienteRepository.saveAll(listaClientes);
    }

    // --------------------------------------------------------------------------
    // ---------------------------ACTUALIZACIÓN DE REGISTROS---------------------
    public Cliente actualizarCliente(Cliente ClienteActualizada) {
        Cliente ClienteBD = clienteRepository.findById(ClienteActualizada.getId()).get();
        ClienteBD.setPersona(ClienteActualizada.getPersona());
        ClienteBD.setContraseña(ClienteActualizada.getContraseña());
        ClienteBD.setEstado(ClienteActualizada.getEstado());
        ClienteBD.setUsuario(ClienteActualizada.getUsuario());
        return clienteRepository.save(ClienteBD);
    }

    public List<Cliente> actualizarClientes(List<Cliente> ClientesActulizadas) {
        // lista que tendra las Clientes de la BD actualizadas
        List<Cliente> listaActualizados = new ArrayList<>();
        for (Cliente p : ClientesActulizadas) {
            Cliente ClienteBD = clienteRepository.findById(p.getId()).get();
            ClienteBD.setPersona(p.getPersona());
            ClienteBD.setContraseña(p.getContraseña());
            ClienteBD.setEstado(p.getEstado());
            ClienteBD.setUsuario(p.getUsuario());
            listaActualizados.add(ClienteBD);
        }
        return clienteRepository.saveAll(listaActualizados);
    }

    // ---------------------------ELIMINACIÓN DE DATOS------------------------
    public void borrarCliente(Cliente Cliente) {
        clienteRepository.delete(Cliente);
    }

    public void borrarClientePorId(int id) {
        clienteRepository.deleteById(id);
    }

    public void borrarClientes(List<Cliente> listaClientes) {
        clienteRepository.deleteAll(listaClientes);
    }

    public void borrarClientesPorIds(List<Integer> ids) {
        clienteRepository.deleteAllById(ids);
    }

    public boolean validarCliente(int id){
        return clienteRepository.existsById(id);
    }

    public boolean validarPersona(int idPersona){
        Persona persona=personaRepository.findById(idPersona).get();
        return clienteRepository.existsByPersona(persona);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Cuentas.Service.ServiceImplement;

import com.example.Cuentas.Entity.Cliente;
import com.example.Cuentas.Repository.ClienteRepository;
import com.example.Cuentas.Service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author USER
 */
@Service
@RequiredArgsConstructor
public class ClienteServiceImplement implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public List<Cliente> listAllClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente obtenerClienteId(Integer id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public Cliente crearCliente(Cliente cliente) {
        cliente.setEstado("true");
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente actualizarCliente(Cliente cliente) {
        Cliente exist = clienteRepository.findById(cliente.getPersonaId()).orElse(null);
        if(exist == null){
            return null;
        }
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente eliminarClinete(Integer id) {
        Cliente eliminar = clienteRepository.findById(id).orElse(null);
        if(eliminar == null){
            return null;
        }
        eliminar.setEstado("False");
        return clienteRepository.save(eliminar);
    }
}

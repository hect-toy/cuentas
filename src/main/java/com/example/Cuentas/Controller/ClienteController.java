/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Cuentas.Controller;

import com.example.Cuentas.Entity.Cliente;
import com.example.Cuentas.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente){
        Cliente newCliente = clienteService.crearCliente(cliente);
        if (newCliente == null){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(newCliente);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> obtenerClientes(){
        List<Cliente> clientes = new ArrayList<>();
        clientes = clienteService.listAllClientes();
        if (clientes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clientes);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> obtenerClienteId(@PathVariable("id")int id){
        Cliente cliente = clienteService.obtenerClienteId(id);
        if (cliente == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable("id")int id,@RequestBody Cliente cliente){
        cliente.setPersonaId(id);
        Cliente clienteActualizado = clienteService.actualizarCliente(cliente);
        if (clienteActualizado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clienteActualizado);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Cliente> borrarCliente(@PathVariable("id")int id){
        Cliente cliente = clienteService.eliminarClinete(id);
        if(cliente==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }

}

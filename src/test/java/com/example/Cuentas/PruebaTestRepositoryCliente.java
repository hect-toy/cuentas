package com.example.Cuentas;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.Cuentas.Entity.Cliente;
import com.example.Cuentas.Repository.ClienteRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class PruebaTestRepositoryCliente {
    

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void busquedaCliente(){
        List<Cliente> listaClientes = clienteRepository.findAll();
        Assertions.assertThat(listaClientes.size()).isEqualTo(1);
    }


    @Test
    public void crearCliente(){
        Cliente cliente = Cliente.builder()
                .nombre("Juan Perez")
                .genero("M")
                .edad(33)
                .identificacion("INE")
                .direccion("calle #3")
                .telefono("96755390")
                .contrasena("12345")
                .estado("True")
                .build();

        Cliente newCliente = clienteRepository.save(cliente);
        Assertions.assertThat(newCliente.getPersonaId()).isEqualTo(3);
    }

    @Test
    public void actualizaCliente(){
        Cliente cliente = Cliente.builder()
                .personaId(4)
                .nombre("Martha Lopez")
                .genero("F")
                .edad(24)
                .identificacion("Cartilla")
                .direccion("calle #4")
                .telefono("44565878")
                .contrasena("46865447")
                .estado("True")
                .build();
        Cliente upCliente = clienteRepository.save(cliente);
        Assertions.assertThat(upCliente.getNombre()).isEqualTo("Martha Lopez");
        Assertions.assertThat(upCliente.getGenero()).isEqualTo("F");
        Assertions.assertThat(upCliente.getEdad()).isEqualTo(24);
        Assertions.assertThat(upCliente.getIdentificacion()).isEqualTo("Cartilla");
        Assertions.assertThat(upCliente.getDireccion()).isEqualTo("calle #4");
        Assertions.assertThat(upCliente.getTelefono()).isEqualTo("44565878");
        Assertions.assertThat(upCliente.getContrasena()).isEqualTo("46865447");
        Assertions.assertThat(upCliente.getEstado()).isEqualTo("True");
    }

    @Test
    public void eliminarCliente(){
        Cliente cliente = Cliente.builder()
                .personaId(4)
                .estado("False")
                .build();
        Cliente clienteElimnado = clienteRepository.save(cliente);
        Assertions.assertThat(clienteElimnado.getEstado()).isEqualTo("False");
    }
}

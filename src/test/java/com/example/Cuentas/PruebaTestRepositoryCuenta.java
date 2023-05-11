package com.example.Cuentas;

import com.example.Cuentas.Entity.Cuenta;
import com.example.Cuentas.Repository.CuentaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class PruebaTestRepositoryCuenta {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Test
    public void buscarCuentaNumeroDecuenta(){
        Cuenta cuenta = cuentaRepository.findById(123451).orElse(null);
        Assertions.assertThat(cuenta.getNumeroCuenta()).isEqualTo(123451);
    }

    @Test
    public void eliminarCuenta(){
        Cuenta cuenta = Cuenta.builder()
                .numeroCuenta(123451)
                .estado("False")
                .build();
        Cuenta cuentaElimnada = cuentaRepository.save(cuenta);
        Assertions.assertThat(cuentaElimnada.getEstado()).isEqualTo("False");
    }

    @Test
    public void obtenerCuentas(){
        List<Cuenta> cuentas = cuentaRepository.findAll();
        Assertions.assertThat(cuentas).isNotEmpty();
    }

    @Test
    public void actualizarCuenta(){
        Cuenta cuenta = Cuenta.builder()
                .numeroCuenta(123451)
                .tipo("ahorro")
                .saldoInicial(2000F)
                .estado("True")
                .build();
        Cuenta upActualizada = cuentaRepository.save(cuenta);
        Assertions.assertThat(upActualizada.getNumeroCuenta()).isEqualTo(123451);
        Assertions.assertThat(upActualizada.getTipo()).isEqualTo("ahorro");
        Assertions.assertThat(upActualizada.getSaldoInicial()).isEqualTo(2000F);
        Assertions.assertThat(upActualizada.getEstado()).isEqualTo("True");
    }

    @Test
    public void cearCuenta(){
        Cuenta cuenta = Cuenta.builder()
                .numeroCuenta(678956)
                .tipo("corriente")
                .saldoInicial(2000F)
                .estado("True")
                .build();
        Cuenta newCuenta = cuentaRepository.save(cuenta);
        Assertions.assertThat(newCuenta.getNumeroCuenta()).isEqualTo(678956);
        Assertions.assertThat(newCuenta.getTipo()).isEqualTo("corriente");
        Assertions.assertThat(newCuenta.getSaldoInicial()).isEqualTo(2000F);
        Assertions.assertThat(newCuenta.getEstado()).isEqualTo("True");
    }
}

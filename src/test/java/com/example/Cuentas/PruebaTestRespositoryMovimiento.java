package com.example.Cuentas;

import com.example.Cuentas.DTO.MovimientosCliente;
import com.example.Cuentas.Entity.Cuenta;
import com.example.Cuentas.Entity.Movimientos;
import com.example.Cuentas.Repository.MovimientoRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Slf4j
public class PruebaTestRespositoryMovimiento {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Test
    public void consultarMovimientos(){
        List<Movimientos> movimientos = movimientoRepository.findAll();
        Assertions.assertThat(movimientos.size()).isEqualTo(4);
    }

    @Test
    public void consultarMovimientoXCuenta(){
        Cuenta cuenta = Cuenta.builder().numeroCuenta(123451).build();
        List<Movimientos> movimientos = movimientoRepository.findByCuenta(cuenta);
        Assertions.assertThat(movimientos).isNotEmpty();
    }

    @Test
    public void actualizaMovimiento(){
        Cuenta cuenta = Cuenta.builder().numeroCuenta(123451).build();
        Movimientos movimientos = Movimientos.builder()
                .cuenta(cuenta)
                .fecha("2022-05-09")
                .tipoMovimiento("abono")
                .valor(400F)
                .saldo(0F)
                .build();
        Movimientos newMovimiento = movimientoRepository.save(movimientos);
        Assertions.assertThat(newMovimiento.getMovimientoId()).isNotNull();
    }

    @Test
    public void eliminarMovimiento(){
        movimientoRepository.deleteById(1);
        Movimientos movimientos = movimientoRepository.findById(1).orElse(null);
        Assertions.assertThat(movimientos).isNull();
    }

    @Test
    public void crearMovimiento(){
        Cuenta cuenta = Cuenta.builder().numeroCuenta(678956).build();
        Movimientos movimientos = Movimientos.builder()
                .cuenta(cuenta)
                .fecha("2022-05-09")
                .tipoMovimiento("abono")
                .valor(400F)
                .saldo(0F)
                .build();
        Movimientos newMovimiento = movimientoRepository.save(movimientos);
        Assertions.assertThat(newMovimiento.getMovimientoId()).isNotNull();
    }

    @Test void reporteXfechas(){
        List<Movimientos> movimientosXFecha = movimientoRepository.findByFechaBetween("2022-05-03","2022-05-04");
        Assertions.assertThat(movimientosXFecha.size()).isEqualTo(3);
    }

    @Test
    public void reporteFechaCliente(){
        List<MovimientosCliente> movimientoCliente = movimientoRepository.findByFechaCliente(1,"2022-05-03");
        Assertions.assertThat(movimientoCliente).isNotEmpty();
    }
}

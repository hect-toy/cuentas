package com.example.Cuentas.Repository;

import com.example.Cuentas.DTO.MovimientosCliente;
import com.example.Cuentas.Entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Cuentas.Entity.Movimientos;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 *
 * @author USER
 */
public interface MovimientoRepository extends JpaRepository<Movimientos, Integer>{

    public List<Movimientos> findByCuenta(Cuenta numeroCuenta);

    public List<Movimientos> findByFechaBetween(String fechaInicio,String fechaFin);

    /*
    @Query("SELECT m.movimientoId,m.fecha,cl.nombre,cu.numeroCuenta,cu.tipo,cu.saldoInicial,cu.estado,m.valor,m.saldo FROM Movimientos m \n" +
            "\tJOIN Cuenta cu ON m.cuenta = cu.numeroCuenta\n" +
            "\tJOIN Cliente cl ON cl.id = cu.clientedI\n" +
            "\twhere cl.id = ?1 AND m.fecha = ?2")
    public List<Movimientos> findByFechaCliente(Integer cienteId, String fecha);
*/
    @Query("SELECT new com.example.Cuentas.DTO.MovimientosCliente(" +
            "m.fecha, cl.nombre, cu.numeroCuenta, cu.tipo, cu.saldoInicial, cu.estado, m.valor, m.saldo) " +
            "FROM Movimientos m " +
            "INNER JOIN Cuenta cu ON m.cuenta = cu.numeroCuenta " +
            "INNER JOIN Cliente cl ON cl.id = cu.clientedI " +
            "WHERE cl.id = ?1 AND m.fecha = ?2")
    public List<MovimientosCliente> findByFechaCliente(Integer clienteId, String fecha);

}

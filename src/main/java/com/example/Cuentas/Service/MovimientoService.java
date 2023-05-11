package com.example.Cuentas.Service;

import com.example.Cuentas.DTO.MovimientosCliente;
import com.example.Cuentas.DTO.RespuestaDTO;
import com.example.Cuentas.Entity.Movimientos;

import java.util.List;

public interface MovimientoService {
    public List<Movimientos> listAllMovimiento();
    public List<Movimientos> obtenerMovimientoCuenta(Integer numeroCuenta);
    public RespuestaDTO crearMovimiento(Movimientos movimientos);
    public Movimientos actualizarMovimiento(Movimientos movimientos);
    public String eliminarMovimiento(Integer numeroCuenta);
    public List<Movimientos> listAllMovimientoFecha(String fechainicio,String fechaFin);
    public List<MovimientosCliente> listMovimientoFechaCliente(Integer clienteId, String fecha);
}

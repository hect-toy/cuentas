package com.example.Cuentas.Service;

import com.example.Cuentas.Entity.Cuenta;

import java.util.List;

public interface CuentaService {

    public List<Cuenta> listAllCuentas();
    public Cuenta obtenerCuentaId(Integer id);
    public Cuenta crearCuenta(Cuenta cuenta);

    public Cuenta actualizarCuenta(Cuenta cuenta);

    public Cuenta eliminarCuenta(Integer numeroCuenta);
}

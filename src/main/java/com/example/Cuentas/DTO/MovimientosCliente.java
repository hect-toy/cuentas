package com.example.Cuentas.DTO;

import lombok.*;

@Data
@AllArgsConstructor
public class MovimientosCliente {
    private String fecha;
    private String nombreCliente;
    private Integer numeroCuenta;
    private String tipoCuenta;
    private Float saldoInicial;
    private String estado;
    private Float valor;
    private Float saldo;
}

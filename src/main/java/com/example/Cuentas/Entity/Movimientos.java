/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Cuentas.Entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

/**
 *
 * @author USER
 */
@Entity
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_movimientos")
public class Movimientos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movimiento_id")
    private Integer movimientoId;
   
    @Column(name = "fecha")
    private String fecha;
   
    @Column(name = "tipo_movimiento")
    private String tipoMovimiento;
 
    @Column(name = "valor")
    private Float valor;
   
    @Column(name = "saldo")
    private Float saldo;

    @ManyToOne
    @JoinColumn(name = "numero_cuenta")
    private Cuenta cuenta;
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Cuentas.Entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

/**
 *
 * @author USER
 */
@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_cuenta")
public class Cuenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "numero_cuenta")
    private Integer numeroCuenta;
  
    @Column(name = "tipo")
    private String tipo;
   
    @Column(name = "saldo_inicial")
    private Float saldoInicial;

    @Column(name = "estado")
    private String estado;

    @Column(name = "cliente_id")
    private Integer clientedI;

    @JsonIgnore
    @OneToMany(mappedBy = "cuenta")
    private Collection<Movimientos> tblMovimientosCollection;

}

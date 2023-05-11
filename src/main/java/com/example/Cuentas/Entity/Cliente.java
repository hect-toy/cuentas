/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Cuentas.Entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 *
 * @author USER
 */
@Entity
@Table(name = "tbl_cliente")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Cliente extends Persona {
   
    @Column(name = "contrasena")
    private String contrasena;

    @Column(name = "estado")
    private String estado;

}

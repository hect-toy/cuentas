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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_persona")
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer personaId;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "genero")
    private String genero;
    
    @Column(name = "edad")
    private int edad;
    
    @Column(name = "identificacion")
    private String identificacion;
    
    @Column(name = "direccion")
    private String direccion;
   
    @Column(name = "telefono")
    private String telefono;
   

}

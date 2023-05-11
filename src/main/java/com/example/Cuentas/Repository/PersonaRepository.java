/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Cuentas.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Cuentas.Entity.Persona;

/**
 *
 * @author USER
 */
public interface PersonaRepository extends JpaRepository<Persona, Integer>{
    
}

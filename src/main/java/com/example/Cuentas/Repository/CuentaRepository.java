/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Cuentas.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Cuentas.Entity.Cuenta;

/**
 *
 * @author USER
 */
public interface CuentaRepository extends JpaRepository<Cuenta, Integer>{

}

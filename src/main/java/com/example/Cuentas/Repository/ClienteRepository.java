
package com.example.Cuentas.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Cuentas.Entity.Cliente;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author USER
 */
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
}

package com.example.Cuentas.Controller;

import com.example.Cuentas.Entity.Cuenta;
import com.example.Cuentas.Service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/cuenta")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @PostMapping
    public ResponseEntity<Cuenta> crearCuenta(@RequestBody Cuenta cuenta){
        Cuenta newCuenta = cuentaService.crearCuenta(cuenta);
        if (newCuenta == null){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(newCuenta);
    }

    @GetMapping
    public ResponseEntity<List<Cuenta>> obtenerCuenta(){
        List<Cuenta> cuentas = new ArrayList<>();
        cuentas = cuentaService.listAllCuentas();
        if (cuentas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cuentas);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cuenta> obtenerCuentaId(@PathVariable("id")int id){
        Cuenta cuenta = cuentaService.obtenerCuentaId(id);
        if (cuenta == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cuenta);
    }

    @PutMapping(value = "/{numeroCuenta}")
    public ResponseEntity<Cuenta> actualizarCuenta(@PathVariable("numeroCuenta")int numeroCuenta,@RequestBody Cuenta cuenta){
        cuenta.setNumeroCuenta(numeroCuenta);
        Cuenta cuentaActualizado = cuentaService.actualizarCuenta(cuenta);
        if (cuentaActualizado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cuentaActualizado);
    }

    @DeleteMapping(value = "/{numeroCuenta}")
    public ResponseEntity<Cuenta> borrarCuenta(@PathVariable("numeroCuenta")Integer numeroCuenta){
        Cuenta cuenta = cuentaService.eliminarCuenta(numeroCuenta);
        if(cuenta==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cuenta);
    }
}

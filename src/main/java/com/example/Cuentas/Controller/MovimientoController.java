package com.example.Cuentas.Controller;

import com.example.Cuentas.DTO.MovimientosCliente;
import com.example.Cuentas.DTO.RespuestaDTO;
import com.example.Cuentas.Entity.Cuenta;
import com.example.Cuentas.Entity.Movimientos;
import com.example.Cuentas.Service.MovimientoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/movimiento")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @GetMapping
    public ResponseEntity<List<Movimientos>> obtenerMovimientos(){
        List<Movimientos> movimientos = new ArrayList<>();
        movimientos = movimientoService.listAllMovimiento();
        if (movimientos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(movimientos);
    }

    @GetMapping(value = "/{numeroCuenta}")
    public ResponseEntity<List<Movimientos>> obtenerMovimientoCuenta(@PathVariable("numeroCuenta")int numeroCuenta){
        List<Movimientos> movimiento = movimientoService.obtenerMovimientoCuenta(numeroCuenta);
        if (movimiento.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movimiento);
    }

    @PutMapping(value = "/{numeroCuenta}")
    public ResponseEntity<Movimientos> actualizarMovimiento(@PathVariable("numeroCuenta")int numeroCuenta,@RequestBody Movimientos movimiento){
        movimiento.setCuenta(Cuenta.builder().numeroCuenta(numeroCuenta).build());
        Movimientos movimientoActualizado = movimientoService.actualizarMovimiento(movimiento);
        if (movimientoActualizado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movimientoActualizado);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> borrarMovimiento(@PathVariable("id")int id){
        String mensaje = movimientoService.eliminarMovimiento(id);
        if(mensaje==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mensaje);
    }

    /*CREAR METODOS CON LA LOGICA SOLICITADA PARA MOVIMIENTO*/
    @PostMapping
    public ResponseEntity<RespuestaDTO> crearMovimiento(@RequestBody Movimientos movimiento){
        log.info(movimiento.toString());
        RespuestaDTO newMovimiento = movimientoService.crearMovimiento(movimiento);
        if(newMovimiento.getMensaje() != null){
            return ResponseEntity.ok(newMovimiento);
        }
        if (newMovimiento.getMovimientos() == null){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(newMovimiento);
    }

    @GetMapping(value = "/reportes")
    public ResponseEntity<List<Movimientos>> obtenerMovimientosRangoFechas(
            @RequestParam (name = "fechaInicio") String fechaInicio,@RequestParam(name = "fechaFin")String fechaFin){
        List<Movimientos> listaMovimientos = movimientoService.listAllMovimientoFecha(fechaInicio,fechaFin);
        if(listaMovimientos.isEmpty() || listaMovimientos == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listaMovimientos);
    }

    @GetMapping(value = "/reposteCliente")
    public ResponseEntity<List<MovimientosCliente>> obtenerMovimientosCliente(
            @RequestParam (name = "clienteId") Integer clienteId,@RequestParam(name = "fecha")String fecha){
        List<MovimientosCliente> listaMovimientos = movimientoService.listMovimientoFechaCliente(clienteId,fecha);
        if(listaMovimientos.isEmpty() || listaMovimientos == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listaMovimientos);
    }
}

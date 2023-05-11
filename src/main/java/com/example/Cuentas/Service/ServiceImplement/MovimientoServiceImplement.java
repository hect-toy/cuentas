package com.example.Cuentas.Service.ServiceImplement;

import com.example.Cuentas.DTO.MovimientosCliente;
import com.example.Cuentas.DTO.RespuestaDTO;
import com.example.Cuentas.Entity.Cuenta;
import com.example.Cuentas.Entity.Movimientos;
import com.example.Cuentas.Repository.CuentaRepository;
import com.example.Cuentas.Repository.MovimientoRepository;
import com.example.Cuentas.Service.MovimientoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovimientoServiceImplement implements MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;
    @Override
    public List<Movimientos> listAllMovimiento() {
        return movimientoRepository.findAll();
    }

    @Override
    public List<Movimientos> obtenerMovimientoCuenta(Integer numeroCuenta) {
        return movimientoRepository.findByCuenta(Cuenta.builder().numeroCuenta(numeroCuenta).build());
    }

    @Override
    public Movimientos actualizarMovimiento(Movimientos movimientos) {
        Movimientos exist = movimientoRepository.findById(movimientos.getMovimientoId()).orElse(null);
        if(exist == null){
            return null;
        }
        return movimientoRepository.save(movimientos);
    }

    @Override
    public String eliminarMovimiento(Integer id) {
        String mensaje = null;
        Movimientos exist = movimientoRepository.findById(id).orElse(null);
        Cuenta cuenta = Cuenta.builder().numeroCuenta(exist.getCuenta().getNumeroCuenta()).saldoInicial(exist.getSaldo()).build();
        cuentaRepository.save(cuenta);
        movimientoRepository.deleteById(id);
        exist = movimientoRepository.findById(id).orElse(null);
        if(exist == null){
            mensaje = "Se elimino el movimiento exitosamente";
        }
        return mensaje;
    }


    @Override
    public RespuestaDTO crearMovimiento(Movimientos movimientos) {
        Float saldoDisponible = null;
        RespuestaDTO respuesta = null;
        log.info(movimientos.getCuenta().getNumeroCuenta().toString());
        Cuenta cuenta = cuentaRepository.findById(movimientos.getCuenta().getNumeroCuenta()).orElse(null);

        if(cuenta.getEstado().equals("False")){
            respuesta = RespuestaDTO.builder().mensaje("La cuenta esta inactiva").build();
            return respuesta;
        }
        List<Movimientos> exite = movimientoRepository.findByCuenta(cuenta);
        if(exite.isEmpty()){
            saldoDisponible = cuenta.getSaldoInicial();
        }else{
            int ultimo = exite.size();
            saldoDisponible = exite.get(ultimo).getSaldo();
        }

        switch (movimientos.getTipoMovimiento()) {
            case "abono":
                saldoDisponible += movimientos.getValor();
            case "retiro":
                if(cuenta.getTipo().equals("corriente") && saldoDisponible == 0){
                    respuesta = RespuestaDTO.builder().mensaje("saldo insuficiente").build();
                    return respuesta;
                }
                saldoDisponible -= movimientos.getValor();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + movimientos.getTipoMovimiento());
        }
        String pattern = "MM/dd/yyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        Date today = Calendar.getInstance().getTime();
        String fecha = df.format(today);

        movimientos.setFecha(fecha);
        movimientos.setSaldo(saldoDisponible);
        Movimientos newMovimiento = movimientoRepository.save(movimientos);
        respuesta = RespuestaDTO.builder().movimientos(newMovimiento).build();
        return respuesta;
    }

    @Override
    public List<Movimientos> listAllMovimientoFecha(String fechaInicio,String fechaFin){
        return movimientoRepository.findByFechaBetween(fechaInicio,fechaFin);
    }

    @Override
    public List<MovimientosCliente> listMovimientoFechaCliente(Integer clienteId,String fecha){
        return movimientoRepository.findByFechaCliente(clienteId,fecha);
    }

}

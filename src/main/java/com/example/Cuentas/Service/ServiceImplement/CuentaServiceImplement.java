package com.example.Cuentas.Service.ServiceImplement;

import com.example.Cuentas.Entity.Cliente;
import com.example.Cuentas.Entity.Cuenta;
import com.example.Cuentas.Repository.ClienteRepository;
import com.example.Cuentas.Repository.CuentaRepository;
import com.example.Cuentas.Service.CuentaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CuentaServiceImplement implements CuentaService {

    private final CuentaRepository cuentaRepository;
    private final ClienteRepository clienteRepository;

    @Override
    public List<Cuenta> listAllCuentas() {
        return cuentaRepository.findAll();
    }

    @Override
    public Cuenta obtenerCuentaId(Integer id) {
        return cuentaRepository.findById(id).orElse(null);
    }

    @Override
    public Cuenta crearCuenta(Cuenta cuenta) {
        Cuenta exist = obtenerCuentaId(cuenta.getNumeroCuenta());
        if(exist != null){
            return null;
        }
        cuenta.setEstado("true");
        return cuentaRepository.save(cuenta);
    }

    @Override
    public Cuenta actualizarCuenta(Cuenta cuenta) {
        Cliente cliente = clienteRepository.findById(cuenta.getClientedI()).orElse(null);
        if(cliente == null){
            return null;
        }
        Cuenta exist = cuentaRepository.findById(cuenta.getNumeroCuenta()).orElse(null);
        if(exist == null){
            return null;
        }
        return cuentaRepository.save(cuenta);
    }

    @Override
    public Cuenta eliminarCuenta(Integer numeroCuenta) {
        Cuenta eliminar = cuentaRepository.findById(numeroCuenta).orElse(null);
        if(eliminar == null){
            return null;
        }
        eliminar.setEstado("False");
        return cuentaRepository.save(eliminar);
    }
}

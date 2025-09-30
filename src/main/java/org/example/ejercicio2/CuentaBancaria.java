package org.example.ejercicio2;

import java.util.List;

public interface CuentaBancaria {

    boolean retirar(double cantidad);
    void ingresar(double cantidad);
    double consultarSaldo();
    List<String> obtenerHistorial();
}

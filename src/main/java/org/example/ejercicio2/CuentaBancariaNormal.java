package org.example.ejercicio2;

import java.util.ArrayList;
import java.util.List;

public class CuentaBancariaNormal implements CuentaBancaria{
    private double saldo;
    private int exitosas;
    private int total;

    public CuentaBancariaNormal() {
        this.saldo = 10000;
    }

    @Override
    public boolean retirar(double cantidad) {
        total++;
        if(saldo - cantidad >= 0){
            saldo -= cantidad;
            exitosas++;
            return true;
        }else
            return false;
    }

    @Override
    public void ingresar(double cantidad) {
        total++;
        saldo += cantidad;
        exitosas++;
    }

    @Override
    public double consultarSaldo() {
        return saldo;
    }

    @Override
    public List<String> obtenerHistorial() {
        List<String> historial = new ArrayList<>();
        historial.add("Operaciones exitosas: " + exitosas + "/" + total +"\n");
        historial.add("Operaciones fallidas: " + (total - exitosas) + "(fondos insuficientes)\n");
        return historial;
    }
}

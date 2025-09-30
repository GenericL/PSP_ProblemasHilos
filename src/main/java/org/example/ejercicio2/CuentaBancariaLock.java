package org.example.ejercicio2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CuentaBancariaLock implements CuentaBancaria{
    private double saldo;
    private int exitosas;
    private int total;
    private final Lock lock;

    public CuentaBancariaLock() {
        this.saldo = 10000;
        total = 0;
        exitosas = 0;
        lock = new ReentrantLock();
    }

    @Override
    public boolean retirar(double cantidad) {
        lock.lock();
        total++;
        if(saldo - cantidad >= 0){
            saldo -= cantidad;
            exitosas++;
            lock.unlock();
            return true;
        }else {
            lock.unlock();
            return false;
        }
    }

    @Override
    public void ingresar(double cantidad) {
        lock.lock();
        total++;
        saldo += cantidad;
        exitosas++;
        lock.unlock();
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

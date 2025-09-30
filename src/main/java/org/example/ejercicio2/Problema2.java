package org.example.ejercicio2;

import java.util.ArrayList;
import java.util.List;

public class Problema2 {
    private final CuentaBancaria cuentaBancaria;
    private final CuentaBancaria cuentaBancariaLock;
    private final CuentaBancaria cuentaBancariaSyncronized;

    public Problema2() {
        this.cuentaBancariaSyncronized = new CuentaBancariaSyncronized();
        this.cuentaBancariaLock = new CuentaBancariaLock();
        this.cuentaBancaria = new CuentaBancariaNormal();
    }

    public void start(){
        System.out.println("=== BANCO VIRTUAL ===");
        System.out.println("Saldo inicial: 10000.00€");
        System.out.println("50 clientes realizando 500 operaciones totales...");
        List<Thread> hilosVirtuales = new ArrayList<>();
        reentrantLockBank(hilosVirtuales);
        hilosVirtuales.clear();
        syncronizedBank(hilosVirtuales);
        hilosVirtuales.clear();
        votatileBank(hilosVirtuales);

    }
    private void votatileBank(List<Thread> hilosVirtuales){
        for (int contador = 0; contador < 50;contador++) {
            Thread hilo = Thread.ofVirtual().start(() -> {
                for (int contador2 = 0; contador2 < 10;contador2++) {
                sleeping();
                if (Math.random() < 0.6) {
                    cuentaBancaria.retirar(Math.random() * 99+1);
                }else cuentaBancaria.ingresar(Math.random() * 49+1);
            }});
            hilosVirtuales.add(hilo);
        }
        esperarFinalHilos(hilosVirtuales);
        System.out.println("--- CON VOLATILE ---");
        System.out.printf("Saldo final: %.2f%n", cuentaBancaria.consultarSaldo());
    }
    private void reentrantLockBank(List<Thread> hilosVirtuales){
        long tiempoInicio = System.currentTimeMillis();
        for (int contador = 0; contador < 50;contador++) {
            Thread hilo = Thread.ofVirtual().start(() -> {
                for (int contador2 = 0; contador2 < 10;contador2++) {
                sleeping();
                if (Math.random() < 0.6) {
                    cuentaBancariaLock.retirar(Math.random() * 99+1);
                }else cuentaBancariaLock.ingresar(Math.random() * 49+1);}
            });
            hilosVirtuales.add(hilo);
        }
        esperarFinalHilos(hilosVirtuales);
        long tiempoFin = System.currentTimeMillis();
        long tiempoTotal = tiempoFin - tiempoInicio;
        System.out.println("--- CON REENTRANTLOCK ---");
        mostrarHistorial(tiempoTotal,cuentaBancariaLock.consultarSaldo(),cuentaBancariaLock.obtenerHistorial());
    }
    private void syncronizedBank(List<Thread> hilosVirtuales){
        long tiempoInicio = System.currentTimeMillis();
        for (int contador = 0; contador < 50;contador++) {
            Thread hilo = Thread.ofVirtual().start(() -> {
                for (int contador2 = 0; contador2 < 10;contador2++) {
                sleeping();
                if (Math.random() < 0.6) {
                    cuentaBancariaSyncronized.retirar(Math.random() * 99+1);
                }else cuentaBancariaSyncronized.ingresar(Math.random() * 49+1);}
            });
            hilosVirtuales.add(hilo);
        }
        esperarFinalHilos(hilosVirtuales);
        long tiempoFin = System.currentTimeMillis();
        long tiempoTotal = tiempoFin - tiempoInicio;
        System.out.println("--- CON SYNCHRONIZED ---");
        mostrarHistorial(tiempoTotal,cuentaBancariaSyncronized.consultarSaldo(),cuentaBancariaSyncronized.obtenerHistorial());
    }

    private void mostrarHistorial(long tiempoTotal, double saldo, List<String> historial){
        System.out.printf("Saldo final: %.2f€%n", saldo);
        System.out.println(historial);
        System.out.printf("Tiempo total: %d ms%n", tiempoTotal);

    }

    private void sleeping(){
        try {
            Thread.sleep(Math.round(Math.random() * 200)+100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    private void esperarFinalHilos(List<Thread> hilosVirtuales) {
        for (Thread hilo : hilosVirtuales) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}

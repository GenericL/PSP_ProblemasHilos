package org.example.ejercicio1;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class Problema1 {
    private final ContadorVisitas contadorVisitas;
    private final ContadorVisitas contadorVisitasSincronized;
    private final ContadorVisitas contadorVisitasAtomic;

    public Problema1() {
        this.contadorVisitasAtomic = new ContadorVisitasAtomic();
        this.contadorVisitasSincronized = new ContadorVisitasSincronizado();
        this.contadorVisitas = new ContadorVisitasNormal();
    }

    public void start(){
        System.out.println("=== CONTADOR DE VISITAS WEB ===");
        System.out.println("Esperando 1000 visitantes...");
        List<Thread> hilosVirtuales = new ArrayList<>();
        sinSincronizacion(hilosVirtuales);
        hilosVirtuales.clear();
        conSincronizacion(hilosVirtuales);
        hilosVirtuales.clear();
        conAtomic(hilosVirtuales);
    }

    private void sinSincronizacion(List<Thread> hilosVirtuales){
        int contador;
        long tiempoInicio = System.currentTimeMillis();
        for (contador = 0; contador < 1000;contador++) {
            Thread hilo = Thread.ofVirtual().start(() -> {
                sleeping();
                contadorVisitas.contadorVisitas();
            });
            hilosVirtuales.add(hilo);
        }
        esperarFinalHilos(hilosVirtuales);
        long tiempoFin = System.currentTimeMillis();
        long tiempoTotal = tiempoFin - tiempoInicio;
        System.out.println("\n--- SIN SINCRONIZACIÓN ---");
        mostrarResultados(contador, contadorVisitas.getVisitas(), tiempoTotal);
    }

    private void conSincronizacion(List<Thread> hilosVirtuales){
        int contador;
        long tiempoInicio = System.currentTimeMillis();
        for (contador = 0; contador < 1000;contador++) {
            Thread hilo = Thread.ofVirtual().start(()-> {
                sleeping();
                contadorVisitasSincronized.contadorVisitas();
            });
            hilosVirtuales.add(hilo);
        }
        esperarFinalHilos(hilosVirtuales);
        long tiempoFin = System.currentTimeMillis();
        long tiempoTotal = tiempoFin - tiempoInicio;
        System.out.println("\n--- CON SYNCHRONIZED ---");
        mostrarResultados(contador, contadorVisitasSincronized.getVisitas(), tiempoTotal);
    }
    private void conAtomic(List<Thread> hilosVirtuales){
        int contador;
        long tiempoInicio = System.currentTimeMillis();
        for (contador = 0; contador < 1000;contador++) {
            Thread hilo = Thread.ofVirtual().start(()-> {
                        sleeping();
                        contadorVisitasAtomic.contadorVisitas();
                    });
            hilosVirtuales.add(hilo);
        }
        esperarFinalHilos(hilosVirtuales);
        long tiempoFin = System.currentTimeMillis();
        long tiempoTotal = tiempoFin - tiempoInicio;
        System.out.println("\n--- CON ATOMICINTEGER ---");
        mostrarResultados(contador, contadorVisitasAtomic.getVisitas(), tiempoTotal);
    }

    private void mostrarResultados(int numerosProcesados,int visitas,
                                   long tiempoTotal) {
        System.out.printf("Números procesados: %d%n", numerosProcesados);
        System.out.printf("Visitas contadas: %d%n", visitas);
        System.out.printf("Visitas esperadas: %s%n", (numerosProcesados == visitas ? "✅ CORRECTO" : "❌ INCORRECTO"));
        System.out.printf("Tiempo total: %d ms%n", tiempoTotal);
    }

    private void sleeping(){
        try {
            Thread.sleep(Math.round(Math.random() * 100)+50);
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

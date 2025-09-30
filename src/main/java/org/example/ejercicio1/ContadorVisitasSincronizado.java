package org.example.ejercicio1;

public class ContadorVisitasSincronizado implements ContadorVisitas {
    private int visitas;
    @Override
    public synchronized void contadorVisitas() {
        visitas++;
    }

    @Override
    public int getVisitas() {
        return visitas;
    }
}

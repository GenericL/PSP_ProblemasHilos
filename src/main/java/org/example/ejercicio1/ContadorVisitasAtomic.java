package org.example.ejercicio1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;

public class ContadorVisitasAtomic implements ContadorVisitas {
    private AtomicInteger contador;
    public ContadorVisitasAtomic() {
        contador = new AtomicInteger(0);
    }
    @Override
    public void contadorVisitas() {
        contador.incrementAndGet();
    }

    @Override
    public int getVisitas() {
        return contador.get();
    }
}

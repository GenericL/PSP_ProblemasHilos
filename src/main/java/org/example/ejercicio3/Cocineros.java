package org.example.ejercicio3;



import lombok.extern.log4j.Log4j2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Log4j2
public class Cocineros implements Runnable {
    private TipoPlato tipo;
    private boolean terminado;

    public Cocineros() {
    }

    @Override
    public void run() {
        try {
            Thread.sleep(tipo.tiempoMs);
            System.out.println("👨‍🍳"+ Thread.currentThread().getName() + " preparó: " + tipo.nombre);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error(e);
        }
    }
}

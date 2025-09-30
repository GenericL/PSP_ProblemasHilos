package org.example.ejercicio3;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class RestauranteConcurrente {
    private final BlockingQueue<Pedidos> pedidosQueue;

    public RestauranteConcurrente() {
        this.pedidosQueue = new ArrayBlockingQueue<>(10);
    }

    public void start(){
        System.out.println("🍽️  === RESTAURANTE CON BLOCKING QUEUE ===\n");
        Thread cocinero1 = new Thread(new Cocineros(), "Chef-Hanoris");
        Thread cocinero2 = new Thread(new Cocineros(), "Chef-Mendax");
        Thread cocinero3 = new Thread(new Cocineros(), "Chef-Soraya");

        cocinero1.start();
        cocinero2.start();
        cocinero3.start();

        for (int i = 0; i < 100; i++) {
            boolean lleno = pedidosQueue.offer(new Pedidos());
            if (lleno){
                System.out.println("Pedido añadido. Pedidos en cola: " + pedidosQueue.size());
            } else {
                System.out.println("La cola de pedidos está llena. No se pudo añadir el pedido.");
            }
        }

    }
}

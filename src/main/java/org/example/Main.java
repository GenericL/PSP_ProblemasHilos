package org.example;

import org.example.ejercicio1.Problema1;
import org.example.ejercicio2.Problema2;

public class Main {
    public static void main(String[] args) {
        System.out.println("Ejercicio 1: Contador Compartido");
        Problema1 problema1 = new Problema1();
        problema1.start();
        System.out.println("Ejercicio 2: Banco virtual");
        Problema2 problema2 = new Problema2();
        problema2.start();
    }
}
package org.example.ejercicio1;

public class ContadorVisitasNormal implements ContadorVisitas {

    private int visitas;

    public ContadorVisitasNormal() {
        this.visitas = 0;
    }

    @Override
    public void contadorVisitas() {
        visitas++;
    }

    @Override
    public int getVisitas() {
        return visitas;
    }
}

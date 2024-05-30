package com.mycompany.controlaeropuertos;

import java.util.List;

import javax.swing.JTextField;

class Actualizar extends Thread {
    private List<Avion> aviones;
    private JTextField campoTexto;
    private StringBuilder nombresAviones;

    public Actualizar(List<Avion> aviones, JTextField campoTexto) {
        this.aviones = aviones;
        this.campoTexto = campoTexto;
        this.nombresAviones = new StringBuilder();
    }
    //Metodo para actualizar las zonas por las que va el avion
    @Override
    public void run() {
        while (true) {
            synchronized (aviones) {
                for (int i = 0; i < aviones.size(); i++) {
                    Avion avion = aviones.get(i);
                    if (avion != null) {
                        nombresAviones.append(avion.Id());
                        if (i < aviones.size() - 1) {
                            nombresAviones.append(", ");
                        }
                    } else {
                        nombresAviones.append("null");
                        if (i < aviones.size() - 1) {
                            nombresAviones.append(", ");
                        }
                    }
                }
                campoTexto.setText(nombresAviones.toString());
                nombresAviones.setLength(0); // Limpiar el StringBuilder
            }
            try {
                Thread.sleep(500); // Pausa el hilo durante un segundo
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restablecer el estado interrumpido
            }
        }
    }
}

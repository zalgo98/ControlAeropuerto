/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controlaeropuertos;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author gonzalo
 */
class Taller {
    private int capacidad=20;
     private List<Avion> colaEsperaInspeccion;
     private List<Avion> avionesEnIspeccion;
     private int avionesEnTaller;
     private Semaphore semaforo = new Semaphore(1);

    public Taller() {
        colaEsperaInspeccion = new ArrayList<>();
        avionesEnIspeccion = new ArrayList<>();
        avionesEnTaller=0;
    }

    public synchronized void solicitarInspeccion(Avion avion) throws InterruptedException {
        avion.getAeropuertoDestino().pausaSiEsNecesario();
        semaforo.acquire(); // Adquiere el semáforo antes de entrar al taller
        try {
            Registro.logEvent(" [ "+ avion.getAeropuertoDestino().getNombre()+ " ] " +"Entrando en el taller el avion " + avion.Id());
            Thread.sleep(1000); // Tiempo para cruzar la puerta
            colaEsperaInspeccion.add(avion);
        } finally {
            semaforo.release(); // Libera el semáforo después de cruzar la puerta
        }
        while (avionesEnTaller == capacidad) {
            wait(); // Espera si el taller está lleno
        }
        colaEsperaInspeccion.remove(avion);    
        avionesEnTaller++;
        realizarInspeccion(avion);
    }

    public synchronized void realizarInspeccion(Avion avion) throws InterruptedException  {
        avion.getAeropuertoDestino().pausaSiEsNecesario();
        Registro.logEvent(" [ "+ avion.getAeropuertoDestino().getNombre()+ " ] " +"Realizando inspección en el avión " + avion.Id());
        avionesEnIspeccion.add(avion);
        int tiempoDeInspeccion;
        if (avion.getVuelos()==15) {
            tiempoDeInspeccion = ThreadLocalRandom.current().nextInt(5000, 10001); // Tiempo aleatorio entre 5 y 10 segundos en milisegundos
        } else {
            tiempoDeInspeccion = ThreadLocalRandom.current().nextInt(1000, 5001); // Tiempo aleatorio entre 1 y 5 segundos en milisegundos
        }
        Thread.sleep(tiempoDeInspeccion);
        avion.getAeropuertoDestino().pausaSiEsNecesario();
        semaforo.acquire(); // Adquiere el semáforo antes de salir al taller
        try {
            avion.getAeropuertoDestino().pausaSiEsNecesario();
            Registro.logEvent(" [ "+ avion.getAeropuertoDestino().getNombre()+ " ] " +"Saliendo del taller el avion " + avion.Id());
            Thread.sleep(1000); // Tiempo para cruzar la puerta
            avionesEnTaller--;
            avionesEnIspeccion.remove(avion);
        } finally {
            semaforo.release(); // Libera el semáforo después de cruzar la puerta
        }
        
        notifyAll(); // Notifica a los aviones en espera que hay espacio en el taller
    }

    public synchronized boolean hayInspeccionesPendientes() {
        return !colaEsperaInspeccion.isEmpty();
    }
    public List<Avion> getAvionesEnIspeccion() {
        return avionesEnIspeccion;
    }
}

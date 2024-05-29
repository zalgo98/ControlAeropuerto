/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controlaeropuertos;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;
/**
 *
 * @author gonzalo
 */
public class PuertaEmbarque {
    private boolean disponible = true;
    private Avion avionAsignado;
    private int idPuertaEmbarque;
    private  ReentrantLock lock = new ReentrantLock();
    private List<Avion> avionesEmbarque;


    public PuertaEmbarque(int idPuertaEmbarque) {
        this.idPuertaEmbarque = idPuertaEmbarque;
        avionesEmbarque = new ArrayList<>(); 
    }
    
    public boolean estaDisponible() {
        lock.lock();  // Bloquea el acceso a la puerta
        try {
            return disponible;
        } finally {
            lock.unlock();  // Libera el bloqueo, permitiendo que otros hilos accedan a la puerta
        }
    }
    public synchronized boolean asignarSiEstaDisponible(Avion avion) {       
        if (estaDisponible() ) {
            disponible = false;
            return true;
        } else {
            return false;
        }
    }

    public synchronized void embarcarPasajeros(Aeropuerto aeropuerto) throws InterruptedException {
       try{
        int intentos = 0;
        while (intentos < 3) {
            aeropuerto.pausaSiEsNecesario();
            if (avionAsignado == null) {
                break;
            }
            int pasajerosDisponibles = aeropuerto.getPasajerosDisponibles();
            int capacidadAvion = avionAsignado.getCapacidad();
            int pasajerosAEmbarcar = Math.min(capacidadAvion, pasajerosDisponibles);
                // Comprobar si hay suficientes pasajeros disponibles antes de reducir
                if (pasajerosAEmbarcar <= pasajerosDisponibles) {
                    aeropuerto.reducirPasajeros(pasajerosAEmbarcar);
                    avionAsignado.aumentarPasajeros(pasajerosAEmbarcar);
                    Registro.logEvent("["+ aeropuerto.getNombre()+ "]" + " Avion " + avionAsignado.Id() + " embarcando " + pasajerosAEmbarcar + " pasajeros");
                }
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 3001)); // Tiempo de transferencia de pasajeros

            if (avionAsignado.getPasajeros() == capacidadAvion || pasajerosDisponibles == 0) {
                break;
            }


            Registro.logEvent("["+ aeropuerto.getNombre()+ "]" + " Avion " + avionAsignado.Id() + " esperando a que lleguen más pasajeros");
            intentos++;
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5001)); // Tiempo de espera antes de admitir más pasajeros
        }
        
        notifyAll();
    } catch (InterruptedException e) {
        System.out.println("Error"+ e.getMessage());
    }
    }
    

    public synchronized void desembarcarAvion(Aeropuerto aeropuerto) {
        try {
        if (avionAsignado!=null) {
            while (avionAsignado.getPasajeros() > 0) {
                aeropuerto.pausaSiEsNecesario();
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 3001)); // Tiempo de transferencia de pasajeros
                aeropuerto.aumentarPasajeros(avionAsignado.getPasajeros());
                Registro.logEvent("["+ aeropuerto.getNombre()+ "]" + " Avion " + avionAsignado.Id() + " desembarcando "+ avionAsignado.getPasajeros() +" pasajeros");
                avionAsignado.reducirPasajeros(avionAsignado.getPasajeros());
                
                
            }
            notifyAll();
        }
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    }
    public void liberarPuerta(PuertaEmbarque puerta) {
        avionesEmbarque.remove(avionAsignado);
        disponible = true;
        avionAsignado = null;
    }

    public int getIdPuertaEmbarque() {
        return idPuertaEmbarque;
    }

    public void setIdPuertaEmbarque(int idPuertaEmbarque) {
        this.idPuertaEmbarque = idPuertaEmbarque;
    }

    public synchronized Avion getAvionAsignado() {
        return avionAsignado;
    }
    public void setAvionAsignado(Avion avionAsignado) {
        avionesEmbarque.add(avionAsignado);
        this.avionAsignado = avionAsignado;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    public List<Avion> getAvionesEmbarque() {
        return avionesEmbarque;
    }
    
    
}

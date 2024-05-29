/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controlaeropuertos;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author gonzalo
 */
/**
 * La clase Pista representa una pista de aterrizaje en un aeropuerto.
 */
public class Pista {
    private boolean disponible;
    private int idPista;
    private Avion avionAsignado;
    private final ReentrantLock lock;
    private List<Avion> avionEnPista;

    /**
     * Crea una nueva instancia de la clase Pista.
     * 
     * @param idPista el identificador de la pista
     */
    public Pista(int idPista) {
        this.disponible = true;
        this.lock = new ReentrantLock();
        this.idPista = idPista;
        avionEnPista= new ArrayList<>();
    }

  
    public boolean estaDisponible() {//metodo que devuelve si la pista esta disponible
        return disponible;
    }

    public synchronized void ocuparPista(Avion avion) {//metodo que ocupa la pista
        lock.lock();
        try {
            avionEnPista.add(avion);
            this.avionAsignado=avion;
            disponible = false;
        } finally {
            lock.unlock();
        }
    }

    public synchronized void liberarPista() {//metodo que libera la pista
        lock.lock();
        try {
            avionEnPista.remove(avionAsignado);
            avionAsignado = null;
            disponible = true;
        } finally {
            lock.unlock();
        }
    }
    public void ControlPista(boolean abierta) {//metodo que cierra la pista
        lock.lock();
        try {
            setDisponible(abierta);
        } finally {
            lock.unlock();
        }
    }

    public synchronized Avion getAvionAsignado() {//metodo que devuelve el avion asignado a la pista
        return avionAsignado;
    }
    public void setDisponible(boolean disponible) {//metodo que establece si la pista esta disponible
        this.disponible = disponible;
    }

    public int getIdPista() {//metodo que devuelve el id de la pista
        return idPista;
    }

    public void setIdPista(int idPista) {//metodo que establece el id de la pista
        this.idPista = idPista;
    }

    public void setAvionAsignado(Avion avionAsignado) {//metodo que establece el avion asignado a la pista
        this.avionAsignado = avionAsignado;
    }
    public List<Avion> getAvionEnPista() {//metodo que devuelve el avion en la pista
        return avionEnPista;
    } 
}

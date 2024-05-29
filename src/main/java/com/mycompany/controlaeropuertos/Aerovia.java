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
public class Aerovia {
    private String nombre;
    private final ReentrantLock lock;
    private List<Avion> avionesVolando;

    public Aerovia(String nombre) {
        this.nombre = nombre;
        this.lock = new ReentrantLock();
        avionesVolando = new ArrayList<>();
    }

    public  void ingresarAvion(Avion avion) {//metodo que ingresa un avion en la aerovia
        lock.lock();
        try {
            Registro.logEvent("["+ nombre + "]" + " Avion " + avion.Id() + " entra en aerovia");
            avionesVolando.add(avion);
        } finally {
            lock.unlock();
        }
    }

    public void salirAvion(Avion avion) {//metodo que saca un avion de la aerovia
        lock.lock();
        try {
            Registro.logEvent("["+ nombre + "]" + " Avion " + avion.Id() + " sale de aerovia");
            avionesVolando.remove(avion);
        } finally {
            lock.unlock();
        }
    }
    public String getNombre() {//metodo que devuelve el nombre de la aerovia
        return nombre;
    }

    public void setNombre(String nombre) {//metodo que establece el nombre de la aerovia
        this.nombre = nombre;
    }

    public void setAvionesVolando(List<Avion> avionesVolando) {//metodo que establece los aviones volando
        this.avionesVolando = avionesVolando;
    }
    public String avionesAerolinea() {//metodo que devuelve los aviones en vuelo
        lock.lock();
        try {
            String aviones = "";
            for (Avion avion : avionesVolando) {
                aviones += avion.Id() + ", ";
            }
            return aviones;
        }
        
        finally {
            lock.unlock();
        }
    }
    
}

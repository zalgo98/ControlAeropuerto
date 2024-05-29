/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controlaeropuertos;

import java.util.Random;

/**
 *
 * @author gonzalo
 */
public class Autobus extends Thread{
    private static final Random random = new Random();
    private String id;
    private Aeropuerto aeropuerto;
    private int pasajerosEnBus_ida=0;
    private int pasajerosEnBus_vuelta=0;
    private boolean ida;


    public Autobus(String id, Aeropuerto aeropuerto) {
        this.id = id;
        this.aeropuerto = aeropuerto;
    }
    public void run() {
        aeropuerto.addAutobus(this);
        while (true) {
            
            // Lógica del ciclo de vida del autobús
            try {    
                ida = true;           
                Thread.sleep(random.nextInt(3000) + 2000); // Espera en la parada de la ciudad
                pasajerosEnBus_ida = generarPasajeros();
                aeropuerto.pausaSiEsNecesario();
                Registro.logEvent("["+ aeropuerto.getNombre()+ "]" + " Autobus " + id + " direccion " + aeropuerto.getNombre() + " con " + pasajerosEnBus_ida + " pasajeros");
                Thread.sleep(random.nextInt(6000) + 5000); // Viaje al aeropuerto
                aeropuerto.pausaSiEsNecesario();
                Registro.logEvent("["+ aeropuerto.getNombre()+ "]" + " Autobus " + id + " deja " + pasajerosEnBus_ida + " pasajeros en " + aeropuerto.getNombre());
                aeropuerto.aumentarPasajeros(pasajerosEnBus_ida);
                pasajerosEnBus_ida = 0;
                Thread.sleep(random.nextInt(3000) + 2000); // Espera a que se suban durante un tiempo aleatorio entre 2 y 5 segundos
                pasajerosEnBus_vuelta = generarPasajeros();
                Registro.logEvent("["+ aeropuerto.getNombre()+ "]" + " Autobus " + id + " direccion ciudad con " + pasajerosEnBus_vuelta + " pasajeros");
                aeropuerto.pausaSiEsNecesario();
                if(aeropuerto.getPasajerosDisponibles() < pasajerosEnBus_vuelta) {
                    pasajerosEnBus_vuelta = aeropuerto.getPasajerosDisponibles();
                }
                ida = false;
                aeropuerto.pausaSiEsNecesario();
                aeropuerto.reducirPasajeros(pasajerosEnBus_vuelta); 
                Thread.sleep(random.nextInt(6000) + 5000); // Viaje en bus al centro de la ciudad
                pasajerosEnBus_vuelta = 0;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void setIda(boolean ida) {
        this.ida = ida;
    }
    public boolean getIda() {
        return ida;
    }
    public int generarPasajeros() {
        return random.nextInt(51);
    }
    public String IdBus() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Aeropuerto getAeropuerto() {
        return aeropuerto;
    }
    public void setAeropuerto(Aeropuerto aeropuerto) {
        this.aeropuerto = aeropuerto;
    }
    public int getPasajerosEnBus_Ida() {
        return pasajerosEnBus_ida;
    }
    public void setPasajerosEnBus_Ida(int pasajerosEnBus) {
        this.pasajerosEnBus_ida = pasajerosEnBus;
    }
    public int getPasajerosEnBus_Vuelta() {
        return pasajerosEnBus_vuelta;
    }
    public void setPasajerosEnBus_Vuelta(int pasajerosEnBus) {
        this.pasajerosEnBus_vuelta = pasajerosEnBus;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controlaeropuertos;


import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author gonzalo
 */

/**
 * La clase Avion representa un avión en un aeropuerto.
 * Extiende la clase Thread para permitir la ejecución en un hilo separado.
 */
public class Avion extends Thread {
    private static final ThreadLocalRandom random = ThreadLocalRandom.current();
    private String id;
    private Aeropuerto aeropuertoOrigen;
    private Aeropuerto aeropuertoDestino;
    private int vuelos;
    private int capacidad;
    private int pasajeros = 0;

    /**
     * Crea una instancia de Avion con el ID, aeropuerto de origen, aeropuerto de destino y capacidad especificados.
     *
     * @param id                 El ID del avión.
     * @param aeropuertoOrigen   El aeropuerto de origen del avión.
     * @param aeropuertoDestino  El aeropuerto de destino del avión.
     * @param capacidad          La capacidad máxima de pasajeros del avión.
     */
    public Avion(String id, Aeropuerto aeropuertoOrigen, Aeropuerto aeropuertoDestino, int capacidad) {
        this.id = id;
        this.aeropuertoDestino = aeropuertoDestino;
        this.aeropuertoOrigen = aeropuertoOrigen;
        this.vuelos = 0;
        this.capacidad = capacidad;
    }

    /**
     * El método run se ejecuta en un hilo separado y contiene la lógica principal del avión.
     * El avión realiza una serie de acciones en bucle, como embarcar pasajeros, despegar, volar, aterrizar, etc.
     */
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(random.nextInt(3000) + 1000); // Espera en el hangar
                aeropuertoOrigen.embarcarAvion(this);// Embarca los pasajeros en el avión
                aeropuertoOrigen.despegarAvion(this, aeropuertoOrigen); // Despega el avión del aeropuerto de origen
                aeropuertoOrigen.vueloYaterrizaje(this, aeropuertoOrigen, aeropuertoDestino);  // Vuela y aterriza en el aeropuerto de destino
                aeropuertoDestino.desembarcarPasajeros(this); // Desembarca los pasajeros del avión en el aeropuerto de destino
                vuelos++; // Incrementa el número de vuelos realizados por el avión
                aeropuertoDestino.inspeccionarAvion(this);// Inspecciona el avión en el aeropuerto de destino
                aeropuertoDestino.cicloDeVidaAvion(this); // Realiza el ciclo de vida del avión en el aeropuerto de destino
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Decide si el avión debe ir al hangar o no.
     *
     * @return true si el avión debe ir al hangar, false en caso contrario.
     */
    public boolean decideIrAlHangar() {
        int decision = ThreadLocalRandom.current().nextInt(0, 2); // Genera un número aleatorio entre 0 y 1
        return decision == 0; // Si el número es 0, el avión irá al hangar
    }

    /**
     * Aumenta el número de pasajeros a bordo del avión.
     *
     * @param pasajerosAEmbarcar El número de pasajeros a embarcar.
     */
    public void aumentarPasajeros(int pasajerosAEmbarcar) {
        pasajeros += pasajerosAEmbarcar;
    }

    /**
     * Reduce el número de pasajeros a bordo del avión.
     *
     * @param pasajerosDesmbarcar El número de pasajeros a desembarcar.
     */
    public void reducirPasajeros(int pasajerosDesmbarcar) {
        pasajeros -= pasajerosDesmbarcar;
    }

    /**
     * Obtiene el ID del avión.
     *
     * @return El ID del avión.
     */
    public String Id() {
        return id;
    }

    /**
     * Obtiene el nombre del avión.
     *
     * @return El nombre del avión.
     */
    public String getNombre() {
        return id;
    }

    /**
     * Establece el ID del avión.
     *
     * @param id El nuevo ID del avión.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el aeropuerto de origen del avión.
     *
     * @return El aeropuerto de origen del avión.
     */
    public Aeropuerto getAeropuertoOrigen() {
        return aeropuertoOrigen;
    }

    /**
     * Establece el aeropuerto de origen del avión.
     *
     * @param aeropuertoOrigen El nuevo aeropuerto de origen del avión.
     */
    public void setAeropuertoOrigen(Aeropuerto aeropuertoOrigen) {
        this.aeropuertoOrigen = aeropuertoOrigen;
    }

    /**
     * Obtiene el aeropuerto de destino del avión.
     *
     * @return El aeropuerto de destino del avión.
     */
    public Aeropuerto getAeropuertoDestino() {
        return aeropuertoDestino;
    }

    /**
     * Establece el aeropuerto de destino del avión.
     *
     * @param aeropuertoDestino El nuevo aeropuerto de destino del avión.
     */
    public void setAeropuertoDestino(Aeropuerto aeropuertoDestino) {
        this.aeropuertoDestino = aeropuertoDestino;
    }

    /**
     * Obtiene el número de vuelos realizados por el avión.
     *
     * @return El número de vuelos realizados por el avión.
     */
    public int getVuelos() {
        return vuelos;
    }

    /**
     * Obtiene la capacidad máxima de pasajeros del avión.
     *
     * @return La capacidad máxima de pasajeros del avión.
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * Establece la capacidad máxima de pasajeros del avión.
     *
     * @param capacidad La nueva capacidad máxima de pasajeros del avión.
     */
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    /**
     * Obtiene el número de pasajeros a bordo del avión.
     *
     * @return El número de pasajeros a bordo del avión.
     */
    public int getPasajeros() {
        return pasajeros;
    }

    /**
     * Establece el número de pasajeros a bordo del avión.
     *
     * @param pasajeros El nuevo número de pasajeros a bordo del avión.
     */
    public void setPasajeros(int pasajeros) {
        this.pasajeros = pasajeros;
    }
}


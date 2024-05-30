/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controlaeropuertos;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author gonzalo
 */
public class Aeropuerto {

    private String nombre;
    private Hangar hangar;
    private Taller taller;
    private List<PuertaEmbarque> puertasEmbarque;
    private List<Pista> pistas;
    private AreaEstacionamiento areaEstacionamiento;
    private AreaRodaje areaRodaje;
    private List<Avion> aviones;
    private List<Autobus> autobuses;
    private List<Avion> avionesEnTaller;
    private Aerovia aerovia;
    private int pasajerosDisponibles = 10000;
    private boolean pausado = false;
    private PuertaEmbarque puertaEmbarque;
    private PuertaEmbarque puertaDesembarque;


    public Aeropuerto(String nombre, Aerovia aerovia) {
        this.nombre = nombre;
        this.aerovia = aerovia;
        hangar = new Hangar();
        taller = new Taller();
        areaEstacionamiento = new AreaEstacionamiento();
        areaRodaje = new AreaRodaje(this);
        
        //Creacion de puerta de embarque
        puertasEmbarque = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            puertasEmbarque.add(new PuertaEmbarque(i));
        }
        //Creacion de pistas
        pistas = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            pistas.add(new Pista(i));
        }
        
        aviones = new ArrayList<>();
        autobuses = new ArrayList<>();
        avionesEnTaller = new ArrayList<>();
        
        //Reserva de la puerta 1 para el embarque
        this.puertaEmbarque= puertasEmbarque.get(0);
        
        //Reserva de la puerta 6 para el desmbarque
        this.puertaDesembarque= puertasEmbarque.get(5);

    }

    public void embarcarAvion(Avion avion) throws InterruptedException { // Embarcar pasajeros en un avión
        try{
            pausaSiEsNecesario();// Pausar si es necesario
            if (hangar.getAviones().contains(avion)) {// Si el avión está en el hangar, salir del hangar
                hangar.salirAvion(avion);
            }
            pausaSiEsNecesario();
            PuertaEmbarque puerta = areaEstacionamiento.esperarPuertaDisponible(avion);// Esperar a que haya una puerta disponible
            if (puerta != null) {
                puerta.setAvionAsignado(avion);// Asignar el avión a la puerta
                pausaSiEsNecesario();// Pausar si es necesario
                puerta.embarcarPasajeros(this);// Embarcar pasajeros en el avión
                puerta.liberarPuerta(puerta);// Liberar la puerta y pone el avion en null
            }
        }catch(InterruptedException e){
            System.out.println("Error en el embarque de pasajeros");
            throw e;
        }
    }

    public void despegarAvion(Avion avion, Aeropuerto origen) throws InterruptedException {// Metodo que simula el despegue de un avión
        try {
            pausaSiEsNecesario();
            areaRodaje.entraEnAreaRodaje(avion);
            Pista pista = origen.solicitarPista(avion);
            while(pista == null){// Mientras no haya pista disponible, esperar
                pausaSiEsNecesario();
                areaRodaje.esperarPista();
                pista = origen.solicitarPista(avion);
            }
                pausaSiEsNecesario();
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 3001));
                areaRodaje.saleDeAreaRodaje(avion);
                pista.ocuparPista(avion); //Se ocupa la pista cuando se le ha asignado una
                pausaSiEsNecesario();
                Registro.logEvent(" [ " + nombre + " ] " + "Pista " + pista.getIdPista() + " ocupada por avion " + avion.Id());              
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5001));
                pausaSiEsNecesario();
                Registro.logEvent(" [ " + origen.getNombre() + " ] " + "Despegando avion " + avion.Id());
                pista.liberarPista(); //Se libera cuando despega el avion
                Registro.logEvent(" [ " + nombre+ " ] " + "Pista " + pista.getIdPista() + " liberada por avion " + avion.Id());

            
        } catch (InterruptedException e) {
            System.out.println("Error en el despegue de aviones");
            throw e;
        }
    }

    public void vueloYaterrizaje(Avion avion, Aeropuerto origen, Aeropuerto destino) throws InterruptedException {// Metodo que simula el vuelo y aterrizaje de un avión
        // Simular el vuelo entre aeropuertos
        pausaSiEsNecesario();
        aerovia.ingresarAvion(avion);// Ingresar el avión a la aerovía
        Thread.sleep(ThreadLocalRandom.current().nextInt(15000, 30001)); // Tiempo de vuelo entre 15 y 30 segundos
        while (true) {
            pausaSiEsNecesario();
            Pista pista = destino.solicitarPista(avion); // Solicitar una pista para aterrizar en el aeropuerto destino
            if (pista != null) {
                pausaSiEsNecesario();
                aerovia.salirAvion(avion); // Salir de la aerovía
                pausaSiEsNecesario();
                destino.aterrizarAvion(avion, pista);// Aterrizar el avión en el aeropuerto destino
                break;
            } else {
                // Dar un rodeo al aeropuerto si la pista está ocupada
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5001)); // Tiempo de espera entre 1 y 5 segundos
            }
        }
       
    }

    public Pista solicitarPista(Avion avion) { //Metodo para solicitar la pista libre
        for (Pista pista : pistas) {
            if (pista.estaDisponible()) {
                return pista;
            }
        }
        return null; // Retorna null si no hay ninguna pista disponible
    }

    public void aterrizarAvion(Avion avion, Pista pista) throws InterruptedException {// Metodo que simula el aterrizaje de un avión
        pausaSiEsNecesario();
        Registro.logEvent(" [ " + avion.getAeropuertoDestino().getNombre() + " ] " + "Aterrizando avion " + avion.Id());
        Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5001)); // Tiempo de espera entre 1 y 5 segundos
        pausaSiEsNecesario();
        pista.liberarPista();// Liberar la pista
        pausaSiEsNecesario();
        areaRodaje.entraEnAreaRodaje(avion);// Entrar en el área de rodaje
        
    }

    public void desembarcarPasajeros(Avion avion) throws InterruptedException {// Metodo que simula el desembarque de pasajeros de un avión
        pausaSiEsNecesario();
        PuertaEmbarque puerta = areaRodaje.solicitarPuertaEmbarque(avion);// Solicitar una puerta de embarque
        pausaSiEsNecesario();
        if (puerta != null) {
            pausaSiEsNecesario();
            areaRodaje.saleDeAreaRodaje(avion);// Salir del área de rodaje
            puerta.setAvionAsignado(avion);// Asignar el avión a la puerta de embarque
            Thread.sleep(ThreadLocalRandom.current().nextInt(3000, 5001)); // Tiempo de viaje entre la pista y la puerta de embarque
            pausaSiEsNecesario();
            puerta.desembarcarAvion(this);// Desembarcar pasajeros del avión  
            pausaSiEsNecesario();
            puerta.liberarPuerta(puerta);// Liberar la puerta de embarque
            areaEstacionamiento.realizarComprobaciones(avion);// Realizar comprobaciones en el área de estacionamiento
        }
    }

    public void cicloDeVidaAvion(Avion avion) throws InterruptedException {// Metodo que simula el ciclo de vida de un avión
        if (avion.decideIrAlHangar()) {
            // El avión va al hangar
            areaEstacionamiento.getAvionesEnEspera().remove(avion);// El avion sale del área de estacionamiento
            hangar.entrarAvion(avion);// El avión entra en el hangar
            pausaSiEsNecesario();
            Thread.sleep(ThreadLocalRandom.current().nextInt(15000, 30001)); // El avión permanece en el hangar entre 15 y 30 segundos
            pausaSiEsNecesario();
        }
        // El avion intercambia su aeropuerto de origen y destino
        Aeropuerto temp = avion.getAeropuertoOrigen();
        avion.setAeropuertoOrigen(avion.getAeropuertoDestino());
        avion.setAeropuertoDestino(temp);
    }
    public void inspeccionarAvion(Avion avion) throws InterruptedException {// Metodo que simula la inspección de un avión
        areaEstacionamiento.getAvionesEnEspera().remove(avion); // El avión sale del área de estacionamiento
        pausaSiEsNecesario();
        avionesEnTaller.add(avion);// El avión entra en el taller
        pausaSiEsNecesario();
        taller.solicitarInspeccion(avion);// El avion solicita la inspección del avión
        avionesEnTaller.remove(avion);// El avión sale del taller
    }

    public void reducirPasajeros(int pasajerosAEmbarcar) {// Metodo que reduce la cantidad de pasajeros disponibles
        pasajerosDisponibles -= pasajerosAEmbarcar;
    }

    public void aumentarPasajeros(int pasajerosAEmbarcar) {// Metodo que aumenta la cantidad de pasajeros disponibles
        pasajerosDisponibles += pasajerosAEmbarcar;
    }

    public String getNombre() {// Metodo que retorna el nombre del aeropuerto
        return nombre;
    }

    public void setNombre(String nombre) {// Metodo que asigna un nombre al aeropuerto
        this.nombre = nombre;
    }

    public Hangar getHangar() {// Metodo que retorna el hangar del aeropuerto
        return hangar;
    }

    public void setHangar(Hangar hangar) {// Metodo que asigna un hangar al aeropuerto
        this.hangar = hangar;
    }

    public Taller getTaller() {// Metodo que retorna el taller del aeropuerto
        return taller;
    }

    public void setTaller(Taller taller) {// Metodo que asigna un taller al aeropuerto
        this.taller = taller;
    }

    List<PuertaEmbarque> getPuertasEmbarque() {// Metodo que retorna las puertas de embarque del aeropuerto
        return puertasEmbarque;
    }

    public void setPuertasEmbarque(List<PuertaEmbarque> puertasEmbarque) {// Metodo que asigna las puertas de embarque al aeropuerto
        this.puertasEmbarque = puertasEmbarque;
    }

    public List<Pista> getPistas() {// Metodo que retorna las pistas del aeropuerto
        return pistas;
    }

    public void setPistas(List<Pista> pistas) {// Metodo que asigna las pistas al aeropuerto
        this.pistas = pistas;
    }

    
    public AreaEstacionamiento getAreaEstacionamiento() {// Metodo que retorna el área de estacionamiento del aeropuerto
        return areaEstacionamiento;
    }

    public void setAreaEstacionamiento(AreaEstacionamiento areaEstacionamiento) {// Metodo que asigna el área de estacionamiento al aeropuerto
        this.areaEstacionamiento = areaEstacionamiento;
    }

    public AreaRodaje getAreaRodaje() {// Metodo que retorna el área de rodaje del aeropuerto
        return areaRodaje;
    }

    public void setAreaRodaje(AreaRodaje areaRodaje) {// Metodo que asigna el área de rodaje al aeropuerto
        this.areaRodaje = areaRodaje;
    }

    public List<Avion> getAviones() {// Metodo que retorna los aviones del aeropuerto
        return aviones;
    }

    public void setAviones(List<Avion> aviones) {// Metodo que asigna los aviones al aeropuerto
        this.aviones = aviones;
    }

    public Aerovia getAerovia() {// Metodo que retorna la aerovía del aeropuerto
        return aerovia;
    }

    public void setAerovia(Aerovia aerovia) {// Metodo que asigna la aerovía al aeropuerto
        this.aerovia = aerovia;
    }

    public int getPasajerosDisponibles() {// Metodo que retorna la cantidad de pasajeros disponibles
        return pasajerosDisponibles;
    }

    public void setPasajerosDisponibles(int pasajerosDisponibles) {// Metodo que asigna la cantidad de pasajeros disponibles
        this.pasajerosDisponibles = pasajerosDisponibles;
    }
    public List<Autobus> getAutobuses() {// Metodo que retorna los autobuses del aeropuerto
        return autobuses;
    }
    public void setAutobuses(List<Autobus> autobuses) {// Metodo que asigna los autobuses al aeropuerto
        this.autobuses = autobuses;
    }
    public void addAutobus(Autobus autobus){// Metodo que añade un autobus al aeropuerto
        autobuses.add(autobus);
    }
    public void removeAutobus(Autobus autobus){// Metodo que elimina un autobus del aeropuerto
        autobuses.remove(autobus);

    }
    public PuertaEmbarque getPuertaEmbarque() {// Metodo que retorna la puerta reservada para el embarque del avion
        return puertaEmbarque;
    }
    public PuertaEmbarque getPuertaDesembarque() {// Metodo que retorna la puerta reservada para el desembarque del avion
        return puertaDesembarque;
    }
    public void pausar() {// Metodo que pausa la simulación
        pausado = true;
    }

    public void reanudar() {// Metodo que reanuda la simulación
        pausado = false;
        synchronized (this) {
            this.notifyAll();
        }
    }

    public void pausaSiEsNecesario() throws InterruptedException {// Metodo que pausa la simulación si es necesario
        synchronized (this) {
            while (pausado) {
                this.wait();
            }
        }
    }

}

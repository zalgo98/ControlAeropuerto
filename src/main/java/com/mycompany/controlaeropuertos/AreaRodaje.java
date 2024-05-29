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
public class AreaRodaje {
   private Aeropuerto aeropuerto;
   private List<Avion> avionesRodaje;

    public AreaRodaje(Aeropuerto aeropuerto) {
        this.aeropuerto = aeropuerto;
        avionesRodaje= new ArrayList<>();
    }

    public synchronized PuertaEmbarque solicitarPuertaEmbarque(Avion avion) {     //metodo que solicita una puerta de embarque  
        List<PuertaEmbarque> puertasEmbarque = aeropuerto.getPuertasEmbarque();
        PuertaEmbarque puertaDesembarqueReservada = avion.getAeropuertoDestino().getPuertaDesembarque();
       if(puertaDesembarqueReservada.asignarSiEstaDisponible(avion)){
           return puertaDesembarqueReservada;
       }
       else{
        for (PuertaEmbarque puerta : puertasEmbarque) {
            if (puerta.asignarSiEstaDisponible(avion)) {
                return puerta;
            }
            }
        }
        
        return null; // Retorna null si no hay ninguna puerta disponible
    } 
 public void esperarPista() throws InterruptedException {//metodo que espera a que haya una pista disponible
        int tiempoDeComprobacion = ThreadLocalRandom.current().nextInt(1000, 5001); // Tiempo aleatorio entre 1 y 5 segundos en milisegundos
        Thread.sleep(tiempoDeComprobacion);
    }
    public synchronized void entraEnAreaRodaje(Avion avion) throws InterruptedException {//metodo que hace que un avion entre en el area de rodaje
        Registro.logEvent("["+ aeropuerto.getNombre()+ "]" + " Avion " + avion.Id() + " entra en area de rodaje");
        avionesRodaje.add(avion);
    }
    public void saleDeAreaRodaje(Avion avion)throws InterruptedException {//metodo que hace que un avion salga del area de rodaje
        avionesRodaje.remove(avion);
        Registro.logEvent("["+ aeropuerto.getNombre()+ "]" + " Avion " + avion.Id() + " sale de area de rodaje");
    }

public Aeropuerto getAeropuerto() {//metodo que devuelve el aeropuerto
    return aeropuerto;
}

public void setAeropuerto(Aeropuerto aeropuerto) {//metodo que establece el aeropuerto
    this.aeropuerto = aeropuerto;
}

public List<Avion> getAvionEmbarques() {//metodo que devuelve los aviones en rodaje
    return avionesRodaje;
}

public void setAvionEmbarques(List<Avion> avionEmbarques) {//metodo que establece los aviones en rodaje
    this.avionesRodaje = avionEmbarques;
}

}

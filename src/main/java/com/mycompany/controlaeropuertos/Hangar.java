/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controlaeropuertos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gonzalo
 */
class Hangar {
     private List<Avion> aviones;

    public Hangar() {
        aviones = new ArrayList<>();
    }

    public void entrarAvion(Avion avion) {//metodo que hace que un avion entre en el hangar
        Registro.logEvent(" [ "+avion.getAeropuertoOrigen().getNombre()+" ] "+"Avion " + avion.Id() + " entra en hangar");
        aviones.add(avion);
    }

    public void salirAvion(Avion avion) {//metodo que hace que un avion salga del hangar
        Registro.logEvent(" [ "+avion.getAeropuertoOrigen().getNombre()+" ] "+"Avion " + avion.Id() + " sale de hangar");
        aviones.remove(avion);
    }
    public List<Avion> getAviones() {
        return aviones;
    }
    public Avion getAvion(String id) {
        for (Avion avion : aviones) {
            if (avion.Id() == id) {
                return avion;
            }
        }
        return null;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.controlaeropuertos;


import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author gonzalo
 */
public class ControlAeropuertos {

    public static void main(String[] args) {
     try {
            //Creacion de objetos
            Aerovia barcelona_madrid = new Aerovia("Barcelona-Madrid");
            Aerovia Madrid_barcelona = new Aerovia("Madrid-Barcelona");
            Aeropuerto aeropuerto_Madrid = new Aeropuerto("Aeropuerto de Madrid", Madrid_barcelona);
            Aeropuerto aeropuerto_Barcelona = new Aeropuerto("Aeropuerto de Barcelona", barcelona_madrid);

            //Creacion de objetos remotos
            ObjetoRemoto server= new ObjetoRemoto(aeropuerto_Madrid,aeropuerto_Barcelona);
            Registry registry = LocateRegistry.createRegistry(1099);
            Naming.rebind("//localhost/ObjetRMI", server);
            System.out.println("Servidor RMI listo");

            //Creacion de interfaz grafica
            Control control=new Control(aeropuerto_Madrid,aeropuerto_Barcelona);
            control.setVisible(true);
            //Bucle principal
            for (int i = 0; i < 8000; i++) {
                control.mostrar();
                aeropuerto_Barcelona.pausaSiEsNecesario();
                aeropuerto_Madrid.pausaSiEsNecesario();
                if (i < 4000) {
                    //Creacion de los autobuses
                    if (i % 2 == 0) {
                        Autobus bus = new Autobus("B-" + String.format("%04d", i), aeropuerto_Madrid);
                        Registro.logEvent("["+ aeropuerto_Madrid.getNombre()+ "]"+" Autobus " + bus.IdBus() + " creado en " + aeropuerto_Madrid.getNombre());
                        bus.start();
                    } else {
                        Autobus bus = new Autobus("B-" + String.format("%04d", i), aeropuerto_Barcelona);
                        Registro.logEvent("["+ aeropuerto_Barcelona.getNombre()+ "]" + " Autobus " + bus.IdBus() + " creado en " + aeropuerto_Barcelona.getNombre());
                        bus.start();
                    }
                    
                    try {
                        Thread.sleep(ThreadLocalRandom.current().nextInt(500, 1001));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //Creacion de los aviones
                if (i % 2 == 0) {
                    String id = generateRandomId(i);
                    int capacidad = ThreadLocalRandom.current().nextInt(100, 301);
                    Avion avion = new Avion(id, aeropuerto_Madrid, aeropuerto_Barcelona, capacidad);
                    Registro.logEvent("["+ aeropuerto_Madrid.getNombre()+ "]" + " Avion " + avion.Id() + " creado en " + aeropuerto_Madrid.getNombre());
                    aeropuerto_Madrid.getHangar().entrarAvion(avion);
                    avion.start();
                } else {
                    String id = generateRandomId(i);
                    int capacidad = ThreadLocalRandom.current().nextInt(100, 301);
                    Avion avion = new Avion(id, aeropuerto_Barcelona, aeropuerto_Madrid, capacidad);
                    Registro.logEvent("["+ aeropuerto_Barcelona.getNombre()+ "]" + " Avion " + avion.Id() + " creado en " + aeropuerto_Barcelona.getNombre());
                    aeropuerto_Barcelona.getHangar().entrarAvion(avion);
                    avion.start();

                }

                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 3001));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
            }
            while (true) {
                control.mostrar();
            }
        } catch (Exception e) {
            System.out.println(" Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    //Metodo que genera un id aleatorio
    private static String generateRandomId(int num) {
        String id = "";
        for (int i = 0; i < 2; i++) {
            id += (char) ThreadLocalRandom.current().nextInt(65, 91);
        }
        id += "-";
        id += String.format("%04d", num);
        return id;
    }
}

package Remoto;

import com.mycompany.controlaeropuertos.Interfaz_servidor;
import java.rmi.Naming;


public class Cliente {
    public static void main(String[] args) {
        InterfazCliente cliente = new InterfazCliente();
        cliente.setVisible(true);
        try {
            // Se obtiene la referencia al objeto remoto
            Interfaz_servidor server = (Interfaz_servidor) Naming.lookup("//localhost/ObjetRMI");
            System.out.println("Conectado al servidor");
            // Bucle principal
            while (true) {
                int pasajerosMadrid = server.numPasajerosMadrid();
                int pasajerosBarcelona = server.numPasajerosBarcelona();
                int avionesHangarMadrid = server.AvionesEnHangarMadrid();
                int avionesHangarBarcelona = server.AvionesEnHangarBarcelona();
                int avionesTallerMadrid = server.avionesEnTallerMadrid();
                int avionesTallerBarcelona = server.avionesEnTallerBarcelona();
                int avionesEstacionadosMadrid = server.AvionesEstacionadosMadrid();
                int avionesEstacionadosBarcelona = server.AvionesEstacionadosBarcelona();
                int avionesRodajeMadrid = server.avionesRodajeMadrid();
                int avionesRodajeBarcelona = server.avionesRodajeBarcelona();
                String avionesEnVueloM = server.avionesEnVueloM();
                String avionesEnVueloB = server.avionesEnVueloB();
                
                cliente.Actualizar(server, pasajerosMadrid, pasajerosBarcelona, avionesHangarMadrid, avionesHangarBarcelona,
                        avionesTallerMadrid, avionesTallerBarcelona, avionesEstacionadosMadrid,
                        avionesEstacionadosBarcelona, avionesRodajeMadrid, avionesRodajeBarcelona, avionesEnVueloM, avionesEnVueloB);
            }
        } catch (Exception e) {
            System.out.println("Excepción : " + e.getMessage());

            e.printStackTrace();
        }

    }
}

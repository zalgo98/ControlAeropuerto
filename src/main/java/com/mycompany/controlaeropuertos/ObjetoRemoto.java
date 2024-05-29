package com.mycompany.controlaeropuertos;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ObjetoRemoto extends UnicastRemoteObject implements Interfaz_servidor {
    private Aeropuerto Madrid;
    private Aeropuerto Barcelona;

    public ObjetoRemoto(Aeropuerto Madrid, Aeropuerto Barcelona) throws RemoteException {
        this.Madrid = Madrid;
        this.Barcelona = Barcelona;
    }

    public int numPasajerosMadrid() throws RemoteException {// Metodo que devuelve el numero de pasajeros disponibles en Madrid
        return Madrid.getPasajerosDisponibles();
    }

    public int numPasajerosBarcelona() throws RemoteException {// Metodo que devuelve el numero de pasajeros disponibles en Barcelona
        return Barcelona.getPasajerosDisponibles();
    }

    public int AvionesEnHangarMadrid() throws RemoteException {// Metodo que devuelve el numero de aviones en el hangar de Madrid
        return Madrid.getHangar().getAviones().size();
    }

    public int AvionesEnHangarBarcelona() throws RemoteException {// Metodo que devuelve el numero de aviones en el hangar de Barcelona
        return Barcelona.getHangar().getAviones().size();
    }

    public int avionesEnTallerMadrid() throws RemoteException {// Metodo que devuelve el numero de aviones en el taller de Madrid
        return Madrid.getTaller().getAvionesEnIspeccion().size();
    }

    public int avionesEnTallerBarcelona() throws RemoteException {// Metodo que devuelve el numero de aviones en el taller de Barcelona
        return Barcelona.getTaller().getAvionesEnIspeccion().size();
    }

    public int AvionesEstacionadosMadrid() throws RemoteException {// Metodo que devuelve el numero de aviones estacionados en Madrid
        return Madrid.getAreaEstacionamiento().getAvionesEnEspera().size();
    }

    public int AvionesEstacionadosBarcelona() throws RemoteException {// Metodo que devuelve el numero de aviones estacionados en Barcelona
        return Barcelona.getAreaEstacionamiento().getAvionesEnEspera().size();
    }

    public int avionesRodajeMadrid() throws RemoteException {// Metodo que devuelve el numero de aviones en el rodaje de Madrid
        return Madrid.getAreaRodaje().getAvionEmbarques().size();
    }

    public int avionesRodajeBarcelona() throws RemoteException {// Metodo que devuelve el numero de aviones en el rodaje de Barcelona
        return Barcelona.getAreaRodaje().getAvionEmbarques().size();
    }

    public String avionesEnVueloM() throws RemoteException {// Metodo que devuelve los aviones en vuelo de Madrid
        return Madrid.getAerovia().avionesAerolinea();
    }

    public String avionesEnVueloB() throws RemoteException {// Metodo que devuelve los aviones en vuelo de Barcelona
        return Barcelona.getAerovia().avionesAerolinea();
    }

    public void cerrarPista1Madrid() throws RemoteException {// Metodo que cierra la pista 1 de Madrid
        Madrid.getPistas().get(0).ControlPista(false);
    }

    public void cerrarPista2Madrid() {
        Madrid.getPistas().get(1).ControlPista(false);// Metodo que cierra la pista 2 de Madrid
    }

    public void cerrarPista3Madrid() throws RemoteException {// Metodo que cierra la pista 3 de Madrid
        Madrid.getPistas().get(2).ControlPista(false);
    }

    public void cerrarPista4Madrid() {
        Madrid.getPistas().get(3).ControlPista(false);// Metodo que cierra la pista 4 de Madrid
    }

    public void abrirPista1Madrid() throws RemoteException {// Metodo que abre la pista 1 de Madrid
        Madrid.getPistas().get(0).ControlPista(true);
    }

    public void abrirPista2Madrid() throws RemoteException {// Metodo que abre la pista 2 de Madrid
        Madrid.getPistas().get(1).ControlPista(true);
    }

    public void abrirPista3Madrid() throws RemoteException {// Metodo que abre la pista 3 de Madrid
        Madrid.getPistas().get(2).ControlPista(true);
    }

    public void abrirPista4Madrid() throws RemoteException {// Metodo que abre la pista 4 de Madrid
        Madrid.getPistas().get(3).ControlPista(true);
    }

    public void cerrarPista1Barcelona() throws RemoteException {// Metodo que cierra la pista 1 de Barcelona
        Barcelona.getPistas().get(0).ControlPista(false);
    }

    public void cerrarPista2Barcelona() throws RemoteException {// Metodo que cierra la pista 2 de Barcelona
        Barcelona.getPistas().get(1).ControlPista(false);
    }
    public void cerrarPista3Barcelona() throws RemoteException {// Metodo que cierra la pista 3 de Barcelona
        Barcelona.getPistas().get(2).ControlPista(false);
    }

    public void cerrarPista4Barcelona() throws RemoteException {// Metodo que cierra la pista 4 de Barcelona
        Barcelona.getPistas().get(3).ControlPista(false);
    }

    public void abrirPista1Barcelona() throws RemoteException {// Metodo que abre la pista 1 de Barcelona
        Barcelona.getPistas().get(0).ControlPista(true);
    }

    public void abrirPista2Barcelona() throws RemoteException {// Metodo que abre la pista 2 de Barcelona
        Barcelona.getPistas().get(1).ControlPista(true);
    }

  
    public void abrirPista3Barcelona() throws RemoteException {// Metodo que abre la pista 3 de Barcelona
        Barcelona.getPistas().get(2).ControlPista(true);
    }

    public void abrirPista4Barcelona() throws RemoteException {// Metodo que abre la pista 4 de Barcelona
        Barcelona.getPistas().get(3).ControlPista(true);
    }
}

package com.mycompany.controlaeropuertos;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Interfaz_servidor extends Remote{
    public int numPasajerosMadrid() throws RemoteException;
    public int numPasajerosBarcelona() throws RemoteException;
    public int AvionesEnHangarMadrid() throws RemoteException;
    public int AvionesEnHangarBarcelona() throws RemoteException;
    public int avionesEnTallerMadrid() throws RemoteException;
    public int avionesEnTallerBarcelona() throws RemoteException;
    public int AvionesEstacionadosMadrid() throws RemoteException;
    public int AvionesEstacionadosBarcelona() throws RemoteException;
    public int avionesRodajeMadrid() throws RemoteException;
    public int avionesRodajeBarcelona() throws RemoteException;
    public String avionesEnVueloM() throws RemoteException;
    public String avionesEnVueloB() throws RemoteException;

    //Metodos para abrir y cerrar pistas de madrid
    public void cerrarPista1Madrid() throws RemoteException;
    public void cerrarPista2Madrid() throws RemoteException;
    public void cerrarPista3Madrid() throws RemoteException;
    public void cerrarPista4Madrid() throws RemoteException;
    
    public void abrirPista1Madrid() throws RemoteException;
    public void abrirPista2Madrid() throws RemoteException;
    public void abrirPista3Madrid() throws RemoteException;
    public void abrirPista4Madrid() throws RemoteException;

    //Metodos para abrir y cerrar pistas de barcelona
    public void cerrarPista1Barcelona() throws RemoteException;
    public void cerrarPista2Barcelona() throws RemoteException;
    public void cerrarPista3Barcelona() throws RemoteException;
    public void cerrarPista4Barcelona() throws RemoteException;
    
    public void abrirPista1Barcelona() throws RemoteException;
    public void abrirPista2Barcelona() throws RemoteException;
    public void abrirPista3Barcelona() throws RemoteException;
    public void abrirPista4Barcelona() throws RemoteException;


}

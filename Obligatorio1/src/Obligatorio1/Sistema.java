package Obligatorio1;

import java.util.ArrayList;

public class Sistema {

    //VARIABLES PRIVADAS DE LA CLASE SISTEMA
    private Partida partida;
    private Interfaz interfaz;
    private ArrayList<Jugador> listaJugadores;

    //METODOS DE ACCESO Y MODIFICACION DE LA CLASE SISTEMA
    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public Interfaz getInterfaz() {
        return interfaz;
    }

    public void setInterfaz(Interfaz interfaz) {
        this.interfaz = interfaz;
    }

    public ArrayList<Jugador> getListaJugadores() {
        return listaJugadores;
    }

    public void setListaJugadores(ArrayList<Jugador> listaJugadores) {
        this.listaJugadores = listaJugadores;
    }

    //CONSTRUCTOR CON PARAMETROS DE LA CLASE SISTEMA
    public Sistema(Partida partida, Interfaz interfaz, ArrayList<Jugador> listaJugadores) {
        this.partida = partida;
        this.interfaz = interfaz;
        this.listaJugadores = listaJugadores;
    }

    //CONSTRUCTOR VACIO DE LA CLASE SISTEMA
    public Sistema() {

    }
}

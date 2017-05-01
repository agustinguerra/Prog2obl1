package Obligatorio1;

import static Obligatorio1.Program.pidoDatoIntPositivo;
import static Obligatorio1.Program.pidoDatoString;
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
        Interfaz inter = new Interfaz();
        this.interfaz = inter;
        ArrayList listaJugadores1 = new ArrayList<>();
        this.listaJugadores = listaJugadores1;
    }
    
    public void registroJugador() {
        Jugador jugador = new Jugador();
        jugador.setEdad(pidoDatoIntPositivo("Ingrese la edad del jugador: ", 0, Integer.MAX_VALUE, 0));
        jugador.setNombre(pidoDatoString("Ingrese el nombre del jugador: ",""));
        jugador.setJuegosGanados(0);
        jugador.setAlias(pidoDatoString("Ingrese el alias del jugador (DEBE SER UNICO, SE COMPROBARA EXISTENCIA REPETIDA). ",""));
        for (int i = 0; i < listaJugadores.size(); i++) {
            if (jugador.getAlias().equals(listaJugadores.get(i).getAlias())) {
                jugador.setAlias(pidoDatoString("Ingrese el alias del jugador: ", jugador.getAlias()));
            }
        }
        listaJugadores.add(jugador);
    }
}

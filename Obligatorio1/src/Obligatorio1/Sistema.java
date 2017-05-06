package Obligatorio1;

import static Obligatorio1.Program.pidoDatoIntPositivo;
import static Obligatorio1.Program.pidoDatoString;
import java.util.ArrayList;
import java.util.Collections;

public class Sistema {

    //VARIABLES PRIVADAS DE LA CLASE SISTEMA
    private Partida partida;
    private ArrayList<Jugador> listaJugadores;

    //METODOS DE ACCESO Y MODIFICACION DE LA CLASE SISTEMA
    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public ArrayList<Jugador> getListaJugadores() {
        return listaJugadores;
    }

    public void setListaJugadores(ArrayList<Jugador> listaJugadores) {
        this.listaJugadores = listaJugadores;
    }

    //CONSTRUCTOR CON PARAMETROS DE LA CLASE SISTEMA
    public Sistema(Partida partida, ArrayList<Jugador> listaJugadores) {
        this.partida = partida;
        this.listaJugadores = listaJugadores;
    }

    //CONSTRUCTOR VACIO DE LA CLASE SISTEMA
    public Sistema() {
        this.listaJugadores = new ArrayList<>();       
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
    
    public void ranking() {
        Collections.sort(this.getListaJugadores());
        for (int i = 0; i < listaJugadores.size(); i++) {
            System.out.println(this.listaJugadores.get(i));
        }        
    }
    
    public void jugarEntreJugadores() {
        int jugadorUnoFichas;
        jugadorUnoFichas = 25;
        int jugadorDosFichas;
        jugadorDosFichas = 25;
        this.partida = new Partida();
        //ACA VA DONDE SE LISTA LOS JUGADORES, Y SE LE PIDE AL USUARIO QUE ELIGA CON CUALES QUIERE JUGAR
        System.out.println("Este es el listado de los jugadores disponibles.");
        for (int i = 0; i < this.listaJugadores.size(); i++) {
            System.out.print(i);
            System.out.println(this.listaJugadores.get(i));
        }
        int jUno = pidoDatoIntPositivo("Ingrese jugador uno: ",-1,this.listaJugadores.size(),-1);
        this.partida.setJugadorUno(this.listaJugadores.get(jUno));
        this.partida.setJugadorDos(this.listaJugadores.get(pidoDatoIntPositivo("Ingrese jugador dos: ",-1,this.listaJugadores.size(),jUno))); 
        //SETEA AMBOS JUGADORES DE LA PARTIDA        
        boolean cond = false;
        boolean turnoDe=true; //BOOLEANO PARA SABER DE QUIEN ES EL TURNO, SI ES TRUE JUNO SI ES FALSE JDOS
        while (!cond) {
            
            
            
            
            
            
            
            if ((jugadorUnoFichas==0) || (jugadorDosFichas==0)){ //CHEQUEO AL FINAL DE CADA TURNO PARA VER SI SE TERMINO LA PARTIDA
                cond=true;
            }
            else {
                turnoDe=!turnoDe; //SI LA PARTIDA NO TERMINO, CAMBIO EL TURNO AL OTRO JUGADOR
            }
        }
    }
    
    public void jugarContraPC() {
        int jugadorUnoFichas = 25;
        int jugadorPCFichas = 25;
        this.partida = new Partida();
        System.out.println("Este es el listado de los jugadores disponibles.");
        for (int i = 0; i < this.listaJugadores.size(); i++) {
            System.out.print(i);
            System.out.println(this.listaJugadores.get(i));
        }
        this.partida.setJugadorDos(this.listaJugadores.get(pidoDatoIntPositivo("Ingrese jugador dos: ",-1,this.listaJugadores.size(),-1)));
        //JUGADOR DOS NO SE SETEA PORQUE ES LA PC Y NO  ES NECESARIO.
        boolean cond = false;
        boolean turnoDe=true; //BOOLEANO PARA SABER DE QUIEN ES EL TURNO, SI ES TRUE JUNO SI ES FALSE JDOS
        while (!cond) {
            
            
            
            
            
            
            
            if ((jugadorUnoFichas==0) || (jugadorPCFichas==0)){ //CHEQUEO AL FINAL DE CADA TURNO PARA VER SI SE TERMINO LA PARTIDA
                cond=true;
            }
            else {
                turnoDe=!turnoDe; //SI LA PARTIDA NO TERMINO, CAMBIO EL TURNO AL OTRO JUGADOR
            }
        }
    } 
}

package Obligatorio1;

import static Obligatorio1.Program.pidoDatoIntPositivo;
import static Obligatorio1.Program.pidoDatoString;
import java.util.ArrayList;
import java.util.Collections;

public class Sistema {

    //VARIABLES PRIVADAS DE LA CLASE SISTEMA
    private Partida partida;
    private ArrayList<Jugador> listaJugadores;
    private Reglas libroDeReglas;

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
        this.libroDeReglas = new Reglas();
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
        int fichaI;
        int fichaJ;
        boolean movimientoValido;
        boolean esPrimerTurno=true;
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
        System.out.println("Para ingresar la posicion donde desea colocar la ficha, recuerde que la A=0,B=1,C=2,D=3,E=4,F=5");
        boolean cond = false;
        boolean turnoDe=true; //BOOLEANO PARA SABER DE QUIEN ES EL TURNO, SI ES TRUE JUNO SI ES FALSE JDOS
        while (!cond) {
            movimientoValido=false;
            if (turnoDe==true){ //DEPENDE DE QUIEN SEA EL TURNO, A QUIEN LE DOY LA BIENVENIDA
                System.out.println("Es el turno del jugador uno");
            }
            else {
                System.out.println("Es el turno del jugador dos");
            }
            while (movimientoValido==false){  //SALE DEL WHILE CUANDO SE COMPROBO QUE EL LUGAR DONDE LA PERSONA QUIERE PONER LA FICHA ES VALIDO
                fichaI = pidoDatoIntPositivo("Ingrese la fila de la ficha",-1,6,-1);
                fichaJ = pidoDatoIntPositivo("Ingrese la columna de la ficha",-1,6,-1);
                if (libroDeReglas.formaCuadrado(fichaI,fichaJ,this.partida.getTablero())==false){ //ACA PONGO TODOS LOS METODOS QUE VALIDAN EL MOVIMIENTO, 
                    if (libroDeReglas.hayFicha(fichaI,fichaJ,this.partida.getTablero())==false){  //SI LOS PASA TODOS ENTONCES VALIDO EL MOVIMIENTO Y SIGO DE LARGO
                        if(esPrimerTurno==true || libroDeReglas.tieneAdyacente(fichaI, fichaJ, this.partida.getTablero())){
                            esPrimerTurno=false; //SI ES EL PRIMER TURNO, NUNCA VA A TENER ADYACENTE, POR ESO ESTE CONTROL ESPECIAL
                            movimientoValido=true; //CONFIRMO QUE ES MOVIMIENTO VALIDO, SALE DEL WHILE Y SIGUE LA JUGADA.
                        }
                    }                
                }
                if (movimientoValido==false){ //SI EL MOVIMIENTO NO FUE VALIDO, LO INFORMO PARA QUE EL JUGADOR SEPA.
                    System.out.println("El movimiento que quiso hacer no fue valido. Intente de nuevo.");
                }
            }
            //AQUI YA SE A DONDE EL JUGADOR QUIERE MOVER LA FICHA, Y SE QUE EL MOVIMIENTO ES VALIDO. PROCEDO A HACER LA JUGADA

            
            
            
            
            
            if ((jugadorUnoFichas==0) || (jugadorDosFichas==0)){ //CHEQUEO AL FINAL DE CADA TURNO PARA VER SI SE TERMINO LA PARTIDA
                cond=true;
            }
            else {
                turnoDe=!turnoDe; //SI LA PARTIDA NO TERMINO, CAMBIO EL TURNO AL OTRO JUGADOR
            }
        }
        //UNA VEZ QUE SE TERMINO LA PARTIDA, ACTUALIZO EL RANKING, ES DECIR LE SUMO UNA PARTIDA GANADA AL QUE GANO
        //SI EMPATAN NO LE SUMO NADA A NADIE
        if (libroDeReglas.calcularPuntaje(1,this.partida.getTablero())>libroDeReglas.calcularPuntaje(2,this.partida.getTablero())){
            int jGanados=this.partida.getJugadorUno().getJuegosGanados();
            this.partida.getJugadorUno().setJuegosGanados(jGanados+1);
        }
        if (libroDeReglas.calcularPuntaje(1,this.partida.getTablero())<libroDeReglas.calcularPuntaje(2,this.partida.getTablero())){
            int jGanados=this.partida.getJugadorDos().getJuegosGanados();
            this.partida.getJugadorDos().setJuegosGanados(jGanados+1);
        }
    }
    
    public void jugarContraPC() {
        int jugadorUnoFichas = 25;
        int jugadorPCFichas = 25;
        this.partida = new Partida();
        int fichaI;
        int fichaJ;
        boolean movimientoValido;
        boolean esPrimerTurno=true;
        System.out.println("Este es el listado de los jugadores disponibles.");
        for (int i = 0; i < this.listaJugadores.size(); i++) {
            System.out.print(i);
            System.out.println(this.listaJugadores.get(i));
        }
        this.partida.setJugadorDos(this.listaJugadores.get(pidoDatoIntPositivo("Ingrese jugador dos: ",-1,this.listaJugadores.size(),-1)));
        //JUGADOR DOS NO SE SETEA PORQUE ES LA PC Y NO  ES NECESARIO.
        boolean cond = false;
        boolean turnoDe=true; //BOOLEANO PARA SABER DE QUIEN ES EL TURNO, SI ES TRUE JUNO SI ES FALSE JDOS
        System.out.println("Para ingresar la posicion donde desea colocar la ficha, recuerde que la A=0,B=1,C=2,D=3,E=4,F=5");
        while (!cond) {
            if (turnoDe==false){
                System.out.println("Es el turno del jugador.");
                fichaI = pidoDatoIntPositivo("Ingrese la fila de la ficha",-1,6,-1);
                fichaJ = pidoDatoIntPositivo("Ingrese la columna de la ficha",-1,6,-1);            
            
            }
            else {
                //TURNO DE LA PC
            }            
            if ((jugadorUnoFichas==0) || (jugadorPCFichas==0)){ //CHEQUEO AL FINAL DE CADA TURNO PARA VER SI SE TERMINO LA PARTIDA
                cond=true;
            }
            else {
                turnoDe=!turnoDe; //SI LA PARTIDA NO TERMINO, CAMBIO EL TURNO AL OTRO JUGADOR
            }
        }
        //VAMOS A SUMAR UNA VICTORIA SI EL JUGADOR LE GANA A LA MAQUINA?
    } 
}

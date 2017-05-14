package Obligatorio1;

import static Obligatorio1.Program.pidoDatoIntPositivo;
import static Obligatorio1.Program.pidoDatoString;
import static Obligatorio1.Program.pidoDatosParaMovimientoValido;
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

    public int primerCoordenadaMovimiento(String x){ //EXTRAIGO LA PRIMER COORDENADA DE LA FICHA A PONER
        int devolver=0;
        switch (x.charAt(0)) {
            case 'A':
                devolver=0;
                break;
            case 'B':
                devolver=1;
                break;
            case 'C':
                devolver=2;
                break;
            case 'D':
                devolver=3;
                break;
            case 'E':
                devolver=4;
                break;
            case 'F':
                devolver=5;
                break;
            default:
                break;
        }
        return devolver;
    }
    
    public int segundaCoordenadaMovimiento(String x){ //EXTRAIGO LA SEGUNDA COORDENADA DE LA FICHA A PONER
        int devolver;
        devolver=Character.getNumericValue(x.charAt(1))-1;
        return devolver;
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
        String movimiento;
        int fichaI=0;
        int fichaJ=0;
        int cantidadFichasTableroPreColocar=0;
        int cantidadFichasTableroPostColocar=0;
        boolean movimientoValido;
        boolean esPrimerTurno=true;
        this.partida = new Partida();
        //ACA VA DONDE SE LISTA LOS JUGADORES, Y SE LE PIDE AL USUARIO QUE ELIGA CON CUALES QUIERE JUGAR
        System.out.println("Este es el listado de los jugadores disponibles.");
        for (int i = 0; i < this.listaJugadores.size(); i++) {
            System.out.print(i+" \n");          
            System.out.println(this.listaJugadores.get(i));
        }
        int jUno = pidoDatoIntPositivo("Ingrese jugador uno: ",-1,this.listaJugadores.size(),-1);
        this.partida.setJugadorUno(this.listaJugadores.get(jUno));
        this.partida.setJugadorDos(this.listaJugadores.get(pidoDatoIntPositivo("Ingrese jugador dos: ",-1,this.listaJugadores.size(),jUno))); 
        //SETEA AMBOS JUGADORES DE LA PARTIDA
        boolean cond = false;
        boolean turnoDe=true; //BOOLEANO PARA SABER DE QUIEN ES EL TURNO, SI ES TRUE JUNO SI ES FALSE JDOS
        int intTurnoDe;
        while (!cond) {
            movimientoValido=false;
            this.partida.getTablero().dibujarTablero();
            if (turnoDe){ //DEPENDE DE QUIEN SEA EL TURNO, A QUIEN LE DOY LA BIENVENIDA
                System.out.println("Es el turno del jugador uno");
                intTurnoDe=1;
            }
            else {
                System.out.println("Es el turno del jugador dos");
                intTurnoDe=2;
            }
            while (!movimientoValido){  //SALE DEL WHILE CUANDO SE COMPROBO QUE EL LUGAR DONDE LA PERSONA QUIERE PONER LA FICHA ES VALIDO
                movimiento = pidoDatosParaMovimientoValido("Ingrese las coordenadas de donde desee colocar la ficha. Si el movimiento no es valido o el formato no es correcto se le volvera a pedir.");              
                fichaI = this.primerCoordenadaMovimiento(movimiento); //PRIMERO VALIDO LA STRING QUE ESTE EN EL FORMATO CORRECTO Y EN EL RANGO, Y LUEGO EXTRAIGO LAS COORDENADAS PARA TRABAJAR CON ELLAS
                fichaJ = this.segundaCoordenadaMovimiento(movimiento);
                if (!libroDeReglas.formaCuadrado(fichaI,fichaJ,this.partida.getTablero())){ //ACA PONGO TODOS LOS METODOS QUE VALIDAN EL MOVIMIENTO, 
                    if(esPrimerTurno==true || libroDeReglas.tieneAdyacente(fichaI, fichaJ, this.partida.getTablero())){
                        esPrimerTurno=false; //SI ES EL PRIMER TURNO, NUNCA VA A TENER ADYACENTE, POR ESO ESTE CONTROL ESPECIAL
                        movimientoValido=true; //CONFIRMO QUE ES MOVIMIENTO VALIDO, SALE DEL WHILE Y SIGUE LA JUGADA.
                    }                                 
                }
                if (movimientoValido==false){ //SI EL MOVIMIENTO NO FUE VALIDO, LO INFORMO PARA QUE EL JUGADOR SEPA.
                    System.out.println("El movimiento que quiso hacer no fue valido. Intente de nuevo.");
                }
            }
            //AQUI YA SE A DONDE EL JUGADOR QUIERE MOVER LA FICHA, Y SE QUE EL MOVIMIENTO ES VALIDO. PROCEDO A HACER LA JUGADA
            //ANTES DE PONER LA FICHA, ME FIJO CUANTAS FICHAS HABIA EN EL TABLERO
            cantidadFichasTableroPreColocar=libroDeReglas.calcularCantidadFichas(this.partida.getTablero());
            libroDeReglas.seFormoEsquina(fichaI, fichaJ, this.partida.getTablero(), intTurnoDe);
            //ACA VA EL METODO DE EXTENDERLAS
            
            //LUEGO DE CHEQUEAR ESQUINAS Y EXTENDIDAS, CUENTO CUANTAS FICHAS SE PUSIERON
            //SOUTEO CUANTAS SE PUSIERON, Y SE LAS RESTO AL MONTON DE FICHAS DEL JUGADOR INDICADO
            cantidadFichasTableroPostColocar=libroDeReglas.calcularCantidadFichas(this.partida.getTablero());
            if (intTurnoDe==1){
                jugadorUnoFichas=jugadorUnoFichas-(cantidadFichasTableroPostColocar-cantidadFichasTableroPreColocar);
            }
            else{
                jugadorDosFichas=jugadorDosFichas-(cantidadFichasTableroPostColocar-cantidadFichasTableroPreColocar);
            }
            
            System.out.println("Al jugador uno le quedan "+jugadorUnoFichas+" cubos"); 
            System.out.println("Al jugador dos le quedan "+jugadorDosFichas+" cubos"); 
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
        String movimiento;
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
        while (!cond) {
            if (turnoDe==false){
                System.out.println("Es el turno del jugador.");
                movimiento = pidoDatosParaMovimientoValido("Ingrese las coordenadas de donde desee colocar la ficha. Si el movimiento no es valido o el formato no es correcto se le volvera a pedir.");              
                fichaI = this.primerCoordenadaMovimiento(movimiento); //PRIMERO VALIDO LA STRING QUE ESTE EN EL FORMATO CORRECTO Y EN EL RANGO, Y LUEGO EXTRAIGO LAS COORDENADAS PARA TRABAJAR CON ELLAS
                fichaJ = this.segundaCoordenadaMovimiento(movimiento);        
            
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

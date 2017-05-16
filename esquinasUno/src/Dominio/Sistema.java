package Dominio;

import Interfaz.Interfaz;

import static Program.Program.pidoDatoIntPositivo;
import static Program.Program.pidoDatoString;
import static Program.Program.pidoDatosParaMovimientoValido;
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

    //ESTE METODO PERMITE REGISTRAR UN JUGADOR, ADEMAS CHEQUEA QUE EL ALIAS ES UNICO
    public void registroJugador() {
        Jugador jugador = new Jugador();
        jugador.setEdad(pidoDatoIntPositivo("Ingrese la edad del jugador: ", 0, Integer.MAX_VALUE, 0));
        jugador.setNombre(pidoDatoString("Ingrese el nombre del jugador: ", ""));
        jugador.setJuegosGanados(0);
        jugador.setAlias(pidoDatoString("Ingrese el alias del jugador (DEBE SER UNICO, SE COMPROBARA EXISTENCIA REPETIDA). ", ""));
        for (int i = 0; i < listaJugadores.size(); i++) {
            if (jugador.getAlias().equals(listaJugadores.get(i).getAlias())) {
                jugador.setAlias(pidoDatoString("Ingrese el alias del jugador: ", jugador.getAlias()));
            }
        }
        listaJugadores.add(jugador);
    }

    //ESTE METODO VALIDA LA PRIMER COORDENADA DEL MOVIMIENTO
    public int primerCoordenadaMovimiento(String x) { //EXTRAIGO LA PRIMER COORDENADA DE LA FICHA A PONER
        int devolver = 0;
        switch (x.charAt(0)) {
            case 'A':
                devolver = 0;
                break;
            case 'B':
                devolver = 1;
                break;
            case 'C':
                devolver = 2;
                break;
            case 'D':
                devolver = 3;
                break;
            case 'E':
                devolver = 4;
                break;
            case 'F':
                devolver = 5;
                break;
            default:
                break;
        }
        return devolver;
    }

    //ESTE METODO VALIDA LA SEGUNDA COORDENADA DEL MOVIMIENTO
    public int segundaCoordenadaMovimiento(String x) { //EXTRAIGO LA SEGUNDA COORDENADA DE LA FICHA A PONER
        int devolver;
        devolver = Character.getNumericValue(x.charAt(1)) - 1;
        return devolver;
    }

    //ESTE METODO RANKEA A LOS JUGADORES POR PARTIDAS GANADAS
    public void ranking() {
        Interfaz interfaz = new Interfaz();
        Collections.sort(this.getListaJugadores());
        for (int i = 0; i < this.getListaJugadores().size(); i++) {
            interfaz.imprimirJugadorEnPantallaParaSeleccion(this.getListaJugadores(), i);
        }
    }

    //ESTE METODO ES EL QUE SE ENCARGA DE REALIZAR EL JUEGO JUGADOR VS JUGADOR
    public void jugarEntreJugadores() {
        Interfaz interfaz = new Interfaz();
        int jugadorUnoFichas;
        jugadorUnoFichas = 25;
        int jugadorDosFichas;
        jugadorDosFichas = 25;
        String movimiento;
        int fichaI = 0;
        int fichaJ = 0;
        boolean movimientoValido;
        boolean esPrimerTurno = true;
        this.partida = new Partida();
        interfaz.imprimirEnPantalla("Este es el listado de los jugadores disponibles.", "", 0, 1);
        for (int i = 0; i < this.listaJugadores.size(); i++) {
            interfaz.imprimirJugadorEnPantallaParaSeleccion(listaJugadores, i);
        }
        int jUno = pidoDatoIntPositivo("Elija al jugador UNO: ", 0, this.listaJugadores.size() + 1, -1);

        //SETEA AMBOS JUGADORES DE LA PARTIDA
        this.partida.setJugadorUno(this.listaJugadores.get(jUno - 1));
        this.partida.setJugadorDos(this.listaJugadores.get(pidoDatoIntPositivo("Elija al jugador DOS: ", 0, this.listaJugadores.size() + 1, jUno) - 1));

        boolean cond = false;
        boolean turnoDe = true; //BOOLEANO PARA SABER DE QUIEN ES EL TURNO, SI ES TRUE jUNO SI ES FALSE jDOS
        int intTurnoDe;
        while (!cond) {
            movimientoValido = false;
            this.partida.getTablero().dibujarTablero();
            if (turnoDe) { //DEPENDE DE QUIEN SEA EL TURNO, A QUIEN LE DOY LA BIENVENIDA
                interfaz.imprimirEnPantalla("Es el turno del jugador uno", "", 0, 1);
                intTurnoDe = 1;
            } else {
                interfaz.imprimirEnPantalla("Es el turno del jugador dos", "", 0, 1);
                intTurnoDe = 2;
            }
            while (!movimientoValido) {  //SALE DEL WHILE CUANDO SE COMPROBO QUE EL LUGAR DONDE LA PERSONA QUIERE PONER LA FICHA ES VALIDO
                movimiento = pidoDatosParaMovimientoValido("Ingrese las coordenadas de donde desee colocar la ficha. Si el movimiento no es valido o el formato no es correcto se le volvera a pedir. Para abanonar la partida ingrese X.");
                if (movimiento.equals("X")) {   //SI SE DA LA X, SE TERMINA EL JUEGO Y GANA EL JUGADOR CONTRARIO AL QUE ABANDONO
                    cond = true;
                    if (intTurnoDe == 2) {
                        int jGanados = this.partida.getJugadorUno().getJuegosGanados();
                        this.partida.getJugadorUno().setJuegosGanados(jGanados + 1);
                        interfaz.imprimirEnPantalla("Gano el jugador uno", "", 0, 1);
                    } else {
                        int jGanados = this.partida.getJugadorDos().getJuegosGanados();
                        this.partida.getJugadorDos().setJuegosGanados(jGanados + 1);
                        interfaz.imprimirEnPantalla("Gano el jugador dos", "", 0, 1);
                    }
                    return;
                }
                fichaI = this.primerCoordenadaMovimiento(movimiento); //PRIMERO VALIDO LA STRING QUE ESTE EN EL FORMATO CORRECTO Y EN EL RANGO, Y LUEGO EXTRAIGO LAS COORDENADAS PARA TRABAJAR CON ELLAS
                fichaJ = this.segundaCoordenadaMovimiento(movimiento);
                if (!libroDeReglas.formaCuadrado(fichaI, fichaJ, this.partida.getTablero())) { //ACA PONGO TODOS LOS METODOS QUE VALIDAN EL MOVIMIENTO, 
                    if (esPrimerTurno == true || libroDeReglas.tieneAdyacente(fichaI, fichaJ, this.partida.getTablero())) {
                        esPrimerTurno = false; //SI ES EL PRIMER TURNO, NUNCA VA A TENER ADYACENTE, POR ESO ESTE CONTROL ESPECIAL
                        movimientoValido = true; //CONFIRMO QUE ES MOVIMIENTO VALIDO, SALE DEL WHILE Y SIGUE LA JUGADA.
                    }
                }
                if (movimientoValido == false) { //SI EL MOVIMIENTO NO FUE VALIDO, LO INFORMO PARA QUE EL JUGADOR SEPA.
                    interfaz.imprimirEnPantalla("El movimiento que quiso hacer no fue valido. Intente de nuevo.", "", 0, 1);
                } else {
                    //AQUI YA SE A DONDE EL JUGADOR QUIERE MOVER LA FICHA, Y SE QUE EL MOVIMIENTO ES VALIDO. PROCEDO A HACER LA JUGADA
                    if (intTurnoDe == 1) {
                        jugadorUnoFichas = libroDeReglas.seFormoEsquina(fichaI, fichaJ, this.partida.getTablero(), intTurnoDe, jugadorUnoFichas);
                        jugadorUnoFichas = libroDeReglas.seExtendioEsquina(fichaI, fichaJ, this.partida.getTablero(), intTurnoDe, jugadorUnoFichas);
                        //ACA VA EL METODO DE EXTENDERLAS
                    } else {
                        jugadorDosFichas = libroDeReglas.seFormoEsquina(fichaI, fichaJ, this.partida.getTablero(), intTurnoDe, jugadorDosFichas);
                        jugadorDosFichas = libroDeReglas.seExtendioEsquina(fichaI, fichaJ, this.partida.getTablero(), intTurnoDe, jugadorDosFichas);
                        //ACA VA EL METODO DE EXTENDERLAS
                    }

                    interfaz.imprimirEnPantalla("Al jugador uno le quedan", "cubos", jugadorUnoFichas, 2);
                    interfaz.imprimirEnPantalla("Al jugador dos le quedan", "cubos", jugadorDosFichas, 2);
                    if ((jugadorUnoFichas == 0) || (jugadorDosFichas == 0)) { //CHEQUEO AL FINAL DE CADA TURNO PARA VER SI SE TERMINO LA PARTIDA
                        cond = true;
                    } else {
                        turnoDe = !turnoDe; //SI LA PARTIDA NO TERMINO, CAMBIO EL TURNO AL OTRO JUGADOR
                    }
                }
            }
        }
        //UNA VEZ QUE SE TERMINO LA PARTIDA, ACTUALIZO EL RANKING, ES DECIR LE SUMO UNA PARTIDA GANADA AL QUE GANO
        //SI EMPATAN NO LE SUMO NADA A NADIE
        this.partida.getTablero().dibujarTablero();
        if (libroDeReglas.calcularPuntaje(1, this.partida.getTablero()) > libroDeReglas.calcularPuntaje(2, this.partida.getTablero())) {
            int jGanados = this.partida.getJugadorUno().getJuegosGanados();
            this.partida.getJugadorUno().setJuegosGanados(jGanados + 1);
            interfaz.imprimirEnPantalla("Gano el jugador uno", "", 0, 1);
            interfaz.imprimirEnPantalla("El puntaje del ganador fue: ", "", 0, 1);
            interfaz.imprimirEnPantalla("", "", libroDeReglas.calcularPuntaje(1, this.partida.getTablero()), 3);
        } else if (libroDeReglas.calcularPuntaje(1, this.partida.getTablero()) < libroDeReglas.calcularPuntaje(2, this.partida.getTablero())) {
            int jGanados = this.partida.getJugadorDos().getJuegosGanados();
            this.partida.getJugadorDos().setJuegosGanados(jGanados + 1);
            interfaz.imprimirEnPantalla("Gano el jugador dos", "", 0, 1);
            interfaz.imprimirEnPantalla("El puntaje del ganador fue: ", "", 0, 1);
            interfaz.imprimirEnPantalla("", "", libroDeReglas.calcularPuntaje(2, this.partida.getTablero()), 3);
        } else {
            interfaz.imprimirEnPantalla("El juego termino en empate", "", 0, 1);
        }
    }

    //ESTE METODO ES EL QUE SE ENCARGA DE REALIZAR EL JUEGO JUGADOR VS PC
    public void jugarContraPC() {
        Interfaz interfaz = new Interfaz();
        int jugadorUnoFichas;
        jugadorUnoFichas = 25;
        int jugadorDosPC;
        jugadorDosPC = 25;
        String movimiento;
        int fichaI = 0;
        int fichaJ = 0;
        boolean movimientoValido;
        boolean esPrimerTurno = true;
        this.partida = new Partida();
        interfaz.imprimirEnPantalla("Este es el listado de los jugadores disponibles.", "", 0, 1);
        for (int i = 0; i < this.listaJugadores.size(); i++) {
            interfaz.imprimirJugadorEnPantallaParaSeleccion(listaJugadores, i);
        }
        int jUno = pidoDatoIntPositivo("Elija al jugador: ", 0, this.listaJugadores.size() + 1, -1);
        this.partida.setJugadorUno(this.listaJugadores.get(jUno - 1));
        boolean cond = false;
        boolean turnoDe = true; //BOOLEANO PARA SABER DE QUIEN ES EL TURNO, SI ES TRUE jUNO SI ES FALSE JUEGA PC
        int intTurnoDe;
        while (!cond) {
            movimientoValido = false;
            this.partida.getTablero().dibujarTablero();
            if (turnoDe) { //DEPENDE DE QUIEN SEA EL TURNO, A QUIEN LE DOY LA BIENVENIDA
                interfaz.imprimirEnPantalla("Es el turno del jugador uno", "", 0, 1);
                intTurnoDe = 1;
            } else {
                interfaz.imprimirEnPantalla("Es el turno de la PC", "", 0, 1);
                intTurnoDe = 2;
            }
            while (!movimientoValido) {  //SALE DEL WHILE CUANDO SE COMPROBO QUE EL LUGAR DONDE LA PERSONA QUIERE PONER LA FICHA ES VALIDO
                if (intTurnoDe == 1) { //FLUJO NORMAL, JUEGA EL JUGADOR
                    movimiento = pidoDatosParaMovimientoValido("Ingrese las coordenadas de donde desee colocar la ficha. Si el movimiento no es valido o el formato no es correcto se le volvera a pedir. Para abanonar la partida ingrese X.");
                    if (movimiento.equals("X")) {   //SI SE DA LA X, SE TERMINA EL JUEGO Y GANA EL JUGADOR PC, PORQUE LA PC NO PUEDE ABANDONAR
                        interfaz.imprimirEnPantalla("Gano la PC", "", 0, 1);
                        return;
                    }
                    fichaI = this.primerCoordenadaMovimiento(movimiento); //PRIMERO VALIDO LA STRING QUE ESTE EN EL FORMATO CORRECTO Y EN EL RANGO, Y LUEGO EXTRAIGO LAS COORDENADAS PARA TRABAJAR CON ELLAS
                    fichaJ = this.segundaCoordenadaMovimiento(movimiento);
                    if (!libroDeReglas.formaCuadrado(fichaI, fichaJ, this.partida.getTablero())) { //ACA PONGO TODOS LOS METODOS QUE VALIDAN EL MOVIMIENTO, 
                        if (esPrimerTurno == true || libroDeReglas.tieneAdyacente(fichaI, fichaJ, this.partida.getTablero())) {
                            esPrimerTurno = false; //SI ES EL PRIMER TURNO, NUNCA VA A TENER ADYACENTE, POR ESO ESTE CONTROL ESPECIAL
                            movimientoValido = true; //CONFIRMO QUE ES MOVIMIENTO VALIDO, SALE DEL WHILE Y SIGUE LA JUGADA.
                        }
                    }
                    if (movimientoValido == false) { //SI EL MOVIMIENTO NO FUE VALIDO, LO INFORMO PARA QUE EL JUGADOR SEPA.
                        interfaz.imprimirEnPantalla("El movimiento que quiso hacer no fue valido. Intente de nuevo.", "", 0, 1);
                    } else {
                        //AQUI YA SE A DONDE EL JUGADOR UNO QUIERE MOVER LA FICHA, Y SE QUE EL MOVIMIENTO ES VALIDO. PROCEDO A HACER LA JUGADA
                        jugadorUnoFichas = libroDeReglas.seFormoEsquina(fichaI, fichaJ, this.partida.getTablero(), intTurnoDe, jugadorUnoFichas);
                        jugadorUnoFichas = libroDeReglas.seExtendioEsquina(fichaI, fichaJ, this.partida.getTablero(), intTurnoDe, jugadorUnoFichas);
                        interfaz.imprimirEnPantalla("Al jugador le quedan", "cubos", jugadorUnoFichas, 2);
                        interfaz.imprimirEnPantalla("A la PC le quedan", "cubos", jugadorDosPC, 2);
                        if ((jugadorUnoFichas == 0)) { //CHEQUEO AL FINAL DE CADA TURNO PARA VER SI SE TERMINO LA PARTIDA
                            cond = true;
                        } else {
                            turnoDe = !turnoDe; //SI LA PARTIDA NO TERMINO, CAMBIO EL TURNO A LA PC
                        }
                    }
                } else { //JUEGA LA PC                    
                    jugadorDosPC = inteligenciaArtificial(jugadorDosPC);
                    //ESTO VA AL FINAL EL ESE JUEGA LA PC, LUEGO DEL MOVIMIENTO Y TODO
                    movimientoValido = true; // AL FINAL VALIDO EL MOVIMIENTO DE LA PC PARA SALIR DEL WHILE
                    if ((jugadorDosPC == 0)) { //CHEQUEO AL FINAL DE CADA TURNO PARA VER SI SE TERMINO LA PARTIDA
                        cond = true;
                    } else {
                        turnoDe = !turnoDe; //SI LA PARTIDA NO TERMINO, CAMBIO EL TURNO A LA PC
                    }
                    interfaz.imprimirEnPantalla("Al jugador le quedan", "cubos", jugadorUnoFichas, 2);
                    interfaz.imprimirEnPantalla("A la PC le quedan", "cubos", jugadorDosPC, 2);
                }
            }
        }

        //UNA VEZ QUE SE TERMINO LA PARTIDA, ACTUALIZO EL RANKING, ES DECIR LE SUMO UNA PARTIDA GANADA AL QUE GANO
        //SI EMPATAN NO LE SUMO NADA A NADIE
        this.partida.getTablero().dibujarTablero();
        if (libroDeReglas.calcularPuntaje(1, this.partida.getTablero()) > libroDeReglas.calcularPuntaje(2, this.partida.getTablero())) {
            int jGanados = this.partida.getJugadorUno().getJuegosGanados();
            this.partida.getJugadorUno().setJuegosGanados(jGanados + 1);
            interfaz.imprimirEnPantalla("Gano el jugador", "", 0, 1);
            interfaz.imprimirEnPantalla("El puntaje del jugador fue: ", "", 0, 1);
            interfaz.imprimirEnPantalla("", "", libroDeReglas.calcularPuntaje(1, this.partida.getTablero()), 3);
        } else if (libroDeReglas.calcularPuntaje(1, this.partida.getTablero()) < libroDeReglas.calcularPuntaje(2, this.partida.getTablero())) {
            interfaz.imprimirEnPantalla("Gano la PC", "", 0, 1);
            interfaz.imprimirEnPantalla("El puntaje de la PC fue: ", "", 0, 1);
            interfaz.imprimirEnPantalla("", "", libroDeReglas.calcularPuntaje(2, this.partida.getTablero()), 3);
        } else {
            interfaz.imprimirEnPantalla("El juego termino en empate", "", 0, 1);
        }
    }

    //ESTE METODO SE ENCARGA DE SIMULAR LA IA DE LA PC (NIVEL: 9999 (INVENCIBLE))
    public int inteligenciaArtificial(int fichasDisponibles) {
        fichasDisponibles = libroDeReglas.mejorJugadaPC(this.partida.getTablero(), fichasDisponibles); //METODO ENCARGADO DE TODO LO RELACIONADO CON EL JUEGO DE LA PC, DEVUELVE LAS FICHAS QUE QUEDARON LUEGO DEL MOVIMIENTO
        return fichasDisponibles;
    }
}

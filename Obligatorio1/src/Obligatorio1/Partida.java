package Obligatorio1;

public class Partida {

    //VARIABLES PRIVADAS DE LA CLASE TABLERO
    private Jugador jugadorUno;
    private Jugador jugadorDos;
    private Tablero tablero;

    //METODOS DE ACCESO Y MODIFICACION DE LA CLASE TABLERO
    public Jugador getJugadorUno() {
        return jugadorUno;
    }

    public void setJugadorUno(Jugador jugadorUno) {
        this.jugadorUno = jugadorUno;
    }

    public Jugador getJugadorDos() {
        return jugadorDos;
    }

    public void setJugadorDos(Jugador jugadorDos) {
        this.jugadorDos = jugadorDos;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    //CONSTRUCTOR CON PARAMETROS DE LA CLASE TABLERO
    public Partida(Jugador jugadorUno, Jugador jugadorDos, Tablero tablero) {
        this.jugadorUno = jugadorUno;
        this.jugadorDos = jugadorDos;
        this.tablero = tablero;
    }

    //CONSTRUCTOR VACIO DE LA CLASE TABLERO
    public Partida() {
    }

    //METODO TO STRING DE LA CLASE TABLERO
    @Override
    public String toString() {
        return "Partida{" + "jugadorUno=" + jugadorUno + ", jugadorDos=" + jugadorDos + ", tablero=" + tablero + '}';
    }
}

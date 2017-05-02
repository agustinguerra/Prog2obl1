package Obligatorio1;

import static Obligatorio1.Program.pidoDatoIntPositivo;

public class Interfaz {

    public static final String blue = "\u001B[34m";
    public static final String red = "\u001B[31m";
    private Sistema sistema;

    public Sistema getSistema() {
        return sistema;
    }

    public void setSistema(Sistema sistema) {
        this.sistema = sistema;
    }

    public Interfaz(Sistema sistema) {
        this.sistema = sistema;
    }

    public Interfaz() {

    }

    public void registrarJugador() {
        sistema.registroJugador();
    }

    public int cantidadJugadores() {
        return sistema.getListaJugadores().size();
    }

    public void menu() {
        boolean cond = false;
        System.out.println("MENU");
        System.out.println("1) REGISTRO DE JUGADOR");
        System.out.println("2) JUGAR PARTIDA MANUAL");
        System.out.println("3) JUGAR PARTIDA CONTRA LA PC");
        System.out.println("4) RANKING");
        System.out.println("5) SALIR");
        while (!cond) {
            int menu = pidoDatoIntPositivo("Ingrese la opcion del menu", 0, 6, -1);
            switch (menu) {
                case 1:
                    registrarJugador();
                    break;
                case 2:
                    if (cantidadJugadores() > 1) {
                        sistema.jugarEntreJugadores();
                    } else {
                        System.out.println("Faltan jugadores. Solo hay: " + cantidadJugadores());
                    }
                    break;
                case 3:
                    if (cantidadJugadores() >= 1) {
                        sistema.jugarContraPC();
                    } else {
                        System.out.println("Faltan jugadores. Solo hay: " + cantidadJugadores());
                    }
                    break;
                case 4:
                    System.out.println("Ranking de jugadores por partidas ganadas.");
                    System.out.println(sistema.ranking());
                    break;
                case 5:
                    cond = true;
                    break;
            }
        }

    }

}

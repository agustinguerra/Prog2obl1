package Obligatorio1;

import static Obligatorio1.Program.pidoDatoIntPositivo;

public class Interfaz {

    public static final String ANSI_RESET = "\u001B[0m"; // STRING PARA RESETEAR EL COLOR. NO LO PUSE EN FICHA PORQUE: NO ES EL PROPIO COLOR DE LA FICHA, Y SIMPLIFICA TENERLO ACA
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

    public int cantidadJugadores() {
        return sistema.getListaJugadores().size();
    }

    //METODO UTILIZADO POR REGLAS PARA IMPRIMIR LA POSICION DONDE SE COLOCAN CUBOS  
    public void imprimePosicionCubo(int i, int j) {
        System.out.print("Se va a poner un cubo en la posicion ");
        String imprimir = "";
        switch (i) {
            case 0:
                imprimir = "A";
                break;
            case 1:
                imprimir = "B";
                break;
            case 2:
                imprimir = "C";
                break;
            case 3:
                imprimir = "D";
                break;
            case 4:
                imprimir = "E";
                break;
            case 5:
                imprimir = "F";
                break;
        }
        switch (j) {
            case 0:
                imprimir = imprimir + "1";
                break;
            case 1:
                imprimir = imprimir + "2";
                break;
            case 2:
                imprimir = imprimir + "3";
                break;
            case 3:
                imprimir = imprimir + "4";
                break;
            case 4:
                imprimir = imprimir + "5";
                break;
            case 5:
                imprimir = imprimir + "6";
                break;
        }
        System.out.println(imprimir);
    }

    public void menu() {
        boolean cond = false;
        while (!cond) {
            System.out.println("MENU");
            System.out.println("1) REGISTRO DE JUGADOR");
            System.out.println("2) JUGAR PARTIDA MANUAL");
            System.out.println("3) JUGAR PARTIDA CONTRA LA PC");
            System.out.println("4) RANKING");
            System.out.println("5) SALIR");
            int menu = pidoDatoIntPositivo("Ingrese la opcion del menu", 0, 6, -1);
            switch (menu) {
                case 1:
                    sistema.registroJugador();
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
                    sistema.ranking(); // METODO CAMBIADO PARA QUE DIRECTAMENTE SE IMPRIMA DEL OTRO LADO.
                    break;
                case 5:
                    cond = true;
                    break;
            }
        }

    }

}

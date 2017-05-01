package Obligatorio1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {   
        Sistema sis = new Sistema();
        menu(sis);
    }

    //METODO ESTATICO QUE PIDE UN DATO DE TIPO STRING Y LO DEVUELVE.
    public static String pidoDatoString(String msg, String check1) {
        //Este metodo pide un dato de tipo String. 
        System.out.println(msg);
        String x = (new Scanner(System.in)).nextLine();
        while (x.length() == 0 || x.equals(check1)) {
            System.out.println(msg);
            x = (new Scanner(System.in)).nextLine();
        }
        return x;
    }

    //METODO ESTATICO QUE PIDE UN DATO DE TIPO INT Y LO DEVUELVE. MANEJA EXCEPCIONES.
    public static int pidoDatoIntPositivo(String msg, int minValue, int maxValue, int check1) {
        int x = 0;
        boolean cond = true;
        while (cond) {
            try {
                System.out.println(msg);
                x = (new Scanner(System.in)).nextInt();
                if (x > minValue && x < maxValue && x != check1) {
                    cond = false;
                } else {
                    System.out.println("Debe ser mayor que " + minValue + " y menor que " + maxValue + " y no puede ser " + check1);
                }
            } catch (InputMismatchException e) {
                System.out.println("Debe ingresar un numero. ");
            }
        }
        return x;
    }

    public static void juego(Sistema sis) {
        int jugadorUnoFichas;
        jugadorUnoFichas = 25;
        int jugadorDosFichas;
        jugadorUnoFichas = 25;
        boolean cond = false;
        while (!cond) {

        }
    }

    public static void menu(Sistema sis) {
        boolean cond = false;
        System.out.println("MENU");
        System.out.println("1) REGISTRO DE JUGADOR");
        System.out.println("2) JUGAR PARTIDA MANUAL");
        System.out.println("3) JUGAR PARTIDA CONTRA LA PC");
        System.out.println("4) RANKING");
        System.out.println("5) SALIR");
        while (!cond) {
            int menu = pidoDatoIntPositivo("Ingrese la opcion del menu", 0, 4, -1);
            switch (menu) {
                case 1:
                    sis.registroJugador();
                    break;
                case 2:
                    if (sis.getListaJugadores().size() > 1) {
                        juego(sis);
                    }
                    else {
                        System.out.println("Faltan jugadores. Solo hay: " + sis.getListaJugadores().size());
                    }
                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:
                    cond = true;
                    break;
            }
        }

    }
    
    public static void ranking() {
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}

package Obligatorio1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        Interfaz interfaz = new Interfaz(sistema);
        Jugador jugador1 = new Jugador();
        Jugador jugador2 = new Jugador();
        jugador1.setAlias("Agus");
        jugador1.setEdad(23);
        jugador1.setJuegosGanados(0);
        jugador1.setNombre("Agustin");
        jugador2.setAlias("Juanchi");
        jugador2.setEdad(25);
        jugador2.setJuegosGanados(0);
        jugador2.setNombre("Juancho");
        sistema.getListaJugadores().add(jugador1);
        sistema.getListaJugadores().add(jugador2);
        interfaz.menu();
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
    
    public static String pidoDatosParaMovimientoValido (String msg) {
        System.out.println(msg);
        String x = (new Scanner(System.in)).nextLine();
        boolean cond;       
        cond = x.equals("X")||(x.length() == 2 && x.charAt(0) >= 'A' && x.charAt(0) <= 'F' && (Character.getNumericValue(x.charAt(1)) >= 1 && Character.getNumericValue(x.charAt(1)) <= 6));
        while (!cond){           
            System.out.println(msg);
            x = (new Scanner(System.in)).nextLine();
            cond = (x.length() == 2 && x.charAt(0) >= 'A' && x.charAt(0) <= 'F' && (Character.getNumericValue(x.charAt(1)) >= 1 && Character.getNumericValue(x.charAt(1)) <= 6));
            
        }
        return x;        
    }
}

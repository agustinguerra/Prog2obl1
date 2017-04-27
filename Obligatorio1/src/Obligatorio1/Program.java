package Obligatorio1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Sistema sUno = new Sistema();
    }

    //METODO ESTATICO QUE PIDE UN DATO DE TIPO STRING Y LO DEVUELVE.
    public static String pidoDatoString(String msg) {
        //Este metodo pide un dato de tipo String. 
        System.out.println(msg);
        String x = (new Scanner(System.in)).nextLine();
        while (x.length() == 0) {
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

    public static void juego() {
        int jugadorUnoFichas;
        jugadorUnoFichas = 25;
        int jugadorDosFichas;
        jugadorUnoFichas = 25;
        while (true) {
            
            
            
            

        }
    }
    
}

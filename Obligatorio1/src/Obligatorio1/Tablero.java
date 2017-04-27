package Obligatorio1;

import java.util.Arrays;

public class Tablero {

    //VARIABLES PRIVADAS DE LA CLASE TABLERO
    private Ficha[][] matriz;

    //METODOS DE ACCESO Y MODIFICACION DE LA CLASE TABLERO
    public Ficha[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(Ficha[][] matriz) {
        this.matriz = matriz;
    }

    //CONSTRUCTOR CON PARAMETROS DE LA CLASE TABLERO
    public Tablero(Ficha[][] matriz, Ficha ficha) {
        this.matriz = matriz;
    }

    //CONSTRUCTOR VACIO DE LA CLASE TABLERO
    public Tablero() {
    }
}

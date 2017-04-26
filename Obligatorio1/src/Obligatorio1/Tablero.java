package Obligatorio1;

public class Tablero {

    //VARIABLES PRIVADAS DE LA CLASE TABLERO
    private int[][] matriz;
    private Ficha ficha;

    //METODOS DE ACCESO Y MODIFICACION DE LA CLASE TABLERO
    public int[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(int[][] matriz) {
        this.matriz = matriz;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    //CONSTRUCTOR CON PARAMETROS DE LA CLASE TABLERO
    public Tablero(int[][] matriz, Ficha ficha) {
        this.matriz = matriz;
        this.ficha = ficha;
    }
   
    //CONSTRUCTOR VACIO DE LA CLASE TABLERO
    public Tablero() {
    }

    //METODO TO STRING DE LA CLASE TABLERO
    @Override
    public String toString() {
        return "Tablero{" + "matriz=" + matriz + ", ficha=" + ficha + '}';
    }
}

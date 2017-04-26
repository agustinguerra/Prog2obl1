package Obligatorio1;

public class Ficha {
    
    //VARIABLES PRIVADAS DE LA CLASE FICHA
    private int color;
    private int columna;
    private int fila;

    //METODOS DE ACCESO Y MODIFICACION DE LA CLASE FICHA
    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    //CONSTRUCTOR CON PARAMETROS DE LA CLASE FICHA
    public Ficha(int color, int columna, int fila) {
        this.color = color;
        this.columna = columna;
        this.fila = fila;
    }

    //CONSTRUCTOR VACIO DE LA CLASE FICHA
    public Ficha() {
    }

    //METODO TO STRING DE LA CLASE FICHA
    @Override
    public String toString() {
        return "Ficha{" + "color=" + color + ", columna=" + columna + ", fila=" + fila + '}';
    }    
}

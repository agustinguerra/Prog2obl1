package Obligatorio1;

public class Ficha {

    //VARIABLES PRIVADAS DE LA CLASE FICHA
    //COLOR 0 SIN DEFINIR, COLOR 1 ROJO, COLOR 2 AZUl
    private int color;
    private int valor;

    //METODOS DE ACCESO Y MODIFICACION DE LA CLASE FICHA
    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    //CONSTRUCTOR CON PARAMETROS DE LA CLASE FICHA
    public Ficha(int color, int valor) {
        this.color = color;
        this.valor = valor;
    }

    //CONSTRUCTOR VACIO DE LA CLASE FICHA
    public Ficha() {
        
    }
}

package Obligatorio1;

public class Ficha {

    //VARIABLES PRIVADAS DE LA CLASE FICHA
    
    private String color; //CAMBIADO A STRING PORQUE FACILITA MUCHO AL MOMENTO DE IMPRIMIR LA FICHA. AHORRA CODIGO DEL LADO DE LA INTERFAZ.
    private int valor;
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLACK = "\u001B[30m";
    //METODOS DE ACCESO Y MODIFICACION DE LA CLASE FICHA
    public String getColor() {
        return color;
    }
    //COLOR 0 NEGRO, COLOR 1 ROJO, COLOR 2 AZUl
    public void setColor(int valorColor) {
        switch (valorColor) {
            case 0:
                this.color=ANSI_BLACK;
                break;
            case 1:
                this.color=ANSI_RED;
                break;
            case 2:
                this.color=ANSI_BLUE;
                break;
            default:
                break;
        }
    }

    public int getValor() {
        return valor;
    }


    public void setValor(int valor) {
        this.valor = valor;
    }


    //CONSTRUCTOR VACIO DE LA CLASE FICHA
    public Ficha() {
        
    }
//ESTO ES CODIGO REPETIDO. SI TRATO DE LLAMAR AL METODO SET COLOR ACA ME SUGIERE QUE LA CLASE FICHA SEA FINAL Y NO ESTA BIEN ESO TAMPOCO. MAS ADELANTE 
//HAY QUE VER COMO EMPROLIJARLO
//CONSTRUCTOR CON PARAMETROS DE LA CLASE FICHA    
    public Ficha(int color, int valor) {
        switch (color) {
            case 0:
                this.color=ANSI_BLACK;
                break;
            case 1:
                this.color=ANSI_RED;
                break;
            case 2:
                this.color=ANSI_BLUE;
                break;
            default:
                break;
        }
        this.valor = valor;
    }
}

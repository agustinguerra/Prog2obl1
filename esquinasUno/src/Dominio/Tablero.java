package Dominio;

import Interfaz.Interfaz;
import static Interfaz.Interfaz.ANSI_RESET;

public class Tablero {

    //VARIABLE PRIVADA DE LA CLASE TABLERO
    private Ficha[][] matriz;

    //METODOS DE ACCESO Y MODIFICACION DE LA CLASE TABLERO
    public Ficha[][] getMatriz() {
        return matriz;
    }

    //METODO QUE COPIA EL TABLERO
    public Tablero copiarTablero() {
        Tablero tabCopia = new Tablero();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                tabCopia.getFicha(i, j).setValor(this.getFicha(i, j).getValor());
                switch (this.getFicha(i, j).getColor()) {
                    case Ficha.ANSI_BLUE:
                        tabCopia.getFicha(i, j).setColor(2);
                        break;
                    case Ficha.ANSI_RED:
                        tabCopia.getFicha(i, j).setColor(1);
                        break;
                    default:
                        tabCopia.getFicha(i, j).setColor(0);
                        break;
                }
            }
        }
        return tabCopia;
    }

    //METODO DE MODIFICACION DE MATRIZ
    public void setMatriz(Ficha[][] matriz) {
        this.matriz = matriz;
    }

    //METODO QUE PERMITE OBETENER UNA FICHA DEL TABLERO
    public Ficha getFicha(int i, int j) {
        return this.matriz[i][j];
    }

    //CONSTRUCTOR CON PARAMETROS DE LA CLASE TABLERO
    public Tablero(Ficha[][] matriz) {
        this.matriz = matriz;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                matriz[i][j] = new Ficha(Ficha.ANSI_BLACK, 0);
            }
        }
    }

    //LE DAS LA FICHA, Y TE DEVUELVE LA STRING CON EL VALOR YA PINTADO DEL COLOR CORRECTO
    public String stringFicha(Ficha fich) {
        String fichaParaImprimir = " ";
        if (fich.getValor() > 0) {
            fichaParaImprimir = fich.getColor() + fich.getValor() + ANSI_RESET;
        }
        return fichaParaImprimir;
    }
    
    //ESTE METODO DIBUJA TODO EL TABLERO Y LAS FICHAS. SI LA FICHA TIENE VALOR 0, ES DECIR NADIE PUSO FICHA, NO LA DIBUJA. NO ESTA DIBUJANDO LA ULTIMA LINEA, REVISAR LUEGO
    public void dibujarTablero() {
        Interfaz interfaz = new Interfaz();
        int dimensiones = 6;
        interfaz.imprimirEnPantalla("", "", 0, 1);
        interfaz.imprimirEnPantalla("    1    2    3    4    5    6", "", 0, 1);
        //System.out.println("");
        //System.out.println("    1    2    3    4    5    6");
        for (int row = 0; row < dimensiones; row++) {
            if (row != 0) {
                interfaz.imprimirEnPantalla("", "", 0, 1);
                //System.out.println("");
            }
            interfaz.imprimirEnPantalla("   -----------------------------", "", 0, 1);
            //System.out.println("  -----------------------------");
            for (int column = 0; column < dimensiones; column++) {
                if (column == 0) {
                    switch (row) {
                        case 0:
                            interfaz.imprimirEnPantalla("A", "", 0, 6);
                            //System.out.print("A");
                            break;
                        case 1:
                            interfaz.imprimirEnPantalla("B", "", 0, 6);
                            //System.out.print("B");
                            break;
                        case 2:
                            interfaz.imprimirEnPantalla("C", "", 0, 6);
                            //System.out.print("C");
                            break;
                        case 3:
                            interfaz.imprimirEnPantalla("D", "", 0, 6);
                            //System.out.print("D");
                            break;
                        case 4:
                            interfaz.imprimirEnPantalla("E", "", 0, 6);
                            //System.out.print("E");
                            break;
                        case 5:
                            interfaz.imprimirEnPantalla("F", "", 0, 6);
                            //System.out.print("F");
                            break;
                    }
                    interfaz.imprimirEnPantalla(stringFicha((this.getFicha(row, column))),"", 0, 4);
                    //System.out.print("| " + stringFicha((this.getFicha(row, column))) + " ");
                } else {
                    interfaz.imprimirEnPantalla(stringFicha((this.getFicha(row, column))),"", 0, 5);
                    //System.out.print(" | " + stringFicha((this.getFicha(row, column))) + " ");
                }

            }
            interfaz.imprimirEnPantalla("", "", 0, 7);
            //System.out.print(" |");
        }
        interfaz.imprimirEnPantalla("", "", 0, 1);
        //System.out.println("");
        interfaz.imprimirEnPantalla("   -----------------------------", "", 0, 1);
        //System.out.println("  -----------------------------");
    }

    //CONSTRUCTOR VACIO DE LA CLASE TABLERO
    public Tablero() {
        this.matriz = new Ficha[6][6];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                matriz[i][j] = new Ficha(Ficha.ANSI_BLACK, 0);
            }
        }
    }
}

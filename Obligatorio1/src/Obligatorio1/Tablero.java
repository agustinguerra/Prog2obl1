package Obligatorio1;

public class Tablero {

    //VARIABLE PRIVADA DE LA CLASE TABLERO
    private Ficha[][] matriz;

    //METODOS DE ACCESO Y MODIFICACION DE LA CLASE TABLERO
    public Ficha[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(Ficha[][] matriz) {
        this.matriz = matriz;
    }

    //METODO QUE PERMITE OBETENER UNA FICHA DEL TABLERO
    public Ficha getFicha(int i,int j){
        return this.matriz[i][j];
    }
        
    //CONSTRUCTOR CON PARAMETROS DE LA CLASE TABLERO
    public Tablero(Ficha[][] matriz) {
        this.matriz = matriz;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                matriz[i][j] = new Ficha(Ficha.ANSI_BLACK,0);
            }
        }
    }

    //CONSTRUCTOR VACIO DE LA CLASE TABLERO
    public Tablero() {
        this.matriz = new Ficha[6][6];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                matriz[i][j] = new Ficha(Ficha.ANSI_BLACK,0);
            }
        }
    }

}

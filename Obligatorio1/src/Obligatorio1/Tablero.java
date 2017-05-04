package Obligatorio1;

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

    public Ficha getFichaDeTablero(int i,int j){
        return this.matriz[i][j];
    }
    
    //CONSTRUCTOR CON PARAMETROS DE LA CLASE TABLERO
    public Tablero(Ficha[][] matriz) {
        this.matriz = matriz;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                matriz[i][j] = new Ficha(0,0);
            }
        }
    }

    //CONSTRUCTOR VACIO DE LA CLASE TABLERO
    public Tablero() {
        this.matriz = new Ficha[6][6];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                matriz[i][j] = new Ficha(0,0);
            }
        }
    }
}

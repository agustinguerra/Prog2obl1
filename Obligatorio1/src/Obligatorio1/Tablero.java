package Obligatorio1;

import static Obligatorio1.Interfaz.ANSI_RESET;

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

    //LE DAS LA FICHA, Y TE DEVUELVE LA STRING CON EL VALOR YA PINTADO DEL COLOR CORRECTO
    public String stringFicha(Ficha fich){
        String fichaParaImprimir=" ";
        if (fich.getValor()>0){
            fichaParaImprimir=fich.getColor()+fich.getValor()+ANSI_RESET;
        }
        return fichaParaImprimir;
    } 
    
    //ESTE METODO DIBUJA TODO EL TABLERO Y LAS FICHAS. SI LA FICHA TIENE VALOR 0, ES DECIR NADIE PUSO FICHA, NO LA DIBUJA. NO ESTA DIBUJANDO LA ULTIMA LINEA, REVISAR LUEGO
    public void dibujarTablero(){
        int dimensiones = 6;
        System.out.println("");
        System.out.println("    A    B    C    D    E    F");
        for (int row = 0; row < dimensiones; row++)
        {
            if (row!=0){
            System.out.println("");
            }
            System.out.println("  -----------------------------");
            for (int column = 0; column < dimensiones; column++)
            {
                if (column==0){
                    System.out.print(row+1+"| " + stringFicha((this.getFicha(row,column))) + " ");
                }
                else{
                    System.out.print(" | " + stringFicha((this.getFicha(row,column))) + " ");
                }
                
            }       
            System.out.print(" |");
        }
        System.out.println("");
        System.out.println("  -----------------------------");
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

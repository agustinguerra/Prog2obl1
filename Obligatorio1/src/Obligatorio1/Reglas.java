package Obligatorio1;

public class Reglas {
    //metodos de validaciones y movimientos
    public boolean formaCuadrado(int i, int j,Tablero tablero){  //DEVUELVE TRUE SI FORMA CUADRADO
        //I Y J ES LA POSICINO DE LA FICHA QUE SE QUIERE VALIDAR
        //AL PONER UNA FICHA, HAY 4 POSIBLES CUADRADOS QUE SE PUEDEN FORMAR
        //PRIMERO CHEQUEO QUE ESE CUADRADO SEA POSIBLE DADA LA POSICION DEL TABLERO, Y LUEGO CHEQUEO SI SE FORMA O NO
        boolean devuelve=false;
        if (i+1<6 && j+1<6){
            if (tablero.getFichaDeTablero(i+1,j+1).getValor()>0){
                if (tablero.getFichaDeTablero(i,j+1).getValor()>0) {
                    if (tablero.getFichaDeTablero(i+1,j).getValor()>0) {
                        devuelve=true;
                    }
                }
            }
        }
        if (i-1>-1 && j+1<6){
            if (tablero.getFichaDeTablero(i-1,j).getValor()>0){
                if (tablero.getFichaDeTablero(i-1,j+1).getValor()>0) {
                    if (tablero.getFichaDeTablero(i,j+1).getValor()>0) {
                        devuelve=true;
                    }
                }
            }            
        }
        if (i-1>-1 && j-1<-1){
            if (tablero.getFichaDeTablero(i-1,j).getValor()>0){
                if (tablero.getFichaDeTablero(i-1,j-1).getValor()>0) {
                    if (tablero.getFichaDeTablero(i,j-1).getValor()>0) {
                        devuelve=true;
                    }
                }
            }            
        }
        if (i+1<6 && j-1>-1){
            if (tablero.getFichaDeTablero(i,j-1).getValor()>0){
                if (tablero.getFichaDeTablero(i+1,j-1).getValor()>0) {
                    if (tablero.getFichaDeTablero(i+1,j).getValor()>0) {
                        devuelve=true;
                    }
                }
            }            
        }
        return devuelve;
    }
    
    //METODO QUE CALCULA EL PUNTAJE DE UN JUGADOR. AL IGUAL QUE HICIMOS CON LAS FICHAS, 1 CORRESPONDE A ROJO (JUGADOR UNO) Y 2 A AZUL (JUGADOR DOS)
    public int calcularPuntaje(int jugador,Tablero tablero){
        int puntaje=0;
        String color;
        if (jugador==1){
            color=Ficha.ANSI_RED;
        }
        else {
            color=Ficha.ANSI_BLUE;
        }
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if(tablero.getFichaDeTablero(i, j).getValor()>1 && tablero.getFichaDeTablero(i, j).getColor().equals(color)){
                    puntaje=puntaje+tablero.getFichaDeTablero(i, j).getValor();
                }
            }
        }
        return puntaje;
    }
    
    public boolean tieneAdyacente(int i,int j, Tablero tablero){
        boolean devuelve=false;
        return devuelve;
    }
}

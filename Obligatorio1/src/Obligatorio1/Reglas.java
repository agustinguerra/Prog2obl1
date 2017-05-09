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
    

    
    public boolean tieneAdyacente(int i,int j, Tablero tablero){ //DEVUELVE TRUE SI TIENE FICHA ADYACENTE
        boolean devuelve= false;
        if (i>0){
            if (tablero.getFichaDeTablero(i-1,j).getValor()>0){
                devuelve=true;
            }
        }
        if (i>0 && j<5){
            if (tablero.getFichaDeTablero(i-1,j+1).getValor()>0){
                devuelve=true;
            }
        }
        if (j<5){
            if (tablero.getFichaDeTablero(i,j+1).getValor()>0){
                devuelve=true;
            }
        }
        if (i<5 && j<5){
            if (tablero.getFichaDeTablero(i+1,j+1).getValor()>0){
                devuelve=true;
            }
        }
        if (i>5){
            if (tablero.getFichaDeTablero(i+1,j).getValor()>0){
                devuelve=true;
            }
        }
        if (i>5 && j>0){
            if (tablero.getFichaDeTablero(i+1,j-1).getValor()>0){
                devuelve=true;
            }
        }
        if (j>0){
            if (tablero.getFichaDeTablero(i,j-1).getValor()>0){
                devuelve=true;
            }
        }
        if (i>0 && j>0){
            if (tablero.getFichaDeTablero(i-1,j-1).getValor()>0){
                devuelve=true;
            }
        }        
        return devuelve;
    }
    
    public boolean hayFicha(int i, int j, Tablero tablero) { //DEVUELVE TRUE SI HAY UNA FICHA EN EL TABLERO
        boolean devolver = false;
        if (tablero.getFichaDeTablero(i, j).getValor()>0){
            devolver=true;
        }
        return devolver;
    }
    
    public boolean seFormoEsquina(int i, int j, Tablero tablero) { //CHEQUEA SI SE FORMARON O NO ESQUINAS AL MOMENTO DE PONER LA FICHA
        //PODEMOS HACER ESTO, QUE TE PARECE?
        //SI TE GUSTA PODEMOS HACER LO MISMO EN EL METODO DE ARRIBA
        //HICE LOS METODOS DE ARRIBA QUE FALTABAN, ESTE QUE ARRANCASTE VOS ENCARALO VOS, Y DESPUES LOS DOS QUE QUEDAN VEMOS COMO LO DIVIDIMOS
        boolean cond = ((i > 0 && j > 0) && (i < 5 && j < 5));
        if (cond) {
            //INTERNO DEL TABLERO
        } else if (!cond) {
            //EXTERNO DEL TABLERO
            switch (i) {
                case 0:
                    switch (j) {
                        case 0:

                            break;
                        case 5:

                            break;
                    }
                    break;
                case 5:
                    switch (j) {
                        case 0:

                            break;
                        case 5:

                            break;
                    }
            }
        }

        boolean devolver = true;
        return devolver;
    }
    
    public int cantidadFichasAPoner(int i,int j,Tablero tablero) { //DEVUELVE LA CANTIDAD
        int fichasAPoner=0;
        return fichasAPoner;
    }
    
    public void ponerFichasEsquinas(int i,int j,Tablero tablero) { // LUEGO DE CHEQUEAR QUE AL PONER LA FICHA SE FORMA ESQUINA, ESTE METODO SUMA LAS FICHAS
        
    }
}

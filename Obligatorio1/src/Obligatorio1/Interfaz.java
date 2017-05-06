package Obligatorio1;

import static Obligatorio1.Program.pidoDatoIntPositivo;

public class Interfaz {

    public static final String ANSI_RESET = "\u001B[0m"; // STRING PARA RESETEAR EL COLOR. NO LO PUSE EN FICHA PORQUE: NO ES EL PROPIO COLOR DE LA FICHA, Y SIMPLIFICA TENERLO ACA
    private Sistema sistema;

    public Sistema getSistema() {
        return sistema;
    }

    public void setSistema(Sistema sistema) {
        this.sistema = sistema;
    }

    public Interfaz(Sistema sistema) {
        this.sistema = sistema;
    }

    public Interfaz() {

    }

    public void registrarJugador() {
        sistema.registroJugador();
    }

    public int cantidadJugadores() {
        return sistema.getListaJugadores().size();
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
        for (int row = 0; row < dimensiones; row++)
        {
            System.out.println("");
            System.out.println("-------------------------");
            for (int column = 0; column < dimensiones; column++)
            {
                System.out.print("| " + stringFicha((sistema.getPartida().getTablero().getFichaDeTablero(row,column))) + " ");
            }       
            System.out.print("|");
        }
        System.out.println("");
        System.out.println("-------------------------");
    }
    
    public void menu() {
        boolean cond = false;
        System.out.println("MENU");
        System.out.println("1) REGISTRO DE JUGADOR");
        System.out.println("2) JUGAR PARTIDA MANUAL");
        System.out.println("3) JUGAR PARTIDA CONTRA LA PC");
        System.out.println("4) RANKING");
        System.out.println("5) SALIR");
        while (!cond) {
            int menu = pidoDatoIntPositivo("Ingrese la opcion del menu", 0, 6, -1);
            switch (menu) {
                case 1:
                    registrarJugador();
                    break;
                case 2:
                    if (cantidadJugadores() > 1) {
                        sistema.jugarEntreJugadores();
                    } else {
                        System.out.println("Faltan jugadores. Solo hay: " + cantidadJugadores());
                    }
                    break;
                case 3:
                    if (cantidadJugadores() >= 1) {
                        sistema.jugarContraPC();
                    } else {
                        System.out.println("Faltan jugadores. Solo hay: " + cantidadJugadores());
                    }
                    break;
                case 4:
                    System.out.println("Ranking de jugadores por partidas ganadas.");
                    sistema.ranking(); // METODO CAMBIADO PARA QUE DIRECTAMENTE SE IMPRIMA DEL OTRO LADO.
                    break;
                case 5:
                    cond = true;
                    break;
            }
        }

    }

}

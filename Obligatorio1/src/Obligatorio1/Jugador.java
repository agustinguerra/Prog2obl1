package Obligatorio1;

public class Jugador extends Persona {

    //VARIABLES PRIVADAS DE LA CLASE JUGADOR QUE ES EL HIJO DE PERSONA
    private String alias;
    private int juegosGanados;

    //METODOS DE ACCESO Y MODIFICACION DE LA CLASE JUGADOR
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getJuegosGanados() {
        return juegosGanados;
    }

    public void setJuegosGanados(int juegosGanados) {
        this.juegosGanados = juegosGanados;
    }
    
    //CONSTRUCTOR CON PARAMETROS DE LA CLASE JUGADOR USANDO SUPER DE PERSONA    
    public Jugador(String alias, String nombre, int edad, int juegosGanados) {
        super(nombre, edad);
    }

    //CONSTRUCTOR VACIO DE LA CLASE JUGADOR
    public Jugador() {

    }

    //METODO TO STRING DE LA CLASE JUGADOR
    @Override
    public String toString() {
        return "Jugador{" + "alias=" + alias + '}' + super.toString();
    }
}

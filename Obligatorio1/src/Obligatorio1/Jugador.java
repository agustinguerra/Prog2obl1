package Obligatorio1;

public class Jugador extends Persona {
    
    //VARIABLES PRIVADAS DE LA CLASE JUGADOR QUE ES EL HIJO DE PERSONA
    private String alias;

    //METODOS DE ACCESO Y MODIFICACION DE LA CLASE JUGADOR
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    //CONSTRUCTOR CON PARAMETROS DE LA CLASE JUGADOR USANDO SUPER DE PERSONA    
    public Jugador(String alias, String nombre, int edad) {
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

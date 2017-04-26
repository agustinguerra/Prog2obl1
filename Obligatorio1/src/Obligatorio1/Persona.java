package Obligatorio1;

public abstract class Persona {

    //VARIABLES PRIVADAS DE LA CLASE PERSONA QUE ES EL PADRE DE JUGADOR
    private String nombre;
    private int edad;

    //METODOS DE ACCESO Y MODIFICACION DE LA CLASE PERSONA
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    //CONSTRUCTOR CON PARAMETROS DE LA CLASE PERSONA
    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    //CONSTRUCTOR VACIO DE LA CLASE PERSONA.
    public Persona() {

    }

    //METODO TO STRING DE LA CLASE PERSONA.

    @Override
    public String toString() {
        return "nombre=" + nombre + ", edad=" + edad + '}';
    }
    
}

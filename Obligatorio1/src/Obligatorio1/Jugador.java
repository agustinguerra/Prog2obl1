package Obligatorio1;

public class Jugador implements Comparable<Jugador>{

    //VARIABLES PRIVADAS DE LA CLASE JUGADOR QUE ES EL HIJO DE PERSONA
    private String nombre;
    private int edad;
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
        this.nombre = nombre;
        this.edad = edad;
        this.alias = alias;
        this.juegosGanados = 0;
    }

    //CONSTRUCTOR VACIO DE LA CLASE JUGADOR
    public Jugador() {
        this.juegosGanados=0;
    }

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

    //METODO TO STRING DE LA CLASE JUGADOR
    @Override
    public String toString() {
        return "Alias = " + alias + ", " + "Nombre = " + nombre + ", Edad = " + edad;
    }
       
    //METODO DE LA CLASE INSPECCION QUE ES USADO EN COLLECTIONS.SORT PARA ORDENAR LA LISTA POR DIA Y MES.
    @Override
    public int compareTo(Jugador i) {
        return (i.getJuegosGanados() - this.getJuegosGanados());
    }
    
    @Override
    public boolean equals(Object o) {
        Jugador jugador = (Jugador)o;
        return (this.getAlias().equals(jugador.getAlias()));
    }
    
}

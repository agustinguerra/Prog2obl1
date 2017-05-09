package Obligatorio1;

public class Jugador extends Persona implements Comparable<Jugador>{

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
        this.juegosGanados=0;
    }

    //METODO TO STRING DE LA CLASE JUGADOR
    @Override
    public String toString() {
        return "Alias = " + alias + ' ' + super.toString();
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

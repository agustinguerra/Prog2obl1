package Obligatorio1;

public class Jugador extends Persona {
    
    private String alias;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
    
    public Jugador(String alias, String nombre, int edad) {
        super(nombre, edad);
    }
    
    public Jugador() {
        
    }

    @Override
    public String toString() {
        return "Jugador{" + "alias=" + alias + '}' + super.toString();
    }
}


package prog2;


public class Ficha {
    private int color;


    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";

    
    public Ficha() {
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String pintar(int color) {
        String ficha = "";
        switch (color) {
            case 2:
                ficha = ANSI_BLUE + "Ê˜";
                break;
            case 1:
                ficha = ANSI_RED + "Ê˜";
                break;
            case 0:
                ficha = " ";
                break;
            default:
                break;
        }
        return ficha;
    }
    
//    @Override
//    public int compareTo(Ficha f) {
//        return (this.getColor() - f.getColor());
//    }
    
    
}

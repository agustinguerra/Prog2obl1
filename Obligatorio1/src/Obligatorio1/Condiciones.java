package Obligatorio1;

public class Condiciones {

    private boolean condArriba;
    private boolean condAbajo;
    private boolean condIzquierda;
    private boolean condDerecha;
    private boolean diagonalArribaIzquierda;
    private boolean diagonalArribaDerecha;
    private boolean diagonalAbajoIzquierda;
    private boolean diagonalAbajoDerecha;

    private boolean cond;
    private boolean condExternaUno;
    private boolean condExternaDos;
    private boolean condExternaTres;
    private boolean condExternaCuatro;

    public boolean isCondArriba() {
        return condArriba;
    }

    public void setCondArriba(boolean condArriba) {
        this.condArriba = condArriba;
    }

    public boolean isCondAbajo() {
        return condAbajo;
    }

    public void setCondAbajo(boolean condAbajo) {
        this.condAbajo = condAbajo;
    }

    public boolean isCondIzquierda() {
        return condIzquierda;
    }

    public void setCondIzquierda(boolean condIzquierda) {
        this.condIzquierda = condIzquierda;
    }

    public boolean isCondDerecha() {
        return condDerecha;
    }

    public void setCondDerecha(boolean condDerecha) {
        this.condDerecha = condDerecha;
    }

    public boolean isDiagonalArribaIzquierda() {
        return diagonalArribaIzquierda;
    }

    public void setDiagonalArribaIzquierda(boolean diagonalArribaIzquierda) {
        this.diagonalArribaIzquierda = diagonalArribaIzquierda;
    }

    public boolean isDiagonalArribaDerecha() {
        return diagonalArribaDerecha;
    }

    public void setDiagonalArribaDerecha(boolean diagonalArribaDerecha) {
        this.diagonalArribaDerecha = diagonalArribaDerecha;
    }

    public boolean isDiagonalAbajoIzquierda() {
        return diagonalAbajoIzquierda;
    }

    public void setDiagonalAbajoIzquierda(boolean diagonalAbajoIzquierda) {
        this.diagonalAbajoIzquierda = diagonalAbajoIzquierda;
    }

    public boolean isDiagonalAbajoDerecha() {
        return diagonalAbajoDerecha;
    }

    public void setDiagonalAbajoDerecha(boolean diagonalAbajoDerecha) {
        this.diagonalAbajoDerecha = diagonalAbajoDerecha;
    }

    public boolean isCond() {
        return cond;
    }

    public void setCond(boolean cond) {
        this.cond = cond;
    }

    public boolean isCondExternaUno() {
        return condExternaUno;
    }

    public void setCondExternaUno(boolean condExternaUno) {
        this.condExternaUno = condExternaUno;
    }

    public boolean isCondExternaDos() {
        return condExternaDos;
    }

    public void setCondExternaDos(boolean condExternaDos) {
        this.condExternaDos = condExternaDos;
    }

    public boolean isCondExternaTres() {
        return condExternaTres;
    }

    public void setCondExternaTres(boolean condExternaTres) {
        this.condExternaTres = condExternaTres;
    }

    public boolean isCondExternaCuatro() {
        return condExternaCuatro;
    }

    public void setCondExternaCuatro(boolean condExternaCuatro) {
        this.condExternaCuatro = condExternaCuatro;
    }

    public Condiciones() {
        this.condArriba = false;
        this.condAbajo = false;
        this.condIzquierda = false;
        this.condDerecha = false;
        this.diagonalArribaIzquierda = false;
        this.diagonalArribaDerecha = false;
        this.diagonalAbajoIzquierda = false;
        this.diagonalAbajoDerecha = false;

        //ESTA CONDICION EVALUA SI ES INTERNO O EXTERNO
        this.cond = false;

        //ESTAS CONDICIONES EVALUAN FICHAS EXTERNAS
        this.condExternaUno = false;
        this.condExternaDos = false;
        this.condExternaTres = false;
        this.condExternaCuatro = false;
    }

    public boolean hayFicha(int i, int j, Tablero tablero) { //DEVUELVE TRUE SI HAY UNA FICHA EN EL TABLERO EN DETERMINADA POSICION
        boolean devolver = false;
        if (tablero.getFicha(i, j).getValor() > 0) {
            devolver = true;
        }
        return devolver;
    }

    public void chequeadorCondiciones(int i, int j, Tablero tablero) {
        //ESTA CONDICION EVALUA SI ES INTERNO O EXTERNO
        this.cond = ((i > 0 && j > 0) && (i < 5 && j < 5));

        //ESTAS CONDICIONES EVALUAN FICHAS EXTERNAS
        this.condExternaUno = ((i == 0) & ((j > 0) && (j < 5)));
        this.condExternaDos = (i == 5 & ((j > 0) && (j < 5)));
        this.condExternaTres = (j == 0 & ((i > 0) && (i < 5)));
        this.condExternaCuatro = (j == 5 & ((i > 0) && (i < 5)));

        if (cond) {
            //INTERNO DEL TABLERO

            //ESTAS CONDICIONES EVALUAN ARRIBA ABAJO IZQUIEDA Y DERECHA
            this.condArriba = hayFicha(i - 1, j, tablero);
            this.condAbajo = hayFicha(i + 1, j, tablero);
            this.condIzquierda = hayFicha(i, j - 1, tablero);
            this.condDerecha = hayFicha(i, j + 1, tablero);

            //EVALUAN DIAGONALES
            this.diagonalArribaIzquierda = hayFicha(i - 1, j - 1, tablero);
            this.diagonalArribaDerecha = hayFicha(i - 1, j + 1, tablero);
            this.diagonalAbajoIzquierda = hayFicha(i + 1, j - 1, tablero);
            this.diagonalAbajoDerecha = hayFicha(i + 1, j + 1, tablero);

        } else if (!cond) {
            //EXTERNO DEL TABLERO
            switch (i) {
                case 0:
                    switch (j) {
                        case 0:
                            this.condDerecha = hayFicha(i, j + 1, tablero);
                            this.condAbajo = hayFicha(i + 1, j, tablero);
                            this.diagonalAbajoDerecha = hayFicha(i + 1, j + 1, tablero);
                            break;
                        case 5:
                            this.condAbajo = hayFicha(i + 1, j, tablero);
                            this.condIzquierda = hayFicha(i, j - 1, tablero);
                            this.diagonalAbajoIzquierda = hayFicha(i + 1, j - 1, tablero);
                            break;
                    }
                    break;
                case 5:
                    switch (j) {
                        case 0:
                            this.condDerecha = hayFicha(i, j + 1, tablero);
                            this.diagonalArribaDerecha = hayFicha(i - 1, j + 1, tablero);
                            this.condArriba = hayFicha(i - 1, j, tablero);
                            break;
                        case 5:
                            this.diagonalArribaIzquierda = hayFicha(i - 1, j - 1, tablero);
                            this.condIzquierda = hayFicha(i, j - 1, tablero);
                            this.condArriba = hayFicha(i - 1, j, tablero);
                            break;
                    }
                    break;
            }

            if (condExternaUno) {
                this.diagonalAbajoIzquierda = hayFicha(i + 1, j - 1, tablero);
                this.condIzquierda = hayFicha(i, j - 1, tablero);
                this.condDerecha = hayFicha(i, j + 1, tablero);
                this.condAbajo = hayFicha(i + 1, j, tablero);
                this.diagonalAbajoDerecha = hayFicha(i + 1, j + 1, tablero);
            }
            if (condExternaDos) {
                this.diagonalArribaIzquierda = hayFicha(i - 1, j - 1, tablero);
                this.condArriba = hayFicha(i - 1, j, tablero);
                this.condIzquierda = hayFicha(i, j - 1, tablero);
                this.diagonalArribaDerecha = hayFicha(i - 1, j + 1, tablero);
                this.condDerecha = hayFicha(i, j + 1, tablero);
            }
            if (condExternaTres) {
                this.diagonalArribaDerecha = hayFicha(i - 1, j + 1, tablero);
                this.condDerecha = hayFicha(i, j + 1, tablero);
                this.condArriba = hayFicha(i - 1, j, tablero);
                this.diagonalAbajoDerecha = hayFicha(i + 1, j + 1, tablero);
                this.condAbajo = hayFicha(i + 1, j, tablero);
            }
            if (condExternaCuatro) {
                this.diagonalArribaIzquierda = hayFicha(i - 1, j - 1, tablero);
                this.condArriba = hayFicha(i - 1, j, tablero);
                this.condAbajo = hayFicha(i + 1, j, tablero);
                this.condIzquierda = hayFicha(i, j - 1, tablero);
                this.diagonalAbajoIzquierda = hayFicha(i + 1, j - 1, tablero);
            }
        }
    }
}

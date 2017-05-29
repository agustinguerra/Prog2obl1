package Dominio;

import Interfaz.Interfaz;

public class Reglas {

    //VARIABLE PRIVADA DE LA CLASE REGLAS
    private final Condiciones condiciones;

    //CONSTRUCTOR VACIO DE LA CLASE REGLAS
    public Reglas() {
        this.condiciones = new Condiciones();
    }

    //METODO DE VALIDACION DE SI SE FORMO UN CUADRADO, [I,J] ES LA POSICION DE LA FICHA QUE SE QUIERE VALIDAR. AL PONER UNA FICHA, HAY POSIBLES CUADRADOS QUE SE PUEDEN FORMAR. 
    //PRIMERO CHEQUEO QUE ESE CUADRADO SEA POSIBLE DADA LA POSISION DEL TABLERO, Y LUEGO CHEQUEO SI SE FORMA O NO. DEVUELVE TRUE SI FORMA CUADRADO
    public boolean formaCuadrado(int i, int j, Tablero tablero) {

        boolean devuelve = false;

        //CHEQUEO QUE EXISTA FICHA EN EL LUGAR, SI ESXISTE YA DEVUELVO QUE EL MOVIMIENTO NO ES VALIDO
        boolean hayFicha = this.condiciones.hayFicha(i, j, tablero);

        if (!hayFicha) {
            this.condiciones.chequeadorCondiciones(i, j, tablero);
            if (this.condiciones.isCondInternaDelTablero()) {
                //INTERNO DEL TABLERO
                if (this.condiciones.isCondIzquierda() && this.condiciones.isCondAbajo() && this.condiciones.isDiagonalAbajoIzquierda()) {
                    devuelve = true;
                }
                if (this.condiciones.isCondDerecha() && this.condiciones.isCondAbajo() && this.condiciones.isDiagonalAbajoDerecha()) {
                    devuelve = true;
                }
                if (this.condiciones.isCondIzquierda() && this.condiciones.isCondArriba() && this.condiciones.isDiagonalArribaIzquierda()) {
                    devuelve = true;
                }
                if (this.condiciones.isCondDerecha() && this.condiciones.isCondArriba() && this.condiciones.isDiagonalArribaDerecha()) {
                    devuelve = true;
                }
            } else if (!this.condiciones.isCondInternaDelTablero()) {
                //EXTERNO DEL TABLERO
                switch (i) {
                    case 0:
                        switch (j) {
                            case 0:
                                if (this.condiciones.isCondDerecha() && this.condiciones.isCondAbajo() && this.condiciones.isDiagonalAbajoDerecha()) {
                                    devuelve = true;
                                }
                                break;
                            case 5:
                                if (this.condiciones.isCondIzquierda() && this.condiciones.isCondAbajo() && this.condiciones.isDiagonalAbajoIzquierda()) {
                                    devuelve = true;
                                }
                                break;
                        }
                        break;
                    case 5:
                        switch (j) {
                            case 0:
                                if (this.condiciones.isCondDerecha() && this.condiciones.isCondArriba() && this.condiciones.isDiagonalArribaDerecha()) {
                                    devuelve = true;
                                }
                                break;
                            case 5:
                                if (this.condiciones.isCondIzquierda() && this.condiciones.isCondArriba() && this.condiciones.isDiagonalArribaIzquierda()) {
                                    devuelve = true;
                                }
                                break;
                        }
                        break;
                }

                if (this.condiciones.isCondExternaUno()) {
                    if (this.condiciones.isCondIzquierda() && this.condiciones.isCondAbajo() && this.condiciones.isDiagonalAbajoIzquierda()) {
                        devuelve = true;
                    }
                    if (this.condiciones.isCondDerecha() && this.condiciones.isCondAbajo() && this.condiciones.isDiagonalAbajoDerecha()) {
                        devuelve = true;
                    }
                }
                if (this.condiciones.isCondExternaDos()) {
                    if (this.condiciones.isCondIzquierda() && this.condiciones.isCondArriba() && this.condiciones.isDiagonalArribaIzquierda()) {
                        devuelve = true;
                    }
                    if (this.condiciones.isCondDerecha() && this.condiciones.isCondArriba() && this.condiciones.isDiagonalArribaDerecha()) {
                        devuelve = true;
                    }
                }
                if (this.condiciones.isCondExternaTres()) {
                    if (this.condiciones.isCondArriba() && this.condiciones.isCondDerecha() && this.condiciones.isDiagonalArribaDerecha()) {
                        devuelve = true;
                    }
                    if (this.condiciones.isCondAbajo() && this.condiciones.isCondDerecha() && this.condiciones.isDiagonalAbajoDerecha()) {
                        devuelve = true;
                    }
                }
                if (condiciones.isCondExternaCuatro()) {
                    if (this.condiciones.isCondArriba() && this.condiciones.isCondIzquierda() && this.condiciones.isDiagonalArribaIzquierda()) {
                        devuelve = true;
                    }
                    if (this.condiciones.isCondAbajo() && this.condiciones.isCondIzquierda() && this.condiciones.isDiagonalAbajoIzquierda()) {
                        devuelve = true;
                    }
                }
            }
        } else {
            devuelve = true;
        }
        return devuelve;
    }

    //METODO QUE CALCULA EL PUNTAJE DE UN JUGADOR. AL IGUAL QUE HICIMOS CON LAS FICHAS, 1 CORRESPONDE A ROJO (JUGADOR UNO) Y 2 A AZUL (JUGADOR DOS)
    public int calcularPuntaje(int jugador, Tablero tablero) {
        int puntaje = 0;
        String color;
        if (jugador == 1) {
            color = Ficha.ANSI_RED;
        } else {
            color = Ficha.ANSI_BLUE;
        }
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (tablero.getFicha(i, j).getValor() > 1 && tablero.getFicha(i, j).getColor().equals(color)) {
                    puntaje = puntaje + tablero.getFicha(i, j).getValor();
                }
            }
        }
        return puntaje;
    }

    //METODO QUE DEVUELVE TRUE SI HAY UNA FICHA ADYACENTE EN DONDE SE QUIERE COLOCAR FICHA
    public boolean tieneAdyacente(int i, int j, Tablero tablero) {
        boolean devuelve = false;
        this.condiciones.chequeadorCondiciones(i, j, tablero);

        if (this.condiciones.isCondInternaDelTablero()) {
            //INTERNO DEL TABLERO
            if (this.condiciones.isCondIzquierda() || this.condiciones.isCondAbajo() || this.condiciones.isCondDerecha() || this.condiciones.isCondArriba() || this.condiciones.isDiagonalAbajoIzquierda() || this.condiciones.isDiagonalAbajoDerecha() || this.condiciones.isDiagonalArribaIzquierda() || this.condiciones.isDiagonalArribaDerecha()) {
                devuelve = true;
            }
        } else if (!this.condiciones.isCondInternaDelTablero()) {
            //EXTERNO DEL TABLERO
            switch (i) {
                case 0:
                    switch (j) {
                        case 0:
                            if (this.condiciones.isCondDerecha() || this.condiciones.isCondAbajo() || this.condiciones.isDiagonalAbajoDerecha()) {
                                devuelve = true;
                            }
                            break;
                        case 5:
                            if (this.condiciones.isCondIzquierda() || this.condiciones.isCondAbajo() || this.condiciones.isDiagonalAbajoIzquierda()) {
                                devuelve = true;
                            }
                            break;
                    }
                    break;
                case 5:
                    switch (j) {
                        case 0:
                            if (this.condiciones.isCondArriba() || this.condiciones.isCondDerecha() || this.condiciones.isDiagonalArribaDerecha()) {
                                devuelve = true;
                            }
                            break;
                        case 5:
                            if (this.condiciones.isCondIzquierda() || this.condiciones.isCondArriba() || this.condiciones.isDiagonalArribaIzquierda()) {
                                devuelve = true;
                            }
                            break;
                    }
                    break;
            }

            if (this.condiciones.isCondExternaUno()) {
                if (this.condiciones.isCondIzquierda() || this.condiciones.isCondAbajo() || this.condiciones.isCondDerecha() || this.condiciones.isDiagonalAbajoIzquierda() || this.condiciones.isDiagonalAbajoDerecha()) {
                    devuelve = true;
                }
            }
            if (this.condiciones.isCondExternaDos()) {
                if (this.condiciones.isCondIzquierda() || this.condiciones.isCondArriba() || this.condiciones.isCondDerecha() || this.condiciones.isDiagonalArribaIzquierda() || this.condiciones.isDiagonalArribaDerecha()) {
                    devuelve = true;
                }
            }
            if (this.condiciones.isCondExternaTres()) {
                if (this.condiciones.isCondAbajo() || this.condiciones.isCondArriba() || this.condiciones.isCondDerecha() || this.condiciones.isDiagonalArribaDerecha() || this.condiciones.isDiagonalAbajoDerecha()) {
                    devuelve = true;
                }
            }
            if (this.condiciones.isCondExternaCuatro()) {
                if (this.condiciones.isCondAbajo() || this.condiciones.isCondArriba() || this.condiciones.isCondIzquierda() || this.condiciones.isDiagonalArribaIzquierda() || this.condiciones.isDiagonalAbajoIzquierda()) {
                    devuelve = true;
                }
            }
        }
        return devuelve;
    }

    //METODO QUE DEVUELVE LA CANTIDAD DE FICHAS DISPONIBLES DEL JUGADOR DESPUES DE VERIFICAR LAS ESQUINAS FORMADAS Y DESPUES DE REALIZADA LA JUGADA
    public int seFormoEsquina(int i, int j, Tablero tablero, int color, int fichasDisponibles) {
        this.condiciones.chequeadorCondiciones(i, j, tablero);
        Interfaz interfaz = new Interfaz();
        tablero.getFicha(i, j).setValor(1);
        fichasDisponibles = fichasDisponibles - 1;
        interfaz.imprimePosicionCubo(i, j);
        tablero.getFicha(i, j).setColor(color);
        if (this.condiciones.isCondInternaDelTablero()) {
            //INTERNO DEL TABLERO
            if (this.condiciones.isCondArriba() && this.condiciones.isCondIzquierda() && fichasDisponibles >= 1 && tablero.getFicha(i, j).getValor() < 5) {
                tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                interfaz.imprimePosicionCubo(i, j);
                fichasDisponibles = fichasDisponibles - 1;
            }
            if (this.condiciones.isCondArriba() && this.condiciones.isCondDerecha() && fichasDisponibles >= 1 && tablero.getFicha(i, j).getValor() < 5) {
                tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                interfaz.imprimePosicionCubo(i, j);
                fichasDisponibles = fichasDisponibles - 1;
            }
            if (this.condiciones.isCondAbajo() && this.condiciones.isCondIzquierda() && fichasDisponibles >= 1 && tablero.getFicha(i, j).getValor() < 5) {
                tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                interfaz.imprimePosicionCubo(i, j);
                fichasDisponibles = fichasDisponibles - 1;
            }
            if (this.condiciones.isCondAbajo() && this.condiciones.isCondDerecha() && fichasDisponibles >= 1 && tablero.getFicha(i, j).getValor() < 5) {
                tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                interfaz.imprimePosicionCubo(i, j);
                fichasDisponibles = fichasDisponibles - 1;
            }
            if (this.condiciones.isCondAbajo() && this.condiciones.isDiagonalAbajoDerecha() && fichasDisponibles >= 1 && tablero.getFicha(i + 1, j).getValor() < 5) {
                tablero.getFicha(i + 1, j).setValor(tablero.getFicha(i + 1, j).getValor() + 1);
                tablero.getFicha(i + 1, j).setColor(color);
                interfaz.imprimePosicionCubo(i + 1, j);
                fichasDisponibles = fichasDisponibles - 1;
            }
            if (this.condiciones.isCondDerecha() && this.condiciones.isDiagonalAbajoDerecha() && fichasDisponibles >= 1 && tablero.getFicha(i, j + 1).getValor() < 5) {
                tablero.getFicha(i, j + 1).setValor(tablero.getFicha(i, j + 1).getValor() + 1);
                tablero.getFicha(i, j + 1).setColor(color);
                interfaz.imprimePosicionCubo(i, j + 1);
                fichasDisponibles = fichasDisponibles - 1;
            }
            if (this.condiciones.isCondIzquierda() && this.condiciones.isDiagonalAbajoIzquierda() && fichasDisponibles >= 1 && tablero.getFicha(i, j - 1).getValor() < 5) {
                tablero.getFicha(i, j - 1).setValor(tablero.getFicha(i, j - 1).getValor() + 1);
                tablero.getFicha(i, j - 1).setColor(color);
                interfaz.imprimePosicionCubo(i, j - 1);
                fichasDisponibles = fichasDisponibles - 1;
            }
            if (this.condiciones.isCondAbajo() && this.condiciones.isDiagonalAbajoIzquierda() && fichasDisponibles >= 1 && tablero.getFicha(i + 1, j).getValor() < 5) {
                tablero.getFicha(i + 1, j).setValor(tablero.getFicha(i + 1, j).getValor() + 1);
                tablero.getFicha(i + 1, j).setColor(color);
                interfaz.imprimePosicionCubo(i + 1, j);
                fichasDisponibles = fichasDisponibles - 1;
            }
            if (this.condiciones.isDiagonalArribaDerecha() && this.condiciones.isCondArriba() && fichasDisponibles >= 1 && tablero.getFicha(i - 1, j).getValor() < 5) {
                tablero.getFicha(i - 1, j).setValor(tablero.getFicha(i - 1, j).getValor() + 1);
                tablero.getFicha(i - 1, j).setColor(color);
                interfaz.imprimePosicionCubo(i - 1, j);
                fichasDisponibles = fichasDisponibles - 1;
            }
            if (this.condiciones.isCondDerecha() && this.condiciones.isDiagonalArribaDerecha() && fichasDisponibles >= 1 && tablero.getFicha(i, j + 1).getValor() < 5) {
                tablero.getFicha(i, j + 1).setValor(tablero.getFicha(i, j + 1).getValor() + 1);
                tablero.getFicha(i, j + 1).setColor(color);
                interfaz.imprimePosicionCubo(i, j + 1);
                fichasDisponibles = fichasDisponibles - 1;
            }
            if (this.condiciones.isCondIzquierda() && this.condiciones.isDiagonalArribaIzquierda() && fichasDisponibles >= 1 && tablero.getFicha(i, j - 1).getValor() < 5) {
                tablero.getFicha(i, j - 1).setValor(tablero.getFicha(i, j - 1).getValor() + 1);
                tablero.getFicha(i, j - 1).setColor(color);
                interfaz.imprimePosicionCubo(i, j - 1);
                fichasDisponibles = fichasDisponibles - 1;
            }
            if (this.condiciones.isDiagonalArribaIzquierda() && this.condiciones.isCondArriba() && fichasDisponibles >= 1 && tablero.getFicha(i - 1, j).getValor() < 5) {
                tablero.getFicha(i - 1, j).setValor(tablero.getFicha(i - 1, j).getValor() + 1);
                tablero.getFicha(i - 1, j).setColor(color);
                interfaz.imprimePosicionCubo(i - 1, j);
                fichasDisponibles = fichasDisponibles - 1;
            }

        } else if (!this.condiciones.isCondInternaDelTablero()) {
            //EXTERNO DEL TABLERO
            switch (i) {
                case 0:
                    switch (j) {
                        case 0:
                            if (this.condiciones.isCondDerecha() && this.condiciones.isCondAbajo() && fichasDisponibles >= 1 && tablero.getFicha(i, j).getValor() < 5) {
                                tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                                interfaz.imprimePosicionCubo(i, j);
                                fichasDisponibles = fichasDisponibles - 1;
                            }
                            if (this.condiciones.isCondDerecha() && this.condiciones.isDiagonalAbajoDerecha() && fichasDisponibles >= 1 && tablero.getFicha(i, j + 1).getValor() < 5) {
                                tablero.getFicha(i, j + 1).setValor(tablero.getFicha(i, j + 1).getValor() + 1);
                                tablero.getFicha(i, j + 1).setColor(color);
                                interfaz.imprimePosicionCubo(i, j + 1);
                                fichasDisponibles = fichasDisponibles - 1;
                            }
                            if (this.condiciones.isCondAbajo() && this.condiciones.isDiagonalAbajoDerecha() && fichasDisponibles >= 1 && tablero.getFicha(i + 1, j).getValor() < 5) {
                                tablero.getFicha(i + 1, j).setValor(tablero.getFicha(i + 1, j).getValor() + 1);
                                tablero.getFicha(i + 1, j).setColor(color);
                                interfaz.imprimePosicionCubo(i + 1, j);
                                fichasDisponibles = fichasDisponibles - 1;
                            }
                            break;
                        case 5:
                            if (this.condiciones.isCondIzquierda() && this.condiciones.isCondAbajo() && fichasDisponibles >= 1 && tablero.getFicha(i, j).getValor() < 5) {
                                tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                                interfaz.imprimePosicionCubo(i, j);
                                fichasDisponibles = fichasDisponibles - 1;
                            }
                            if (this.condiciones.isCondIzquierda() && this.condiciones.isDiagonalAbajoIzquierda() && fichasDisponibles >= 1 && tablero.getFicha(i, j - 1).getValor() < 5) {
                                tablero.getFicha(i, j - 1).setValor(tablero.getFicha(i, j - 1).getValor() + 1);
                                tablero.getFicha(i, j - 1).setColor(color);
                                interfaz.imprimePosicionCubo(i, j - 1);
                                fichasDisponibles = fichasDisponibles - 1;
                            }
                            if (this.condiciones.isCondAbajo() && this.condiciones.isDiagonalAbajoIzquierda() && fichasDisponibles >= 1 && tablero.getFicha(i + 1, j).getValor() < 5) {
                                tablero.getFicha(i + 1, j).setValor(tablero.getFicha(i + 1, j).getValor() + 1);
                                tablero.getFicha(i + 1, j).setColor(color);
                                interfaz.imprimePosicionCubo(i + 1, j);
                                fichasDisponibles = fichasDisponibles - 1;
                            }
                            break;
                    }
                    break;
                case 5:
                    switch (j) {
                        case 0:
                            if (this.condiciones.isCondDerecha() && this.condiciones.isCondArriba() && fichasDisponibles >= 1 && tablero.getFicha(i, j).getValor() < 5) {
                                tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                                interfaz.imprimePosicionCubo(i, j);
                                fichasDisponibles = fichasDisponibles - 1;
                            }
                            if (this.condiciones.isDiagonalArribaDerecha() && this.condiciones.isCondArriba() && fichasDisponibles >= 1 && tablero.getFicha(i - 1, j).getValor() < 5) {
                                tablero.getFicha(i - 1, j).setValor(tablero.getFicha(i - 1, j).getValor() + 1);
                                tablero.getFicha(i - 1, j).setColor(color);
                                interfaz.imprimePosicionCubo(i - 1, j);
                                fichasDisponibles = fichasDisponibles - 1;
                            }
                            if (this.condiciones.isCondDerecha() && this.condiciones.isDiagonalArribaDerecha() && fichasDisponibles >= 1 && tablero.getFicha(i, j + 1).getValor() < 5) {
                                tablero.getFicha(i, j + 1).setValor(tablero.getFicha(i, j + 1).getValor() + 1);
                                tablero.getFicha(i, j + 1).setColor(color);
                                interfaz.imprimePosicionCubo(i, j + 1);
                                fichasDisponibles = fichasDisponibles - 1;
                            }
                            break;
                        case 5:
                            if (this.condiciones.isCondIzquierda() && this.condiciones.isCondArriba() && fichasDisponibles >= 1 && tablero.getFicha(i, j).getValor() < 5) {
                                tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                                interfaz.imprimePosicionCubo(i, j);
                                fichasDisponibles = fichasDisponibles - 1;
                            }
                            if (this.condiciones.isCondIzquierda() && this.condiciones.isDiagonalArribaIzquierda() && fichasDisponibles >= 1 && tablero.getFicha(i, j - 1).getValor() < 5) {
                                tablero.getFicha(i, j - 1).setValor(tablero.getFicha(i, j - 1).getValor() + 1);
                                tablero.getFicha(i, j - 1).setColor(color);
                                interfaz.imprimePosicionCubo(i, j - 1);
                                fichasDisponibles = fichasDisponibles - 1;
                            }
                            if (this.condiciones.isDiagonalArribaIzquierda() && this.condiciones.isCondArriba() && fichasDisponibles >= 1 && tablero.getFicha(i - 1, j).getValor() < 5) {
                                tablero.getFicha(i - 1, j).setValor(tablero.getFicha(i - 1, j).getValor() + 1);
                                tablero.getFicha(i - 1, j).setColor(color);
                                interfaz.imprimePosicionCubo(i - 1, j);
                                fichasDisponibles = fichasDisponibles - 1;
                            }
                            break;
                    }
                    break;
            }

            if (this.condiciones.isCondExternaUno()) {
                if (this.condiciones.isCondIzquierda() && this.condiciones.isCondAbajo() && fichasDisponibles >= 1 && tablero.getFicha(i, j).getValor() < 5) {
                    tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                    interfaz.imprimePosicionCubo(i, j);
                    fichasDisponibles = fichasDisponibles - 1;
                }
                if (this.condiciones.isCondDerecha() && this.condiciones.isCondAbajo() && fichasDisponibles >= 1 && tablero.getFicha(i, j).getValor() < 5) {
                    tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                    interfaz.imprimePosicionCubo(i, j);
                    fichasDisponibles = fichasDisponibles - 1;
                }
                if (this.condiciones.isDiagonalAbajoIzquierda() && this.condiciones.isCondIzquierda() && fichasDisponibles >= 1 && tablero.getFicha(i, j - 1).getValor() < 5) {
                    tablero.getFicha(i, j - 1).setValor(tablero.getFicha(i, j - 1).getValor() + 1);
                    tablero.getFicha(i, j - 1).setColor(color);
                    interfaz.imprimePosicionCubo(i, j - 1);
                    fichasDisponibles = fichasDisponibles - 1;
                }
                if (this.condiciones.isDiagonalAbajoIzquierda() && this.condiciones.isCondAbajo() && fichasDisponibles >= 1 && tablero.getFicha(i + 1, j).getValor() < 5) {
                    tablero.getFicha(i + 1, j).setValor(tablero.getFicha(i + 1, j).getValor() + 1);
                    tablero.getFicha(i + 1, j).setColor(color);
                    interfaz.imprimePosicionCubo(i + 1, j);
                    fichasDisponibles = fichasDisponibles - 1;
                }
                if (this.condiciones.isDiagonalAbajoDerecha() && this.condiciones.isCondAbajo() && fichasDisponibles >= 1 && tablero.getFicha(i + 1, j).getValor() < 5) {
                    tablero.getFicha(i + 1, j).setValor(tablero.getFicha(i + 1, j).getValor() + 1);
                    tablero.getFicha(i + 1, j).setColor(color);
                    interfaz.imprimePosicionCubo(i + 1, j);
                    fichasDisponibles = fichasDisponibles - 1;
                }
                if (this.condiciones.isDiagonalAbajoDerecha() && this.condiciones.isCondDerecha() && fichasDisponibles >= 1 && tablero.getFicha(i, j + 1).getValor() < 5) {
                    tablero.getFicha(i, j + 1).setValor(tablero.getFicha(i, j + 1).getValor() + 1);
                    tablero.getFicha(i, j + 1).setColor(color);
                    interfaz.imprimePosicionCubo(i, j + 1);
                    fichasDisponibles = fichasDisponibles - 1;
                }

            }
            if (this.condiciones.isCondExternaDos()) {
                if (this.condiciones.isCondDerecha() && this.condiciones.isCondArriba() && fichasDisponibles >= 1 && tablero.getFicha(i, j).getValor() < 5) {
                    tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                    interfaz.imprimePosicionCubo(i, j);
                    fichasDisponibles = fichasDisponibles - 1;
                }
                if (this.condiciones.isCondIzquierda() && this.condiciones.isCondArriba() && fichasDisponibles >= 1 && tablero.getFicha(i, j).getValor() < 5) {
                    tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                    interfaz.imprimePosicionCubo(i, j);
                    fichasDisponibles = fichasDisponibles - 1;
                }
                if (this.condiciones.isDiagonalArribaIzquierda() && this.condiciones.isCondIzquierda() && fichasDisponibles >= 1 && tablero.getFicha(i, j - 1).getValor() < 5) {
                    tablero.getFicha(i, j - 1).setValor(tablero.getFicha(i, j - 1).getValor() + 1);
                    tablero.getFicha(i, j - 1).setColor(color);
                    interfaz.imprimePosicionCubo(i, j - 1);
                    fichasDisponibles = fichasDisponibles - 1;
                }
                if (this.condiciones.isDiagonalArribaIzquierda() && this.condiciones.isCondArriba() && fichasDisponibles >= 1 && tablero.getFicha(i - 1, j).getValor() < 5) {
                    tablero.getFicha(i - 1, j).setValor(tablero.getFicha(i - 1, j).getValor() + 1);
                    tablero.getFicha(i - 1, j).setColor(color);
                    interfaz.imprimePosicionCubo(i - 1, j);
                    fichasDisponibles = fichasDisponibles - 1;
                }
                if (this.condiciones.isDiagonalArribaDerecha() && this.condiciones.isCondArriba() && fichasDisponibles >= 1 && tablero.getFicha(i - 1, j).getValor() < 5) {
                    tablero.getFicha(i - 1, j).setValor(tablero.getFicha(i - 1, j).getValor() + 1);
                    tablero.getFicha(i - 1, j).setColor(color);
                    interfaz.imprimePosicionCubo(i - 1, j);
                    fichasDisponibles = fichasDisponibles - 1;
                }
                if (this.condiciones.isDiagonalArribaDerecha() && this.condiciones.isCondDerecha() && fichasDisponibles >= 1 && tablero.getFicha(i, j + 1).getValor() < 5) {
                    tablero.getFicha(i, j + 1).setValor(tablero.getFicha(i, j + 1).getValor() + 1);
                    tablero.getFicha(i, j + 1).setColor(color);
                    interfaz.imprimePosicionCubo(i, j + 1);
                    fichasDisponibles = fichasDisponibles - 1;
                }
            }
            if (this.condiciones.isCondExternaTres()) {
                if (this.condiciones.isCondDerecha() && this.condiciones.isCondArriba() && fichasDisponibles >= 1 && tablero.getFicha(i, j).getValor() < 5) {
                    tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                    interfaz.imprimePosicionCubo(i, j);
                    fichasDisponibles = fichasDisponibles - 1;
                }
                if (this.condiciones.isCondDerecha() && this.condiciones.isCondAbajo() && fichasDisponibles >= 1 && tablero.getFicha(i, j).getValor() < 5) {
                    tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                    interfaz.imprimePosicionCubo(i, j);
                    fichasDisponibles = fichasDisponibles - 1;
                }
                if (this.condiciones.isDiagonalArribaDerecha() && this.condiciones.isCondArriba() && fichasDisponibles >= 1 && tablero.getFicha(i - 1, j).getValor() < 5) {
                    tablero.getFicha(i - 1, j).setValor(tablero.getFicha(i - 1, j).getValor() + 1);
                    tablero.getFicha(i - 1, j).setColor(color);
                    interfaz.imprimePosicionCubo(i - 1, j);
                    fichasDisponibles = fichasDisponibles - 1;
                }
                if (this.condiciones.isDiagonalArribaDerecha() && this.condiciones.isCondDerecha() && fichasDisponibles >= 1 && tablero.getFicha(i, j + 1).getValor() < 5) {
                    tablero.getFicha(i, j + 1).setValor(tablero.getFicha(i, j + 1).getValor() + 1);
                    tablero.getFicha(i, j + 1).setColor(color);
                    interfaz.imprimePosicionCubo(i, j + 1);
                    fichasDisponibles = fichasDisponibles - 1;
                }
                if (this.condiciones.isDiagonalAbajoDerecha() && this.condiciones.isCondDerecha() && fichasDisponibles >= 1 && tablero.getFicha(i, j + 1).getValor() < 5) {
                    tablero.getFicha(i, j + 1).setValor(tablero.getFicha(i, j + 1).getValor() + 1);
                    tablero.getFicha(i, j + 1).setColor(color);
                    interfaz.imprimePosicionCubo(i, j + 1);
                    fichasDisponibles = fichasDisponibles - 1;
                }
                if (this.condiciones.isDiagonalAbajoDerecha() && this.condiciones.isCondAbajo() && fichasDisponibles >= 1 && tablero.getFicha(i + 1, j).getValor() < 5) {
                    tablero.getFicha(i + 1, j).setValor(tablero.getFicha(i + 1, j).getValor() + 1);
                    tablero.getFicha(i + 1, j).setColor(color);
                    interfaz.imprimePosicionCubo(i + 1, j);
                    fichasDisponibles = fichasDisponibles - 1;
                }
            }
            if (this.condiciones.isCondExternaCuatro()) {
                if (this.condiciones.isCondIzquierda() && this.condiciones.isCondArriba() && fichasDisponibles >= 1 && tablero.getFicha(i, j).getValor() < 5) {
                    tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                    interfaz.imprimePosicionCubo(i, j);
                    fichasDisponibles = fichasDisponibles - 1;
                }
                if (this.condiciones.isCondIzquierda() && this.condiciones.isCondAbajo() && fichasDisponibles >= 1 && tablero.getFicha(i, j).getValor() < 5) {
                    tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                    interfaz.imprimePosicionCubo(i, j);
                    fichasDisponibles = fichasDisponibles - 1;
                }
                if (this.condiciones.isDiagonalArribaIzquierda() && this.condiciones.isCondArriba() && fichasDisponibles >= 1 && tablero.getFicha(i - 1, j).getValor() < 5) {
                    tablero.getFicha(i - 1, j).setValor(tablero.getFicha(i - 1, j).getValor() + 1);
                    tablero.getFicha(i - 1, j).setColor(color);
                    interfaz.imprimePosicionCubo(i - 1, j);
                    fichasDisponibles = fichasDisponibles - 1;
                }
                if (this.condiciones.isDiagonalArribaIzquierda() && this.condiciones.isCondIzquierda() && fichasDisponibles >= 1 && tablero.getFicha(i, j - 1).getValor() < 5) {
                    tablero.getFicha(i, j - 1).setValor(tablero.getFicha(i, j - 1).getValor() + 1);
                    tablero.getFicha(i, j - 1).setColor(color);
                    interfaz.imprimePosicionCubo(i, j - 1);
                    fichasDisponibles = fichasDisponibles - 1;
                }
                if (this.condiciones.isDiagonalAbajoIzquierda() && this.condiciones.isCondIzquierda() && fichasDisponibles >= 1 && tablero.getFicha(i, j - 1).getValor() < 5) {
                    tablero.getFicha(i, j - 1).setValor(tablero.getFicha(i, j - 1).getValor() + 1);
                    tablero.getFicha(i, j - 1).setColor(color);
                    interfaz.imprimePosicionCubo(i, j - 1);
                    fichasDisponibles = fichasDisponibles - 1;
                }
                if (this.condiciones.isDiagonalAbajoIzquierda() && this.condiciones.isCondAbajo() && fichasDisponibles >= 1 && tablero.getFicha(i + 1, j).getValor() < 5) {
                    tablero.getFicha(i + 1, j).setValor(tablero.getFicha(i + 1, j).getValor() + 1);
                    tablero.getFicha(i + 1, j).setColor(color);
                    interfaz.imprimePosicionCubo(i + 1, j);
                    fichasDisponibles = fichasDisponibles - 1;
                }
            }
        }
        return fichasDisponibles;
    }

    //METODO QUE DEVUELVE LA CANTIDAD DE FICHAS DISPONIBLES DEL JUGADOR DESPUES DE VERIFICAR SI SE EXTENDIO ESQUINA Y DESPUES DE REALIZADA LA JUGADA.
    public int seExtendioEsquina(int i, int j, Tablero tablero, int color, int fichasDisponibles) {
        int iAtras = i - 1;
        int iAdelante = i + 1;
        int jAtras = j - 1;
        int jAdelante = j + 1;
        Interfaz interfaz = new Interfaz();
        boolean hayFicha = true;
        boolean tengoQueExtenderHaciaArriba = false;
        boolean tengoQueExtenderHaciaAbajo = false;
        //FOR QUE RECORRE LAS FILAS BUSCANDO ESQUINAS PARA EXTENDER.
        for (int x = i - 1; x >= 0 && hayFicha; x--) {
            if (tablero.getFicha(x, j).getValor() > 0) {
                hayFicha = true;
                if (x != i - 1) {
                    if (j > 0) {
                        if (tablero.getFicha(x, j - 1).getValor() > 0) {
                            tengoQueExtenderHaciaArriba = true;
                        }
                    }
                    if (j < 5) {
                        if (tablero.getFicha(x, j + 1).getValor() > 0) {
                            tengoQueExtenderHaciaAbajo = true;
                        }
                    }
                    //ACA, SI HAY QUE EXTENDER ES TRUE, QUIERE DECIR SE ENCONTRO UNA ESQUINA Y PROCEDO A TRABAJAR CON ELLA
                    if (tengoQueExtenderHaciaArriba && fichasDisponibles > 0 && tablero.getFicha(x, j).getValor() < 5) {
                        tablero.getFicha(x, j).setValor(tablero.getFicha(x, j).getValor() + 1);
                        interfaz.imprimePosicionCubo(x, j);
                        fichasDisponibles = fichasDisponibles - 1;
                        tablero.getFicha(x, j).setColor(color);
                    }
                    if (tengoQueExtenderHaciaAbajo && fichasDisponibles > 0 && tablero.getFicha(x, j).getValor() < 5) {
                        tablero.getFicha(x, j).setValor(tablero.getFicha(x, j).getValor() + 1);
                        interfaz.imprimePosicionCubo(x, j);
                        fichasDisponibles = fichasDisponibles - 1;
                        tablero.getFicha(x, j).setColor(color);
                    }
                }
            } else {
                hayFicha = false;
            }
            tengoQueExtenderHaciaArriba = false;
            tengoQueExtenderHaciaAbajo = false;
        }
        hayFicha = true;
        tengoQueExtenderHaciaArriba = false;
        tengoQueExtenderHaciaAbajo = false;
        //FOR QUE RECORRE LAS FILAS BUSCANDO ESQUINAS PARA EXTENDER.
        for (int x = i + 1; x <= 5 && hayFicha; x++) {
            if (tablero.getFicha(x, j).getValor() > 0) {
                hayFicha = true;
                if (x != i + 1) {
                    if (j > 0) {
                        if (tablero.getFicha(x, j - 1).getValor() > 0) {
                            tengoQueExtenderHaciaArriba = true;
                        }
                    }
                    if (j < 5) {
                        if (tablero.getFicha(x, j + 1).getValor() > 0) {
                            tengoQueExtenderHaciaAbajo = true;
                        }
                    }
                    //ACA, SI HAY QUE EXTENDER ES TRUE, QUIERE DECIR SE ENCONTRO UNA ESQUINA Y PROCEDO A TRABAJAR CON ELLA
                    if (tengoQueExtenderHaciaArriba && fichasDisponibles > 0 && tablero.getFicha(x, j).getValor() < 5) {
                        tablero.getFicha(x, j).setValor(tablero.getFicha(x, j).getValor() + 1);
                        interfaz.imprimePosicionCubo(x, j);
                        fichasDisponibles = fichasDisponibles - 1;
                        tablero.getFicha(x, j).setColor(color);
                    }
                    if (tengoQueExtenderHaciaAbajo && fichasDisponibles > 0 && tablero.getFicha(x, j).getValor() < 5) {
                        tablero.getFicha(x, j).setValor(tablero.getFicha(x, j).getValor() + 1);
                        interfaz.imprimePosicionCubo(x, j);
                        fichasDisponibles = fichasDisponibles - 1;
                        tablero.getFicha(x, j).setColor(color);
                    }
                }
            } else {
                hayFicha = false;
            }
            tengoQueExtenderHaciaArriba = false;
            tengoQueExtenderHaciaAbajo = false;
        }
        hayFicha = true;
        tengoQueExtenderHaciaArriba = false;
        tengoQueExtenderHaciaAbajo = false;
        //FOR QUE RECORRE LAS FILAS BUSCANDO ESQUINAS PARA EXTENDER.
        for (int x = j - 1; x >= 0 && hayFicha; x--) {
            if (tablero.getFicha(i, x).getValor() > 0) {
                hayFicha = true;
                if (x != j - 1) {
                    if (i > 0) {
                        if (tablero.getFicha(i - 1, x).getValor() > 0) {
                            tengoQueExtenderHaciaArriba = true;
                        }
                    }
                    if (i < 5) {
                        if (tablero.getFicha(i + 1, x).getValor() > 0) {
                            tengoQueExtenderHaciaAbajo = true;
                        }
                    }
                    //ACA, SI HAY QUE EXTENDER ES TRUE, QUIERE DECIR SE ENCONTRO UNA ESQUINA Y PROCEDO A TRABAJAR CON ELLA
                    if (tengoQueExtenderHaciaArriba && fichasDisponibles > 0 && tablero.getFicha(i, x).getValor() < 5) {
                        tablero.getFicha(i, x).setValor(tablero.getFicha(i, x).getValor() + 1);
                        interfaz.imprimePosicionCubo(i, x);
                        fichasDisponibles = fichasDisponibles - 1;
                        tablero.getFicha(i, x).setColor(color);
                    }
                    if (tengoQueExtenderHaciaAbajo && fichasDisponibles > 0 && tablero.getFicha(i, x).getValor() < 5) {
                        tablero.getFicha(i, x).setValor(tablero.getFicha(i, x).getValor() + 1);
                        interfaz.imprimePosicionCubo(i, x);
                        fichasDisponibles = fichasDisponibles - 1;
                        tablero.getFicha(i, x).setColor(color);
                    }
                }
            } else {
                hayFicha = false;
            }
            tengoQueExtenderHaciaArriba = false;
            tengoQueExtenderHaciaAbajo = false;
        }
        hayFicha = true;
        tengoQueExtenderHaciaArriba = false;
        tengoQueExtenderHaciaAbajo = false;
        //FOR QUE RECORRE LAS FILAS BUSCANDO ESQUINAS PARA EXTENDER.
        for (int x = j + 1; x <= 5 && hayFicha; x++) {
            if (tablero.getFicha(i, x).getValor() > 0) {
                hayFicha = true;
                if (x != j + 1) {
                    if (i > 0) {
                        if (tablero.getFicha(i - 1, x).getValor() > 0) {
                            tengoQueExtenderHaciaArriba = true;
                        }
                    }
                    if (i < 5) {
                        if (tablero.getFicha(i + 1, x).getValor() > 0) {
                            tengoQueExtenderHaciaAbajo = true;
                        }
                    }
                    //ACA, SI HAY QUE EXTENDER ES TRUE, QUIERE DECIR SE ENCONTRO UNA ESQUINA Y PROCEDO A TRABAJAR CON ELLA
                    if (tengoQueExtenderHaciaArriba && fichasDisponibles > 0 && tablero.getFicha(i, x).getValor() < 5) {
                        tablero.getFicha(i, x).setValor(tablero.getFicha(i, x).getValor() + 1);
                        interfaz.imprimePosicionCubo(i, x);
                        fichasDisponibles = fichasDisponibles - 1;
                        tablero.getFicha(i, x).setColor(color);
                    }
                    if (tengoQueExtenderHaciaAbajo && fichasDisponibles > 0 && tablero.getFicha(i, x).getValor() < 5) {
                        tablero.getFicha(i, x).setValor(tablero.getFicha(i, x).getValor() + 1);
                        interfaz.imprimePosicionCubo(i, x);
                        fichasDisponibles = fichasDisponibles - 1;
                        tablero.getFicha(i, x).setColor(color);
                    }
                }
            } else {
                hayFicha = false;
            }
            tengoQueExtenderHaciaArriba = false;
            tengoQueExtenderHaciaAbajo = false;
        }
        return fichasDisponibles;
    }

    //METODO IGUAL QUE SE FORMO ESQUINA, PERO SIN IMPRESION EN PANTALLA. ESTE METODO ES USADO PARA LA JUGADA CONTRA LA PC.
    public int seFormoEsquinaSinSoutear(int i, int j, Tablero tablero, int color, int fichasDisponibles, int[][] matrizInt) {
        this.condiciones.chequeadorCondiciones(i, j, tablero);
        //YA AGREGAMOS LA FICHA VALOR 1 Y COLOR DEL JUGADOR DE TURNO AL TABLERO Y DESP EVALUAMOS
        tablero.getFicha(i, j).setValor(1);
        fichasDisponibles = fichasDisponibles - 1;
        matrizInt[i][j] = matrizInt[i][j] + 1;
        tablero.getFicha(i, j).setColor(color);
        if (this.condiciones.isCondInternaDelTablero()) {
            //INTERNO DEL TABLERO
            if (this.condiciones.isCondArriba() && this.condiciones.isCondIzquierda() && fichasDisponibles >= 1 && tablero.getFicha(i, j).getValor() < 5) {
                tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                matrizInt[i][j] = matrizInt[i][j] + 1;
                fichasDisponibles = fichasDisponibles - 1;
            }
            if (this.condiciones.isCondArriba() && this.condiciones.isCondDerecha() && fichasDisponibles >= 1 && tablero.getFicha(i, j).getValor() < 5) {
                tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                matrizInt[i][j] = matrizInt[i][j] + 1;
                fichasDisponibles = fichasDisponibles - 1;
            }
            if (this.condiciones.isCondAbajo() && this.condiciones.isCondIzquierda() && fichasDisponibles >= 1 && tablero.getFicha(i, j).getValor() < 5) {
                tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                matrizInt[i][j] = matrizInt[i][j] + 1;
                fichasDisponibles = fichasDisponibles - 1;
            }
            if (this.condiciones.isCondAbajo() && this.condiciones.isCondDerecha() && fichasDisponibles >= 1 && tablero.getFicha(i, j).getValor() < 5) {
                tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                matrizInt[i][j] = matrizInt[i][j] + 1;
                fichasDisponibles = fichasDisponibles - 1;
            }
            if (this.condiciones.isCondAbajo() && this.condiciones.isDiagonalAbajoDerecha() && fichasDisponibles >= 1 && tablero.getFicha(i + 1, j).getValor() < 5) {
                tablero.getFicha(i + 1, j).setValor(tablero.getFicha(i + 1, j).getValor() + 1);
                tablero.getFicha(i + 1, j).setColor(color);
                matrizInt[i][j] = matrizInt[i][j] + 1;
                fichasDisponibles = fichasDisponibles - 1;
            }
            if (this.condiciones.isCondDerecha() && this.condiciones.isDiagonalAbajoDerecha() && fichasDisponibles >= 1 && tablero.getFicha(i, j + 1).getValor() < 5) {
                tablero.getFicha(i, j + 1).setValor(tablero.getFicha(i, j + 1).getValor() + 1);
                tablero.getFicha(i, j + 1).setColor(color);
                matrizInt[i][j] = matrizInt[i][j] + 1;
                fichasDisponibles = fichasDisponibles - 1;
            }
            if (this.condiciones.isCondIzquierda() && this.condiciones.isDiagonalAbajoIzquierda() && fichasDisponibles >= 1 && tablero.getFicha(i, j - 1).getValor() < 5) {
                tablero.getFicha(i, j - 1).setValor(tablero.getFicha(i, j - 1).getValor() + 1);
                tablero.getFicha(i, j - 1).setColor(color);
                matrizInt[i][j] = matrizInt[i][j] + 1;
                fichasDisponibles = fichasDisponibles - 1;
            }
            if (this.condiciones.isCondAbajo() && this.condiciones.isDiagonalAbajoIzquierda() && fichasDisponibles >= 1 && tablero.getFicha(i + 1, j).getValor() < 5) {
                tablero.getFicha(i + 1, j).setValor(tablero.getFicha(i + 1, j).getValor() + 1);
                tablero.getFicha(i + 1, j).setColor(color);
                matrizInt[i][j] = matrizInt[i][j] + 1;
                fichasDisponibles = fichasDisponibles - 1;
            }
            if (this.condiciones.isDiagonalArribaDerecha() && this.condiciones.isCondArriba() && fichasDisponibles >= 1 && tablero.getFicha(i - 1, j).getValor() < 5) {
                tablero.getFicha(i - 1, j).setValor(tablero.getFicha(i - 1, j).getValor() + 1);
                tablero.getFicha(i - 1, j).setColor(color);
                matrizInt[i][j] = matrizInt[i][j] + 1;
                fichasDisponibles = fichasDisponibles - 1;
            }
            if (this.condiciones.isCondDerecha() && this.condiciones.isDiagonalArribaDerecha() && fichasDisponibles >= 1 && tablero.getFicha(i, j + 1).getValor() < 5) {
                tablero.getFicha(i, j + 1).setValor(tablero.getFicha(i, j + 1).getValor() + 1);
                tablero.getFicha(i, j + 1).setColor(color);
                matrizInt[i][j] = matrizInt[i][j] + 1;
                fichasDisponibles = fichasDisponibles - 1;
            }
            if (this.condiciones.isCondIzquierda() && this.condiciones.isDiagonalArribaIzquierda() && fichasDisponibles >= 1 && tablero.getFicha(i, j - 1).getValor() < 5) {
                tablero.getFicha(i, j - 1).setValor(tablero.getFicha(i, j - 1).getValor() + 1);
                tablero.getFicha(i, j - 1).setColor(color);
                matrizInt[i][j] = matrizInt[i][j] + 1;
                fichasDisponibles = fichasDisponibles - 1;
            }
            if (this.condiciones.isDiagonalArribaIzquierda() && this.condiciones.isCondArriba() && fichasDisponibles >= 1 && tablero.getFicha(i - 1, j).getValor() < 5) {
                tablero.getFicha(i - 1, j).setValor(tablero.getFicha(i - 1, j).getValor() + 1);
                tablero.getFicha(i - 1, j).setColor(color);
                matrizInt[i][j] = matrizInt[i][j] + 1;
                fichasDisponibles = fichasDisponibles - 1;
            }

        } else if (!this.condiciones.isCondInternaDelTablero()) {
            //EXTERNO DEL TABLERO
            switch (i) {
                case 0:
                    switch (j) {
                        case 0:
                            if (this.condiciones.isCondDerecha() && this.condiciones.isCondAbajo() && fichasDisponibles >= 1 && tablero.getFicha(i, j).getValor() < 5) {
                                tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                                matrizInt[i][j] = matrizInt[i][j] + 1;
                                fichasDisponibles = fichasDisponibles - 1;
                            }
                            if (this.condiciones.isCondDerecha() && this.condiciones.isDiagonalAbajoDerecha() && fichasDisponibles >= 1 && tablero.getFicha(i, j + 1).getValor() < 5) {
                                tablero.getFicha(i, j + 1).setValor(tablero.getFicha(i, j + 1).getValor() + 1);
                                tablero.getFicha(i, j + 1).setColor(color);
                                matrizInt[i][j] = matrizInt[i][j] + 1;
                                fichasDisponibles = fichasDisponibles - 1;
                            }
                            if (this.condiciones.isCondAbajo() && this.condiciones.isDiagonalAbajoDerecha() && fichasDisponibles >= 1 && tablero.getFicha(i + 1, j).getValor() < 5) {
                                tablero.getFicha(i + 1, j).setValor(tablero.getFicha(i + 1, j).getValor() + 1);
                                tablero.getFicha(i + 1, j).setColor(color);
                                matrizInt[i][j] = matrizInt[i][j] + 1;
                                fichasDisponibles = fichasDisponibles - 1;
                            }
                            break;
                        case 5:
                            if (this.condiciones.isCondIzquierda() && this.condiciones.isCondAbajo() && fichasDisponibles >= 1 && tablero.getFicha(i, j).getValor() < 5) {
                                tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                                matrizInt[i][j] = matrizInt[i][j] + 1;
                                fichasDisponibles = fichasDisponibles - 1;
                            }
                            if (this.condiciones.isCondIzquierda() && this.condiciones.isDiagonalAbajoIzquierda() && fichasDisponibles >= 1 && tablero.getFicha(i, j - 1).getValor() < 5) {
                                tablero.getFicha(i, j - 1).setValor(tablero.getFicha(i, j - 1).getValor() + 1);
                                tablero.getFicha(i, j - 1).setColor(color);
                                matrizInt[i][j] = matrizInt[i][j] + 1;
                                fichasDisponibles = fichasDisponibles - 1;
                            }
                            if (this.condiciones.isCondAbajo() && this.condiciones.isDiagonalAbajoIzquierda() && fichasDisponibles >= 1 && tablero.getFicha(i + 1, j).getValor() < 5) {
                                tablero.getFicha(i + 1, j).setValor(tablero.getFicha(i + 1, j).getValor() + 1);
                                tablero.getFicha(i + 1, j).setColor(color);
                                matrizInt[i][j] = matrizInt[i][j] + 1;
                                fichasDisponibles = fichasDisponibles - 1;
                            }
                            break;
                    }
                    break;
                case 5:
                    switch (j) {
                        case 0:
                            if (this.condiciones.isCondDerecha() && this.condiciones.isCondArriba() && fichasDisponibles >= 1 && tablero.getFicha(i, j).getValor() < 5) {
                                tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                                matrizInt[i][j] = matrizInt[i][j] + 1;
                                fichasDisponibles = fichasDisponibles - 1;
                            }
                            if (this.condiciones.isDiagonalArribaDerecha() && this.condiciones.isCondArriba() && fichasDisponibles >= 1 && tablero.getFicha(i - 1, j).getValor() < 5) {
                                tablero.getFicha(i - 1, j).setValor(tablero.getFicha(i - 1, j).getValor() + 1);
                                tablero.getFicha(i - 1, j).setColor(color);
                                matrizInt[i][j] = matrizInt[i][j] + 1;
                                fichasDisponibles = fichasDisponibles - 1;
                            }
                            if (this.condiciones.isCondDerecha() && this.condiciones.isDiagonalArribaDerecha() && fichasDisponibles >= 1 && tablero.getFicha(i, j + 1).getValor() < 5) {
                                tablero.getFicha(i, j + 1).setValor(tablero.getFicha(i, j + 1).getValor() + 1);
                                tablero.getFicha(i, j + 1).setColor(color);
                                matrizInt[i][j] = matrizInt[i][j] + 1;
                                fichasDisponibles = fichasDisponibles - 1;
                            }
                            break;
                        case 5:
                            if (this.condiciones.isCondIzquierda() && this.condiciones.isCondArriba() && fichasDisponibles >= 1 && tablero.getFicha(i, j).getValor() < 5) {
                                tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                                matrizInt[i][j] = matrizInt[i][j] + 1;
                                fichasDisponibles = fichasDisponibles - 1;
                            }
                            if (this.condiciones.isCondIzquierda() && this.condiciones.isDiagonalArribaIzquierda() && fichasDisponibles >= 1 && tablero.getFicha(i, j - 1).getValor() < 5) {
                                tablero.getFicha(i, j - 1).setValor(tablero.getFicha(i, j - 1).getValor() + 1);
                                tablero.getFicha(i, j - 1).setColor(color);
                                matrizInt[i][j] = matrizInt[i][j] + 1;
                                fichasDisponibles = fichasDisponibles - 1;
                            }
                            if (this.condiciones.isDiagonalArribaIzquierda() && this.condiciones.isCondArriba() && fichasDisponibles >= 1 && tablero.getFicha(i - 1, j).getValor() < 5) {
                                tablero.getFicha(i - 1, j).setValor(tablero.getFicha(i - 1, j).getValor() + 1);
                                tablero.getFicha(i - 1, j).setColor(color);
                                matrizInt[i][j] = matrizInt[i][j] + 1;
                                fichasDisponibles = fichasDisponibles - 1;
                            }
                            break;
                    }
                    break;
            }

            if (this.condiciones.isCondExternaUno()) {
                if (this.condiciones.isCondIzquierda() && this.condiciones.isCondAbajo() && fichasDisponibles >= 1 && tablero.getFicha(i, j).getValor() < 5) {
                    tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                    matrizInt[i][j] = matrizInt[i][j] + 1;
                    fichasDisponibles = fichasDisponibles - 1;
                }
                if (this.condiciones.isCondDerecha() && this.condiciones.isCondAbajo() && fichasDisponibles >= 1 && tablero.getFicha(i, j).getValor() < 5) {
                    tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                    matrizInt[i][j] = matrizInt[i][j] + 1;
                    fichasDisponibles = fichasDisponibles - 1;
                }
                if (this.condiciones.isDiagonalAbajoIzquierda() && this.condiciones.isCondIzquierda() && fichasDisponibles >= 1 && tablero.getFicha(i, j - 1).getValor() < 5) {
                    tablero.getFicha(i, j - 1).setValor(tablero.getFicha(i, j - 1).getValor() + 1);
                    tablero.getFicha(i, j - 1).setColor(color);
                    matrizInt[i][j] = matrizInt[i][j] + 1;
                    fichasDisponibles = fichasDisponibles - 1;
                }
                if (this.condiciones.isDiagonalAbajoIzquierda() && this.condiciones.isCondAbajo() && fichasDisponibles >= 1 && tablero.getFicha(i + 1, j).getValor() < 5) {
                    tablero.getFicha(i + 1, j).setValor(tablero.getFicha(i + 1, j).getValor() + 1);
                    tablero.getFicha(i + 1, j).setColor(color);
                    matrizInt[i][j] = matrizInt[i][j] + 1;
                    fichasDisponibles = fichasDisponibles - 1;
                }
                if (this.condiciones.isDiagonalAbajoDerecha() && this.condiciones.isCondAbajo() && fichasDisponibles >= 1 && tablero.getFicha(i + 1, j).getValor() < 5) {
                    tablero.getFicha(i + 1, j).setValor(tablero.getFicha(i + 1, j).getValor() + 1);
                    tablero.getFicha(i + 1, j).setColor(color);
                    matrizInt[i][j] = matrizInt[i][j] + 1;
                    fichasDisponibles = fichasDisponibles - 1;
                }
                if (this.condiciones.isDiagonalAbajoDerecha() && this.condiciones.isCondDerecha() && fichasDisponibles >= 1 && tablero.getFicha(i, j + 1).getValor() < 5) {
                    tablero.getFicha(i, j + 1).setValor(tablero.getFicha(i, j + 1).getValor() + 1);
                    tablero.getFicha(i, j + 1).setColor(color);
                    matrizInt[i][j] = matrizInt[i][j] + 1;
                    fichasDisponibles = fichasDisponibles - 1;
                }

            }
            if (this.condiciones.isCondExternaDos()) {
                if (this.condiciones.isCondDerecha() && this.condiciones.isCondArriba() && fichasDisponibles >= 1 && tablero.getFicha(i, j).getValor() < 5) {
                    tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                    matrizInt[i][j] = matrizInt[i][j] + 1;
                    fichasDisponibles = fichasDisponibles - 1;
                }
                if (this.condiciones.isCondIzquierda() && this.condiciones.isCondArriba() && fichasDisponibles >= 1 && tablero.getFicha(i, j).getValor() < 5) {
                    tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                    matrizInt[i][j] = matrizInt[i][j] + 1;
                    fichasDisponibles = fichasDisponibles - 1;
                }
                if (this.condiciones.isDiagonalArribaIzquierda() && this.condiciones.isCondIzquierda() && fichasDisponibles >= 1 && tablero.getFicha(i, j - 1).getValor() < 5) {
                    tablero.getFicha(i, j - 1).setValor(tablero.getFicha(i, j - 1).getValor() + 1);
                    tablero.getFicha(i, j - 1).setColor(color);
                    matrizInt[i][j] = matrizInt[i][j] + 1;
                    fichasDisponibles = fichasDisponibles - 1;
                }
                if (this.condiciones.isDiagonalArribaIzquierda() && this.condiciones.isCondArriba() && fichasDisponibles >= 1 && tablero.getFicha(i - 1, j).getValor() < 5) {
                    tablero.getFicha(i - 1, j).setValor(tablero.getFicha(i - 1, j).getValor() + 1);
                    tablero.getFicha(i - 1, j).setColor(color);
                    matrizInt[i][j] = matrizInt[i][j] + 1;
                    fichasDisponibles = fichasDisponibles - 1;
                }
                if (this.condiciones.isDiagonalArribaDerecha() && this.condiciones.isCondArriba() && fichasDisponibles >= 1 && tablero.getFicha(i - 1, j).getValor() < 5) {
                    tablero.getFicha(i - 1, j).setValor(tablero.getFicha(i - 1, j).getValor() + 1);
                    tablero.getFicha(i - 1, j).setColor(color);
                    matrizInt[i][j] = matrizInt[i][j] + 1;
                    fichasDisponibles = fichasDisponibles - 1;
                }
                if (this.condiciones.isDiagonalArribaDerecha() && this.condiciones.isCondDerecha() && fichasDisponibles >= 1 && tablero.getFicha(i, j + 1).getValor() < 5) {
                    tablero.getFicha(i, j + 1).setValor(tablero.getFicha(i, j + 1).getValor() + 1);
                    tablero.getFicha(i, j + 1).setColor(color);
                    matrizInt[i][j] = matrizInt[i][j] + 1;
                    fichasDisponibles = fichasDisponibles - 1;
                }
            }
            if (this.condiciones.isCondExternaTres()) {
                if (this.condiciones.isCondDerecha() && this.condiciones.isCondArriba() && fichasDisponibles >= 1 && tablero.getFicha(i, j).getValor() < 5) {
                    tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                    matrizInt[i][j] = matrizInt[i][j] + 1;
                    fichasDisponibles = fichasDisponibles - 1;
                }
                if (this.condiciones.isCondDerecha() && this.condiciones.isCondAbajo() && fichasDisponibles >= 1 && tablero.getFicha(i, j).getValor() < 5) {
                    tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                    matrizInt[i][j] = matrizInt[i][j] + 1;
                    fichasDisponibles = fichasDisponibles - 1;
                }
                if (this.condiciones.isDiagonalArribaDerecha() && this.condiciones.isCondArriba() && fichasDisponibles >= 1 && tablero.getFicha(i - 1, j).getValor() < 5) {
                    tablero.getFicha(i - 1, j).setValor(tablero.getFicha(i - 1, j).getValor() + 1);
                    tablero.getFicha(i - 1, j).setColor(color);
                    matrizInt[i][j] = matrizInt[i][j] + 1;
                    fichasDisponibles = fichasDisponibles - 1;
                }
                if (this.condiciones.isDiagonalArribaDerecha() && this.condiciones.isCondDerecha() && fichasDisponibles >= 1 && tablero.getFicha(i, j + 1).getValor() < 5) {
                    tablero.getFicha(i, j + 1).setValor(tablero.getFicha(i, j + 1).getValor() + 1);
                    tablero.getFicha(i, j + 1).setColor(color);
                    matrizInt[i][j] = matrizInt[i][j] + 1;
                    fichasDisponibles = fichasDisponibles - 1;
                }
                if (this.condiciones.isDiagonalAbajoDerecha() && this.condiciones.isCondDerecha() && fichasDisponibles >= 1 && tablero.getFicha(i, j + 1).getValor() < 5) {
                    tablero.getFicha(i, j + 1).setValor(tablero.getFicha(i, j + 1).getValor() + 1);
                    tablero.getFicha(i, j + 1).setColor(color);
                    matrizInt[i][j] = matrizInt[i][j] + 1;
                    fichasDisponibles = fichasDisponibles - 1;
                }
                if (this.condiciones.isDiagonalAbajoDerecha() && this.condiciones.isCondAbajo() && fichasDisponibles >= 1 && tablero.getFicha(i + 1, j).getValor() < 5) {
                    tablero.getFicha(i + 1, j).setValor(tablero.getFicha(i + 1, j).getValor() + 1);
                    tablero.getFicha(i + 1, j).setColor(color);
                    matrizInt[i][j] = matrizInt[i][j] + 1;
                    fichasDisponibles = fichasDisponibles - 1;
                }
            }
            if (this.condiciones.isCondExternaCuatro()) {
                if (this.condiciones.isCondIzquierda() && this.condiciones.isCondArriba() && fichasDisponibles >= 1 && tablero.getFicha(i, j).getValor() < 5) {
                    tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                    matrizInt[i][j] = matrizInt[i][j] + 1;
                    fichasDisponibles = fichasDisponibles - 1;
                }
                if (this.condiciones.isCondIzquierda() && this.condiciones.isCondAbajo() && fichasDisponibles >= 1 && tablero.getFicha(i, j).getValor() < 5) {
                    tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                    matrizInt[i][j] = matrizInt[i][j] + 1;
                    fichasDisponibles = fichasDisponibles - 1;
                }
                if (this.condiciones.isDiagonalArribaIzquierda() && this.condiciones.isCondArriba() && fichasDisponibles >= 1 && tablero.getFicha(i - 1, j).getValor() < 5) {
                    tablero.getFicha(i - 1, j).setValor(tablero.getFicha(i - 1, j).getValor() + 1);
                    tablero.getFicha(i - 1, j).setColor(color);
                    matrizInt[i][j] = matrizInt[i][j] + 1;
                    fichasDisponibles = fichasDisponibles - 1;
                }
                if (this.condiciones.isDiagonalArribaIzquierda() && this.condiciones.isCondIzquierda() && fichasDisponibles >= 1 && tablero.getFicha(i, j - 1).getValor() < 5) {
                    tablero.getFicha(i, j - 1).setValor(tablero.getFicha(i, j - 1).getValor() + 1);
                    tablero.getFicha(i, j - 1).setColor(color);
                    matrizInt[i][j] = matrizInt[i][j] + 1;
                    fichasDisponibles = fichasDisponibles - 1;
                }
                if (this.condiciones.isDiagonalAbajoIzquierda() && this.condiciones.isCondIzquierda() && fichasDisponibles >= 1 && tablero.getFicha(i, j - 1).getValor() < 5) {
                    tablero.getFicha(i, j - 1).setValor(tablero.getFicha(i, j - 1).getValor() + 1);
                    tablero.getFicha(i, j - 1).setColor(color);
                    matrizInt[i][j] = matrizInt[i][j] + 1;
                    fichasDisponibles = fichasDisponibles - 1;
                }
                if (this.condiciones.isDiagonalAbajoIzquierda() && this.condiciones.isCondAbajo() && fichasDisponibles >= 1 && tablero.getFicha(i + 1, j).getValor() < 5) {
                    tablero.getFicha(i + 1, j).setValor(tablero.getFicha(i + 1, j).getValor() + 1);
                    tablero.getFicha(i + 1, j).setColor(color);
                    matrizInt[i][j] = matrizInt[i][j] + 1;
                    fichasDisponibles = fichasDisponibles - 1;
                }
            }
        }
        return fichasDisponibles;
    }

    //METODO IGUAL QUE SE EXTENDIO ESQUINA, PERO SIN IMPRESION EN PANTALLA. ESTE METODO ES USADO PARA LA JUGADA CONTRA LA PC.
    public int seExtendioEsquinaSinSoutear(int i, int j, Tablero tablero, int color, int fichasDisponibles, int[][] matrizInt) {
        int iAtras = i - 1;
        int iAdelante = i + 1;
        int jAtras = j - 1;
        int jAdelante = j + 1;
        boolean hayFicha = true;
        boolean tengoQueExtenderHaciaArriba = false;
        boolean tengoQueExtenderHaciaAbajo = false;
        //FOR QUE RECORRE LAS FILAS BUSCANDO ESQUINAS PARA EXTENDER.
        for (int x = i - 1; x >= 0 && hayFicha; x--) {
            if (tablero.getFicha(x, j).getValor() > 0) {
                hayFicha = true;
                if (x != i - 1) {
                    if (j > 0) {
                        if (tablero.getFicha(x, j - 1).getValor() > 0) {
                            tengoQueExtenderHaciaArriba = true;
                        }
                    }
                    if (j < 5) {
                        if (tablero.getFicha(x, j + 1).getValor() > 0) {
                            tengoQueExtenderHaciaAbajo = true;
                        }
                    }
                    //ACA, SI HAY QUE EXTENDER ES TRUE, QUIERE DECIR SE ENCONTRO UNA ESQUINA Y PROCEDO A TRABAJAR CON ELLA
                    if (tengoQueExtenderHaciaArriba && fichasDisponibles > 0 && tablero.getFicha(x, j).getValor() < 5) {
                        tablero.getFicha(x, j).setValor(tablero.getFicha(x, j).getValor() + 1);
                        matrizInt[i][j] = matrizInt[i][j] + 1;
                        fichasDisponibles = fichasDisponibles - 1;
                        tablero.getFicha(x, j).setColor(color);
                    }
                    if (tengoQueExtenderHaciaAbajo && fichasDisponibles > 0 && tablero.getFicha(x, j).getValor() < 5) {
                        tablero.getFicha(x, j).setValor(tablero.getFicha(x, j).getValor() + 1);
                        matrizInt[i][j] = matrizInt[i][j] + 1;
                        fichasDisponibles = fichasDisponibles - 1;
                        tablero.getFicha(x, j).setColor(color);
                    }
                }
            } else {
                hayFicha = false;
            }
            tengoQueExtenderHaciaArriba = false;
            tengoQueExtenderHaciaAbajo = false;
        }
        hayFicha = true;
        tengoQueExtenderHaciaArriba = false;
        tengoQueExtenderHaciaAbajo = false;
        //FOR QUE RECORRE LAS FILAS BUSCANDO ESQUINAS PARA EXTENDER.
        for (int x = i + 1; x <= 5 && hayFicha; x++) {
            if (tablero.getFicha(x, j).getValor() > 0) {
                hayFicha = true;
                if (x != i + 1) {
                    if (j > 0) {
                        if (tablero.getFicha(x, j - 1).getValor() > 0) {
                            tengoQueExtenderHaciaArriba = true;
                        }
                    }
                    if (j < 5) {
                        if (tablero.getFicha(x, j + 1).getValor() > 0) {
                            tengoQueExtenderHaciaAbajo = true;
                        }
                    }
                    //ACA, SI HAY QUE EXTENDER ES TRUE, QUIERE DECIR SE ENCONTRO UNA ESQUINA Y PROCEDO A TRABAJAR CON ELLA
                    if (tengoQueExtenderHaciaArriba && fichasDisponibles > 0 && tablero.getFicha(x, j).getValor() < 5) {
                        tablero.getFicha(x, j).setValor(tablero.getFicha(x, j).getValor() + 1);
                        matrizInt[i][j] = matrizInt[i][j] + 1;
                        fichasDisponibles = fichasDisponibles - 1;
                        tablero.getFicha(x, j).setColor(color);
                    }
                    if (tengoQueExtenderHaciaAbajo && fichasDisponibles > 0 && tablero.getFicha(x, j).getValor() < 5) {
                        tablero.getFicha(x, j).setValor(tablero.getFicha(x, j).getValor() + 1);
                        matrizInt[i][j] = matrizInt[i][j] + 1;
                        fichasDisponibles = fichasDisponibles - 1;
                        tablero.getFicha(x, j).setColor(color);
                    }
                }
            } else {
                hayFicha = false;
            }
            tengoQueExtenderHaciaArriba = false;
            tengoQueExtenderHaciaAbajo = false;
        }
        hayFicha = true;
        tengoQueExtenderHaciaArriba = false;
        tengoQueExtenderHaciaAbajo = false;
        //FOR QUE RECORRE LAS FILAS BUSCANDO ESQUINAS PARA EXTENDER.
        for (int x = j - 1; x >= 0 && hayFicha; x--) {
            if (tablero.getFicha(i, x).getValor() > 0) {
                hayFicha = true;
                if (x != j - 1) {
                    if (i > 0) {
                        if (tablero.getFicha(i - 1, x).getValor() > 0) {
                            tengoQueExtenderHaciaArriba = true;
                        }
                    }
                    if (i < 5) {
                        if (tablero.getFicha(i + 1, x).getValor() > 0) {
                            tengoQueExtenderHaciaAbajo = true;
                        }
                    }
                    //ACA, SI HAY QUE EXTENDER ES TRUE, QUIERE DECIR SE ENCONTRO UNA ESQUINA Y PROCEDO A TRABAJAR CON ELLA
                    if (tengoQueExtenderHaciaArriba && fichasDisponibles > 0 && tablero.getFicha(i, x).getValor() < 5) {
                        tablero.getFicha(i, x).setValor(tablero.getFicha(i, x).getValor() + 1);
                        matrizInt[i][j] = matrizInt[i][j] + 1;
                        fichasDisponibles = fichasDisponibles - 1;
                        tablero.getFicha(i, x).setColor(color);
                    }
                    if (tengoQueExtenderHaciaAbajo && fichasDisponibles > 0 && tablero.getFicha(i, x).getValor() < 5) {
                        tablero.getFicha(i, x).setValor(tablero.getFicha(i, x).getValor() + 1);
                        matrizInt[i][j] = matrizInt[i][j] + 1;
                        fichasDisponibles = fichasDisponibles - 1;
                        tablero.getFicha(i, x).setColor(color);
                    }
                }
            } else {
                hayFicha = false;
            }
            tengoQueExtenderHaciaArriba = false;
            tengoQueExtenderHaciaAbajo = false;
        }
        hayFicha = true;
        tengoQueExtenderHaciaArriba = false;
        tengoQueExtenderHaciaAbajo = false;
        //FOR QUE RECORRE LAS FILAS BUSCANDO ESQUINAS PARA EXTENDER.
        for (int x = j + 1; x <= 5 && hayFicha; x++) {
            if (tablero.getFicha(i, x).getValor() > 0) {
                hayFicha = true;
                if (x != j + 1) {
                    if (i > 0) {
                        if (tablero.getFicha(i - 1, x).getValor() > 0) {
                            tengoQueExtenderHaciaArriba = true;
                        }
                    }
                    if (i < 5) {
                        if (tablero.getFicha(i + 1, x).getValor() > 0) {
                            tengoQueExtenderHaciaAbajo = true;
                        }
                    }
                    //ACA, SI HAY QUE EXTENDER ES TRUE, QUIERE DECIR SE ENCONTRO UNA ESQUINA Y PROCEDO A TRABAJAR CON ELLA
                    if (tengoQueExtenderHaciaArriba && fichasDisponibles > 0 && tablero.getFicha(i, x).getValor() < 5) {
                        tablero.getFicha(i, x).setValor(tablero.getFicha(i, x).getValor() + 1);
                        matrizInt[i][j] = matrizInt[i][j] + 1;
                        fichasDisponibles = fichasDisponibles - 1;
                        tablero.getFicha(i, x).setColor(color);
                    }
                    if (tengoQueExtenderHaciaAbajo && fichasDisponibles > 0 && tablero.getFicha(i, x).getValor() < 5) {
                        tablero.getFicha(i, x).setValor(tablero.getFicha(i, x).getValor() + 1);
                        matrizInt[i][j] = matrizInt[i][j] + 1;
                        fichasDisponibles = fichasDisponibles - 1;
                        tablero.getFicha(i, x).setColor(color);
                    }
                }
            } else {
                hayFicha = false;
            }
            tengoQueExtenderHaciaArriba = false;
            tengoQueExtenderHaciaAbajo = false;
        }
        return fichasDisponibles;
    }

    //METODO QUE BARAJA LAS POSIBILIDADES DE LA PC Y ELIJE LA MEJOR JUGADA (LLENA EL ARRAY DE INTS). LA PC ES PRACTICAMENTE INVENCIBLE.
    public void barajarPosibilidades(Tablero tableroAUX, int fichasDisponibles, int[][] matrizInt) {
        int fichasATRabajar;
        for (int i = 0; i < 6; i++) { //ESTA ES LA MATRIZ DE INT QUE VAMOS A USAR PARA VER EN QUE POSICION DEL TABLERO SE PONEN MAS FICHAS
            for (int j = 0; j < 6; j++) {
                fichasATRabajar = fichasDisponibles;
                Tablero tableroATrabajar = tableroAUX.copiarTablero(); //COPIO EL TABLERO, ASI NO LO ALTERO PARA LA SIGUIENTE INTERACION DEL FOR
                if (!this.formaCuadrado(i, j, tableroATrabajar)) { //ACA PONGO TODOS LOS METODOS QUE VALIDAN EL MOVIMIENTO, 
                    if (this.tieneAdyacente(i, j, tableroATrabajar)) {
                        //ACA PARA LA POSICION DEL FOR DONDE ESTOY, COLOCO LAS FICHAS EN EL TABLERO AUX Y UPDATEO LA MATRIZ INT
                        fichasATRabajar = this.seFormoEsquinaSinSoutear(i, j, tableroATrabajar, 2, fichasATRabajar, matrizInt);
                        this.seExtendioEsquinaSinSoutear(i, j, tableroATrabajar, 2, fichasATRabajar, matrizInt);
                    }
                }

            }
        }
    }

    //METODO QUE EVALUA LA MATRIZ DE INTS Y DECIDE CUAL ES EL MAXIMO PARA EFECTUAR LA JUGADA DE LA PC Y DEVUELVE LA CANTIDAD DE FICHAS DISPONIBLES
    public int mejorJugadaPC(Tablero tablero, int fichasDisponibles) {
        int maximo = 0;
        int[][] matrizInt = new int[6][6];
        for (int i = 0; i < 6; i++) { //ESTA ES LA MATRIZ DE INT QUE VAMOS A USAR PARA VER EN QUE POSICION DEL TABLERO SE PONEN MAS FICHAS
            for (int j = 0; j < 6; j++) {
                matrizInt[i][j] = 0;
            }
        }
        this.barajarPosibilidades(tablero, fichasDisponibles, matrizInt);
        boolean puse = false;
        for (int i = 0; i < 6; i++) { //PRIMERO RECORRO LA MATRIZ BUSCANDO EL MAXIMO
            for (int j = 0; j < 6; j++) {
                if (matrizInt[i][j] > maximo) {
                    maximo = matrizInt[i][j];
                }
            }
        }
        //UNA VEZ QUE TENGO EL MAXIMO, BUSCO LA POSICION CON MENOR I, Y SINO J, PARA COLOCAR LAS FICHAS
        for (int i = 0; i < 6; i++) { //PRIMERO RECORRO LA MATRIZ BUSCANDO EL MAXIMO
            for (int j = 0; j < 6; j++) {
                if (matrizInt[i][j] == maximo && !puse) {
                    fichasDisponibles = this.seFormoEsquina(i, j, tablero, 2, fichasDisponibles);
                    fichasDisponibles = this.seExtendioEsquina(i, j, tablero, 2, fichasDisponibles);
                    puse = true;
                }
            }
        }
        return fichasDisponibles; //ESTE METODO ME TERMINA DEVOLVIENDO CUANTAS FICHAS QUEDARON
    }
}

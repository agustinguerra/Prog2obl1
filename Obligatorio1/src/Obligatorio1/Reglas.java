package Obligatorio1;

public class Reglas {

    private final Condiciones condiciones;

    public Reglas() {
        this.condiciones = new Condiciones();
    }

    //metodos de validaciones y movimientos
    public boolean formaCuadrado(int i, int j, Tablero tablero) {  //DEVUELVE TRUE SI FORMA CUADRADO
        //I Y J ES LA POSICION DE LA FICHA QUE SE QUIERE VALIDAR
        //AL PONER UNA FICHA, HAY POSIBLES CUADRADOS QUE SE PUEDEN FORMAR
        //PRIMERO CHEQUEO QUE ESE CUADRADO SEA POSIBLE DADA LA POSICION DEL TABLERO, Y LUEGO CHEQUEO SI SE FORMA O NO

        //VARIABLE BOOLEANA DE RETORNO
        boolean devuelve = false;
        //CHEQUEO QUE EXISTA FICHA EN EL LUGAR, SI ESXISTE YA DEVUELVO QUE EL MOV NO ES VALIDO
        boolean hayFicha = this.condiciones.hayFicha(i, j, tablero);

        if (!hayFicha) {
            this.condiciones.chequeadorCondiciones(i, j, tablero);
            if (this.condiciones.isCond()) {
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
            } else if (!this.condiciones.isCond()) {
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
    
    public int calcularCantidadFichas(Tablero tablero) {
        int cantidad = 0;        
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                cantidad = cantidad + tablero.getFicha(i, j).getValor();
            }
        }
        return cantidad;
    }    //no se si se va a usar, verificar borrarlo antes de borrar este comentario si no se uso

    public boolean tieneAdyacente(int i, int j, Tablero tablero) { //DEVUELVE TRUE SI TIENE FICHA ADYACENTE
        boolean devuelve = false;
        this.condiciones.chequeadorCondiciones(i, j, tablero);

        if (this.condiciones.isCond()) {
            //INTERNO DEL TABLERO
            if (this.condiciones.isCondIzquierda() || this.condiciones.isCondAbajo() || this.condiciones.isCondDerecha() || this.condiciones.isCondArriba() || this.condiciones.isDiagonalAbajoIzquierda() || this.condiciones.isDiagonalAbajoDerecha() || this.condiciones.isDiagonalArribaIzquierda() || this.condiciones.isDiagonalArribaDerecha()) {
                devuelve = true;
            }
        } else if (!this.condiciones.isCond()) {
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

    public int seFormoEsquina(int i, int j, Tablero tablero, int color,int fichasDisponibles) { //CHEQUEA SI SE FORMARON O NO ESQUINAS AL MOMENTO DE PONER LA FICHA
        //PODEMOS HACER ESTO, QUE TE PARECE?
        //SI TE GUSTA PODEMOS HACER LO MISMO EN EL METODO DE ARRIBA
        //HICE LOS METODOS DE ARRIBA QUE FALTABAN, ESTE QUE ARRANCASTE VOS ENCARALO VOS, Y DESPUES LOS DOS QUE QUEDAN VEMOS COMO LO DIVIDIMOS
        this.condiciones.chequeadorCondiciones(i, j, tablero);
        //YA AGREGAMOS LA FICHA VALOR 1 Y COLOR DEL JUGADOR DE TURNO AL TABLERO Y DESP EVALUAMOS
        Interfaz interfaz = new Interfaz();        
        tablero.getFicha(i, j).setValor(1);
        fichasDisponibles=fichasDisponibles-1;
        interfaz.imprimePosicionCubo(i, j);
        tablero.getFicha(i, j).setColor(color);
        if (this.condiciones.isCond()) {
            //INTERNO DEL TABLERO
            if (this.condiciones.isCondArriba() && this.condiciones.isCondIzquierda() && fichasDisponibles>=1 && tablero.getFicha(i, j).getValor()<5) {
                tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                interfaz.imprimePosicionCubo(i, j);
                fichasDisponibles=fichasDisponibles-1;
            }
            if (this.condiciones.isCondArriba() && this.condiciones.isCondDerecha() && fichasDisponibles>=1 && tablero.getFicha(i, j).getValor()<5) {
                tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                interfaz.imprimePosicionCubo(i, j);
                fichasDisponibles=fichasDisponibles-1;
            }
            if (this.condiciones.isCondAbajo() && this.condiciones.isCondIzquierda() && fichasDisponibles>=1 && tablero.getFicha(i, j).getValor()<5) {
                tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                interfaz.imprimePosicionCubo(i, j);
                fichasDisponibles=fichasDisponibles-1;
            }
            if (this.condiciones.isCondAbajo() && this.condiciones.isCondDerecha() && fichasDisponibles>=1 && tablero.getFicha(i, j).getValor()<5) {
                tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                interfaz.imprimePosicionCubo(i, j);
                fichasDisponibles=fichasDisponibles-1;
            }
            //ACA VAN LOS QUE AGREGUE, HASTA EL ELSE IF POR SI NO ANDA BORRAR ESO
            if (this.condiciones.isCondAbajo() && this.condiciones.isDiagonalAbajoDerecha() && fichasDisponibles>=1 && tablero.getFicha(i+1, j).getValor()<5) {
                //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                tablero.getFicha(i + 1, j).setValor(tablero.getFicha(i + 1, j).getValor() + 1);
                tablero.getFicha(i + 1, j).setColor(color);
                interfaz.imprimePosicionCubo(i + 1, j);
                fichasDisponibles=fichasDisponibles-1;
            }
            if (this.condiciones.isCondDerecha() && this.condiciones.isDiagonalAbajoDerecha() && fichasDisponibles>=1 && tablero.getFicha(i, j+1).getValor()<5) {
                //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                tablero.getFicha(i, j + 1).setValor(tablero.getFicha(i, j + 1).getValor() + 1);
                tablero.getFicha(i, j + 1).setColor(color);
                interfaz.imprimePosicionCubo(i, j + 1);
                fichasDisponibles=fichasDisponibles-1;
            }
            if (this.condiciones.isCondIzquierda() && this.condiciones.isDiagonalAbajoIzquierda() && fichasDisponibles>=1 && tablero.getFicha(i, j-1).getValor()<5) {
                //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                tablero.getFicha(i, j - 1).setValor(tablero.getFicha(i, j - 1).getValor() + 1);
                tablero.getFicha(i, j - 1).setColor(color);
                interfaz.imprimePosicionCubo(i, j - 1);
                fichasDisponibles=fichasDisponibles-1;
            }
            if (this.condiciones.isCondAbajo() && this.condiciones.isDiagonalAbajoIzquierda() && fichasDisponibles>=1 && tablero.getFicha(i+1, j).getValor()<5) {
                //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                tablero.getFicha(i + 1, j).setValor(tablero.getFicha(i + 1, j).getValor() + 1);
                tablero.getFicha(i + 1, j).setColor(color);
                interfaz.imprimePosicionCubo(i + 1, j);
                fichasDisponibles=fichasDisponibles-1;
            }
            if (this.condiciones.isDiagonalArribaDerecha() && this.condiciones.isCondArriba() && fichasDisponibles>=1 && tablero.getFicha(i-1, j).getValor()<5) {
                //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                tablero.getFicha(i - 1, j).setValor(tablero.getFicha(i - 1, j).getValor() + 1);
                tablero.getFicha(i - 1, j).setColor(color);
                interfaz.imprimePosicionCubo(i - 1, j);
                fichasDisponibles=fichasDisponibles-1;
            }
            if (this.condiciones.isCondDerecha() && this.condiciones.isDiagonalArribaDerecha() && fichasDisponibles>=1 && tablero.getFicha(i, j+1).getValor()<5) {
                //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                tablero.getFicha(i, j + 1).setValor(tablero.getFicha(i, j + 1).getValor() + 1);
                tablero.getFicha(i, j + 1).setColor(color);
                interfaz.imprimePosicionCubo(i, j + 1);
                fichasDisponibles=fichasDisponibles-1;
            }
            if (this.condiciones.isCondIzquierda() && this.condiciones.isDiagonalArribaIzquierda() && fichasDisponibles>=1 && tablero.getFicha(i, j-1).getValor()<5) {
                //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                tablero.getFicha(i, j - 1).setValor(tablero.getFicha(i, j - 1).getValor() + 1);
                tablero.getFicha(i, j - 1).setColor(color);
                interfaz.imprimePosicionCubo(i, j - 1);
                fichasDisponibles=fichasDisponibles-1;
            }
            if (this.condiciones.isDiagonalArribaIzquierda() && this.condiciones.isCondArriba() && fichasDisponibles>=1 && tablero.getFicha(i-1, j).getValor()<5) {
                //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                tablero.getFicha(i - 1, j).setValor(tablero.getFicha(i - 1, j).getValor() + 1);
                tablero.getFicha(i - 1, j).setColor(color);
                interfaz.imprimePosicionCubo(i - 1, j);
                fichasDisponibles=fichasDisponibles-1;
            }
            
        } else if (!this.condiciones.isCond()) {
            //EXTERNO DEL TABLERO
            switch (i) {
                case 0:
                    switch (j) {
                        case 0:
                            if (this.condiciones.isCondDerecha() && this.condiciones.isCondAbajo() && fichasDisponibles>=1 && tablero.getFicha(i, j).getValor()<5) {
                                //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                                tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                                interfaz.imprimePosicionCubo(i, j);
                                fichasDisponibles=fichasDisponibles-1;
                            }
                            if (this.condiciones.isCondDerecha() && this.condiciones.isDiagonalAbajoDerecha() && fichasDisponibles>=1 && tablero.getFicha(i, j+1).getValor()<5) {
                                //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                                tablero.getFicha(i, j+1).setValor(tablero.getFicha(i, j+1).getValor() + 1);
                                tablero.getFicha(i, j+1).setColor(color);
                                interfaz.imprimePosicionCubo(i, j+1);
                                fichasDisponibles=fichasDisponibles-1;
                            }  
                            if (this.condiciones.isCondAbajo() && this.condiciones.isDiagonalAbajoDerecha() && fichasDisponibles>=1 && tablero.getFicha(i+1, j).getValor()<5) {
                                //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                                tablero.getFicha(i+1, j).setValor(tablero.getFicha(i+1, j).getValor() + 1);
                                tablero.getFicha(i+1, j).setColor(color);
                                interfaz.imprimePosicionCubo(i+1, j);
                                fichasDisponibles=fichasDisponibles-1;
                            }                            
                            break;
                        case 5:
                            if (this.condiciones.isCondIzquierda() && this.condiciones.isCondAbajo() && fichasDisponibles>=1 && tablero.getFicha(i, j).getValor()<5) {
                                //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                                tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                                interfaz.imprimePosicionCubo(i, j);
                                fichasDisponibles=fichasDisponibles-1;
                            }
                            if (this.condiciones.isCondIzquierda() && this.condiciones.isDiagonalAbajoIzquierda() && fichasDisponibles>=1 && tablero.getFicha(i, j-1).getValor()<5) {
                                //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                                tablero.getFicha(i, j-1).setValor(tablero.getFicha(i, j-1).getValor() + 1);
                                tablero.getFicha(i, j-1).setColor(color);
                                interfaz.imprimePosicionCubo(i, j-1);
                                fichasDisponibles=fichasDisponibles-1;
                            }
                            if (this.condiciones.isCondAbajo() && this.condiciones.isDiagonalAbajoIzquierda() && fichasDisponibles>=1 && tablero.getFicha(i+1, j).getValor()<5) {
                                //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                                tablero.getFicha(i+1, j).setValor(tablero.getFicha(i+1, j).getValor() + 1);
                                tablero.getFicha(i+1, j).setColor(color);
                                interfaz.imprimePosicionCubo(i+1, j);
                                fichasDisponibles=fichasDisponibles-1;
                            }                            
                            break;
                    }
                    break;
                case 5:
                    switch (j) {
                        case 0:
                            if (this.condiciones.isCondDerecha() && this.condiciones.isCondArriba() && fichasDisponibles>=1 && tablero.getFicha(i, j).getValor()<5) {
                                //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                                tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                                interfaz.imprimePosicionCubo(i, j);
                                fichasDisponibles=fichasDisponibles-1;
                            }
                            if (this.condiciones.isDiagonalArribaDerecha() && this.condiciones.isCondArriba() && fichasDisponibles>=1 && tablero.getFicha(i-1, j).getValor()<5) {
                                //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                                tablero.getFicha(i-1, j).setValor(tablero.getFicha(i-1, j).getValor() + 1);
                                tablero.getFicha(i-1, j).setColor(color);
                                interfaz.imprimePosicionCubo(i-1, j);
                                fichasDisponibles=fichasDisponibles-1;
                            }
                            if (this.condiciones.isCondDerecha() && this.condiciones.isDiagonalArribaDerecha() && fichasDisponibles>=1 && tablero.getFicha(i, j+1).getValor()<5) {
                                //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                                tablero.getFicha(i, j+1).setValor(tablero.getFicha(i, j+1).getValor() + 1);
                                tablero.getFicha(i, j+1).setColor(color);
                                interfaz.imprimePosicionCubo(i, j+1);
                                fichasDisponibles=fichasDisponibles-1;
                            }                            
                            break;
                        case 5:
                            if (this.condiciones.isCondIzquierda() && this.condiciones.isCondArriba() && fichasDisponibles>=1 && tablero.getFicha(i, j).getValor()<5) {
                                //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                                tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                                interfaz.imprimePosicionCubo(i, j);
                                fichasDisponibles=fichasDisponibles-1;
                            }
                            if (this.condiciones.isCondIzquierda() && this.condiciones.isDiagonalArribaIzquierda() && fichasDisponibles>=1 && tablero.getFicha(i, j-1).getValor()<5) {
                                //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                                tablero.getFicha(i, j-1).setValor(tablero.getFicha(i, j-1).getValor() + 1);
                                tablero.getFicha(i, j-1).setColor(color);
                                interfaz.imprimePosicionCubo(i, j-1);
                                fichasDisponibles=fichasDisponibles-1;
                            }
                            if (this.condiciones.isDiagonalArribaIzquierda() && this.condiciones.isCondArriba() && fichasDisponibles>=1 && tablero.getFicha(i-1, j).getValor()<5) {
                                //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                                tablero.getFicha(i-1, j).setValor(tablero.getFicha(i-1, j).getValor() + 1);
                                tablero.getFicha(i-1, j).setColor(color);
                                interfaz.imprimePosicionCubo(i-1, j);
                                fichasDisponibles=fichasDisponibles-1;
                            }                            
                            break;
                    }
                    break;
            }

            if (this.condiciones.isCondExternaUno()) {
                if (this.condiciones.isCondIzquierda() && this.condiciones.isCondAbajo() && fichasDisponibles>=1 && tablero.getFicha(i, j).getValor()<5) {
                    tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                    interfaz.imprimePosicionCubo(i, j);
                    fichasDisponibles=fichasDisponibles-1;
                }
                if (this.condiciones.isCondDerecha() && this.condiciones.isCondAbajo() && fichasDisponibles>=1 && tablero.getFicha(i, j).getValor()<5) {
                    tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                    interfaz.imprimePosicionCubo(i, j);
                    fichasDisponibles=fichasDisponibles-1;
                }
                if (this.condiciones.isDiagonalAbajoIzquierda() && this.condiciones.isCondIzquierda() && fichasDisponibles>=1 && tablero.getFicha(i, j-1).getValor()<5) {
                    //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                    tablero.getFicha(i, j-1).setValor(tablero.getFicha(i, j-1).getValor() + 1);
                    tablero.getFicha(i, j-1).setColor(color);
                    interfaz.imprimePosicionCubo(i, j-1);
                    fichasDisponibles=fichasDisponibles-1;
                }
                if (this.condiciones.isDiagonalAbajoIzquierda() && this.condiciones.isCondAbajo() && fichasDisponibles>=1 && tablero.getFicha(i+1, j).getValor()<5) {
                    //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                    tablero.getFicha(i+1, j).setValor(tablero.getFicha(i+1, j).getValor() + 1);
                    tablero.getFicha(i+1, j).setColor(color);
                    interfaz.imprimePosicionCubo(i+1, j);
                    fichasDisponibles=fichasDisponibles-1;
                }
                if (this.condiciones.isDiagonalAbajoDerecha() && this.condiciones.isCondAbajo() && fichasDisponibles>=1 && tablero.getFicha(i+1, j).getValor()<5) {
                    //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                    tablero.getFicha(i+1, j).setValor(tablero.getFicha(i+1, j).getValor() + 1);
                    tablero.getFicha(i+1, j).setColor(color);
                    interfaz.imprimePosicionCubo(i+1, j);
                    fichasDisponibles=fichasDisponibles-1;
                }
                if (this.condiciones.isDiagonalAbajoDerecha() && this.condiciones.isCondDerecha() && fichasDisponibles>=1 && tablero.getFicha(i, j+1).getValor()<5) {
                    //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                    tablero.getFicha(i, j+1).setValor(tablero.getFicha(i, j+1).getValor() + 1);
                    tablero.getFicha(i, j+1).setColor(color);
                    interfaz.imprimePosicionCubo(i, j+1);
                    fichasDisponibles=fichasDisponibles-1;
                }
                
            }
            if (this.condiciones.isCondExternaDos()) {
                if (this.condiciones.isCondDerecha() && this.condiciones.isCondArriba() && fichasDisponibles>=1 && tablero.getFicha(i, j).getValor()<5) {
                    tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                    interfaz.imprimePosicionCubo(i, j);
                    fichasDisponibles=fichasDisponibles-1;
                }
                if (this.condiciones.isCondIzquierda() && this.condiciones.isCondArriba() && fichasDisponibles>=1 && tablero.getFicha(i, j).getValor()<5) {
                    tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                    interfaz.imprimePosicionCubo(i, j);
                    fichasDisponibles=fichasDisponibles-1;
                }
                if (this.condiciones.isDiagonalArribaIzquierda() && this.condiciones.isCondIzquierda() && fichasDisponibles>=1 && tablero.getFicha(i, j-1).getValor()<5) {
                    //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                    tablero.getFicha(i, j-1).setValor(tablero.getFicha(i, j-1).getValor() + 1);
                    tablero.getFicha(i, j-1).setColor(color);
                    interfaz.imprimePosicionCubo(i, j-1);
                    fichasDisponibles=fichasDisponibles-1;
                }
                if (this.condiciones.isDiagonalArribaIzquierda() && this.condiciones.isCondArriba() && fichasDisponibles>=1 && tablero.getFicha(i-1, j).getValor()<5) {
                    //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                    tablero.getFicha(i-1, j).setValor(tablero.getFicha(i-1, j).getValor() + 1);
                    tablero.getFicha(i-1, j).setColor(color);
                    interfaz.imprimePosicionCubo(i-1, j);
                    fichasDisponibles=fichasDisponibles-1;
                }
                if (this.condiciones.isDiagonalArribaDerecha() && this.condiciones.isCondArriba() && fichasDisponibles>=1 && tablero.getFicha(i-1, j).getValor()<5) {
                    //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                    tablero.getFicha(i-1, j).setValor(tablero.getFicha(i-1, j).getValor() + 1);
                    tablero.getFicha(i-1, j).setColor(color);
                    interfaz.imprimePosicionCubo(i-1, j);
                    fichasDisponibles=fichasDisponibles-1;
                }
                if (this.condiciones.isDiagonalArribaDerecha() && this.condiciones.isCondDerecha() && fichasDisponibles>=1 && tablero.getFicha(i, j+1).getValor()<5) {
                    //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                    tablero.getFicha(i, j+1).setValor(tablero.getFicha(i, j+1).getValor() + 1);
                    tablero.getFicha(i, j+1).setColor(color);
                    interfaz.imprimePosicionCubo(i, j+1);
                    fichasDisponibles=fichasDisponibles-1;
                }
            }
            if (this.condiciones.isCondExternaTres()) {
                if (this.condiciones.isCondDerecha() && this.condiciones.isCondArriba() && fichasDisponibles>=1 && tablero.getFicha(i, j).getValor()<5) {
                    tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                    interfaz.imprimePosicionCubo(i, j);
                    fichasDisponibles=fichasDisponibles-1;
                }
                if (this.condiciones.isCondDerecha() && this.condiciones.isCondAbajo() && fichasDisponibles>=1 && tablero.getFicha(i, j).getValor()<5) {
                    tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                    interfaz.imprimePosicionCubo(i, j);
                    fichasDisponibles=fichasDisponibles-1;
                }
                if (this.condiciones.isDiagonalArribaDerecha() && this.condiciones.isCondArriba() && fichasDisponibles>=1 && tablero.getFicha(i-1, j).getValor()<5) {
                    //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                    tablero.getFicha(i-1, j).setValor(tablero.getFicha(i-1, j).getValor() + 1);
                    tablero.getFicha(i-1, j).setColor(color);
                    interfaz.imprimePosicionCubo(i-1, j);
                    fichasDisponibles=fichasDisponibles-1;
                }
                if (this.condiciones.isDiagonalArribaDerecha() && this.condiciones.isCondDerecha() && fichasDisponibles>=1 && tablero.getFicha(i, j+1).getValor()<5) {
                    //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                    tablero.getFicha(i, j+1).setValor(tablero.getFicha(i, j+1).getValor() + 1);
                    tablero.getFicha(i, j+1).setColor(color);
                    interfaz.imprimePosicionCubo(i, j+1);
                    fichasDisponibles=fichasDisponibles-1;
                }
                if (this.condiciones.isDiagonalAbajoDerecha() && this.condiciones.isCondDerecha() && fichasDisponibles>=1 && tablero.getFicha(i, j+1).getValor()<5) {
                    //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                    tablero.getFicha(i, j+1).setValor(tablero.getFicha(i, j+1).getValor() + 1);
                    tablero.getFicha(i, j+1).setColor(color);
                    interfaz.imprimePosicionCubo(i, j+1);
                    fichasDisponibles=fichasDisponibles-1;
                }
                if (this.condiciones.isDiagonalAbajoDerecha() && this.condiciones.isCondAbajo() && fichasDisponibles>=1 && tablero.getFicha(i+1, j).getValor()<5) {
                    //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                    tablero.getFicha(i+1, j).setValor(tablero.getFicha(i+1, j).getValor() + 1);
                    tablero.getFicha(i+1, j).setColor(color);
                    interfaz.imprimePosicionCubo(i+1, j);
                    fichasDisponibles=fichasDisponibles-1;
                }
            }
            if (this.condiciones.isCondExternaCuatro()) {
                if (this.condiciones.isCondIzquierda() && this.condiciones.isCondArriba() && fichasDisponibles>=1 && tablero.getFicha(i, j).getValor()<5) {
                    tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                    interfaz.imprimePosicionCubo(i, j);
                    fichasDisponibles=fichasDisponibles-1;
                }
                if (this.condiciones.isCondIzquierda() && this.condiciones.isCondAbajo() && fichasDisponibles>=1 && tablero.getFicha(i, j).getValor()<5) {
                    tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                    interfaz.imprimePosicionCubo(i, j);
                    fichasDisponibles=fichasDisponibles-1;
                }
                if (this.condiciones.isDiagonalArribaIzquierda() && this.condiciones.isCondArriba() && fichasDisponibles>=1 && tablero.getFicha(i-1, j).getValor()<5) {
                    //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                    tablero.getFicha(i-1, j).setValor(tablero.getFicha(i-1, j).getValor() + 1);
                    tablero.getFicha(i-1, j).setColor(color);
                    interfaz.imprimePosicionCubo(i-1, j);
                    fichasDisponibles=fichasDisponibles-1;
                }
                if (this.condiciones.isDiagonalArribaIzquierda() && this.condiciones.isCondIzquierda() && fichasDisponibles>=1 && tablero.getFicha(i, j-1).getValor()<5) {
                    //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                    tablero.getFicha(i, j-1).setValor(tablero.getFicha(i, j-1).getValor() + 1);
                    tablero.getFicha(i, j-1).setColor(color);
                    interfaz.imprimePosicionCubo(i, j-1);
                    fichasDisponibles=fichasDisponibles-1;
                }
                if (this.condiciones.isDiagonalAbajoIzquierda() && this.condiciones.isCondIzquierda() && fichasDisponibles>=1 && tablero.getFicha(i, j-1).getValor()<5) {
                    //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                    tablero.getFicha(i, j-1).setValor(tablero.getFicha(i, j-1).getValor() + 1);
                    tablero.getFicha(i, j-1).setColor(color);
                    interfaz.imprimePosicionCubo(i, j-1);
                    fichasDisponibles=fichasDisponibles-1;
                }
                if (this.condiciones.isDiagonalAbajoIzquierda() && this.condiciones.isCondAbajo() && fichasDisponibles>=1 && tablero.getFicha(i+1, j).getValor()<5) {
                    //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                    tablero.getFicha(i+1, j).setValor(tablero.getFicha(i+1, j).getValor() + 1);
                    tablero.getFicha(i+1, j).setColor(color);
                    interfaz.imprimePosicionCubo(i+1, j);
                    fichasDisponibles=fichasDisponibles-1;
                }
            }
        }
        return fichasDisponibles;
    }

    public int cantidadFichasAPoner(int i, int j, Tablero tablero) { //DEVUELVE LA CANTIDAD
        int fichasAPoner = 0;
        return fichasAPoner;
    } //no se si se va a usar, verificar borrarlo antes de borrar este comentario si no se uso
    
    public int seExtendioEsquina(int i,int j,Tablero tablero,int color,int fichasDisponibles){
        int iAtras=i-1;
        int iAdelante=i+1;
        int jAtras=j-1;
        int jAdelante=j+1;       
        Interfaz interfaz = new Interfaz();
        boolean hayFicha=true;
        boolean tengoQueExtenderHaciaArriba=false;
        boolean tengoQueExtenderHaciaAbajo=false;
        //FOR QUE RECORRE LAS FILAS BUSCANDO ESQUINAS PARA EXTENDER.
        for (int x=i-1;x>=0 && hayFicha;x--){
            if (tablero.getFicha(x, j).getValor()>0){
                hayFicha=true;
                if(x!=i-1){
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
        hayFicha=true;
        tengoQueExtenderHaciaArriba=false;
        tengoQueExtenderHaciaAbajo=false;
        //FOR QUE RECORRE LAS FILAS BUSCANDO ESQUINAS PARA EXTENDER.
        for (int x=i+1;x<=5 && hayFicha;x++){
            if (tablero.getFicha(x, j).getValor()>0){
                hayFicha=true;
                if(x!=i+1){
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
        hayFicha=true;
        tengoQueExtenderHaciaArriba=false;
        tengoQueExtenderHaciaAbajo=false;
        //FOR QUE RECORRE LAS FILAS BUSCANDO ESQUINAS PARA EXTENDER.
        for (int x=j-1;x>=0 && hayFicha;x--){
            if (tablero.getFicha(i, x).getValor()>0){
                hayFicha=true;
                if(x!=j-1){
                    if (i > 0) {
                        if (tablero.getFicha(i-1, x).getValor() > 0) {
                            tengoQueExtenderHaciaArriba = true;
                        }
                    }
                    if (i < 5) {
                        if (tablero.getFicha(i+1, x).getValor() > 0) {
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
        hayFicha=true;
        tengoQueExtenderHaciaArriba=false;
        tengoQueExtenderHaciaAbajo=false;
        //FOR QUE RECORRE LAS FILAS BUSCANDO ESQUINAS PARA EXTENDER.
        for (int x=j+1;x<=5 && hayFicha;x++){
            if (tablero.getFicha(i, x).getValor()>0){
                hayFicha=true;
                if(x!=j+1){
                    if (i > 0) {
                        if (tablero.getFicha(i-1, x).getValor() > 0) {
                            tengoQueExtenderHaciaArriba = true;
                        }
                    }
                    if (i < 5) {
                        if (tablero.getFicha(i+1, x).getValor() > 0) {
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
    
}

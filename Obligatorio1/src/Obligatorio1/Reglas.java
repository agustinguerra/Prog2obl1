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
    }    

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

    public void seFormoEsquina(int i, int j, Tablero tablero, int color) { //CHEQUEA SI SE FORMARON O NO ESQUINAS AL MOMENTO DE PONER LA FICHA
        //PODEMOS HACER ESTO, QUE TE PARECE?
        //SI TE GUSTA PODEMOS HACER LO MISMO EN EL METODO DE ARRIBA
        //HICE LOS METODOS DE ARRIBA QUE FALTABAN, ESTE QUE ARRANCASTE VOS ENCARALO VOS, Y DESPUES LOS DOS QUE QUEDAN VEMOS COMO LO DIVIDIMOS
        this.condiciones.chequeadorCondiciones(i, j, tablero);
        //YA AGREGAMOS LA FICHA VALOR 1 Y COLOR DEL JUGADOR DE TURNO AL TABLERO Y DESP EVALUAMOS
        Interfaz interfaz = new Interfaz();        
        tablero.getFicha(i, j).setValor(1);
        interfaz.imprimePosicionCubo(i, j);
        tablero.getFicha(i, j).setColor(color);
        if (this.condiciones.isCond()) {
            //INTERNO DEL TABLERO
            if (this.condiciones.isCondArriba() && this.condiciones.isCondIzquierda()) {
                tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                interfaz.imprimePosicionCubo(i, j);
            }
            if (this.condiciones.isCondArriba() && this.condiciones.isCondDerecha()) {
                tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                interfaz.imprimePosicionCubo(i, j);
            }
            if (this.condiciones.isCondAbajo() && this.condiciones.isCondIzquierda()) {
                tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                interfaz.imprimePosicionCubo(i, j);
            }
            if (this.condiciones.isCondAbajo() && this.condiciones.isCondDerecha()) {
                tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                interfaz.imprimePosicionCubo(i, j);
            }
            //ACA VAN LOS QUE AGREGUE, HASTA EL ELSE IF POR SI NO ANDA BORRAR ESO
            if (this.condiciones.isCondAbajo() && this.condiciones.isDiagonalAbajoDerecha()) {
                //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                tablero.getFicha(i + 1, j).setValor(tablero.getFicha(i + 1, j).getValor() + 1);
                tablero.getFicha(i + 1, j).setColor(color);
                interfaz.imprimePosicionCubo(i + 1, j);
            }
            if (this.condiciones.isCondDerecha() && this.condiciones.isDiagonalAbajoDerecha()) {
                //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                tablero.getFicha(i, j + 1).setValor(tablero.getFicha(i, j + 1).getValor() + 1);
                tablero.getFicha(i, j + 1).setColor(color);
                interfaz.imprimePosicionCubo(i, j + 1);
            }
            if (this.condiciones.isCondIzquierda() && this.condiciones.isDiagonalAbajoIzquierda()) {
                //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                tablero.getFicha(i, j - 1).setValor(tablero.getFicha(i, j - 1).getValor() + 1);
                tablero.getFicha(i, j - 1).setColor(color);
                interfaz.imprimePosicionCubo(i, j - 1);
            }
            if (this.condiciones.isCondAbajo() && this.condiciones.isDiagonalAbajoIzquierda()) {
                //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                tablero.getFicha(i + 1, j).setValor(tablero.getFicha(i + 1, j).getValor() + 1);
                tablero.getFicha(i + 1, j).setColor(color);
                interfaz.imprimePosicionCubo(i + 1, j);
            }
            if (this.condiciones.isDiagonalArribaDerecha() && this.condiciones.isCondArriba()) {
                //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                tablero.getFicha(i - 1, j).setValor(tablero.getFicha(i - 1, j).getValor() + 1);
                tablero.getFicha(i - 1, j).setColor(color);
                interfaz.imprimePosicionCubo(i - 1, j);
            }
            if (this.condiciones.isCondDerecha() && this.condiciones.isDiagonalArribaDerecha()) {
                //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                tablero.getFicha(i, j + 1).setValor(tablero.getFicha(i, j + 1).getValor() + 1);
                tablero.getFicha(i, j + 1).setColor(color);
                interfaz.imprimePosicionCubo(i, j + 1);
            }
            if (this.condiciones.isCondIzquierda() && this.condiciones.isDiagonalArribaIzquierda()) {
                //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                tablero.getFicha(i, j - 1).setValor(tablero.getFicha(i, j - 1).getValor() + 1);
                tablero.getFicha(i, j - 1).setColor(color);
                interfaz.imprimePosicionCubo(i, j - 1);
            }
            if (this.condiciones.isDiagonalArribaIzquierda() && this.condiciones.isCondArriba()) {
                //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                tablero.getFicha(i - 1, j).setValor(tablero.getFicha(i - 1, j).getValor() + 1);
                tablero.getFicha(i - 1, j).setColor(color);
                interfaz.imprimePosicionCubo(i - 1, j);
            }
            
        } else if (!this.condiciones.isCond()) {
            //EXTERNO DEL TABLERO
            switch (i) {
                case 0:
                    switch (j) {
                        case 0:
                            if (this.condiciones.isCondDerecha() && this.condiciones.isCondAbajo()) {
                                //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                                tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                                interfaz.imprimePosicionCubo(i, j);
                            }
                            if (this.condiciones.isCondDerecha() && this.condiciones.isDiagonalAbajoDerecha()) {
                                //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                                tablero.getFicha(i, j+1).setValor(tablero.getFicha(i, j+1).getValor() + 1);
                                tablero.getFicha(i, j+1).setColor(color);
                                interfaz.imprimePosicionCubo(i, j+1);
                            }  
                            if (this.condiciones.isCondAbajo() && this.condiciones.isDiagonalAbajoDerecha()) {
                                //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                                tablero.getFicha(i+1, j).setValor(tablero.getFicha(i+1, j).getValor() + 1);
                                tablero.getFicha(i+1, j).setColor(color);
                                interfaz.imprimePosicionCubo(i+1, j);
                            }                            
                            break;
                        case 5:
                            if (this.condiciones.isCondIzquierda() && this.condiciones.isCondAbajo()) {
                                //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                                tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                                interfaz.imprimePosicionCubo(i, j);
                            }
                            if (this.condiciones.isCondIzquierda() && this.condiciones.isDiagonalAbajoIzquierda()) {
                                //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                                tablero.getFicha(i, j-1).setValor(tablero.getFicha(i, j-1).getValor() + 1);
                                tablero.getFicha(i, j-1).setColor(color);
                                interfaz.imprimePosicionCubo(i, j-1);
                            }
                            if (this.condiciones.isCondAbajo() && this.condiciones.isDiagonalAbajoIzquierda()) {
                                //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                                tablero.getFicha(i+1, j).setValor(tablero.getFicha(i+1, j).getValor() + 1);
                                tablero.getFicha(i+1, j).setColor(color);
                                interfaz.imprimePosicionCubo(i+1, j);
                            }                            
                            break;
                    }
                    break;
                case 5:
                    switch (j) {
                        case 0:
                            if (this.condiciones.isCondDerecha() && this.condiciones.isCondArriba()) {
                                //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                                tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                                interfaz.imprimePosicionCubo(i, j);
                            }
                            if (this.condiciones.isDiagonalArribaDerecha() && this.condiciones.isCondArriba()) {
                                //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                                tablero.getFicha(i-1, j).setValor(tablero.getFicha(i-1, j).getValor() + 1);
                                tablero.getFicha(i-1, j).setColor(color);
                                interfaz.imprimePosicionCubo(i-1, j);
                            }
                            if (this.condiciones.isCondDerecha() && this.condiciones.isDiagonalArribaDerecha()) {
                                //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                                tablero.getFicha(i, j+1).setValor(tablero.getFicha(i, j+1).getValor() + 1);
                                tablero.getFicha(i, j+1).setColor(color);
                                interfaz.imprimePosicionCubo(i, j+1);
                            }                            
                            break;
                        case 5:
                            if (this.condiciones.isCondIzquierda() && this.condiciones.isCondArriba()) {
                                //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                                tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                                interfaz.imprimePosicionCubo(i, j);
                            }
                            if (this.condiciones.isCondIzquierda() && this.condiciones.isDiagonalArribaIzquierda()) {
                                //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                                tablero.getFicha(i, j-1).setValor(tablero.getFicha(i, j-1).getValor() + 1);
                                tablero.getFicha(i, j-1).setColor(color);
                                interfaz.imprimePosicionCubo(i, j-1);
                            }
                            if (this.condiciones.isDiagonalArribaIzquierda() && this.condiciones.isCondArriba()) {
                                //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                                tablero.getFicha(i-1, j).setValor(tablero.getFicha(i-1, j).getValor() + 1);
                                tablero.getFicha(i-1, j).setColor(color);
                                interfaz.imprimePosicionCubo(i-1, j);
                            }                            
                            break;
                    }
                    break;
            }

            if (this.condiciones.isCondExternaUno()) {
                if (this.condiciones.isCondIzquierda() && this.condiciones.isCondAbajo()) {
                    tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                    interfaz.imprimePosicionCubo(i, j);
                }
                if (this.condiciones.isCondDerecha() && this.condiciones.isCondAbajo()) {
                    tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                    interfaz.imprimePosicionCubo(i, j);
                }
                if (this.condiciones.isDiagonalAbajoIzquierda() && this.condiciones.isCondIzquierda()) {
                    //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                    tablero.getFicha(i, j-1).setValor(tablero.getFicha(i, j-1).getValor() + 1);
                    tablero.getFicha(i, j-1).setColor(color);
                    interfaz.imprimePosicionCubo(i, j-1);
                }
                if (this.condiciones.isDiagonalAbajoIzquierda() && this.condiciones.isCondAbajo()) {
                    //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                    tablero.getFicha(i+1, j-1).setValor(tablero.getFicha(i+1, j-1).getValor() + 1);
                    tablero.getFicha(i+1, j-1).setColor(color);
                    interfaz.imprimePosicionCubo(i+1, j-1);
                }
                if (this.condiciones.isDiagonalAbajoDerecha() && this.condiciones.isCondAbajo()) {
                    //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                    tablero.getFicha(i+1, j).setValor(tablero.getFicha(i+1, j).getValor() + 1);
                    tablero.getFicha(i+1, j).setColor(color);
                    interfaz.imprimePosicionCubo(i+1, j);
                }
                if (this.condiciones.isDiagonalAbajoDerecha() && this.condiciones.isCondDerecha()) {
                    //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                    tablero.getFicha(i, j+1).setValor(tablero.getFicha(i, j+1).getValor() + 1);
                    tablero.getFicha(i, j+1).setColor(color);
                    interfaz.imprimePosicionCubo(i, j+1);
                }
                
            }
            if (this.condiciones.isCondExternaDos()) {
                if (this.condiciones.isCondDerecha() && this.condiciones.isCondArriba()) {
                    tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                    interfaz.imprimePosicionCubo(i, j);
                }
                if (this.condiciones.isCondIzquierda() && this.condiciones.isCondArriba()) {
                    tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                    interfaz.imprimePosicionCubo(i, j);
                }
                if (this.condiciones.isDiagonalArribaIzquierda() && this.condiciones.isCondIzquierda()) {
                    //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                    tablero.getFicha(i, j-1).setValor(tablero.getFicha(i, j-1).getValor() + 1);
                    tablero.getFicha(i, j-1).setColor(color);
                    interfaz.imprimePosicionCubo(i, j-1);
                }
                if (this.condiciones.isDiagonalArribaIzquierda() && this.condiciones.isCondArriba()) {
                    //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                    tablero.getFicha(i-1, j).setValor(tablero.getFicha(i-1, j).getValor() + 1);
                    tablero.getFicha(i-1, j).setColor(color);
                    interfaz.imprimePosicionCubo(i-1, j);
                }
                if (this.condiciones.isDiagonalArribaDerecha() && this.condiciones.isCondArriba()) {
                    //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                    tablero.getFicha(i-1, j).setValor(tablero.getFicha(i-1, j).getValor() + 1);
                    tablero.getFicha(i-1, j).setColor(color);
                    interfaz.imprimePosicionCubo(i-1, j);
                }
                if (this.condiciones.isDiagonalArribaDerecha() && this.condiciones.isCondDerecha()) {
                    //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                    tablero.getFicha(i, j+1).setValor(tablero.getFicha(i, j+1).getValor() + 1);
                    tablero.getFicha(i, j+1).setColor(color);
                    interfaz.imprimePosicionCubo(i, j+1);
                }
            }
            if (this.condiciones.isCondExternaTres()) {
                if (this.condiciones.isCondDerecha() && this.condiciones.isCondArriba()) {
                    tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                    interfaz.imprimePosicionCubo(i, j);
                }
                if (this.condiciones.isCondDerecha() && this.condiciones.isCondAbajo()) {
                    tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                    interfaz.imprimePosicionCubo(i, j);
                }
                if (this.condiciones.isDiagonalArribaDerecha() && this.condiciones.isCondArriba()) {
                    //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                    tablero.getFicha(i-1, j).setValor(tablero.getFicha(i-1, j).getValor() + 1);
                    tablero.getFicha(i-1, j).setColor(color);
                    interfaz.imprimePosicionCubo(i-1, j);
                }
                if (this.condiciones.isDiagonalArribaDerecha() && this.condiciones.isCondDerecha()) {
                    //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                    tablero.getFicha(i, j+1).setValor(tablero.getFicha(i, j+1).getValor() + 1);
                    tablero.getFicha(i, j+1).setColor(color);
                    interfaz.imprimePosicionCubo(i, j+1);
                }
                if (this.condiciones.isDiagonalAbajoDerecha() && this.condiciones.isCondDerecha()) {
                    //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                    tablero.getFicha(i, j+1).setValor(tablero.getFicha(i, j+1).getValor() + 1);
                    tablero.getFicha(i, j+1).setColor(color);
                    interfaz.imprimePosicionCubo(i, j+1);
                }
                if (this.condiciones.isDiagonalAbajoDerecha() && this.condiciones.isCondAbajo()) {
                    //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                    tablero.getFicha(i+1, j).setValor(tablero.getFicha(i+1, j).getValor() + 1);
                    tablero.getFicha(i+1, j).setColor(color);
                    interfaz.imprimePosicionCubo(i+1, j);
                }
            }
            if (this.condiciones.isCondExternaCuatro()) {
                if (this.condiciones.isCondIzquierda() && this.condiciones.isCondArriba()) {
                    tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                    interfaz.imprimePosicionCubo(i, j);
                }
                if (this.condiciones.isCondIzquierda() && this.condiciones.isCondAbajo()) {
                    tablero.getFicha(i, j).setValor(tablero.getFicha(i, j).getValor() + 1);
                    interfaz.imprimePosicionCubo(i, j);
                }
                if (this.condiciones.isDiagonalArribaIzquierda() && this.condiciones.isCondArriba()) {
                    //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                    tablero.getFicha(i-1, j).setValor(tablero.getFicha(i-1, j).getValor() + 1);
                    tablero.getFicha(i-1, j).setColor(color);
                    interfaz.imprimePosicionCubo(i-1, j);
                }
                if (this.condiciones.isDiagonalArribaIzquierda() && this.condiciones.isCondIzquierda()) {
                    //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                    tablero.getFicha(i, j-1).setValor(tablero.getFicha(i, j-1).getValor() + 1);
                    tablero.getFicha(i, j-1).setColor(color);
                    interfaz.imprimePosicionCubo(i, j-1);
                }
                if (this.condiciones.isDiagonalAbajoIzquierda() && this.condiciones.isCondIzquierda()) {
                    //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                    tablero.getFicha(i, j-1).setValor(tablero.getFicha(i, j-1).getValor() + 1);
                    tablero.getFicha(i, j-1).setColor(color);
                    interfaz.imprimePosicionCubo(i, j-1);
                }
                if (this.condiciones.isDiagonalAbajoIzquierda() && this.condiciones.isCondAbajo()) {
                    //TARIA BUENO UN METODO PROPIO DE LA CLASE QUE SEA INCREMENTAR POR ESQUINA (CLASE TABLERO)
                    tablero.getFicha(i+1, j).setValor(tablero.getFicha(i+1, j).getValor() + 1);
                    tablero.getFicha(i+1, j).setColor(color);
                    interfaz.imprimePosicionCubo(i+1, j);
                }
            }
        }
    }

    public int cantidadFichasAPoner(int i, int j, Tablero tablero) { //DEVUELVE LA CANTIDAD
        int fichasAPoner = 0;
        return fichasAPoner;
    }
}

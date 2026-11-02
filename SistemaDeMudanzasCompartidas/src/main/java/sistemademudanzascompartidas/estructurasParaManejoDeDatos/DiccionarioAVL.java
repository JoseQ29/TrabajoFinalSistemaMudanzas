/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemademudanzascompartidas.estructurasParaManejoDeDatos;

/**
 *
 * @author Razor-PC V.3
 */
public class DiccionarioAVL {
    
    private NodoAVLDicc raiz;

    public DiccionarioAVL(NodoAVLDicc raiz) {
        this.raiz = raiz;
    }
    
     public boolean insertar(Comparable clave, Object dato) {
        boolean verificado = true;
        if (this.raiz == null) {
            this.raiz = new NodoAVLDicc(clave,dato ,null, null);
        } else {
            verificado = insertarAux(this.raiz, clave, dato);
        }
        return verificado;
    }

    private boolean insertarAux(NodoAVLDicc nodo, Comparable clave, Object dato) {
        //inserta el elemento en el arbol AVL
        boolean salida = true;
        
        if (clave.compareTo(nodo.getClave()) == 0 ) {
            salida = false;
        } else if (clave.compareTo(nodo.getClave()) < 0) {
            if (nodo.getIzquierdo() != null) {
                salida = insertarAux(nodo.getIzquierdo(), clave, dato);
                if (nodo.getIzquierdo() != this.raiz) {
                    nodo.setIzquierdo(this.raiz);
                }
            } else {
                nodo.setIzquierdo(new NodoAVLDicc(clave,dato, null, null));
            }
        } else {
            if (nodo.getDerecho() != null) {
                salida = insertarAux(nodo.getDerecho(), clave, dato);
                if (nodo.getDerecho() != this.raiz) {
                    nodo.setDerecho(this.raiz);
                }
            } else {
                nodo.setDerecho(new NodoAVLDicc(clave,dato, null, null));
            }
        }
        //realiza las rotaciones correspondientes
        if (salida) {
            nodo.recalcularAltura();
            if (calcularBalance(nodo) == 2) {
                if (calcularBalance(nodo.getIzquierdo()) != (-1)) {

                    nodo = rotacionDerecha(nodo);
                } else {
                    nodo = rotacionDobleIzquierda(nodo);
                }
            } else if (calcularBalance(nodo) == (-2)) {
                if (calcularBalance(nodo.getDerecho()) != 1) {
                    nodo = rotacionIzquierda(nodo);
                } else {
                    nodo = rotacionDobleDerecha(nodo);
                }
            }
        }
        this.raiz = nodo;
        return salida;
    }

    private NodoAVLDicc rotacionDobleIzquierda(NodoAVLDicc n) {
        NodoAVLDicc nodo;
        NodoAVLDicc nodo2 = rotacionIzquierda(n.getIzquierdo());
        n.setIzquierdo(nodo2);
        nodo = rotacionDerecha(n);
        return nodo;
    }

    private NodoAVLDicc rotacionDobleDerecha(NodoAVLDicc n) {
        NodoAVLDicc nodo;
        NodoAVLDicc nodo2 = rotacionDerecha(n.getDerecho());
        n.setDerecho(nodo2);
        nodo = rotacionIzquierda(n);
        return nodo;
    }

    private NodoAVLDicc rotacionIzquierda(NodoAVLDicc n) {
        NodoAVLDicc hijoDerecho = n.getDerecho();
        NodoAVLDicc temp = hijoDerecho.getIzquierdo();
        hijoDerecho.setIzquierdo(n);
        n.setDerecho(temp);
        n.recalcularAltura();
        hijoDerecho.recalcularAltura();
        return hijoDerecho;
    }

    private NodoAVLDicc rotacionDerecha(NodoAVLDicc n) {
        NodoAVLDicc hijoIzquierdo = n.getIzquierdo();
        NodoAVLDicc temp = hijoIzquierdo.getDerecho();
        hijoIzquierdo.setDerecho(n);
        n.setIzquierdo(temp);
        n.recalcularAltura();
        hijoIzquierdo.recalcularAltura();
        return hijoIzquierdo;
    }

    private int calcularBalance(NodoAVLDicc n) {
        int salida = 0;
        if (n.getIzquierdo() != null && n.getDerecho() != null) {
            salida = n.getIzquierdo().getAltura() - n.getDerecho().getAltura();
        } else if (n.getDerecho() != null && n.getIzquierdo() == null) {
            salida = (-1) - n.getDerecho().getAltura();
        } else if (n.getIzquierdo() != null && n.getDerecho() == null) {
            salida = n.getIzquierdo().getAltura() - (-1);
        }
        return salida;
    }
    
    public void vaciar(){
        this.raiz = null;
    }
    
    private NodoAVLDicc getValorMaximo(NodoAVLDicc nodo) {
        NodoAVLDicc nodoActual = nodo;

        while (nodoActual.getDerecho() != null) {
            nodoActual = nodoActual.getDerecho();
        }
        return nodoActual;
    }

    public boolean eliminar(Comparable clave) {
        boolean verificado = false;
        if (this.raiz != null) {
            verificado = eliminarAux(this.raiz, clave, null);
        }
        return verificado;
    }

    private boolean eliminarAux(NodoAVLDicc nodo, Comparable clave, NodoAVLDicc padre) {
    
        boolean salida = false;
        if (nodo != null) {
            if (clave.compareTo(nodo.getClave()) == 0) {
                salida = true;
                if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
                    if (padre == null) {
                        this.raiz = null;
                    } else if (padre.getIzquierdo() == nodo) {
                        padre.setIzquierdo(null);
                    } else {
                        padre.setDerecho(null);
                    }
                } else if (nodo.getIzquierdo() == null || nodo.getDerecho() == null) {
                    NodoAVLDicc nuevoNodo = (nodo.getIzquierdo() != null) ? nodo.getIzquierdo() : nodo.getDerecho();

                    if (padre == null) {
                        this.raiz = nuevoNodo;
                    } else if (padre.getIzquierdo() == nodo) {
                        padre.setIzquierdo(nuevoNodo);
                    } else {
                        padre.setDerecho(nuevoNodo);
                    }
                } else {
                    NodoAVLDicc temp = getValorMaximo(nodo.getIzquierdo());
                    nodo.setClave(temp.getClave());
                    salida = eliminarAux(nodo.getIzquierdo(), temp.getClave(), nodo);
                }
            } else if (clave.compareTo(nodo.getClave()) < 0) {
                salida = eliminarAux(nodo.getIzquierdo(), clave, nodo);
            } else {
                salida = eliminarAux(nodo.getDerecho(), clave, nodo);
            }

            if (salida) {
                nodo.recalcularAltura();
            int balance = calcularBalance(nodo);

            if (balance > 1 && clave.compareTo(nodo.getIzquierdo().getClave()) < 0) {
                nodo = rotacionDerecha(nodo);
                if (padre == null) {
                    this.raiz = nodo;
                } else if (padre.getIzquierdo() == nodo) {
                    padre.setIzquierdo(nodo);
                } else {
                    padre.setDerecho(nodo);
                }
            } else if (balance < -1 && clave.compareTo(nodo.getDerecho().getClave()) > 0) {
                nodo = rotacionIzquierda(nodo);
                if (padre == null) {
                    this.raiz = nodo;
                } else if (padre.getIzquierdo() == nodo) {
                    padre.setIzquierdo(nodo);
                } else {
                    padre.setDerecho(nodo);
                }
            } else if (balance > 1 && clave.compareTo(nodo.getIzquierdo().getClave()) > 0) {
                nodo.setIzquierdo(rotacionIzquierda(nodo.getIzquierdo()));
                nodo = rotacionDerecha(nodo);
                if (padre == null) {
                    this.raiz = nodo;
                } else if (padre.getIzquierdo() == nodo) {
                    padre.setIzquierdo(nodo);
                } else {
                    padre.setDerecho(nodo);
                }
            } else if (balance < -1 && clave.compareTo(nodo.getDerecho().getClave()) < 0) {
                nodo.setDerecho(rotacionDerecha(nodo.getDerecho()));
                nodo = rotacionIzquierda(nodo);
                if (padre == null) {
                    this.raiz = nodo;
                } else if (padre.getIzquierdo() == nodo) {
                    padre.setIzquierdo(nodo);
                } else {
                    padre.setDerecho(nodo);
                }
            }
        }
    }

    return salida;
}


    
    public Object obtenerDato(Comparable clave){
        //obtiene el dato correspondiente a la clave ingresada
        Object dato = null;
        if (this.raiz != null) {
            dato = obtenerDatoAux(this.raiz, clave);
        }
        return dato;
    }

    private Object obtenerDatoAux(NodoAVLDicc n, Comparable clave) {
        //Busca la clave en el AVL y devuelve el dato correspondiente
        Object dato = null;

        if (clave.compareTo(n.getClave()) == 0) {
            dato = n.getDato();
        } else if (clave.compareTo(n.getClave()) < 0) {

            if (n.getIzquierdo() != null) {
                dato = obtenerDatoAux(n.getIzquierdo(), clave);
            }
        } else if (n.getDerecho() != null) {
            dato = obtenerDatoAux(n.getDerecho(), clave);
        }
        return dato;
    }
    
    public boolean existeClave(Comparable clave) {
        //devuelve verdadero si el elemento recibido pertenece al arbol
        boolean pertenece = false;
        if (this.raiz != null) {
            pertenece = existeClaveAux(this.raiz, clave);
        }
        return pertenece;
    }
    public Lista listarRango(Comparable min, Comparable max) {
        //Realiza el listado en inorden de los elementos del arbol que pertenezcan al intervalo cerrado ingresado
        Lista lista = new Lista();
        if (this.raiz != null) {
            listarRangoAux(min, max, this.raiz, lista);
        }
        return lista;
    }

    private void listarRangoAux(Comparable min, Comparable max, NodoAVLDicc n, Lista lista) {

        if (n.getClave().compareTo(min) >= 0 && n.getIzquierdo() != null) {
            listarRangoAux(min, max, n.getIzquierdo(), lista);
        }
        if (n.getClave().compareTo(min) >= 0 && n.getClave().compareTo(max) <= 0) {
            lista.insertar(n.getClave(), lista.longitud() + 1);
        }
        if (n.getClave().compareTo(max) <= 0 && n.getDerecho() != null) {
            listarRangoAux(min, max, n.getDerecho(), lista);
        }
    }

    private boolean existeClaveAux(NodoAVLDicc n, Comparable clave) {
        //Busca el elemento en el AVL
        boolean encontrado = false;

        if (clave.compareTo(n.getClave()) == 0) {
            encontrado = true;
        } else if (clave.compareTo(n.getClave()) < 0) {

            if (n.getIzquierdo() != null) {
                encontrado = existeClaveAux(n.getIzquierdo(), clave);
            }
        } else if (n.getDerecho() != null) {
            encontrado = existeClaveAux(n.getDerecho(), clave);
        }
        return encontrado;
    }
    
     public String toString() {
        String cadena = "[";
        if (this.raiz != null) {
            cadena = toStringAux(this.raiz, cadena);
        }
        cadena += "]";
        return cadena;
    }

    private String toStringAux(NodoAVLDicc n, String cadena) {
        if (n != null) {
            cadena += "numero postal:" + n.getClave().toString();
            cadena +=" altura: " + n.getAltura();
            cadena += " HI: ";
            if (n.getIzquierdo() != null) {
                cadena += n.getIzquierdo().getClave().toString();
            } else {
                cadena += "-";
            }
            cadena += " HD: ";
            if (n.getDerecho() != null) {
                cadena += n.getDerecho().getClave().toString();
            } else {
                cadena += "-";
            }
            
            cadena += "\n";
            if (n.getIzquierdo() != null) {
                cadena = toStringAux(n.getIzquierdo(), cadena);
            }
            if (n.getDerecho() != null) {
                cadena = toStringAux(n.getDerecho(), cadena);
            }
            
        }
        return cadena;
    }
    
}

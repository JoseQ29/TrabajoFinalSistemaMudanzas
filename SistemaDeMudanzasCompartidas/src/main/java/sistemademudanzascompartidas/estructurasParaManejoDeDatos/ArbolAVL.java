/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemademudanzascompartidas.estructurasParaManejoDeDatos;

import sistemademudanzascompartidas.estructurasParaManejoDeDatos.Lista;
import sistemademudanzascompartidas.estructurasParaManejoDeDatos.NodoAVL;

/**
 *
 * @author Razor-PC V.3
 */
public class ArbolAVL {

    private NodoAVL raiz;

    public ArbolAVL(NodoAVL raiz) {
        this.raiz = raiz;
    }

    public boolean pertenece(Comparable elemento) {
        //devuelve verdadero si el elemento recibido pertenece al arbol
        boolean pertenece = false;
        if (this.raiz != null) {
            pertenece = perteneceAux(this.raiz, elemento);
        }
        return pertenece;
    }

    private boolean perteneceAux(NodoAVL n, Comparable elemento) {
        //Busca el elemento en el AVL
        boolean encontrado = false;

        if (elemento.compareTo(n.getElem()) == 0) {
            encontrado = true;
        } else if (elemento.compareTo(n.getElem()) < 0) {

            if (n.getIzquierdo() != null) {
                encontrado = perteneceAux(n.getIzquierdo(), elemento);
            }
        } else if (n.getDerecho() != null) {
            encontrado = perteneceAux(n.getDerecho(), elemento);
        }
        return encontrado;
    }

    public boolean esVacio() {
        //retorna true si es vacio el arbol
        return (this.raiz == null);
    }

    public Lista listar() {
        //Lista en inorden y devuelve esa lista
        Lista lista = new Lista();
        if (this.raiz != null) {
            listarAux(this.raiz, lista);
        }
        return lista;
    }

    private void listarAux(NodoAVL n, Lista lista) {
        //Realiza el listado en inorden
        if (n != null) {
            if (n.getIzquierdo() != null) {
                listarAux(n.getIzquierdo(), lista);
            }

            lista.insertar(n.getElem(), lista.longitud() + 1);

            if (n.getDerecho() != null) {
                listarAux(n.getDerecho(), lista);
            }
        }
    }

    public Lista listarRango(Comparable min, Comparable max) {
        //Realiza el listado en inorden de los elementos del arbol que pertenezcan al intervalo cerrado ingresado
        Lista lista = new Lista();
        if (this.raiz != null) {
            listarRangoAux(min, max, this.raiz, lista);
        }
        return lista;
    }

    private void listarRangoAux(Comparable min, Comparable max, NodoAVL n, Lista lista) {

        if (n.getElem().compareTo(min) >= 0 && n.getIzquierdo() != null) {
            listarRangoAux(min, max, n.getIzquierdo(), lista);
        }
        if (n.getElem().compareTo(min) >= 0 && n.getElem().compareTo(max) <= 0) {
            lista.insertar(n.getElem(), lista.longitud() + 1);
        }
        if (n.getElem().compareTo(max) <= 0 && n.getDerecho() != null) {
            listarRangoAux(min, max, n.getDerecho(), lista);
        }
    }

    public Comparable minimoElem() {
        //retorna el minimo elemento
        Comparable elemento = null;
        if (this.raiz != null) {
            elemento = minimoElemAux(this.raiz);
        }
        return elemento;
    }

    private Comparable minimoElemAux(NodoAVL n) {
        //Encuentra el menor elemento del arbol y lo devuelve
        Comparable elemento = null;
        if (n.getIzquierdo() != null) {
            elemento = minimoElemAux(n.getIzquierdo());
        } else {
            elemento = n.getElem();
        }
        return elemento;
    }

    public Comparable maximoElem() {
        //retorna el maximo elemento 
        Comparable elemento = null;
        if (this.raiz != null) {
            elemento = maximoElemAux(this.raiz);
        }
        return elemento;
    }

    private Comparable maximoElemAux(NodoAVL n) {
        //Encuentra el maximo elemento del arbol y lo devuelve
        Comparable elemento;
        if (n.getDerecho() != null) {
            elemento = maximoElemAux(n.getDerecho());
        } else {
            elemento = n.getElem();
        }
        return elemento;
    }

    private NodoAVL getValorMaximo(NodoAVL nodo) {
        NodoAVL nodoActual = nodo;

        while (nodoActual.getDerecho() != null) {
            nodoActual = nodoActual.getDerecho();
        }
        return nodoActual;
    }

    public boolean eliminar(Comparable elemento) {
        boolean verificado = false;
        if (this.raiz != null) {
            verificado = eliminarAux(this.raiz, elemento, null);
        }
        return verificado;
    }

    private boolean eliminarAux(NodoAVL nodo, Comparable elemento, NodoAVL padre) {
        //Elimina el nodo del arbol AVL
        boolean salida = false;

        if (elemento.compareTo(nodo.getElem()) == 0) {
            salida = true;
            //determina cuantos hijos tiene
            if (nodo.getIzquierdo() == null || nodo.getDerecho() == null) {
                NodoAVL temp = null;
                if (temp == nodo.getIzquierdo() && temp == nodo.getDerecho()) {
                    if (padre.getIzquierdo() != null) {
                        if (padre.getIzquierdo().getElem().compareTo(elemento) == 0) {
                            padre.setIzquierdo(null);
                        }
                    }else{
                        padre.setDerecho(null);
                    }
                } else if (temp == nodo.getIzquierdo()) {
                    temp = nodo.getDerecho();
                    padre.setDerecho(temp);
                } else {
                    temp = nodo.getIzquierdo();
                    padre.setIzquierdo(temp);
                }
            } else {
                //nodo con dos hijos
                NodoAVL temp = getValorMaximo(nodo.getIzquierdo());
                nodo.setElemento(temp.getElem());
                salida = eliminarAux(nodo.getIzquierdo(), temp.getElem(), nodo);
            }

        } else if (elemento.compareTo(nodo.getElem()) < 0) {
            if (nodo.getIzquierdo() != null) {
                salida = eliminarAux(nodo.getIzquierdo(), elemento, nodo);
            }
        } else {
            if (nodo.getDerecho() != null) {
                salida = eliminarAux(nodo.getDerecho(), elemento, nodo);
            }
        }

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

    public boolean insertar(Comparable elemento) {
        boolean verificado = true;
        if (this.raiz == null) {
            this.raiz = new NodoAVL(elemento, null, null);
        } else {
            verificado = insertarAux(this.raiz, elemento);
        }
        return verificado;
    }

    private boolean insertarAux(NodoAVL nodo, Comparable elemento) {
        //inserta el elemento en el arbol AVL
        boolean salida = true;
        
        if (elemento.compareTo(nodo.getElem()) == 0) {
            salida = false;
        } else if (elemento.compareTo(nodo.getElem()) < 0) {
            if (nodo.getIzquierdo() != null) {
                salida = insertarAux(nodo.getIzquierdo(), elemento);
                if (nodo.getIzquierdo() != this.raiz) {
                    nodo.setIzquierdo(this.raiz);
                }
            } else {
                nodo.setIzquierdo(new NodoAVL(elemento, null, null));
            }
        } else {
            if (nodo.getDerecho() != null) {
                salida = insertarAux(nodo.getDerecho(), elemento);
                if (nodo.getDerecho() != this.raiz) {
                    nodo.setDerecho(this.raiz);
                }
            } else {
                nodo.setDerecho(new NodoAVL(elemento, null, null));
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

    private NodoAVL rotacionDobleIzquierda(NodoAVL n) {
        NodoAVL nodo;
        n.setIzquierdo(rotacionIzquierda(n.getIzquierdo()));
        nodo = rotacionDerecha(n);
        return nodo;
    }

    private NodoAVL rotacionDobleDerecha(NodoAVL n) {
        NodoAVL nodo;
        n.setDerecho(rotacionDerecha(n.getDerecho()));
        nodo = rotacionIzquierda(n);
        return nodo;
    }

    private NodoAVL rotacionIzquierda(NodoAVL n) {
        NodoAVL hijoDerecho = n.getDerecho();
        NodoAVL temp = hijoDerecho.getIzquierdo();
        hijoDerecho.setIzquierdo(n);
        n.setDerecho(temp);
        n.recalcularAltura();
        hijoDerecho.recalcularAltura();
        return hijoDerecho;
    }

    private NodoAVL rotacionDerecha(NodoAVL n) {
        NodoAVL hijoIzquierdo = n.getIzquierdo();
        NodoAVL temp = hijoIzquierdo.getDerecho();
        hijoIzquierdo.setDerecho(n);
        n.setIzquierdo(temp);
        n.recalcularAltura();
        hijoIzquierdo.recalcularAltura();
        return hijoIzquierdo;
    }

    private int calcularBalance(NodoAVL n) {
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

    public String toString() {
        String cadena = "[";
        if (this.raiz != null) {
            cadena = toStringAux(this.raiz, cadena);
        }
        cadena += "]";
        return cadena;
    }

    private String toStringAux(NodoAVL n, String cadena) {
        if (n != null) {
            cadena += n.getElem().toString();
            cadena +="altura: " + n.getAltura();
            cadena += " HI: ";
            if (n.getIzquierdo() != null) {
                cadena += n.getIzquierdo().getElem().toString();
            } else {
                cadena += "-";
            }
            cadena += " HD: ";
            if (n.getDerecho() != null) {
                cadena += n.getDerecho().getElem().toString();
            } else {
                cadena += "-";
            }
            cadena += " | ";
            if (n.getIzquierdo() != null) {
                cadena = toStringAux(n.getIzquierdo(), cadena);
            }
            if (n.getDerecho() != null) {
                cadena = toStringAux(n.getDerecho(), cadena);
            }

        }
        return cadena;
    }
    public void vaciar(){
        this.raiz = null;
    }
}

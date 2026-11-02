/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemademudanzascompartidas.estructurasParaManejoDeDatos;
import sistemademudanzascompartidas.*;
/**
 *
 * @author Razor-PC V.3
 */
public class MapeoAMuchos {

    private NodoAVLMapeoM raiz;

    public MapeoAMuchos(NodoAVLMapeoM raiz) {
        this.raiz = raiz;
    }

    public boolean asociar(Comparable dominio, Object rango) {
        //encuentra el valor "dominio" en el avl y asocia rango a su lista de los mismos

        boolean verificado = true;
        if (this.raiz == null) {
            this.raiz = new NodoAVLMapeoM(dominio, rango, null, null);
        } else {
            verificado = asociarAux(this.raiz, dominio, rango);
        }
        return verificado;
    }

    private boolean asociarAux(NodoAVLMapeoM nodo, Comparable dominio, Object rango) {
        //inserta el elemento en el arbol AVL
        boolean salida = true;

        if (dominio.compareTo(nodo.getDominio()) == 0) {
            nodo.setRango(rango);
        } else if (dominio.compareTo(nodo.getDominio()) < 0) {
            if (nodo.getIzquierdo() != null) {
                salida = asociarAux(nodo.getIzquierdo(), dominio, rango);
                if (nodo.getIzquierdo() != this.raiz) {
                    nodo.setIzquierdo(this.raiz);
                }
            } else {
                nodo.setIzquierdo(new NodoAVLMapeoM(dominio, rango, null, null));
            }
        } else {
            if (nodo.getDerecho() != null) {
                salida = asociarAux(nodo.getDerecho(), dominio, rango);
                if (nodo.getDerecho() != this.raiz) {
                    nodo.setDerecho(this.raiz);
                }
            } else {
                nodo.setDerecho(new NodoAVLMapeoM(dominio, rango, null, null));
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

    private NodoAVLMapeoM rotacionDobleIzquierda(NodoAVLMapeoM n) {
        NodoAVLMapeoM nodo;
        n.setIzquierdo(rotacionIzquierda(n.getIzquierdo()));
        nodo = rotacionDerecha(n);
        return nodo;
    }

    private NodoAVLMapeoM rotacionDobleDerecha(NodoAVLMapeoM n) {
        NodoAVLMapeoM nodo;
        n.setDerecho(rotacionDerecha(n.getDerecho()));
        nodo = rotacionIzquierda(n);
        return nodo;
    }

    private NodoAVLMapeoM rotacionIzquierda(NodoAVLMapeoM n) {
        NodoAVLMapeoM hijoDerecho = n.getDerecho();
        NodoAVLMapeoM temp = hijoDerecho.getIzquierdo();
        hijoDerecho.setIzquierdo(n);
        n.setDerecho(temp);
        n.recalcularAltura();
        hijoDerecho.recalcularAltura();
        return hijoDerecho;
    }

    private NodoAVLMapeoM rotacionDerecha(NodoAVLMapeoM n) {
        NodoAVLMapeoM hijoIzquierdo = n.getIzquierdo();
        NodoAVLMapeoM temp = hijoIzquierdo.getDerecho();
        hijoIzquierdo.setDerecho(n);
        n.setIzquierdo(temp);
        n.recalcularAltura();
        hijoIzquierdo.recalcularAltura();
        return hijoIzquierdo;
    }

    
    private int calcularBalance(NodoAVLMapeoM n) {
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

    public void vaciar() {
        this.raiz = null;
    }

    private NodoAVLMapeoM getValorMaximo(NodoAVLMapeoM nodo) {
        NodoAVLMapeoM nodoActual = nodo;

        while (nodoActual.getDerecho() != null) {
            nodoActual = nodoActual.getDerecho();
        }
        return nodoActual;
    }
    
    public Lista obtenerValores(Comparable dominio){
        Lista lista = new Lista();
        if(this.raiz != null){
            lista = obtenerValoresAux(this.raiz, dominio);
        }
        return lista;
    }
    
    private Lista obtenerValoresAux(NodoAVLMapeoM nodo, Comparable dominio){
        Lista lista = null;
        if (dominio.compareTo(nodo.getDominio()) == 0) {
            lista = nodo.obtenerValores();
        } else if (dominio.compareTo(nodo.getDominio()) < 0) {

            if (nodo.getIzquierdo() != null) {
                lista = obtenerValoresAux(nodo.getIzquierdo(), dominio);
            }
        } else if (nodo.getDerecho() != null) {
            lista = obtenerValoresAux(nodo.getDerecho(), dominio);
        }
        return lista;
    }

    public boolean desasociar(Comparable dominio, Object rango) {
        boolean verificado = false;
        if (this.raiz != null) {
            verificado = desasociarAux(this.raiz, dominio, null, rango);
        }
        return verificado;
    }

    private boolean desasociarAux(NodoAVLMapeoM nodo, Comparable clave, NodoAVLMapeoM padre, Object rango) {
    
        boolean salida = false;
        if (nodo != null) {
            if (clave.compareTo(nodo.getDominio()) == 0) {
                salida = true;
                //si tiene algun valor en el rango elimina ese valor
                if(nodo.obtenerValores().longitud() >0){
                    nodo.eliminarRango(rango);
                }else{ 
                    // si el rango no tiene valores, elimina el dominio
                    if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
                    if (padre == null) {
                        this.raiz = null;
                    } else if (padre.getIzquierdo() == nodo) {
                        padre.setIzquierdo(null);
                    } else {
                        padre.setDerecho(null);
                    }
                } else if (nodo.getIzquierdo() == null || nodo.getDerecho() == null) {
                    NodoAVLMapeoM nuevoNodo = (nodo.getIzquierdo() != null) ? nodo.getIzquierdo() : nodo.getDerecho();

                    if (padre == null) {
                        this.raiz = nuevoNodo;
                    } else if (padre.getIzquierdo() == nodo) {
                        padre.setIzquierdo(nuevoNodo);
                    } else {
                        padre.setDerecho(nuevoNodo);
                    }
                } else {
                    NodoAVLMapeoM temp = getValorMaximo(nodo.getIzquierdo());
                    nodo.setDominio(temp.getDominio());
                    salida = desasociarAux(nodo.getIzquierdo(), temp.getDominio(), nodo, rango);
                }
                }
            }else if (clave.compareTo(nodo.getDominio()) < 0) {
                salida = eliminarAux(nodo.getIzquierdo(), clave, nodo);
            } else {
                salida = eliminarAux(nodo.getDerecho(), clave, nodo);
            }
            
            if (salida) {
                nodo.recalcularAltura();
            int balance = calcularBalance(nodo);

            if (balance > 1 && clave.compareTo(nodo.getIzquierdo().getDominio()) < 0) {
                nodo = rotacionDerecha(nodo);
                if (padre == null) {
                    this.raiz = nodo;
                } else if (padre.getIzquierdo() == nodo) {
                    padre.setIzquierdo(nodo);
                } else {
                    padre.setDerecho(nodo);
                }
            } else if (balance < -1 && clave.compareTo(nodo.getDerecho().getDominio()) > 0) {
                nodo = rotacionIzquierda(nodo);
                if (padre == null) {
                    this.raiz = nodo;
                } else if (padre.getIzquierdo() == nodo) {
                    padre.setIzquierdo(nodo);
                } else {
                    padre.setDerecho(nodo);
                }
            } else if (balance > 1 && clave.compareTo(nodo.getIzquierdo().getDominio()) > 0) {
                nodo.setIzquierdo(rotacionIzquierda(nodo.getIzquierdo()));
                nodo = rotacionDerecha(nodo);
                if (padre == null) {
                    this.raiz = nodo;
                } else if (padre.getIzquierdo() == nodo) {
                    padre.setIzquierdo(nodo);
                } else {
                    padre.setDerecho(nodo);
                }
            } else if (balance < -1 && clave.compareTo(nodo.getDerecho().getDominio()) < 0) {
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
    
    public boolean eliminar(Comparable dominio) {
        boolean verificado = false;
        if (this.raiz != null) {
            verificado = eliminarAux(this.raiz, dominio, null);
        }
        return verificado;
    }

    private boolean eliminarAux(NodoAVLMapeoM nodo, Comparable clave, NodoAVLMapeoM padre) {
    
        boolean salida = false;
        if (nodo != null) {
            if (clave.compareTo(nodo.getDominio()) == 0) {
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
                    NodoAVLMapeoM nuevoNodo = (nodo.getIzquierdo() != null) ? nodo.getIzquierdo() : nodo.getDerecho();

                    if (padre == null) {
                        this.raiz = nuevoNodo;
                    } else if (padre.getIzquierdo() == nodo) {
                        padre.setIzquierdo(nuevoNodo);
                    } else {
                        padre.setDerecho(nuevoNodo);
                    }
                } else {
                    NodoAVLMapeoM temp = getValorMaximo(nodo.getIzquierdo());
                    nodo.setDominio(temp.getDominio());
                    salida = eliminarAux(nodo.getIzquierdo(), temp.getDominio(), nodo);
                }
            } else if (clave.compareTo(nodo.getDominio()) < 0) {
                salida = eliminarAux(nodo.getIzquierdo(), clave, nodo);
            } else {
                salida = eliminarAux(nodo.getDerecho(), clave, nodo);
            }

            if (salida) {
                nodo.recalcularAltura();
            int balance = calcularBalance(nodo);

            if (balance > 1 && clave.compareTo(nodo.getIzquierdo().getDominio()) < 0) {
                nodo = rotacionDerecha(nodo);
                if (padre == null) {
                    this.raiz = nodo;
                } else if (padre.getIzquierdo() == nodo) {
                    padre.setIzquierdo(nodo);
                } else {
                    padre.setDerecho(nodo);
                }
            } else if (balance < -1 && clave.compareTo(nodo.getDerecho().getDominio()) > 0) {
                nodo = rotacionIzquierda(nodo);
                if (padre == null) {
                    this.raiz = nodo;
                } else if (padre.getIzquierdo() == nodo) {
                    padre.setIzquierdo(nodo);
                } else {
                    padre.setDerecho(nodo);
                }
            } else if (balance > 1 && clave.compareTo(nodo.getIzquierdo().getDominio()) > 0) {
                nodo.setIzquierdo(rotacionIzquierda(nodo.getIzquierdo()));
                nodo = rotacionDerecha(nodo);
                if (padre == null) {
                    this.raiz = nodo;
                } else if (padre.getIzquierdo() == nodo) {
                    padre.setIzquierdo(nodo);
                } else {
                    padre.setDerecho(nodo);
                }
            } else if (balance < -1 && clave.compareTo(nodo.getDerecho().getDominio()) < 0) {
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
    
    

     public String toString() {
        String cadena = "[";
        if (this.raiz != null) {
            cadena = toStringAux(this.raiz, cadena);
        }
        cadena += "]";
        return cadena;
    }

    private String toStringAux(NodoAVLMapeoM n, String cadena) {
        if (n != null) {
            cadena += " clave:" + n.getDominio().toString();
            cadena +=" altura: " + n.getAltura();
            Lista lista = n.obtenerValores();
            cadena += " rango: ";
            for (int i = 1; i <= lista.longitud(); i++) {
                    cadena += ((Solicitud)lista.recuperar(i)).toString() + " ";
            }
            cadena += " HI: ";
            if (n.getIzquierdo() != null) {
                cadena += n.getIzquierdo().getDominio().toString();
            } else {
                cadena += "-";
            }
            cadena += " HD: ";
            if (n.getDerecho() != null) {
                cadena += n.getDerecho().getDominio().toString();
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

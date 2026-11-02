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
public class Grafo {

    private NodoVert inicio;

    public Grafo(NodoVert inicio) {
        this.inicio = inicio;
    }

    public NodoVert ubicarVertice(Object buscado) {
        //encuentra el vertice que se le ingresa por parametro
        NodoVert aux = this.inicio;
        while (aux != null && !aux.getElem().equals(buscado)) {
            aux = aux.getSigVertice();
        }
        return aux;
    }

    public boolean insertarArco(Object verticeO, Object verticeD, double etiqueta) {
        boolean encontrado = false;
        NodoVert vertice1 = null;
        NodoVert vertice2 = null;
        NodoVert n = this.inicio;

        while (n != null && !encontrado) {
            if (n.getElem().equals(verticeO)) {
                vertice1 = n;
            }
            if (n.getElem().equals(verticeD)) {
                vertice2 = n;
            }
            if (vertice1 != null && vertice2 != null) {
                encontrado = true;
            } else {
                n = n.getSigVertice();
            }
        }

        if (encontrado) {
            if (vertice1 != null && vertice2 != null) {
                vertice1.setPrimerAdy(new NodoAdy(vertice2, vertice1.getPrimerAdy(), etiqueta));
                vertice2.setPrimerAdy(new NodoAdy(vertice1, vertice2.getPrimerAdy(), etiqueta));
            }
        }
        return encontrado;
    }

    public boolean eliminarArco(Object verticeO, Object verticeD, double etiqueta) {
        boolean exito = false, eliminado = false;
        NodoAdy aux, aux2, nodoAnterior1, nodoAnterior2;
        NodoVert n = this.inicio, nodoApuntado;
        while (n != null && !exito) {
            if (n.getElem().equals(verticeO) || n.getElem().equals(verticeD)) {
                exito = true;
            }
            n = n.getSigVertice();
        }
        //encontro uno de los dos vertices y procede a eliminarlos
        if (exito) {
            if (n != null) {
                aux = n.getPrimerAdy();
                if ((aux.getVertice().getElem().equals(verticeO) || aux.getVertice().getElem().equals(verticeD)) && aux.getEtiqueta() == etiqueta) {
                    nodoApuntado = aux.getVertice();
                    aux2 = nodoApuntado.getPrimerAdy();
                    if ((aux2.getVertice().getElem().equals(verticeO) ||aux2.getVertice().getElem().equals(verticeD) ) && aux2.getEtiqueta() == etiqueta) {
                        nodoApuntado.setPrimerAdy(aux2.getSigAdyacente());
                        n.setPrimerAdy(aux.getSigAdyacente());
                        eliminado = true;
                    } else {
                        nodoAnterior1 = aux2;
                        aux2 = aux2.getSigAdyacente();
                        borrarArco(aux, aux2, nodoAnterior1, n, etiqueta);
                    }
                } else {
                    nodoAnterior1 = aux;
                    aux = aux.getSigAdyacente();
                    while (aux != null && !((aux.getVertice().getElem().equals(verticeO) || aux.getVertice().getElem().equals(verticeD)) && aux.getEtiqueta() == etiqueta)) {
                        nodoAnterior1 = nodoAnterior1.getSigAdyacente();
                        aux = aux.getSigAdyacente();
                    }
                    if (aux != null) {
                        if ((aux.getVertice().getElem().equals(verticeO) || aux.getVertice().getElem().equals(verticeD)) && aux.getEtiqueta() == etiqueta) {
                            nodoApuntado = aux.getVertice();
                            aux2 = nodoApuntado.getPrimerAdy();
                            if ((aux2.getVertice().getElem().equals(verticeO) ||aux2.getVertice().getElem().equals(verticeD)) && aux2.getEtiqueta() == etiqueta) {
                                nodoApuntado.setPrimerAdy(aux2.getSigAdyacente());
                                nodoAnterior1.setSigAdyacente(aux.getSigAdyacente());
                                eliminado = true;
                            } else {
                                nodoAnterior2 = aux2;
                                aux2 = aux2.getSigAdyacente();
                                while ((nodoAnterior1 != null && aux2 != null) &&!((aux2.getVertice().getElem().equals(verticeO) ||aux2.getVertice().getElem().equals(verticeD)) && aux2.getEtiqueta() == etiqueta)) {
                                    nodoAnterior1 = nodoAnterior1.getSigAdyacente();
                                    aux2 = aux2.getSigAdyacente();
                                }
                                if ((aux2.getVertice().getElem().equals(verticeO) ||aux2.getVertice().getElem().equals(verticeD))) {
                                    nodoAnterior1.setSigAdyacente(aux.getSigAdyacente());
                                    nodoAnterior2.setSigAdyacente(aux2.getSigAdyacente());
                                }
                            }
                        }
                    }
                }
            }
        }

        return eliminado;
    }
        
        private void borrarArco(NodoAdy aux, NodoAdy aux2, NodoAdy nodoAnterior1, NodoVert n, double etiqueta){
            while (!(aux2.getVertice().getElem().equals(n.getElem()) && aux2.getEtiqueta() == etiqueta)) {
                nodoAnterior1 = nodoAnterior1.getSigAdyacente();
                aux2 = aux2.getSigAdyacente();
            }
            if (aux2.getVertice().getElem().equals(n.getElem())) {
                n.setPrimerAdy(aux.getSigAdyacente());
                nodoAnterior1.setSigAdyacente(aux2.getSigAdyacente());
            }
        }

    public boolean insertarVertice(Object nuevoVertice) {
        boolean exito = false;
        NodoVert aux = this.ubicarVertice(nuevoVertice);
        if (aux == null) {
            this.inicio = new NodoVert(nuevoVertice, this.inicio, null);
            exito = true;
        }
        return exito;
    }

    public boolean eliminarVertice(Object vertice) {
        boolean exito = false;

        if (this.inicio != null) {
            exito = eliminarVerticeAux(vertice, this.inicio);
        }
        return exito;
    }

    private boolean eliminarVerticeAux(Object vertice, NodoVert n) {
        //busca el vertice y lo elimina junto a sus arcos
        boolean exito = false;
        NodoVert nodoAnterior = n;
        if (n.getElem().equals(vertice)) {
            NodoAdy aux = n.getPrimerAdy();
            borrarArcos(aux, vertice);
            this.inicio = inicio.getSigVertice();
            exito = true;
        } else {
            n = n.getSigVertice();
            while (n != null && !exito) {
                if (!n.getElem().equals(vertice)) {
                    n = n.getSigVertice();
                    nodoAnterior = nodoAnterior.getSigVertice();
                } else {
                    exito = true;
                }
            }
            if (exito) {
                if (n != null) {
                    NodoAdy aux = n.getPrimerAdy();
                    borrarArcos(aux, vertice);
                    nodoAnterior.setSigVertice(n.getSigVertice());
                }
            }
        }
        return exito;
    }

    private void borrarArcos(NodoAdy aux, Object vertice) {
        NodoVert nodoApuntado;
        NodoAdy aux2;
        while (aux != null) {
            nodoApuntado = aux.getVertice();
            aux2 = nodoApuntado.getPrimerAdy();
            while (aux2 != null) {
                if (aux2.getVertice().getElem().equals(vertice)) {
                    nodoApuntado.setPrimerAdy(aux2.getSigAdyacente());
                }
                aux2 = aux2.getSigAdyacente();
            }
            aux = aux.getSigAdyacente();
        }
    }

    public Lista listarEnProfundidad() {
        Lista visitados = new Lista();
        NodoVert aux = this.inicio;

        while (aux != null) {
            if (visitados.localizar(aux.getElem()) < 0) {
                listarEnProfundidadAux(aux, visitados);
            }
            aux = aux.getSigVertice();
        }
        return visitados;
    }

    private void listarEnProfundidadAux(NodoVert n, Lista vis) {
        if (n != null) {
            vis.insertar(n.getElem(), vis.longitud() + 1);
            NodoAdy ady = n.getPrimerAdy();
            while (ady != null) {
                if (vis.localizar(ady.getVertice().getElem()) < 0) {
                    listarEnProfundidadAux(ady.getVertice(), vis);
                }
                ady = ady.getSigAdyacente();
            }
        }
    }
    
    public boolean existeCamino(Object origen, Object destino){
        boolean exito = false;
        
        NodoVert auxO = null;
        NodoVert auxD = null;
        NodoVert aux = this.inicio;
        
        while((auxO == null || auxD == null) && aux != null){
            if(aux.getElem().equals(origen)) auxO=aux;
            if(aux.getElem().equals(destino)) auxD=aux;
            aux = aux.getSigVertice();
        }
        
        if(auxO != null && auxD != null){
            Lista visitados = new Lista();
            exito = existeCaminoAux(auxO,destino,visitados);
        }
        return exito;
        
    }
    
    private boolean existeCaminoAux(NodoVert n,Object dest, Lista vis){
        boolean exito = false;
        if(n != null){
            //si vertice n es el destino: hay camino
            if(n.getElem().equals(dest)){
                exito = true;
            } else {
                //si no es el destino verifica si hay camino entre n y destino
                vis.insertar(n.getElem(), vis.longitud() + 1);
                NodoAdy ady = n.getPrimerAdy();
                while(!exito && ady != null){
                    if(vis.localizar(ady.getVertice().getElem()) < 0){
                        exito = existeCaminoAux(ady.getVertice(),dest,vis);
                    }
                    ady = ady.getSigAdyacente();
                }
            }
        }
        return exito;
    }
   
    
    public Lista caminoMasCorto(Object origen, Object destino) {
        Lista visitados = new Lista();
        Lista masCorto = new Lista();
        NodoVert auxOrigen = null;
        NodoVert auxDestino = null;
        NodoVert aux = this.inicio;
        while (aux != null && (auxOrigen == null || auxDestino == null)) {
            if (aux.getElem().equals(origen)) {
                auxOrigen = aux;
            }
            if (aux.getElem().equals(destino)) {
                auxDestino = aux;
            }
            aux = aux.getSigVertice();
        }
        if (auxOrigen != null && auxDestino != null) {
            caminoMasCortoAux(auxOrigen, auxDestino, masCorto, visitados);
        }
        return masCorto;
    }

    private void caminoMasCortoAux(NodoVert n, NodoVert dest, Lista masCorta, Lista vis) {
        int i;
        if (n != null) {
            vis.insertar(n.getElem(), vis.longitud() + 1);
            if (n.getElem().equals(dest.getElem())) {
                masCorta.vaciar();
                for (i = 1; i <= vis.longitud(); i++) {
                    masCorta.insertar(vis.recuperar(i), masCorta.longitud() + 1);
                }
            } else {
                NodoAdy ady = n.getPrimerAdy();
                while (ady != null && (masCorta.longitud() > vis.longitud() || masCorta.esVacia())) {
                    if (vis.localizar(ady.getVertice().getElem()) < 0) {
                        caminoMasCortoAux(ady.getVertice(), dest, masCorta, vis);
                    }
                    ady = ady.getSigAdyacente();
                }
            }
        }
        vis.eliminar(vis.longitud());
    }
    
    public Lista caminoMasCortoPorPesos(Object origen, Object destino) {
        Lista visitados = new Lista();
        Lista mejorCamino = new Lista();
        NodoVert auxOrigen = null;
        NodoVert auxDestino = null;
        NodoVert aux = this.inicio;
        double km = 0;
        while (aux != null && (auxOrigen == null || auxDestino == null)) {
            if (aux.getElem().equals(origen)) {
                auxOrigen = aux;
            }
            if (aux.getElem().equals(destino)) {
                auxDestino = aux;
            }
            aux = aux.getSigVertice();
        }
        if (auxOrigen != null && auxDestino != null) {
            km = caminoMasCortoPorPesosAux(auxOrigen, auxDestino, 0, Double.MAX_VALUE, mejorCamino, visitados);
        }
        System.out.println("Kilometros: " + km);
        return mejorCamino;
    }

    private double caminoMasCortoPorPesosAux(NodoVert n, NodoVert dest, double acumEtiqueta, double acumulador, Lista mejor, Lista vis) {
      
        if (n != null) {
            vis.insertar(n.getElem(), vis.longitud() + 1);
            if (n.getElem().equals(dest.getElem()) && acumEtiqueta<acumulador) {
                acumulador = acumEtiqueta;
                mejor.vaciar();
                mejor = vis.clone();
            } else {
                NodoAdy ady = n.getPrimerAdy();
                while (ady != null && acumEtiqueta < acumulador) {
                    if (vis.localizar(ady.getVertice().getElem()) < 0) {
                        acumulador = caminoMasCortoPorPesosAux(ady.getVertice(), dest, acumEtiqueta + (double) ady.getEtiqueta(), acumulador, mejor, vis);
                    }
                    ady = ady.getSigAdyacente();
                }
            }
        }
        vis.eliminar(vis.longitud());
        return acumulador;
    }
    
    public String toString(){
        String cadena = "[";
        NodoVert n = this.inicio;
        NodoAdy nodo;
        
        while(n != null){
            cadena += "vertice: " + n.getElem();
            nodo = n.getPrimerAdy();
            while(nodo != null){
                cadena += " arco hacia: " + nodo.getVertice().getElem() + " peso: " + nodo.getEtiqueta();
                nodo = nodo.getSigAdyacente();
            }
            cadena += "\n";
            n = n.getSigVertice();
        }
        cadena += "]";
        return cadena;
    }

}

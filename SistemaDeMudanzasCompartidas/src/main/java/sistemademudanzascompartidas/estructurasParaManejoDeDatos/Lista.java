/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemademudanzascompartidas.estructurasParaManejoDeDatos;

/**
 *
 * @author Razor-PC V.3
 */
public class Lista {

    private Nodo cabecera;
    private int longitud;

//constructor
    public Lista() {
        cabecera = null;
        longitud = 0;
    }
//propias de tipo

    public boolean insertar(Object elemento, int pos) {
        //inserta un elemento en la lista
        
        boolean exito = true;
        
        if (pos < 1 || pos > this.longitud() + 1) {//Detecta si la pos ingresada es invalida
            exito = false;
        } else {
            if (pos == 1) {//Caso especial, crear el primer nodo
                this.cabecera = new Nodo(elemento, this.cabecera);
            } else {
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                Nodo nuevo = new Nodo(elemento, aux.getEnlace());
                aux.setEnlace(nuevo);
            }
            longitud++;
        }
        return exito;
    }

    public int longitud() {
        return longitud;
    }

    public boolean eliminar(int pos) {
        boolean exito = true;

        if (pos < 1 || pos > longitud || this.cabecera == null) {
            exito = false;
        } else {
            if (pos == 1) {
                //Si existe más de un elemento enlaza la cabecera con el siguiente.
                if (cabecera.getEnlace() != null) {
                    this.cabecera = cabecera.getEnlace();
                } else {
                    this.cabecera = null;
                }
            } else {
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                aux.setEnlace(aux.getEnlace().getEnlace());
            }
            longitud--;
        }
        return exito;
    }

    public Object recuperar(int pos) {
        Object elemento;
        if (pos < 1 || pos > longitud) {
            elemento = null;
        } else if (pos == 1) {
            elemento = this.cabecera.getElemento();
        } else {
            Nodo aux = this.cabecera;
            int i = 1;
            while (i < pos - 1) {
                aux = aux.getEnlace();
                i++;
            }
            elemento = aux.getEnlace().getElemento();
        }
        return elemento;
    }

    public int localizar(Object elemento) {
        Object elementoPrueba;
        Nodo aux = this.cabecera;
        int i = 1, pos = -1;
        boolean encontrado = false;

        while (i <= longitud && !encontrado) {
            elementoPrueba = aux.getElemento();
            if (elemento.equals(elementoPrueba)) {
                encontrado = true;
                pos = i;
            }
            aux = aux.getEnlace();
            i++;
        }
        return pos;
    }

    public void vaciar() {
        this.cabecera = null;
    }

    public boolean esVacia() {
        boolean vacia = true;

        if (cabecera != null) {
            vacia = false;
        }
        return vacia;
    }

    public Lista clone() {
        Lista clon = new Lista();
        int longitud = 0;

        if (this.cabecera != null) {
            Nodo aux = this.cabecera;
            clon.cabecera = new Nodo(aux.getElemento(), null);
            Nodo aux2 = clon.cabecera;
            longitud = 1;

            while (aux.getEnlace() != null) {
                aux = aux.getEnlace();
                aux2.setEnlace(new Nodo(aux.getElemento(), null));
                aux2 = aux2.getEnlace();
                longitud++;
            }
            clon.longitud = longitud;
        }
        return clon;
    }

    public String toString() {
        Nodo aux = this.cabecera;
        String cadena = "[ ";
        if (aux != null) {
            for (int i = 1; i <= longitud; i++) {
                if(aux != null){
                if (aux.getElemento() != null) {
                    cadena += aux.getElemento().toString() + " ";
                    aux = aux.getEnlace();
                } else {
                    cadena += " null "; 
                }
                }
            }
        }
        cadena += "]";
        return cadena;
    }

    
    public void invertir(){
        Nodo aux = this.cabecera;
        Nodo n = new Nodo(aux.getElemento(), null);
        Nodo primerNodo = n;
        
        while(aux.getEnlace() != null){
            aux = aux.getEnlace();
            Nodo nuevo = new Nodo(aux.getElemento(), n);
            n = n.getEnlace();
        }
        this.cabecera = primerNodo;
    }
}

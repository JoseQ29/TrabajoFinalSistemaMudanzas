/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemademudanzascompartidas.estructurasParaManejoDeDatos;

/**
 *
 * @author Razor-PC V.3
 */
public class NodoAVL {
    
    private Comparable elemento;
    private int altura;
    private NodoAVL izquierdo;
    private NodoAVL derecho;

    public NodoAVL(Comparable elemento, NodoAVL izquierdo, NodoAVL derecho) {
        this.elemento = elemento;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
        this.altura = 0;
    }
    public void recalcularAltura(){
        if(this.izquierdo == null && this.derecho == null){
            this.altura = 0;
        }else{
            this.altura = alturaAux(-1,this);
        }
        
    }
    
    private int alturaAux(int alturaParcial, NodoAVL n) {
        int hIzquierdo, hDerecho;

        if (n != null) {
            alturaParcial++;
            hIzquierdo = alturaAux(alturaParcial, n.getIzquierdo());
            hDerecho = alturaAux(alturaParcial, n.getDerecho());

            if (hIzquierdo >= hDerecho) {
                alturaParcial = hIzquierdo;
            } else {
                alturaParcial = hDerecho;
            }
        }
        return alturaParcial;
    }

    public Comparable getElem() {
        return elemento;
    }

    public void setElemento(Comparable elemento) {
        this.elemento = elemento;
    }

    public NodoAVL getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoAVL izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoAVL getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoAVL derecho) {
        this.derecho = derecho;
    }

    public int getAltura() {
        return altura;
    }
    
    
}

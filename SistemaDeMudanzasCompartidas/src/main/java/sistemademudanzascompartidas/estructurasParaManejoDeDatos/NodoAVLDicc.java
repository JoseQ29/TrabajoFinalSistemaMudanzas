/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemademudanzascompartidas.estructurasParaManejoDeDatos;

/**
 *
 * @author Razor-PC V.3
 */
public class NodoAVLDicc {
    
    
    private Comparable clave;
    private Object dato;
    private int altura;
    private NodoAVLDicc izquierdo;
    private NodoAVLDicc derecho;

    public NodoAVLDicc(Comparable clave, Object dato, NodoAVLDicc hijoIzquierdo, NodoAVLDicc hijoDerecho) {
        this.clave = clave;
        this.dato = dato;
        this.izquierdo = hijoIzquierdo;
        this.derecho = hijoDerecho;
        this.altura = 0;
    }
    
    public void recalcularAltura(){
        if(this.izquierdo == null && this.derecho == null){
            this.altura = 0;
        }else{
            this.altura = alturaAux(-1,this);
        }
        
    }
    
    private int alturaAux(int alturaParcial, NodoAVLDicc n) {
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

    //solo para el eliminar del diccionario avl
    public void setClave(Comparable clave) {
        this.clave = clave;
    }
    
    public Comparable getClave() {
        return clave;
    }

    public Object getDato() {
        return dato;
    }

    public int getAltura() {
        return altura;
    }

    public NodoAVLDicc getIzquierdo() {
        return izquierdo;
    }

    public NodoAVLDicc getDerecho() {
        return derecho;
    }

    public void setIzquierdo(NodoAVLDicc izquierdo) {
        this.izquierdo = izquierdo;
    }

    public void setDerecho(NodoAVLDicc derecho) {
        this.derecho = derecho;
    }
    
    
    
}

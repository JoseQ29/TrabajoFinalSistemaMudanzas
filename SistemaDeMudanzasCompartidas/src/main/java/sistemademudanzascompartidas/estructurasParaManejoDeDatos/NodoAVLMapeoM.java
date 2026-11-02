/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemademudanzascompartidas.estructurasParaManejoDeDatos;

/**
 *
 * @author Razor-PC V.3
 */
public class NodoAVLMapeoM {
    
    private Comparable dominio;
    private Lista rango;
    private NodoAVLMapeoM izquierdo;
    private NodoAVLMapeoM derecho;
    private int altura;

    public NodoAVLMapeoM(Comparable dominio, Object rango, NodoAVLMapeoM izquierdo, NodoAVLMapeoM derecho) {
        this.dominio = dominio;
        this.rango = new Lista();
        this.rango.insertar(rango, 1);
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
    
    private int alturaAux(int alturaParcial, NodoAVLMapeoM n) {
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

    public int getAltura() {
        return altura;
    }
    
    //para el eliminar de mapeo a muchos
    public void setDominio(Comparable dominio){
        this.dominio = dominio;
    }
    
    public Comparable getDominio() {
        return dominio;
    }

    public Lista obtenerValores() {
        return rango;
    }

    public NodoAVLMapeoM getIzquierdo() {
        return izquierdo;
    }

    public NodoAVLMapeoM getDerecho() {
        return derecho;
    }

    public void setIzquierdo(NodoAVLMapeoM izquierdo) {
        this.izquierdo = izquierdo;
    }

    public void setDerecho(NodoAVLMapeoM derecho) {
        this.derecho = derecho;
    }
    
    public void setRango(Object rango){
        this.rango.insertar(rango, this.rango.longitud() + 1);
    }
    
    public void eliminarRango(Object unRango){
        int pos = -1;
        pos = this.rango.localizar(unRango);
        if(pos > 0){
            this.rango.eliminar(pos);
        }
    }
    
}

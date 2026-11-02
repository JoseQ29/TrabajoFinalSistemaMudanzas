/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemademudanzascompartidas.estructurasParaManejoDeDatos;

/**
 *
 * @author Razor-PC V.3
 */
public class Nodo {
    
    private Object elemento;
    private Nodo enlace;
    
    //Constructor
    
    public Nodo(Object elemento, Nodo enlace){
        this.elemento = elemento;
        this.enlace = enlace;
    }
    //Modificadoras
    
    public void setElemento(Object elemento){
        //Pone el elemento en el nodo
        this.elemento = elemento;
    }
    public void setEnlace(Nodo enlace){
        //Crea el enlace del nodo
        this.enlace = enlace;
    }
    //Observadoras
    public Object getElemento(){
        //Retorna el elemento del nodo
        return elemento;
    }
    public Nodo getEnlace(){
        //Retorna el enlace del nodo
        return enlace;
    }
}

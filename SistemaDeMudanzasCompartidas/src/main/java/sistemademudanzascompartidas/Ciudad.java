/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemademudanzascompartidas;

/**
 *
 * @author Razor-PC V.3
 */
public class Ciudad {
   
    private final int codigoPostal;
    private final String nombre;
    private final String nombreProvincia;

    public Ciudad(int codigoPostal, String nombre, String nombreProvincia) {
        this.codigoPostal = codigoPostal;
        this.nombre = nombre;
        this.nombreProvincia = nombreProvincia;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNombreProvincia() {
        return nombreProvincia;
    }
    
    public int compareTo(Ciudad ciudad){
        int salida;
        if(this.codigoPostal > ciudad.codigoPostal){
            salida = 1;
        }else if(this.codigoPostal < ciudad.codigoPostal){
            salida = -1;
        }else{
            salida = 0;
        }
        return salida;
    }
    
    public String toString(){
        String cadena = "codigo postal: " + codigoPostal + " nombre: "+ nombre + " nombre de la provincia: " + nombreProvincia;
        return cadena;
    }
    
}
        
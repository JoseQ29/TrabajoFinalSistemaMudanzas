/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemademudanzascompartidas;

import java.util.Objects;

/**
 *
 * @author Razor-PC V.3
 */
public class Cliente {
    
    private final String tipoDocumento;
    private final int numeroDoc;
    private String apellido;
    private String nombre;
    private String numeroTelefono;

    public Cliente(String tipoDocumento, int numeroDoc, String apellido, String nombre, String numeroTelefono) {
        this.tipoDocumento = tipoDocumento;
        this.numeroDoc = numeroDoc;
        this.apellido = apellido;
        this.nombre = nombre;
        this.numeroTelefono = numeroTelefono;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public int getNumeroDoc() {
        return numeroDoc;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.tipoDocumento);
        hash = 17 * hash + this.numeroDoc;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        return true;
    }
    
    public String toString(){
        String cadena;
        cadena = "nombre: " + nombre + " apellido: " + apellido + " tipo de documento: " + tipoDocumento + " numero de documento: " + numeroDoc + " numero de telefono: " + numeroTelefono;
        return cadena;
    }
    
    
    
}

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
public class Solicitud {
    
    private int ciudadOrigen;
    private int ciudadDestino;
    private String fecha;
    private String tipoDocumento;
    private int numeroDocumento;
    private int metrosCubicos;
    private int cantBultos;
    private String calleRetiro;
    private String calleEntrega;
    private boolean estaPago;

    public Solicitud(int ciudadOrigen, int ciudadDestino, String fecha, String tipoDocumento, int numeroDocumento, int metrosCubicos, int cantBultos, String calleRetiro, String calleEntrega, boolean estaPago) {
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.fecha = fecha;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.metrosCubicos = metrosCubicos;
        this.cantBultos = cantBultos;
        this.calleRetiro = calleRetiro;
        this.calleEntrega = calleEntrega;
        this.estaPago = estaPago;
    }

    public boolean getEstaPago() {
        return estaPago;
    }
    
    public int getCiudadOrigen() {
        return ciudadOrigen;
    }

    public int getCiudadDestino() {
        return ciudadDestino;
    }

    public String getFecha() {
        return fecha;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public int getNumeroDocumento() {
        return numeroDocumento;
    }

    public int getMetrosCubicos() {
        return metrosCubicos;
    }

    public int getCantBultos() {
        return cantBultos;
    }

    public String getCalleRetiro() {
        return calleRetiro;
    }

    public String getCalleEntrega() {
        return calleEntrega;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Solicitud other = (Solicitud) obj;
        if (this.ciudadOrigen != other.ciudadOrigen) {
            return false;
        }
        if (this.ciudadDestino != other.ciudadDestino) {
            return false;
        }
        if (this.numeroDocumento != other.numeroDocumento) {
            return false;
        }
        if (this.metrosCubicos != other.metrosCubicos) {
            return false;
        }
        if (this.cantBultos != other.cantBultos) {
            return false;
        }
        if (this.estaPago != other.estaPago) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.tipoDocumento, other.tipoDocumento)) {
            return false;
        }
        if (!Objects.equals(this.calleRetiro, other.calleRetiro)) {
            return false;
        }
        return Objects.equals(this.calleEntrega, other.calleEntrega);
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setMetrosCubicos(int metrosCubicos) {
        this.metrosCubicos = metrosCubicos;
    }

    public void setCantBultos(int cantBultos) {
        this.cantBultos = cantBultos;
    }

    public void setEstaPago(boolean estaPago) {
        this.estaPago = estaPago;
    }
    public String toString(){
        String cadena = "" +this.ciudadOrigen + this.ciudadDestino;
        return cadena;
    }
    
}

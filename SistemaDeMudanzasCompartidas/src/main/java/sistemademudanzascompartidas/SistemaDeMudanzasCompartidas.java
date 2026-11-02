/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package sistemademudanzascompartidas;
import sistemademudanzascompartidas.lecturaEscrituraTXT.*;
import java.util.StringTokenizer;
/**
 *
 * @author Razor-PC V.3
 */
public class SistemaDeMudanzasCompartidas {

    public static void main(String[] args) {
         String texto;
         leerTexto lineas = new leerTexto();
         texto = lineas.leerTexto("C:\\Users\\Razor-PC V.3\\Desktop\\cargaInicial.txt");
         StringTokenizer st = new StringTokenizer(texto, "; \n");  
         Mudanza mudanza = new Mudanza();
         
         while(st.hasMoreTokens()){
             String token = st.nextToken();
             switch(token){
                 case "C":
                     mudanza.cargarCiudadInicial(st);
                     break;
                 case "P":
                     mudanza.cargarClienteInicial(st);
                     break;
                 case "R":
                     mudanza.cargarRutaInicial(st);
                     break;
                 case "S":
                     mudanza.cargarSolicitudInicial(st);
                     break;
             }
             
             
         }
         mudanza.menu();
         mudanza.borrarLog();
    }    
}


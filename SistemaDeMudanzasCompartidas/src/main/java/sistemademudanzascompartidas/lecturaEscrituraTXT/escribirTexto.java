/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemademudanzascompartidas.lecturaEscrituraTXT;
import java.io.*;
/**
 *
 * @author Razor-PC V.3
 */
public class escribirTexto {
    
    public void escribirTexto(String texto) {
        try {
            File archivo = new File("C:\\Users\\Razor-PC V.3\\Desktop\\log.txt");
            
            FileWriter escribir = new FileWriter(archivo, true);
            escribir.write(texto + "\n");
            escribir.close();
        } 
        catch (IOException e) {
            System.out.println("Error al escribir");
        }
    }
    
    public void borrarTexto(){
        try {
            File archivo = new File("C:\\Users\\Razor-PC V.3\\Desktop\\log.txt");
            
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
            bw.write("");
            bw.close();
        } 
        catch (IOException e) {
            System.out.println("Error al escribir");
        }
    }
}

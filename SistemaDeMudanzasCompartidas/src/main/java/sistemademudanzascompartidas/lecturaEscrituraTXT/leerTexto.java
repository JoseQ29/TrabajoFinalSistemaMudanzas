/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemademudanzascompartidas.lecturaEscrituraTXT;

import java.io.*;

public class leerTexto {
    
    public String leerTexto(String direccion){
        
        String texto = "";
        
        try{
            FileReader fileReader = new FileReader(direccion);
            BufferedReader bf = new BufferedReader(fileReader);
            String temp = "";
            String linea;
            while((linea = bf.readLine()) != null){
                temp = temp + linea + "\n";
            }
            texto = temp;
            bf.close();
            
        }catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
        catch (IOException ex) {
            System.err.println("Error leyendo o escribiendo en algun archivo.");
        }
        return texto;
    } 
            
}

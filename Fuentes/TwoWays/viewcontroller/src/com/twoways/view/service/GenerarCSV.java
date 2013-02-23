package com.twoways.view.service;

import java.io.FileWriter;
import java.io.IOException;

public class GenerarCSV {
    public GenerarCSV() {
        
    }
    
    public static void generarCsvFile(String[] valores, String nombreArchivo){
    
        try{
            FileWriter writer = new FileWriter(nombreArchivo);
            
            for(String aux:valores){
                String atribs[]= aux.split("#");
                
                writer.append(atribs[0]);
                writer.append(',');
                writer.append(atribs[1]);
                writer.append(',');
                writer.append(atribs[3]);
                writer.append(',');
                writer.append(atribs[2]);
                writer.append(',');      
                writer.append(atribs[5]);
                writer.append(',');   
                writer.append(atribs[4]);
                writer.append(',');      
                writer.append(atribs[6]);
                writer.append(',');                     
                writer.append('\n');
            }
            writer.flush();
            writer.close();
        }
        catch(IOException e)
                {
                     e.printStackTrace();
                }
        
    }
}

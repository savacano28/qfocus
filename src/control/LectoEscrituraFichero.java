/*
 * QFocus 1.0 es un software para programar secuencias en un dispositivo externo>
    Copyright (C) <2015>  <Casanova Stephanya>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
   
    
 */
package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Esta clase permite la lectura y escritura de un conjunto de datos
 * @version 1.0
 * @author Stephanya
 */
public class LectoEscrituraFichero {
    ArrayList datosFichero; //varable en donde se almacena el grupo de datos a leer o escribir
    
    /**
     * Metodo constructor System.out.println(datosFichero.get(3));
     */    
    public LectoEscrituraFichero(){            
    }    
    
    /**
     * Metodo que lee fichero     * 
     * @return datosFichero conjunto de datos que han sido leidos
     */
    public ArrayList lecturaFicheroC(){
        datosFichero=new ArrayList(); //creacion de objeto
          String aux="";   
          String texto="";
          String[] datosFicheroL = null;
          try{
           /**Se llama al metodo para cargar fichero*/
           JFileChooser file=new JFileChooser();
           file.showOpenDialog(file);
           /**Se abre el archivo seleccionado*/
           File abre=file.getSelectedFile();
           /**Se recorre el archivo para capturar los datos*/ 
           if(abre!=null){     
              FileReader archivos=new FileReader(abre);
              BufferedReader lee=new BufferedReader(archivos);
              while((aux=lee.readLine())!=null){ //se lee linea por linea
                 System.out.println("lectura "+aux);
                 texto+= aux+ "\n";                
              }
                 lee.close(); //se cierra el buffer del lectura
                 datosFicheroL=texto.split("\\s");   //Se separan los datos 
            }              
          }
           catch(IOException ex){ //excepciones
             JOptionPane.showMessageDialog(null,ex+"" +"\nNo se ha encontrado el archivo.",
                         "ADVERTENCIA!!!",JOptionPane.WARNING_MESSAGE);
           lecturaFicheroC(); 
           }catch(NumberFormatException ex){
             JOptionPane.showMessageDialog(null,ex+"" +"Se han introducido caracteres no numéricos.",
                         "ADVERTENCIA!!!",JOptionPane.WARNING_MESSAGE);  
            lecturaFicheroC(); 
           }        
          
          for(int i=0;i<datosFicheroL.length;i++){   //se almacenan los datos en el array de salida                          
              datosFichero.add(datosFicheroL[i]);
          }
          
          if (datosFicheroL.length!=221)    //Se valida que sean 221 datos, si no son 221 datos se hace la peticion de 
              lecturaFicheroC();            //seleccion de fichero de nuevo              
          
          return datosFichero; 
    }
    
    /**
     * Metodo para la escritura de un fichero
     * @param datosFicheroE Aqui estan los datos que seran escritos
     */
    public void escrituraFicheroC(ArrayList datosFicheroE){   
         try
         {
          String nombre="";
          JFileChooser file=new JFileChooser();
          file.showSaveDialog(file);
          File guarda =file.getSelectedFile();
          if(guarda !=null)
          {
           /* SE guarda el archivo y se le da formato */            
            FileWriter  save=new FileWriter(guarda+".txt");            
            for(int i=0;i<17;i++){  //Se ecsriben los datos en el fichero
                for(int j=0;j<13;j++){
                   save.write(datosFichero.get((13*i)+j).toString()+" ");
                }
                save.write( "\r\n" );
            }              
            
            save.close();
            JOptionPane.showMessageDialog(null,
                 "El archivo se ha guardado con éxito.",
                     "Información",JOptionPane.INFORMATION_MESSAGE);
            }
         }
          catch(IOException ex)
          {
           JOptionPane.showMessageDialog(null,
                "Su archivo no se ha guardado.",
                   "Advertencia",JOptionPane.WARNING_MESSAGE);
          }
         }       
}

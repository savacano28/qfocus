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
    
    Control
*/
package control;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Este metodo es el encargado de organizar las tareas entre las diferentes clases, este es el puente entre la clase vista y modelo
 * @version 1.0
 * @author Stephanya
 */
public class Control {
    ArrayList datosFichero; //Declaracion de variables
    ArrayList datosSalida; 
    LectoEscrituraFichero miLectura;
    ValidaFichero miValida;
    modelo.Modelo miModelo;    
    boolean validoL;    
     
    /**
     * Metodo constructor de la clase 
     */
    public Control(){
    datosFichero=new ArrayList(); //Creacion de objetos
    datosSalida=new ArrayList();
    miLectura=new LectoEscrituraFichero();
    miValida=new ValidaFichero();
    miModelo=new modelo.Modelo();    
    }   
    
/**
 * Este metodo es el encargado de enviarle la orden a la clase lectoEscritura de leer un fichero
 * @return datosFichero conjunto de datos leidos
 */   
    public ArrayList lecturaFicheroC(){    
        datosFichero=miLectura.lecturaFicheroC(); //invoca al metodo lectura fichero
        validoL=miValida.validaFicheroC(datosFichero);  //Invoca al metodo validafichero       
        while (!validoL){ // si no es valida la informacion del fichero se solicita leer un nuevo archivo 
            JOptionPane.showMessageDialog(null,"Por favor verifique los datos a cargar","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);             
            lecturaFicheroC();   //Llamada al metodo
        }        
        return datosFichero; //si es valido entonces entrega los datos
    }
    
    public boolean validaF(ArrayList datos){
    boolean val;
        val=miValida.validaFicheroC(datos);
    
        if(val==true){
    return true;
    }else
        return false;    
    }
    
    /**
     * Metodo para llamar a la clase escritura fichero
     * @param datosFicheroE arraylist donde se guardan los datos que seran escritos
     */    
    public void escrituraFicheroC(ArrayList datosFicheroE){        
        miLectura.escrituraFicheroC(datosFicheroE);     //invoca el metodo escrituraFicheroC     
        JOptionPane.showMessageDialog(null,"Los datos han sido guardados correctamente"); 
    }
    /**
     * Metodo para llamar a la clase comunicacion y transmitir datos
     * @param datosFicheroD conjunto de datos que seran transmitidos al dispositivo
     * @param NoPort Numero del puerto seleccionado apra establecer la comunicacion
     */
    public void descargarDatosC(ArrayList datosFicheroD, String NoPort){
   // comunicacion.Comunicacion miComunicacion=new comunicacion.Comunicacion(NoPort);     //Creacion objeto de la clase comunciacion
   // miLectura.escrituraFicheroC(datosFicheroD);   
    datosSalida=miModelo.procesarDatosS(datosFicheroD);    //invocacion del metodo procesa datos de la clase modelo
    Thread miComunicacion = new comunicacion.Comunicacion(NoPort,datosSalida);
    miComunicacion.start();
   // miComunicacion.transmitirDatosS(datosSalida); //invocacion del metodo transmitir datos de la clase comunicacion
    }
    
}

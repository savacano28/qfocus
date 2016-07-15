/*
 *QFocus 1.0 es un software para programar secuencias en un dispositivo externo>
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
   
Validacion de Fichero
 */
package control;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Esta clase permite la validación de los datos leídos y escritos en un fichero.
 * @version 1.0
 * @author Stephanya
 */
public class ValidaFichero {   
    int dato,dato1,dato2,sumaSec,modSecu;    //Variables
    
    /** 
     * Metodo  que permite determinar si los datos leidos se encuenran en los rangos establecidos
     * @param datosFichero Arralist donde se alamcenan los datos a validar
     * @return boolean que indica si el conjunto de daros cumple con las especifiaciones de rango y formato
     */
    public boolean validaFicheroC(ArrayList datosFichero){         
    try{
    for(int k=0; k<17;k++){ //Ciclo para validacion
    sumaSec=0;              //Variable para suma secuencia
    dato2=Integer.parseInt(datosFichero.get((13*k)+2).toString()); //variable donde se almacena el retardo
    
        for(int h=3;h<13;h++){ //ciclo de suma
        sumaSec=Integer.parseInt(datosFichero.get(h+13*k).toString())+sumaSec;     
        }         
        modSecu=dato2%sumaSec;//Se calcula el modulo                     

        datosFichero.set(((13*k)+2),modSecu); //Aqui se almacena el valor normalizado del retardo      
    }    

    for (int j=0;j<17;j++){  //ciclo de validacion 
          dato=Integer.parseInt(datosFichero.get(13*j).toString()); //variable donde se guarda si la secuencia es Habilitada
          dato1=Integer.parseInt(datosFichero.get((13*j)+1).toString()); //variable donde se guarda el estado inicial   
            if(dato<0 || dato>1){            //Validacion de dato 0 y dato 1, estos solos pueden tomar 0 o 1
                return false;
            }           
            if(dato1<0 || dato1>1){
                return false;
            }                    
        for(int i=3;i<13;i++){               //validacion de tiempos, estos solo pueden tomar de 0 a 10
            if(((Integer.parseInt(datosFichero.get(13*j+i).toString()))>76)|| 
                ((Integer.parseInt(datosFichero.get(13*j+i).toString()))<0)){
            return false;
            }                                  
        }    
    }
    return true;      
   
    
 }catch(NumberFormatException ex){
             JOptionPane.showMessageDialog(null,"Se han introducido caracteres no numéricos",
                         "ADVERTENCIA!!!",JOptionPane.WARNING_MESSAGE);  
 return false;}
        
    }

}
 
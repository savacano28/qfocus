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
    
    Modelo
 */
package modelo;

import java.util.ArrayList;

/** 
 * Esta clase permite genrar las tramas finales que seran transmitidas al dispoitivo. La forma es:
 * [I][D][No][Tr][Est INI][R1][R2][R3][R4] [T1--T10][CHK][F]
 * @version 1.0
 * @author Stephanya
 */
public class Modelo {
    ArrayList datosSalida;    //Declaracion de variables
    int datoC,ceros,cer;
    /**
     * Metodo constructor
     */
    public Modelo(){   
    }
    
    
//TRAMA DISPO [I][D][No][Tr][Est INI][R1][R2][R3][R4] [T1--T10][CHK][F]
//TRAMA DISPO [I][D][No][Tr][Est INI][L][D][0][1] [T1--T10][CHK][F] ------R3 y R4 son el numero de led 1
//TRAMA DISPO [I][D][A][x][x][S][T][x][x] [T1--T10][CHK][F]  ----condicion de inicio para todos 2
//TRAMA DISPO [I][D][A][x][x][O][P][x][x] [T1--T10][CHK][F]  ----condicion de parada para todos
//TRAMA DISPO [I][D][E][x][x][E][E][x][x] [T1--T10][CHK][F]  ----condicion de carga eeprom
    /**
     * En esta clase se procesan los datos que seran transmitifos por el puerto serial, para esto se le agregan las
     * cabeceras y finales adecuados
     * @param datosFicheroD conjunto de datos donde se llegan los dtaos a procesar
     * @return datosSalida conjunto de tramas 
     */
    public ArrayList procesarDatosS(ArrayList datosFicheroD){    
    datosSalida=new ArrayList(); 
    datosSalida.add("ID"); //trama para inicializar
    datosSalida.add("A");
    datosSalida.add("X");
    datosSalida.add("X");
    datosSalida.add("OP");
    datosSalida.add("XX");
    datosSalida.add("1");
    datosSalida.add("2");
    datosSalida.add("3");
    datosSalida.add("4");
    datosSalida.add("5");
    datosSalida.add("6");
    datosSalida.add("1");
    datosSalida.add("2");
    datosSalida.add("3");
    datosSalida.add("4");
    datosSalida.add("W"); 
    datosSalida.add("}");
        for(int i=0;i<17;i++){      //ciclo
        datosSalida.add("ID");     //Se le agrega la primera parte de la cabecera, corresponde a etiquetas de inicio
        datosSalida.add(datosFicheroD.get(13*i)); //se agrega habilitacion                
        int ret;
        ret=Integer.parseInt(datosFicheroD.get((13*i)+2).toString());        
        if(ret>9){//ascii
        ret=ret+48;     
        char c = (char)ret;                         
        datosSalida.add(c); //se agrega el retardo        
        }else {
        datosSalida.add(ret); //se agrega el retardo        
        }   
        if(Integer.parseInt(datosFicheroD.get((13*i)).toString())==0){
        datosSalida.add("0");        
        }else{ //se agrega el estado inicial)
        datosSalida.add(datosFicheroD.get((13*i)+1)); //se agrega el estado inicial
        }
        datosSalida.add("LD"); //se agrega la funcion LOAD
        if((i+1)==10){
        datosSalida.add("10");
        }else if ((i+1)<10){ //se copian los 10 tiempos
        datosSalida.add("0"+(i+1));
        }else{ 
        datosSalida.add(i+1);
        }
        ceros=0;
        for(int j=0;j<10;j++){             
            datoC=Integer.parseInt(datosFicheroD.get((13*i)+(j+3)).toString());
            if(datoC==0){
            ceros++;
            }
            if(datoC>9){//ascii
            datoC=datoC+48;     
            char c = (char)datoC;                         
            datosSalida.add(c); //se agrega el retardo        
            }else {
            datosSalida.add(datoC); //se agrega el retardo        
            }                          
        }
        System.out.println("numero de datos válidos"+(10-ceros));
//        
//        datosSalida.add(10-ceros);// numero de ceros
//       // datosSalida.add("W"); //final de trama   
        
        if(ceros==0){//ascii       
        char d = (char)58; //10 + 48, se le manda 10 en ascii porque hay 10 datos validos                         
        datosSalida.add(d); //se agrega el retardo        
        }else{
        datosSalida.add(10-ceros); //se agrega el retardo        
        }                
        datosSalida.add("}"); //final de trama   
        
        }               
 
    datosSalida.add("ID"); //trama para inicializar
    datosSalida.add("A");    
    datosSalida.add("X");
    datosSalida.add("X");
    datosSalida.add("ST");
    datosSalida.add("XX");
    datosSalida.add("1");
    datosSalida.add("2");
    datosSalida.add("3");
    datosSalida.add("4");
    datosSalida.add("5");
    datosSalida.add("6");
    datosSalida.add("1");
    datosSalida.add("2");
    datosSalida.add("3");
    datosSalida.add("4");
    datosSalida.add("W");    
    datosSalida.add("}");    
    return datosSalida; //regresa tramas completas
    }
}

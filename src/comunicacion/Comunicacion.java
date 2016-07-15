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
    Comunicacion
 */
package comunicacion;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
/**
 * Esta clase permite la comunciacion serial entre el pc y el dispositivo.
 * Emplea la librería jsss para la busqueda de puertos, apertura, lectura, escritura y cierre.
 * @version 1.0
 * @author Stephanya
 */
public class Comunicacion extends Thread implements SerialPortEventListener{
    static SerialPort serialPort; //Variables para el puerto serial
    String NoPort = new String(); //Varibale para alamcenar el nombre del puertop
    ArrayList datosSalida;
    /**
     * Método constructor
     * @param NoPort
     */
    public Comunicacion(String NoPort, ArrayList datosTransmitir) {
        datosSalida=new ArrayList();
        
        for(int i=0;i<datosTransmitir.size();i++){
        datosSalida.add(i,datosTransmitir.get(i));
        //System.out.println(datosSalida.get(i));
        }
        System.out.println(datosSalida.size());
        
        AbrirPuertoSerie(NoPort); //Se recibe el nombre del puerto serial elejido en el otro jFrame y se abre para la 
                                 //comunicación                
    }
    
    /** Método para dejar listo el puerto serial para la recepción y envío de datos
     * @param nPort
    */
    public void AbrirPuertoSerie(String nPort ) {
    serialPort = new SerialPort(nPort);  // Crear el objeto 
    try {
      serialPort.openPort();//Abre el puerto serial
      serialPort.setParams(SerialPort.BAUDRATE_9600,  //Configuración del puerto serial
                           SerialPort.DATABITS_8,
                           SerialPort.STOPBITS_1,
                           SerialPort.PARITY_NONE);//Set params. Also you can set params by this string: serialPort.setParams(9600, 8, 1, 0);            
                           serialPort.addEventListener(this, SerialPort.MASK_RXCHAR);
    }        
    catch (SerialPortException ex) {
      System.out.println(ex);
    } 
}    
    /** Método para transmitir la data por el puerto serial
     * @param datosSalida     
     */
    public void run (){           
        try {
            Thread.sleep(2000);
           for(int k=0;k<19;k++){
             Thread.sleep(15000);
            for(int j=0;j<18;j++){ //Escribe los datos en el puerto               
               serialPort.writeString(datosSalida.get(k*18+j).toString());
               System.out.println(datosSalida.get(k*18+j));
            }
            System.out.println("duerme "+ k);           
           }
           serialPort.closePort(); // cierra el puerto       
         JOptionPane.showMessageDialog(null,"Las tramas han sido transmitidas con éxito");  
        }catch (SerialPortException ex) {
            System.out.println(ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Comunicacion.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }                                        

    
    /** Este método es el encargado de detectar si el puerto serial esta enviando datos
     * @param event evento
     */
    @Override
    public void serialEvent(SerialPortEvent event) {        
            try {                
                System.out.print(serialPort.readString()); // lee el dato del puerto serial
            }
            catch (SerialPortException ex) {
                System.out.println("Error in receiving string from COM-port: " + ex);
                }
            }              
}



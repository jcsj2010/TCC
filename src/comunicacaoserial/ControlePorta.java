/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacaoserial;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import javax.swing.JOptionPane;

/**
 *
 * @author JoseCarlos
 */
public class ControlePorta implements SerialPortEventListener{
  
  private SerialPort serialPort;
  private OutputStream serialOut;
  private static final int DATA_RATE = 9600;
  private static final int TIME_OUT = 2000;
  private String portaCOM = new String("COM3");
  private BufferedReader serialInput;
  

  
  
  
  /**
   * Construtor da classe ControlePorta
   * @param portaCOM - Porta COM que será utilizada para enviar os dados para o arduino
   * @param taxa - Taxa de transferência da porta serial geralmente é 9600
   */
  public ControlePorta(String portaCOM) {
    this.portaCOM = portaCOM;    
    this.initialize();
  }     
 
  public ControlePorta(){
      this.initialize(); 
  }
  /**
   * Médoto que verifica se a comunicação com a porta serial está ok
   */
  private void initialize() {
    try {
      //Define uma variável portId do tipo CommPortIdentifier para realizar a comunicação serial
      CommPortIdentifier portId = null;
      try {
        //Tenta verificar se a porta COM informada existe
        portId = CommPortIdentifier.getPortIdentifier(this.portaCOM);
      }catch (NoSuchPortException npe) {
        //Caso a porta COM não exista será exibido um erro 
        JOptionPane.showMessageDialog(null, "Porta COM não encontrada.",
                  "Porta COM", JOptionPane.PLAIN_MESSAGE);
      }
      //Abre a porta COM 
      serialPort = (SerialPort) portId.open("Comunicação serial", this.DATA_RATE);
      
      serialOut = serialPort.getOutputStream();
      serialInput = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
      
      
      serialPort.setSerialPortParams(this.DATA_RATE, //taxa de transferência da porta serial 
                               SerialPort.DATABITS_8, //taxa de 10 bits 8 (envio)
                               SerialPort.STOPBITS_1, //taxa de 10 bits 1 (recebimento)
                               SerialPort.PARITY_NONE); //receber e enviar dados
      
      // add event listeners
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
      
    }catch (Exception e) {
      e.printStackTrace();
    }
    
}
 
  /**
   * Método que fecha a comunicação com a porta serial
   */
  public void close() {
      if (serialPort != null) {
          serialPort.removeEventListener();
          serialPort.close();
      }
  }
 
  /**
   * @param opcao - Valor a ser enviado pela porta serial
   */
  public void enviaDados(int opcao){
    try {
      serialOut.write(opcao);//escreve o valor na porta serial para ser enviado
      
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(null, "Não foi possível enviar o dado. ",
                "Enviar dados", JOptionPane.PLAIN_MESSAGE);
    }
  } 

    @Override
    public void serialEvent(SerialPortEvent spe) {
       if (spe.getEventType() == SerialPortEvent.DATA_AVAILABLE ) {
			try {
				String inputLine = serialInput.readLine();
				System.out.println(inputLine);
			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}
		// Ignore all the other eventTypes, but you should consider the other ones.
    }
}

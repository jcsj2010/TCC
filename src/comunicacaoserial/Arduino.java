/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacaoserial;

import javax.swing.JButton;

/**
 *
 * @author JoseCarlos
 */
public class Arduino {
    private ControlePorta arduino;
    private Boolean isReceiver = true;
  /**
   * Construtor da classe Arduino
   */
  public Arduino(){
      arduino = new ControlePorta();//Windows - porta e taxa de transmissão
      //arduino = new ControlePorta("/dev/ttyUSB0",9600);//Linux - porta e taxa de transmissão
  }    
 
  /**
   * Envia o comando para a porta serial
   * @param button - Botão que é clicado na interface Java
   */
  public void EnviarArduino(JButton button) {        
    if("Ligar".equals(button.getActionCommand())){
      arduino.enviaDados(1);
      System.out.println(button.getText());//Imprime o nome do botão pressionado
    }
    else if("Desligar".equals(button.getActionCommand())){
      arduino.enviaDados(2);
      System.out.println(button.getText());
    }
    else{
      arduino.close();
      System.out.println(button.getText());//Imprime o nome do botão pressionado
    }
  }  
  
  public void ReceberArduino(){
    
}
  
  
  public static void main(String[] args){
      Arduino ard = new Arduino();
      
  }
}

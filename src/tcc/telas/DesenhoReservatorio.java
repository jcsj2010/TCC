/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.telas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author JoseCarlos
 */
public class DesenhoReservatorio extends JPanel implements Runnable{    
    Thread thread;
    private Integer nivel = 10;
    private float n = 0.0f;
    private Integer auxNivel = 10;
    private final Integer TAMANHO_RESERVATORIO = 4000;
    private String volReservatorio = "1";
    
    public DesenhoReservatorio() {
        super();
        setPreferredSize(new Dimension(130 , 400));        
        thread = new Thread(this);
        thread.start();
    }
    
    public void atualizaReservatorio(){
       
        try{
        auxNivel = Integer.parseInt(volReservatorio);
        }catch(ExceptionInInitializerError e){
            System.out.println(e.getMessage());
        }
                
        if(auxNivel<TAMANHO_RESERVATORIO && 0<=auxNivel){
            n = (float) (auxNivel/10.26);
            this.nivel = (int) n;
        }else{
            this.nivel =0;
        }
        
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLUE);
        g2.drawRect(0, 0, 121 , 390);
        
        g2.setColor(new Color(0,127,255));
        g2.fill3DRect(1, 390-nivel, 120, nivel, true);
    }
    
    

    @Override
    public synchronized void run() {                
        while(true){
        try{
            Thread.sleep(500);
            repaint();
            atualizaReservatorio();
        }catch(Exception e){
            System.out.println(e);
        }
        }
    }

    public void setVolReservatorio(String volReservatorio) {
        this.volReservatorio = volReservatorio;
    }

    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.telas;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author JoseCarlos
 */
public class DesenhoPeso extends JPanel implements Runnable{

    private String peso = "4576";
    Thread thread;
    
    public DesenhoPeso() {
        super();
        setPreferredSize(new Dimension(450, 400));
        thread = new Thread(this);
        thread.start();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        g2.setFont(new Font(Font.DIALOG, Font.BOLD, 100));
        FontMetrics fm = g.getFontMetrics( g2.getFont() );
        int larguraString = fm.stringWidth(peso);
        int alturaString = fm.getAscent();
        
        g2.setColor(Color.red);
        g2.draw3DRect(0, 0, 449, 220, true);        
        
        System.out.println(this.getClass().getName()+" "+ g2.getFont().getSize() );
        g2.drawString(peso, (449-larguraString)/2 , 140);        
    }

    public void atualizaPeso(String peso){     
        int auxPeso = Integer.parseInt(peso);
        if(auxPeso > 0){
        this.peso = peso;
        }else{
            this.peso = "0";
        }
    }
    
   
    
    @Override
    public synchronized void run() {        
        while(true){            
                atualizaPeso(peso);
                repaint();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(DesenhoPeso.class.getName()).log(Level.SEVERE, null, ex);
                }
         
        }
    }
}

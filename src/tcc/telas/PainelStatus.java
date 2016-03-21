/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.telas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author JoseCarlos
 */
public class PainelStatus extends JPanel implements Runnable{
    
    
    
    public PainelStatus(){
        super();
        setPreferredSize(new Dimension(140 , 400));
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        
    }
    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

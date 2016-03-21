package tcc.telas;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import tcc.padrao.Desenhavel;
import tcc.padrao.Visualizavel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JoseCarlos
 */
public class PainelMotores extends JPanel implements Visualizavel{

    
    PainelMotores(){
        super();
        configJanela();
    }
    @Override
    public void configJanela() {
        setPreferredSize(new Dimension(200, 440));
        setBackground(Color.WHITE);
        setBorder(new TitledBorder(new EtchedBorder(1),"MOTORES"));
    }

   

    @Override
    public void addHandler() {
        
    }

    @Override
    public void addComponente(Component component, int row, int column, int width, int height) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}

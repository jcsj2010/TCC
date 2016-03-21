/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.telas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import tcc.padrao.Visualizavel;

/**
 *
 * @author JoseCarlos
 */
public class PainelInferiorBotoes extends JPanel implements Visualizavel{
    private final GridBagLayout layout;
    private final GridBagConstraints constraints;
    private final JButton start;
    private final JButton stop;
    
    public PainelInferiorBotoes() {
        super();        
        layout = new GridBagLayout();
        setLayout(layout);
        constraints = new GridBagConstraints();    
        constraints.insets = new Insets(4, 4, 4, 4);
        configJanela();       
        
        ///INSTANCIA OS OBJETOS
        start = new JButton("START"); start.setEnabled(false);
        stop = new JButton("OFF ALL"); stop.setEnabled(false);
        ///////////////////////////////////
        
        //ADICIONA OS COMPONENTES NA TELA
        addComponente(start, 0, 0, 1, 1);
        addComponente(stop, 0, 1, 1, 1);
        //////////////////////////////////////
    }
        
    @Override
    public final void configJanela() {
        setPreferredSize(new Dimension(800, 50));
        setBackground(Color.ORANGE);
    }
    
    @Override
    public final void addComponente(Component component,int row, int column,int width, int height){   
        constraints.gridx = column; //coluna que componente sera colocado
        constraints.gridy = row; //linha que componente sera colocado
        constraints.gridwidth = width; //numero de colunas de ocupa
        constraints.gridheight = height; //numero de linhas que ocupa
        layout.setConstraints(component, constraints);
        add ( component );
    }

    @Override
    public void addHandler() {
        
    }

    public JButton getStart() {
        return start;
    }

    public JButton getStop() {
        return stop;
    }

    
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.telas;

import comunicacaoserial.ControlePortaSerial;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.*;
import java.util.TooManyListenersException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import tcc.padrao.Visualizavel;

/**
 *
 * @author JoseCarlos
 */
public class PainelSuperiorConfig extends JPanel implements Visualizavel{
    private final GridBagLayout layout;
    private final GridBagConstraints constraints;
    private final JButton button_parear;
    private final JLabel porta;
    private final JLabel taxa;
    private final JLabel pareado;
    private final JComboBox combo_porta;
    private final JComboBox combo_taxa;
    private final String[] porta_names;
    private final String[] porta_taxa;
    private final ControlePortaSerial controlePortaSerial;
    private final PainelInferiorBotoes painelInferiorBotoes;
    
    @SuppressWarnings("empty-statement")
    public PainelSuperiorConfig(ControlePortaSerial controlePortaSerial,PainelInferiorBotoes painelInferiorBotoes) {
        super();
        layout = new GridBagLayout();
        setLayout(layout);
        constraints = new GridBagConstraints();        
        constraints.insets = new Insets(4, 20, 4, 20);
        
        //////////INSTANCIA OS OBJETOS//////////////////////////////////////
        porta            = new JLabel("Porta Serial");
        taxa             = new JLabel("Taxa");
        this.porta_names = new String[]{"COM1", "COM2", "COM3", "COM4", "COM5", "COM6", "COM7", "COM8", "COM9"};
        this.porta_taxa  = new String[]{"9600"};        
        combo_porta      = new JComboBox(porta_names);       
        combo_porta.setSelectedIndex(2);
        combo_taxa       = new JComboBox(porta_taxa);   
        button_parear    = new JButton("PAREAR");
        pareado          = new JLabel("NÃO PAREADO"); pareado.setForeground(Color.red);
        this.controlePortaSerial = controlePortaSerial;
        this.painelInferiorBotoes = painelInferiorBotoes;
        ///////////////////////////////////////////////////////////////////
        
        ////ADICIONA OS COMPONENTES NA TELA
        addComponente(porta, 0, 0, 1, 1);
        addComponente(taxa, 1, 0, 1, 1);
        addComponente(combo_porta, 0, 1, 1, 1);
        addComponente(combo_taxa, 1, 1, 1, 1);
        addComponente(button_parear, 0, 2, 1, 1);
        addComponente(pareado, 1, 2, 1, 1);
        //////////////////////////////////////
        
        //////CHAMADA AOS MÉTODOS NESCESSÁRIOS PARA FUNCIONALIDADES DA TELA
        configJanela();        
        addHandler();
        ///////////////////////////////////////////////////////////////////
    }

    
    
    
    
    @Override
    public final void configJanela() {
        setPreferredSize(new Dimension(800, 100));
        setBackground(Color.WHITE);
        setBorder(new TitledBorder(new EtchedBorder(1),"PARAMETROS SERIAL"));
    }   

    /*
     *MÉTODO CRIA OS EVENTOS E SUAS AÇÕES DOS BOTÔES E COMBOBOX 
     */
    @Override
    public final void addHandler() {
        combo_porta.addItemListener((ItemEvent e) -> {
            if (e.getSource() == combo_porta){
                System.out.println("combo porta");                
                this.controlePortaSerial.setNomePorta((String) e.getItem());
            }
        });
        button_parear.addActionListener((ActionEvent e) -> {
            if(button_parear == e.getSource() ){
                System.out.println("botao parear");
                System.out.println( controlePortaSerial.getPareado() );
                try {
                    this.controlePortaSerial.parear();
                } catch (TooManyListenersException ex) {
                    Logger.getLogger(PainelSuperiorConfig.class.getName()).log(Level.SEVERE, null, ex);
                }
                if( controlePortaSerial.getPareado() ){                    
                    button_parear.setEnabled(false);
                    this.painelInferiorBotoes.getStart().setEnabled(true);
                    
                    this.pareado.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
                    this.pareado.setForeground(Color.BLUE);
                    this.pareado.setText("PORTA SERIAL: "+this.controlePortaSerial.getNomePorta()+" OK");
                    
                    this.combo_porta.setEnabled(false);
                    this.combo_taxa.setEnabled(false);
                    
                }
            }
        });
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
}
 
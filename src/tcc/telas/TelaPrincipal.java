package tcc.telas;


import comunicacaoserial.ControlePortaSerial;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import tcc.padrao.Visualizavel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 *[
and open the template in the editor.
 */

/**
 *
 * @author JoseCarlos
 */
public class TelaPrincipal extends JFrame implements Visualizavel{
    private final GridBagLayout layout;
    private final GridBagConstraints constraints;
    
    //Criação das variáveis dos paineis 
    private final PainelMotores painelMotores;
    private final PainelSuperiorConfig painelSuperiorConfig;
    private final PainelInferiorBotoes painelInferiorBotoes;
    private final PainelBalanca2 painelBalanca; 
    private  ControlePortaSerial controlePortaSerial;
    
    public TelaPrincipal(String titulo){
        super(titulo);
        layout = new GridBagLayout();
        setLayout(layout);
        constraints = new GridBagConstraints();
        
        ////Construção de todas as variáveis globais///////
        
        painelBalanca        = new PainelBalanca2();
        controlePortaSerial = new ControlePortaSerial(painelBalanca.getDesenhoReservatorio(),painelBalanca.getDesenhoPeso());
        painelMotores        = new PainelMotores();
        painelInferiorBotoes = new PainelInferiorBotoes();
        painelSuperiorConfig = new PainelSuperiorConfig(controlePortaSerial,painelInferiorBotoes);        
        
        ////////////////////////////////////////////////////
        addComponente(painelSuperiorConfig, 0,0,1,1);
        //addComponente(painelMotores, 1,0,1,1);
        addComponente(painelBalanca, 1,0,1,1);
        addComponente(painelInferiorBotoes, 2,0,1,1);
        
        ////Chamada aos métodos que são nescessários para construcao////

        
        configJanela(); //Configuracao padrao das janelas        
        
        addHandler(); //Adiciona os eventos
        
        ////////////////////////////////////////////////////////////////
        
    }
    
    
    
    @Override
    public final void configJanela(){        
        this.setResizable(true);
        this.setSize(900,900);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
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
    public final void addHandler() {
        
        
    }
    
    
    
    
    public static void main(String[] args){
        
        
        TelaPrincipal t = new TelaPrincipal("Esteira Envasadora");        
        t.setVisible(true);
        
        //Thread pinta = new Thread( t.painelBalanca.getDesenhoReservatorio() );
        //pinta.start();
    }
}



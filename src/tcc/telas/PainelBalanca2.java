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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author JoseCarlos
 */
public class PainelBalanca2 extends JPanel {

    private final GridBagLayout layout;
    private final GridBagConstraints constraints;
    private final DesenhoReservatorio desenhoReservatorio;
    private final DesenhoPeso DesenhoPeso;
    private final PainelStatus painelStatus;
    private final JLabel label_Reservatorio;
    private final JLabel label_Balanca;
    private final JLabel label_Status;
    int x = 1;

    

    public PainelBalanca2() {
        super();
        layout = new GridBagLayout();
        setLayout(layout);
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(4, 4, 4, 4);
        configJanela();
        
        //////CRIA INSTANCIA DOS OBJETOS/////////////////
        DesenhoPeso =       new DesenhoPeso(); 
        desenhoReservatorio =       new DesenhoReservatorio();
        label_Balanca =      new JLabel("PESO (g)");
        label_Reservatorio = new JLabel("RESERVATÓRIO");
        label_Status =       new JLabel("STATUS");
        painelStatus =       new PainelStatus();
        /////////////////////////////////////////////////
        
        addComponente(desenhoReservatorio, 1, 0, 1, 1 );
        addComponente( label_Reservatorio, 0, 0, 1, 1 );
        addComponente( label_Balanca, 0, 1, 1, 1 );
        addComponente( DesenhoPeso, 1, 1, 1, 1 );
        addComponente( label_Status, 0, 2, 1, 1 );
        addComponente( painelStatus, 1, 2, 1, 1 );        
    }

    public final void configJanela() {
        setPreferredSize(new Dimension(800, 500));
        setBackground(Color.WHITE);
        setBorder(new TitledBorder( new EtchedBorder(1), "RESERVATÓRIO" ));
    }

    public final void addComponente(Component component, int row, int column, int width, int height) {
        constraints.gridx = column; //coluna que componente sera colocado
        constraints.gridy = row; //linha que componente sera colocado
        constraints.gridwidth = width; //numero de colunas de ocupa
        constraints.gridheight = height; //numero de linhas que ocupa

        layout.setConstraints(component, constraints);
        add(component);
    }

    public DesenhoReservatorio getDesenhoReservatorio() {
        return desenhoReservatorio;
    }

    public DesenhoPeso getDesenhoPeso() {
        return DesenhoPeso;
    }
    
}

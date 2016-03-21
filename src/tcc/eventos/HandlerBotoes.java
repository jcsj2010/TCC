/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.eventos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import tcc.telas.PainelInferiorBotoes;

/**
 *
 * @author JoseCarlos
 */
public class HandlerBotoes implements ActionListener{
    private PainelInferiorBotoes painelInferiorBotoes;
    
    public HandlerBotoes(PainelInferiorBotoes painelInferiorBotoes) {
        this.painelInferiorBotoes = painelInferiorBotoes;
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("+")){
            
        }else{
            
        }
    }
    
}

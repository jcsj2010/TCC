/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.padrao;

import java.awt.Component;

/**
 *
 * @author JoseCarlos
 */
public interface Visualizavel {
    
    /** 
     * Método que configura as telas com seus valores iniciais
     */
    void configJanela();
    
    /**
     * Métodos que adiciona os paineis
     */
    void addComponente(Component component,int row, int column,int width, int height);
    
    /**
     * Adiciona handler de eventos
     */
    void addHandler();
}

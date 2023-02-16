/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jp.modelos;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.util.Duration;
import javax.swing.Timer;

/**
 *
 * @author Woly
 */
public class Tempo extends Timer {
    
    private int cont = 0;
    private ActionListener acao = null;
    private Timeline timeline = null;
    
    public ActionListener agir(int repeticoes, ActionListener acaoDeRepeticao, ActionListener acaoFinal){
        ActionListener acao = (e) -> {
            if(cont == repeticoes){
                if(acaoFinal != null) acaoFinal.actionPerformed(null);
                this.stop();
            }else{
                if(acaoDeRepeticao != null) acaoDeRepeticao.actionPerformed(null);
            }
            cont++;
        };
        
        return acao;
    }

    public Tempo(int delay, int repeticoes, ActionListener acaoDeRepeticao, ActionListener acaoFinal) {
        super(delay, null);
        
        //super.addActionListener(agir(repeticoes, acaoDeRepeticao, acaoFinal));
        acao = agir(repeticoes, acaoDeRepeticao, acaoFinal);
        
        
        timeline = new Timeline(
                new KeyFrame(
                Duration.ZERO, 
                (event) -> { this.acao.actionPerformed(null);}
                ), 
                new KeyFrame(
                        Duration.seconds(1)
                )
        );
        if(repeticoes < 0){
            timeline.setCycleCount(Timeline.INDEFINITE);
        }
        else timeline.setCycleCount(repeticoes);
        
        
    };
    
    public Tempo() {
        super(0, null);
        
        super.addActionListener(agir(0, null, null));
        
        
        timeline = new Timeline(
                new KeyFrame(
                Duration.ZERO, 
                (event) -> { this.acao.actionPerformed(null);}
                ), 
                new KeyFrame(
                        Duration.seconds(1)
                )
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        
    };
    
    @Override
    public void start(){
        timeline.play();
    }
    
    
    @Override
    public void stop(){
        timeline.stop();
    }

    public int getCont() {
        return cont;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package com.jp.visao;

import com.jp.modelos.Tempo;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 *
 * @author Woly
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML private Pane panelFundo;
    @FXML private ComboBox comboBox;
    @FXML private Label titulo;
    @FXML private Label labelDuracao;
    @FXML private Label labelPlay_Pause;
    @FXML private Label labelTempoAtual;
    @FXML private Label labelEstado;
    @FXML private Slider slider;
    
    //private
//    private Label label;
//    
    private void setDuracao(Duration duracao){
        labelDuracao.setText(Reprodutor.player.minutos(duracao.toSeconds()));
    }
    
    private void setDisable(boolean condicao){
        slider.setDisable(condicao);
        labelPlay_Pause.setDisable(condicao);
        labelTempoAtual.setDisable(condicao);
        labelDuracao.setDisable(condicao);
        if(condicao){
            labelEstado.setOpacity(1);
            labelTempoAtual.setText(Reprodutor.player.minutos(0));
            labelDuracao.setText(Reprodutor.player.minutos(0));
            slider.setMax(0);
        }else{
            labelEstado.setOpacity(0);
        }
    }
    
    private void ready(){
        slider.setValue(0);
        //labelTempoAtual.setText(Reprodutor.player.minutos(0));
        setDisable(true);
        
        MediaPlayer player = Reprodutor.player.getPlayer();
        
        ActionListener acaoDeRepeticao = (event) -> {
            labelTempoAtual.setText(Reprodutor.player.minutos(player.getCurrentTime().toSeconds()));
            slider.setValue(player.getCurrentTime().toSeconds());
        };
        
        player.setOnReady(() -> {
            player.pause();
            Reprodutor.player.setDuracao(player.getTotalDuration());
            setDuracao(Reprodutor.player.getDuracao());
            slider.setMax(player.getTotalDuration().toSeconds());
            Reprodutor.player.timer = new Tempo(1000, -1, acaoDeRepeticao, null);
            setDisable(false);
        });
        
        
        player.setOnPlaying(() -> {
            Reprodutor.player.timer.start();
        });
        
        player.setOnPaused(() -> {
            Reprodutor.player.timer.stop();
            labelTempoAtual.setText(Reprodutor.player.minutos(player.getCurrentTime().toSeconds()));
            slider.setValue(player.getCurrentTime().toSeconds());
        });
        Reprodutor.player.setPlayer(player);
    }
    
    @FXML
    private void btnclicked(ActionEvent event) {
        String itens[] = Reprodutor.player.importFiles();
        comboBox.setItems(FXCollections.observableArrayList());
        if(itens != null){
            if(itens.length != 0){
                comboBox.setDisable(false);

                comboBox.getItems().addAll(itens);
                comboBox.setValue(comboBox.getItems().get(0));
                //comboBox = new ComboBox<>(FXCollections.observableArrayList(itens));
                
                ready();
                return;
            }
        }
        comboBox.setDisable(true);
        slider.setDisable(true);
        slider.setMax(0);
        labelDuracao.setText(Reprodutor.player.minutos(0));
        
    }
    
    @FXML
    private void playPauseClicked(MouseEvent event) {
        String saida = Reprodutor.player.playPauseAction(labelPlay_Pause.getText(), slider.getValue());
        
        if(saida != null) labelPlay_Pause.setText(saida);
        
    }
    
    @FXML
    private void comboBoxClicked(ActionEvent event){
        if(!comboBox.getItems().isEmpty()){
            int index = comboBox.getItems().lastIndexOf(comboBox.getValue());
            boolean mudou = Reprodutor.player.comboBoxAction(index);
            if(mudou) {
                if(labelPlay_Pause.getText().equals("II")) Reprodutor.player.timer.stop();
                labelPlay_Pause.setText("▶");
                ready();
            }
        }
        
    }
    
    @FXML
    private void sliderMouseDragged(MouseEvent event){
        labelTempoAtual.setText(Reprodutor.player.minutos(slider.getValue()));
        Reprodutor.player.timer.stop();
    }
    
    @FXML
    private void sliderMouseReleased(MouseEvent event){
        if(Reprodutor.player.getPlayer().getStatus() == MediaPlayer.Status.PLAYING){
            Reprodutor.player.getPlayer().seek(Duration.seconds(slider.getValue()));
            Reprodutor.player.timer.start();
        }
        labelTempoAtual.setText(Reprodutor.player.minutos(slider.getValue()));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //panelFundo.setStyle("-fx-background-color: #000");
    }    
    
}

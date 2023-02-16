/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package com.jp.visao;

import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 *
 * @author Woly
 */
public class Reprodutor extends Application {
    
    public static Player player = null;
    public static Stage stage = null;
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Reprodutor");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        player = new Player();
        //File musica = new File("C:\\Users\\Woly\\Desktop\\Arquivos da Área de Trabalho\\Músicas\\Cornfield Chase.mp3");
        //Media media = new Media(musica.toURI().toString());
        //MediaPlayer player = new MediaPlayer(media);
        //player.play();
        launch(args);
    }
    
}

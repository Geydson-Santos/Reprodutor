/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jp.visao;

import com.jp.modelos.Tempo;
import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.DirectoryChooser;
import javafx.util.Duration;

/**
 *
 * @author Woly
 */
public class Player {
    
    
    private ArrayList<File> musicas = null;
    private File musicaAtual = null;
    private Media media = null;
    private MediaPlayer player = null;
    private Duration duracao = null;
    public Tempo timer = null;
    
//    private enum Estado {
//        NAOABERTA, ABERTA
//    };
//    
//    private Estado estadoDaMusicaAtual = Estado.NAOABERTA;
    
    //@FXML
    
    public Player(){
        
    }
    
    public String[] importFiles(){
        musicas = new ArrayList<File>();
        musicas.clear();
        DirectoryChooser dc = new DirectoryChooser();
        //ExploradorDeArquivos eda = new ExploradorDeArquivos();
        //fc.
        //eda.setFileSelectionMode(ExploradorDeArquivos.DIRECTORIES_ONLY);
        dc.setTitle("Escolha um diretório");
        File diretorio = dc.showDialog(Reprodutor.stage);
        if(diretorio != null && diretorio.isDirectory()){
            File arquivos[] = diretorio.listFiles();
            
            for(int i = 0; i < arquivos.length; i++){
                if(eMusica(arquivos[i])){
                    musicas.add(arquivos[i]);
                }
            }
            
            if(musicas.size() == -1) return null;
            String musicasDaPasta[] = new String[musicas.size()];
            if(musicasDaPasta.length > 0){
                for(int i = 0; i < musicasDaPasta.length; i++){
                    File musica = musicas.get(i);
                    musicasDaPasta[i] = musica.getName().replace("." + extensao(musica), "");
                }
                
                //FXMLDocumentController.comboBox.setDisable(false);
                //FXMLDocumentController.comboBox.getItems().addAll(musicasDaPasta);
                //comboBox.setModel(new DefaultComboBoxModel<>(musicasDaPasta));
                //comboBox.setEnabled(true);
                //jLabel2DPlayPause.setEnabled(true);
                musicaAtual = musicas.get(0);
                abrirMusica();
                return musicasDaPasta;
            }
        }
        return null;
    }
    
    
    private void abrirMusica(){
        media = new Media(musicaAtual.toURI().toString());
        player = new MediaPlayer(media);
        player.play();
    }
    
    private String extensao(File arquivo){
        String nomeDoArquivo = arquivo.getName();
        char letras[] = nomeDoArquivo.toCharArray();
        String extensao = "";
        
        for(int i = letras.length - 1; i >= 0 && letras[i] != '.'; i--){
            extensao += letras[i];
        }
        extensao = new StringBuilder(extensao).reverse().toString();
        System.out.println(extensao);
        return extensao;
    }
    
    private boolean eMusica(File arquivo){
        String extensoes[] = {
            "mp3", "flac"
        };
        
        String extensaoDoArquivo = extensao(arquivo);
        
        for(int i = 0; i < extensoes.length; i++){
            if(extensaoDoArquivo.equals(extensoes[i])){
                return true;
            }
        }
        
        return false;
    }
    
    public String playPauseAction(String labelText, double seconds){
        String newLabelText = null;
        if(player != null){
            if(labelText.equals("▶")){
                newLabelText = "II";
    //            if(estadoDaMusicaAtual == Estado.NAOABERTA){
    //                
    //            }
                try {
                    player.seek(Duration.seconds(seconds));
                    player.play();
                } catch (Exception e) {
                    e.printStackTrace();
                    newLabelText = null;
                }

    //            new Thread(){
    //
    //                public void run(){
    //                    try {
    //                        
    //                    } catch (Exception e) {
    //                        e.printStackTrace();
    //                    }
    //                }
    //            }.run();
            }else{
                newLabelText = "▶";
                try{
                    player.pause();
                }catch(Exception e){
                    e.printStackTrace();
                    newLabelText = null;
                }
            }
        }
        
        return newLabelText;
    }
    
    public boolean comboBoxAction(int index){
        if(musicas != null && musicaAtual != null){
            if(musicas.size() != -1){
                if(musicaAtual != musicas.get(index)){
                    //jLabel2DPlayPause.setText("▶");
                    musicaAtual = musicas.get(index);
                    try{
                        player.dispose();
                    }catch(Exception erro){
                        erro.printStackTrace();
                    }
                    abrirMusica();
                    return true;
                }
            }
            
        }
        
        return false;
    }
    
    public String minutos(double tempoSegundos){
        
        int segundos = (int) (tempoSegundos);
        
        int minutos = segundos / 60;
        segundos = segundos % 60;
        
        String tempo = "";
        
        int horas = minutos / 60;
        minutos -= horas * 60;
        
        if(horas > 0){
            tempo += horas;

            tempo += ":";

            if(minutos > 9) tempo += minutos;
            else tempo += "0" + minutos;

            tempo += ":";

            if(segundos > 9) tempo += segundos;
            else tempo += "0" + segundos;
        }else{
            tempo += minutos;

            tempo += ":";

            if(segundos > 9) tempo += segundos;
            else tempo += "0" + segundos;
            
        }
        
        return tempo;
    }

    public Duration getDuracao() {
        return duracao;
    }

    public void setDuracao(Duration duracao) {
        this.duracao = duracao;
    }

    public MediaPlayer getPlayer() {
        return player;
    }

    public void setPlayer(MediaPlayer player) {
        this.player = player;
    }
    
    
    
}

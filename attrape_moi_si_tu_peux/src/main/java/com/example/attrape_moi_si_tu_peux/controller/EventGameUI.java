package com.example.attrape_moi_si_tu_peux.controller;

import com.example.attrape_moi_si_tu_peux.Model.Labyrinthe;
import com.example.attrape_moi_si_tu_peux.view.GameUI;
import com.example.attrape_moi_si_tu_peux.view.Menu_demarrer;
import com.example.attrape_moi_si_tu_peux.view.Option;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Objects;

public class EventGameUI implements EventHandler {
    private Menu_demarrer menu;
    private GameUI gameUI;

    private Option option;

    public EventGameUI(Menu_demarrer menu) {
        this.menu = menu;
        option = new Option();
        option.setEventGameUI(this);
    }

    @Override
    public void handle(Event event) {
        if(event.getSource() instanceof Button){
            Button b = (Button) event.getSource();

            // Evenement de l'interface Option
            if(Objects.equals(((Button) event.getSource()).getId(), "bJouer")){
                if ( option.getXchoix() != 0 && option.getYchoix() != 0){
                    gameUI = new GameUI(option.getXchoix(), option.getYchoix());
                }else {
                    gameUI = new GameUI();
                }
                gameUI.setEventGameUI(this);
                gameUI.show();
                menu.close();
            }

            if(Objects.equals(((Button) event.getSource()).getId(), "bImport")) {
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Ouvrir votre fichier labyrinthe");

                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Fichiers texte (*.txt)", "*.txt");
                fileChooser.getExtensionFilters().add(extFilter);

                File fileSelected = fileChooser.showOpenDialog(stage);

                if (fileSelected != null) {
                    Labyrinthe lab = new Labyrinthe(fileSelected);
                    gameUI = new GameUI(lab);
                    Alert mesImport = new Alert(Alert.AlertType.INFORMATION, "Labyrinthe Chargé");
                    gameUI.setEventGameUI(this);
                    this.gameUI.show();
                    menu.close();
                    option.close();
                    mesImport.show();

                }
            }

            // Evenement de l'interface jeux
            if (Objects.equals(((Button) event.getSource()).getId(), "bSimulation")) {
                if (b.getText().equals("Pause")) {
                    b.setText("Reprendre simulation");
                    gameUI.pause();
                    gameUI.setRunning(false);

                }
                else {
                    b.setText("Pause");
                    this.gameUI.simulation();
                    gameUI.setRunning(true);

                }
            }
            if(!gameUI.isRunning()) {
                if (Objects.equals(((Button) event.getSource()).getId(), "bEdition")) {
                    if (gameUI.getEdition()) {
                        ((Button) event.getSource()).setText("Editer labyrinthe");
                        gameUI.activerEdition();
                    } else {
                        gameUI.activerEdition();
                        ((Button) event.getSource()).setText("Arreter edition");
                    }
                }
                if (Objects.equals(((Button) event.getSource()).getId(), "bEditionAnimal")) {
                    if (gameUI.getLab().getLesAnimaux().size() != 2) {
                        ((Button) event.getSource()).setText("Enregistrer emplacement");
                        gameUI.activerAddAnimal();
                    } else {
                        gameUI.activerAddAnimal();
                        ((Button) event.getSource()).setText("Ajouter animaux");
                    }
                }
                if (Objects.equals(((Button) event.getSource()).getId(), "bGeneLab")) {
                    this.gameUI.getLab().getLesAnimaux().clear();
                    this.gameUI.genererLab();
                }
                if (Objects.equals(((Button) event.getSource()).getId(), "bSauvegarder")) {
                    Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setTitle("Choisir l'emplacement de sauvegarde");
                    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers texte", "*.txt"));
                    fileChooser.setInitialFileName("labConf.txt");

                    File selectedFile = fileChooser.showSaveDialog(stage);
                    this.gameUI.getLab().sauvegarderLabyrinthe(selectedFile);
                }
            }

            // Evenement du Menu démarer
            if(Objects.equals(((Button) event.getSource()).getId(), "bOption")){
                option.show();
                menu.close();
            }

            // Autres Evenement
            if(Objects.equals(((Button) event.getSource()).getId(), "bRetour")){
                if(b.getScene().getWindow() instanceof Option){
                    option.close();
                }
                if(b.getScene().getWindow() instanceof GameUI){
                    gameUI.close();
                }
                menu.open();
            }

        }
    }


    public GameUI getGameUI() {
        return gameUI;
    }
}

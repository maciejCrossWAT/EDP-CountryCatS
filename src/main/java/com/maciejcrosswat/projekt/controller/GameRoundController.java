package com.maciejcrosswat.projekt.controller;

import com.maciejcrosswat.projekt.Main;
import com.maciejcrosswat.projekt.SceneName;
import javafx.concurrent.Worker;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class GameRoundController {
    private Stage stage;

    /** Must inject a stage */
    public GameRoundController(Stage stage) {
        if (stage == null) {
            throw new IllegalArgumentException("Stage cannot be null");
        }

        this.stage = stage;
    }

    public void handleOnPressMenuButton(MouseEvent event) {
        stage.setScene(Main.getScenes().get(SceneName.MENU));

        // serwis aktualizacji wyniku
        if (Main.gameScoreResetService.getState().equals(Worker.State.READY)) {
            Main.gameScoreResetService.start();
        } else {
            Main.gameScoreResetService.restart();
        }

        // wyłączenie innych serwisów
        Main.getQuestionTimerService.cancel();
        Main.roundNumber.set(0);
        Main.roundString.set("0");
    }

    /** Display main scene when the "back" button is clicked */
    public void handleNewRound(MouseEvent event) {
        Node node = (Node) event.getSource() ;
        int value = (Integer)node.getUserData();
        System.out.println("User chosen index: " + value);

        // pobieranie danych do pytania
        if (Main.resetQuestionInformationService.getState().equals(Worker.State.READY)) {
            Main.resetQuestionInformationService.start();
        } else {
            Main.resetQuestionInformationService.restart();
        }

        // pobieranie danych do pytania
        if (Main.getQuestionInformationService.getState().equals(Worker.State.READY)) {
            Main.getQuestionInformationService.start();
        } else {
            Main.getQuestionInformationService.restart();
        }

        // serwis aktualizacji progress baru
        if (Main.getQuestionTimerService.getState().equals(Worker.State.READY)) {
            Main.getQuestionTimerService.start();
        } else {
            Main.getQuestionTimerService.restart();
        }

        // serwis aktuazalicji nr rundy
        if (Main.getRoundNumberService.getState().equals(Worker.State.READY)) {
            Main.getRoundNumberService.start();
        } else {
            Main.getRoundNumberService.restart();
        }

        // serwis aktualizacji wyniku
        if (Integer.valueOf(value).equals(Main.getQuestionProperties().getCorrectAnswerIndex().get())) {
            if (Main.gameScoreSetService.getState().equals(Worker.State.READY)) {
                Main.gameScoreSetService.start();
            } else {
                Main.gameScoreSetService.restart();
            }
        }

        // zakończenie rozgrywki
        if (Main.roundNumber.get() == Main.maxRoundNumber) {
            stage.setScene(Main.getScenes().get(SceneName.GAME_ENDING));
        }
    }
}

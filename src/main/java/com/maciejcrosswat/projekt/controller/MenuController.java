package com.maciejcrosswat.projekt.controller;

import com.maciejcrosswat.projekt.Main;
import com.maciejcrosswat.projekt.SceneName;
import javafx.concurrent.Worker;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.time.Instant;
import java.util.Timer;

public class MenuController {
    private Stage stage;

    /** Inject the stage from {@link Main} */
    public MenuController(Stage stage) {
        this.stage = stage;
    }

    /** Display the game round scene */
    public void handleOnPressStartButton(MouseEvent event) {
        stage.setScene(Main.getScenes().get(SceneName.GAME_ROUND));
        Main.timeStart = Instant.now();

        if (Main.resetQuestionInformationService.getState().equals(Worker.State.READY)) {
            Main.resetQuestionInformationService.start();
        } else {
            Main.resetQuestionInformationService.restart();
        }

        if (Main.getQuestionInformationService.getState().equals(Worker.State.READY)) {
            Main.getQuestionInformationService.start();
        } else {
            Main.getQuestionInformationService.restart();
        }

        if (Main.getQuestionTimerService.getState().equals(Worker.State.READY)) {
            Main.getQuestionTimerService.start();
        } else {
            Main.getQuestionTimerService.restart();
        }

        if (Main.getRoundNumberService.getState().equals(Worker.State.READY)) {
            Main.getRoundNumberService.start();
        } else {
            Main.getRoundNumberService.restart();
        }
    }

    /** Display the ranking scene */
    public void handleOnPressRankingButton(MouseEvent event) {
        stage.setScene(Main.getScenes().get(SceneName.RANKING));
    }
}

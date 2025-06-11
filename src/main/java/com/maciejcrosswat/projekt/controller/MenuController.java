package com.maciejcrosswat.projekt.controller;

import com.maciejcrosswat.projekt.Main;
import com.maciejcrosswat.projekt.SceneName;
import javafx.concurrent.Worker;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MenuController {
    private Stage stage;

    /** Inject the stage from {@link Main} */
    public MenuController(Stage stage) {
        this.stage = stage;
    }

    /** Display the game round scene */
    public void handleOnPressStartButton(MouseEvent event) {
        stage.setScene(Main.getScenes().get(SceneName.GAME_ROUND));

        if (
                Main.getQuestionInformationService.getState()
                        .equals(Worker.State.SUCCEEDED) ||
                        Main.getQuestionInformationService.getState()
                                .equals(Worker.State.CANCELLED)) {
            Main.getQuestionInformationService.restart();
        } else {
            Main.getQuestionInformationService.start();
        }

        if (
                Main.getQuestionTimerService.getState()
                        .equals(Worker.State.SUCCEEDED) ||
                Main.getQuestionTimerService.getState()
                        .equals(Worker.State.CANCELLED)) {
            Main.getQuestionTimerService.restart();
        } else {
            Main.getQuestionTimerService.start();
        }
    }

    /** Display the ranking scene */
    public void handleOnPressRankingButton(MouseEvent event) {
        stage.setScene(Main.getScenes().get(SceneName.RANKING));
    }
}

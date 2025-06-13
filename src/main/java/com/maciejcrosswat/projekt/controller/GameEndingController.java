package com.maciejcrosswat.projekt.controller;

import com.maciejcrosswat.projekt.Main;
import com.maciejcrosswat.projekt.SceneName;
import com.maciejcrosswat.projekt.data.Question;
import com.maciejcrosswat.projekt.data.QuestionCategory;
import com.maciejcrosswat.projekt.data.QuestionProperties;
import javafx.concurrent.Worker;
import javafx.event.Event;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class GameEndingController {
    private Stage stage;

    /** Must inject a stage */
    public GameEndingController(Stage stage) {
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

        // serwis aktualizacji wyniku
        if (Main.resetQuestionInformationService.getState().equals(Worker.State.READY)) {
            Main.resetQuestionInformationService.start();
        } else {
            Main.resetQuestionInformationService.restart();
        }
    }
}

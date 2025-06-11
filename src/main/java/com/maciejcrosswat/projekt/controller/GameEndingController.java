package com.maciejcrosswat.projekt.controller;

import com.maciejcrosswat.projekt.Main;
import com.maciejcrosswat.projekt.SceneName;
import javafx.event.Event;
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

    /** Display main scene when the "back" button is clicked */
    public void handleMousePress(Event event) {
        stage.setScene(Main.getScenes().get(SceneName.MENU));
    }
}

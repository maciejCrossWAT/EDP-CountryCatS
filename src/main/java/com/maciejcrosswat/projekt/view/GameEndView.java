package com.maciejcrosswat.projekt.view;

import com.maciejcrosswat.projekt.controller.GameEndingController;
import com.maciejcrosswat.projekt.controller.GameRoundController;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GameEndView implements IView {

    private Stage stage;

    /** Must inject a stage */
    public GameEndView(Stage stage) {
        this.stage = stage;
    }

    @Override
    public Scene getScene() {

        // Inject stage from Main into controller
        GameEndingController controller = new GameEndingController(stage);

        // root element
        BorderPane root = new BorderPane();

        Scene scene = new Scene(root, 810, 540);
        return scene;
    }
}

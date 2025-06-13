package com.maciejcrosswat.projekt.view;

import com.maciejcrosswat.projekt.Main;
import com.maciejcrosswat.projekt.controller.GameEndingController;
import com.maciejcrosswat.projekt.controller.GameRoundController;
import com.maciejcrosswat.projekt.data.Colors;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
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

        // scene label
        Label sceneLabel = new Label("Game ended!");
        sceneLabel.setFont(new Font("System Bold", 32));
        sceneLabel.setAlignment(Pos.CENTER);

        // point total label
        Label pointTextBeginningLabel = new Label("You received ");
        pointTextBeginningLabel.setFont(new Font("System Bold", 24));

        Label pointValueLabel = new Label("10");
        pointValueLabel.textProperty().bind(Main.scoreString);
        pointValueLabel.setFont(new Font("System Bold", 24));

        Label pointTextEndingLabel = new Label("/" + Main.correctAnswerPointIncrease*Main.maxRoundNumber + " points!");
        pointTextEndingLabel.setFont(new Font("System Bold", 24));

        // point total container
        HBox pointTotalContainer = new HBox();
        pointTotalContainer.setPrefWidth(200);
        pointTotalContainer.getChildren().addAll(pointTextBeginningLabel, pointValueLabel, pointTextEndingLabel);
        pointTotalContainer.setAlignment(Pos.CENTER);

        // content container
        VBox contentContainer = new VBox();
        contentContainer.getChildren().addAll(sceneLabel, pointTotalContainer);
        contentContainer.setPrefWidth(200);
        contentContainer.setAlignment(Pos.CENTER);

        // menu button
        Button menuButton = new Button("Wróć");
        menuButton.setOnMousePressed(controller::handleOnPressMenuButton);
        menuButton.setFont(new Font("System Bold", 24));
        contentContainer.setPrefWidth(200);

        HBox buttonContainer = new HBox();
        buttonContainer.getChildren().add(menuButton);
        buttonContainer.setAlignment(Pos.CENTER);

        // main container
        BorderPane container = new BorderPane();
        container.setStyle(String.format("-fx-background-color: %s", Colors.primary));
        container.setPrefWidth(300);
        container.setCenter(contentContainer);
        container.setBottom(buttonContainer);
        container.setPadding(new Insets(40));

        // root element
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        root.setCenter(container);

        Scene scene = new Scene(root, 810, 540);
        return scene;
    }
}

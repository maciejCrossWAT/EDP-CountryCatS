package com.maciejcrosswat.projekt.view;

import com.maciejcrosswat.projekt.Main;
import com.maciejcrosswat.projekt.controller.GameRoundController;
import com.maciejcrosswat.projekt.data.Colors;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class GameRoundView implements IView {

    private Stage stage;

    /** Must inject a stage */
    public GameRoundView(Stage stage) {
        this.stage = stage;
    }

    @Override
    public Scene getScene() {

        // Inject stage from Main into controller
        GameRoundController controller = new GameRoundController(stage);

        // root element
        BorderPane root = new BorderPane();

        // Making timer bar
        ProgressBar timerBar = new ProgressBar();
        timerBar.setPrefWidth(800);
        timerBar.setPrefHeight(50);
        timerBar.progressProperty().bind(Main.getQuestionTimerService.progressProperty());

        // Making new timer thread

        // Making bottom container
        BorderPane bottomContainer = new BorderPane();
        bottomContainer.setCenter(timerBar);
        bottomContainer.setPadding(new Insets(0, 10, 0, 10));

        // Making answer elements
        List<Pane> answerPanes = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            BorderPane pane = new BorderPane();

            Label answer = new Label("answer " + i);
            answer.textProperty().bind(Main.questionProperties.getAnswer(i));
            answer.setFont(new Font("System Bold", 20));
            pane.setUserData(i);

            pane.setPrefWidth(390);
            pane.setPrefHeight(210);
            pane.setStyle(String.format("-fx-background-color: %s", Colors.primary));
            pane.setOnMousePressed(controller::handleNewRound);
            pane.setCenter(answer);
            pane.setOnMouseEntered(event -> {
                pane.setStyle(String.format("-fx-background-color: %s", Colors.accent));
            });
            pane.setOnMouseExited(event -> {
                pane.setStyle(String.format("-fx-background-color: %s", Colors.primary));
            });

            answerPanes.add(pane);
        }

        // Making grid element
        GridPane answerGrid = new GridPane();
        answerGrid.add(answerPanes.get(0), 0, 0);
        answerGrid.add(answerPanes.get(1), 1, 0);
        answerGrid.add(answerPanes.get(2), 0, 1);
        answerGrid.add(answerPanes.get(3), 1, 1);
        answerGrid.setHgap(10);
        answerGrid.setVgap(10);
        answerGrid.setStyle(String.format("-fx-background-color: %s", Colors.secondary));
        answerGrid.setPrefWidth(800);

        // Making question element
        Label questionLabel = new Label("Question");
        questionLabel.setFont(new Font("System Bold", 20));
        questionLabel.setAlignment(Pos.TOP_LEFT);
        questionLabel.textProperty().bind(Main.questionProperties.getQuestion());

        // Making center container
        VBox centerContainer = new VBox();
        centerContainer.getChildren().addAll(questionLabel, answerGrid);
        centerContainer.setPrefWidth(800);
        centerContainer.setPadding(new Insets(10));
        centerContainer.disableProperty().bind(Main.isRoundAnswerContainerDisabled);

        // Making round title
        Label roundLabel = new Label("Round: ");
        roundLabel.setFont(new Font("System Bold", 32));
        roundLabel.setAlignment(Pos.CENTER);

        Label roundValueLabel = new Label();
        roundValueLabel.textProperty().bind(Main.roundString);
        roundValueLabel.setFont(new Font("System Bold", 32));
        roundValueLabel.setAlignment(Pos.TOP_CENTER);

        BorderPane roundLabelContainer = new BorderPane();
        roundLabelContainer.setLeft(roundLabel);
        roundLabelContainer.setRight(roundValueLabel);

        // Making flag container
        HBox flagContainer = new HBox();
        flagContainer.getChildren().addAll(Main.mainFlagImage, Main.oddFlagImage);
        flagContainer.setAlignment(Pos.TOP_CENTER);

        // Go back button
        Button menuButton = new Button("Wróć");
        menuButton.setOnMousePressed(controller::handleOnPressMenuButton);
        menuButton.setFont(new Font("System Bold", 24));

        // Making top container
        BorderPane topContainer = new BorderPane();
        topContainer.setStyle(String.format("-fx-background-color: %s", Colors.primary));
        topContainer.setLeft(roundLabelContainer);
        topContainer.setCenter(flagContainer);
        topContainer.setRight(menuButton);
        topContainer.setPadding(new Insets(10));
        topContainer.setPrefHeight(80);

        root.setTop(topContainer);
        root.setCenter(centerContainer);
        root.setBottom(bottomContainer);

        Scene scene = new Scene(root, 810, 540);
        return scene;
    }
}

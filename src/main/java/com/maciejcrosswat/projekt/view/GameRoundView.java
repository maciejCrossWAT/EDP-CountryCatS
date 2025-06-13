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
        BorderPane pane1 = new BorderPane();

        Label answer1 = new Label("answer " + 0);
        answer1.textProperty().bind(Main.questionProperties.getAnswer0());
        answer1.textProperty();
        answer1.setFont(new Font("System Bold", 20));
        pane1.setUserData(0);

        pane1.setPrefWidth(390);
        pane1.setPrefHeight(210);
        pane1.setStyle(String.format("-fx-background-color: %s", Colors.primary));
        pane1.setOnMousePressed(controller::handleNewRound);
        pane1.setCenter(answer1);
        pane1.setOnMouseEntered(event -> {
            pane1.setStyle(String.format("-fx-background-color: %s", Colors.accent));
        });
        pane1.setOnMouseExited(event -> {
            pane1.setStyle(String.format("-fx-background-color: %s", Colors.primary));
        });

        BorderPane pane2 = new BorderPane();

        Label answer2 = new Label("answer " + 0);
        answer2.textProperty().bind(Main.questionProperties.getAnswer1());
        answer2.textProperty();
        answer2.setFont(new Font("System Bold", 20));
        pane2.setUserData(1);

        pane2.setPrefWidth(390);
        pane2.setPrefHeight(210);
        pane2.setStyle(String.format("-fx-background-color: %s", Colors.primary));
        pane2.setOnMousePressed(controller::handleNewRound);
        pane2.setCenter(answer2);
        pane2.setOnMouseEntered(event -> {
            pane2.setStyle(String.format("-fx-background-color: %s", Colors.accent));
        });
        pane2.setOnMouseExited(event -> {
            pane2.setStyle(String.format("-fx-background-color: %s", Colors.primary));
        });

        BorderPane pane3 = new BorderPane();

        Label answer3 = new Label("answer " + 0);
        answer3.textProperty().bind(Main.questionProperties.getAnswer2());
        answer3.textProperty();
        answer3.setFont(new Font("System Bold", 20));
        pane3.setUserData(2);

        pane3.setPrefWidth(390);
        pane3.setPrefHeight(210);
        pane3.setStyle(String.format("-fx-background-color: %s", Colors.primary));
        pane3.setOnMousePressed(controller::handleNewRound);
        pane3.setCenter(answer3);
        pane3.setOnMouseEntered(event -> {
            pane3.setStyle(String.format("-fx-background-color: %s", Colors.accent));
        });
        pane3.setOnMouseExited(event -> {
            pane3.setStyle(String.format("-fx-background-color: %s", Colors.primary));
        });

        BorderPane pane4 = new BorderPane();

        Label answer4 = new Label("answer " + 0);
        answer4.textProperty().bind(Main.questionProperties.getAnswer3());
        answer4.textProperty();
        answer4.setFont(new Font("System Bold", 20));
        pane4.setUserData(3);

        pane4.setPrefWidth(390);
        pane4.setPrefHeight(210);
        pane4.setStyle(String.format("-fx-background-color: %s", Colors.primary));
        pane4.setOnMousePressed(controller::handleNewRound);
        pane4.setCenter(answer4);
        pane4.setOnMouseEntered(event -> {
            pane4.setStyle(String.format("-fx-background-color: %s", Colors.accent));
        });
        pane4.setOnMouseExited(event -> {
            pane4.setStyle(String.format("-fx-background-color: %s", Colors.primary));
        });

        // Making grid element
        GridPane answerGrid = new GridPane();
        answerGrid.add(pane1, 0, 0);
        answerGrid.add(pane2, 1, 0);
        answerGrid.add(pane3, 0, 1);
        answerGrid.add(pane4, 1, 1);
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

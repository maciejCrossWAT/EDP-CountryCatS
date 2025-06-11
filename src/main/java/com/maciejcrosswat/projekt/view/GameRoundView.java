package com.maciejcrosswat.projekt.view;

import com.maciejcrosswat.projekt.Main;
import com.maciejcrosswat.projekt.controller.GameRoundController;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
            Pane pane = new Pane();

            String imageURL = "https://www.pexels.com/photo/red-concrete-brick-259915/";
            ImageView imageView = new ImageView();
            Image image = new Image(imageURL, true);
            imageView.setImage(image);

            pane.setPrefWidth(390);
            pane.setPrefHeight(210);
            pane.setStyle("-fx-background-color: #880000");
            pane.setOnMousePressed(controller::handleMousePress);
            pane.getChildren().add(imageView);

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
        answerGrid.setStyle("-fx-background-color: #669966");
        answerGrid.setPrefWidth(800);

        // Making question element
        Label questionLabel = new Label("Question");
        questionLabel.setFont(new Font("System Bold", 28));
        questionLabel.setAlignment(Pos.TOP_LEFT);
        questionLabel.textProperty().bind(Main.questionProperties.getQuestion());

        // Making center container
        VBox centerContainer = new VBox();
        centerContainer.getChildren().addAll(questionLabel, answerGrid);
        centerContainer.setPrefWidth(800);
        centerContainer.setPadding(new Insets(10));

        // Making round title
        Label roundLabel = new Label("Round 1");
        roundLabel.setFont(new Font("System Bold", 32));
        roundLabel.setAlignment(Pos.TOP_CENTER);

        // Making flag container
        Pane flagPane = new Pane();
        ImageView flagImage = new ImageView();
        String imageURL = "https://flagsapi.com/BE/flat/64.png";
        Image image = new Image(imageURL, true);
        flagImage.setImage(image);
        flagPane.getChildren().add(flagImage);

        // Making top container
        BorderPane topContainer = new BorderPane();
        topContainer.setStyle("-fx-background-color: #669966");
        topContainer.setLeft(roundLabel);
        topContainer.setRight(flagPane);
        topContainer.setPadding(new Insets(10));

        root.setTop(topContainer);
        root.setCenter(centerContainer);
        root.setBottom(bottomContainer);

        Scene scene = new Scene(root, 810, 540);
        return scene;
    }
}

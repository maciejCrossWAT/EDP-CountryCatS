package com.maciejcrosswat.projekt.view;

import com.maciejcrosswat.projekt.controller.MenuController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MenuView implements IView {

    private Stage stage;

    /** Must inject a stage */
    public MenuView(Stage stage) {
        this.stage = stage;
    }

    @Override
    public Scene getScene() {

        // Inject stage from Main into controller
        MenuController controller = new MenuController(stage);

        // Switch between scenes
        Button startButton = new Button("Start");
        startButton.setPrefWidth(100);
        startButton.setFont(new Font(24));
        startButton.setOnMousePressed(controller::handleOnPressStartButton);

        Button rankingButton = new Button("Ranking");
        rankingButton.setPrefWidth(150);
        rankingButton.setFont(new Font(24));
        rankingButton.setOnMousePressed(controller::handleOnPressRankingButton);

        // Label Title
        Label label = new Label("Country Cat-S");
        label.setFont(new Font("System Bold", 32));

        // Build scene
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(label, startButton, rankingButton);
        vbox.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setCenter(vbox);

        Button closeButton = new Button("Quit");
        closeButton.setPrefWidth(100);
        closeButton.setFont(new Font(24));
        closeButton.setOnMousePressed(e -> stage.close());

        ButtonBar bbar = new ButtonBar();
        bbar.setPadding(new Insets(10));
        bbar.getButtons().add(closeButton);
        root.setBottom(bbar);
        Scene scene = new Scene(root, 810, 540);

        return scene;
    }
}

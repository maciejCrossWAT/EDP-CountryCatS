package com.maciejcrosswat.projekt.view;

import com.maciejcrosswat.projekt.controller.GameEndingController;
import com.maciejcrosswat.projekt.controller.RankingController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class RankingView implements IView {

    private Stage stage;

    /** Must inject a stage */
    public RankingView(Stage stage) {
        this.stage = stage;
    }

    @Override
    public Scene getScene() {

        // Inject stage from Main into controller
        RankingController controller = new RankingController(stage);

        // root element
        BorderPane root = new BorderPane();

        // Header
        Label headerLabel = new Label("Ranking");
        headerLabel.setAlignment(Pos.TOP_LEFT);
        headerLabel.setFont(new Font("System Bold", 32));

        Button headerReturnButton = new Button("Wróć");
        headerReturnButton.setAlignment(Pos.TOP_RIGHT);

        HBox headerContainer = new HBox();
        headerContainer.getChildren().addAll(headerLabel, headerReturnButton);
        headerContainer.setPadding(new Insets(10));
        headerContainer.setStyle("-fx-background-color: #222266");

        // Ranking container
        VBox rankingList = new VBox();
        rankingList.setStyle("-fx-background-color: #226622");
        rankingList.setPadding(new Insets(5));

        Pane rankingContainer = new Pane();
        rankingContainer.getChildren().add(rankingList);
        rankingContainer.setStyle("-fx-background-color: #222266");
        rankingContainer.setPadding(new Insets(10));

        // Main container
        BorderPane container = new BorderPane();
        container.setTop(headerContainer);
        container.setCenter(rankingContainer);
        root.setCenter(container);

        Scene scene = new Scene(root, 810, 540);
        return scene;
    }
}

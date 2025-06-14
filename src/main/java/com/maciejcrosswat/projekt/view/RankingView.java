package com.maciejcrosswat.projekt.view;

import com.maciejcrosswat.projekt.Main;
import com.maciejcrosswat.projekt.controller.GameEndingController;
import com.maciejcrosswat.projekt.controller.RankingController;
import com.maciejcrosswat.projekt.data.Colors;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
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
        headerLabel.setAlignment(Pos.CENTER_LEFT);
        headerLabel.setFont(new Font("System Bold", 32));

        Button headerReturnButton = new Button("Wróć");
        headerReturnButton.setPrefWidth(100);
        headerReturnButton.setFont(new Font("System Bold", 20));
        headerReturnButton.setOnMousePressed(controller::handleOnPressMenuButton);

        BorderPane headerContainer = new BorderPane();
        headerContainer.setLeft(headerLabel);
        headerContainer.setRight(headerReturnButton);
        headerContainer.setPrefWidth(800);
        headerContainer.setPadding(new Insets(10));

        // Ranking container
        BorderPane rankingContainer = new BorderPane();
        rankingContainer.setCenter(Main.rankingContainer);
        rankingContainer.setPadding(new Insets(10));

        // Main container
        BorderPane container = new BorderPane();
        container.setTop(headerContainer);
        container.setCenter(Main.rankingContainer);
        root.setCenter(container);

        Scene scene = new Scene(root, 810, 540);
        return scene;
    }
}

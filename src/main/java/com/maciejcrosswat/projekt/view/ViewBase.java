package com.maciejcrosswat.projekt.view;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ViewBase implements IView {
    private Stage stage;
    private String labelText;
    private EventHandler<? super MouseEvent> handler;

    public ViewBase(Stage stage, String labelText, EventHandler<? super MouseEvent> handler) {
        if (stage == null) {
            throw new IllegalArgumentException("Stage cannot be null");
        }

        if (handler == null) {
            throw new IllegalArgumentException("Handler cannot be null");
        }

        this.stage = stage;
        this.labelText = labelText;
        this.handler = handler;
    }

    @Override
    public Scene getScene() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        Label label = new Label(labelText);
        label.setFont(new Font(32));
        root.setCenter(label);

        Button backButton = new Button("Back");
        backButton.setOnMousePressed(handler);
        Button closeButton = new Button("Close");
        closeButton.setOnMousePressed(e -> stage.close());

        ButtonBar bbar = new ButtonBar();
        bbar.setPadding(new Insets(10, 0, 0, 10));
        bbar.getButtons().addAll(backButton, closeButton);
        root.setBottom(bbar);

        return new Scene(root);
    }
}

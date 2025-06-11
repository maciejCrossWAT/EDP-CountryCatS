package com.maciejcrosswat.projekt;

import com.maciejcrosswat.projekt.data.Question;
import com.maciejcrosswat.projekt.data.QuestionCategory;
import com.maciejcrosswat.projekt.data.QuestionProperties;
import com.maciejcrosswat.projekt.service.GetQuestionInformationService;
import com.maciejcrosswat.projekt.service.GetQuestionTimerService;
import com.maciejcrosswat.projekt.view.GameEndView;
import com.maciejcrosswat.projekt.view.GameRoundView;
import com.maciejcrosswat.projekt.view.MenuView;
import com.maciejcrosswat.projekt.view.RankingView;
import javafx.application.Application;
import javafx.beans.value.ObservableStringValue;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.*;

public class Main extends Application {

    public static GetQuestionTimerService getQuestionTimerService = new GetQuestionTimerService();
    public static GetQuestionInformationService getQuestionInformationService = new GetQuestionInformationService();

    public static Question question = getQuestion();
    public static QuestionProperties questionProperties = getQuestionProperties();
    public static int roundNumber;
    public static final int maxRoundNumber = 3;
    public static ObservableStringValue questionText;

    private static Map<SceneName, Scene> scenes = new HashMap<>();

    @Override
    public void start(Stage stage) {

        getQuestionInformationService.setOnSucceeded(
                t -> questionProperties = getQuestionInformationService.getValue());

        scenes.put(SceneName.MENU, new MenuView(stage).getScene());
        scenes.put(SceneName.GAME_ROUND, new GameRoundView(stage).getScene());
        scenes.put(SceneName.GAME_ENDING, new GameEndView(stage).getScene());
        scenes.put(SceneName.RANKING, new RankingView(stage).getScene());

        // Start with the main scene
        stage.setScene(scenes.get(SceneName.MENU));
        stage.setTitle("Country Cat-S");
        stage.setResizable(false);
        stage.show();
    }

    /** Returns a Map of the scenes by {@link SceneName} */
    public static Map<SceneName, Scene> getScenes() {
        return scenes;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Question getQuestion() {
        Question question = new Question(
                QuestionCategory.FOOD,
                "Finland",
                "Sweden",
                "Which of these foods are not from Fineland?",
                new String[]{"swedish meatballs","finnish food 1","finnish food 2","finnish food 3"},
                3
        );

        return question;
    }

    public static QuestionProperties getQuestionProperties() {
        String[] answers = question.getAnswers();

        QuestionProperties questionProperties = new QuestionProperties(
                question.getMainCountry(),
                question.getQuestion(),
                answers,
                question.getCorrectAnswerIndex()
        );

        return questionProperties;
    }
}
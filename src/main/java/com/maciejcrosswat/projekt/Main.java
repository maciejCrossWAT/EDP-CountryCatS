package com.maciejcrosswat.projekt;

import com.maciejcrosswat.projekt.data.Question;
import com.maciejcrosswat.projekt.data.QuestionCategory;
import com.maciejcrosswat.projekt.data.QuestionProperties;
import com.maciejcrosswat.projekt.service.*;
import com.maciejcrosswat.projekt.view.GameEndView;
import com.maciejcrosswat.projekt.view.GameRoundView;
import com.maciejcrosswat.projekt.view.MenuView;
import com.maciejcrosswat.projekt.view.RankingView;
import javafx.application.Application;
import javafx.beans.property.*;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.*;

public class Main extends Application {

    // services
    public static GetQuestionTimerService getQuestionTimerService = new GetQuestionTimerService();
    public static GetQuestionInformationService getQuestionInformationService = new GetQuestionInformationService();
    public static ResetQuestionInformationService resetQuestionInformationService = new ResetQuestionInformationService();
    public static GetRoundNumberService getRoundNumberService = new GetRoundNumberService();
    public static GameScoreSetService gameScoreSetService = new GameScoreSetService();
    public static GameScoreResetService gameScoreResetService = new GameScoreResetService();

    // application variables
    public static Question question = getQuestion();
    public static QuestionProperties questionProperties = getQuestionProperties();

    public static IntegerProperty roundNumber = new SimpleIntegerProperty(0);
    public static StringProperty roundString = new SimpleStringProperty("0");
    public static final int maxRoundNumber = 5;

    public static IntegerProperty scoreNumber = new SimpleIntegerProperty(0);
    public static StringProperty scoreString = new SimpleStringProperty("0");
    public static final int correctAnswerPointIncrease = 10;

    public static ImageView mainFlagImage = new ImageView();
    public static ImageView oddFlagImage = new ImageView();

    public static BooleanProperty isRoundAnswerContainerDisabled = new SimpleBooleanProperty(false);
    public static BooleanProperty canTimerMove = new SimpleBooleanProperty(false);

    private static Map<SceneName, Scene> scenes = new HashMap<>();

    @Override
    public void start(Stage stage) {
        getQuestionInformationService.setOnSucceeded(
            t -> {
                QuestionProperties newProps = getQuestionInformationService.getValue();
                questionProperties.setMainCountry(newProps.getMainCountry().get());
                questionProperties.setQuestion(newProps.getQuestion().get());
                for (int i = 0; i < 4; i++) {
                    questionProperties.setAnswer(newProps.getAnswer(i).get(), i);
                    System.out.println("transfered answer nr " + i + ": " + questionProperties.getAnswer(i));
                }
                questionProperties.setCorrectAnswerIndex(newProps.getCorrectAnswerIndex().get());
            }
        );

        getQuestionInformationService.setOnFailed(
                t -> {
                    if (Main.getQuestionInformationService.getState().equals(Worker.State.READY)) {
                        Main.getQuestionInformationService.start();
                    } else {
                        Main.getQuestionInformationService.restart();
                    }
                }
        );

        getQuestionTimerService.setOnSucceeded(
                t -> {
                    // pobieranie danych do pytania
                    if (Main.resetQuestionInformationService.getState().equals(Worker.State.READY)) {
                        Main.resetQuestionInformationService.start();
                    } else {
                        Main.resetQuestionInformationService.restart();
                    }

                    // pobieranie danych do pytania
                    if (Main.getQuestionInformationService.getState().equals(Worker.State.READY)) {
                        Main.getQuestionInformationService.start();
                    } else {
                        Main.getQuestionInformationService.restart();
                    }

                    // serwis aktualizacji progress baru
                    if (Main.getQuestionTimerService.getState().equals(Worker.State.READY)) {
                        Main.getQuestionTimerService.start();
                    } else {
                        Main.getQuestionTimerService.restart();
                    }

                    // serwis aktuazalicji nr rundy
                    if (Main.getRoundNumberService.getState().equals(Worker.State.READY)) {
                        Main.getRoundNumberService.start();
                    } else {
                        Main.getRoundNumberService.restart();
                    }

                    // zakończenie rozgrywki
                    if (Main.roundNumber.get() == Main.maxRoundNumber) {
                        stage.setScene(Main.getScenes().get(SceneName.GAME_ENDING));
                    }
                }
        );

        resetQuestionInformationService.setOnSucceeded(
                t -> {
                    QuestionProperties newProps = resetQuestionInformationService.getValue();
                    questionProperties.setMainCountry(newProps.getMainCountry().get());
                    questionProperties.setQuestion(newProps.getQuestion().get());
                    for (int i = 0; i < 4; i++) {
                        questionProperties.setAnswer(newProps.getAnswer(i).get(), i);
                    }
                    questionProperties.setCorrectAnswerIndex(newProps.getCorrectAnswerIndex().get());
                }
        );

        getRoundNumberService.setOnSucceeded(
            t -> {
                int roundNumberValue = getRoundNumberService.getValue();
                roundNumber.set(roundNumberValue);
                String roundNumberValueString = String.valueOf(roundNumberValue);
                roundString.set(roundNumberValueString);
            }
        );

        gameScoreSetService.setOnSucceeded(
                t -> {
                    int scoreValue = gameScoreSetService.getValue();
                    scoreNumber.set(scoreValue);
                    String scoreValueString = String.valueOf(scoreValue);
                    scoreString.set(scoreValueString);
                }
        );

        gameScoreResetService.setOnSucceeded(
                t -> {
                    int scoreValue = gameScoreResetService.getValue();
                    scoreNumber.set(scoreValue);
                    String scoreValueString = String.valueOf(scoreValue);
                    scoreString.set(scoreValueString);
                }
        );

        scenes.put(SceneName.MENU, new MenuView(stage).getScene());
        scenes.put(SceneName.GAME_ROUND, new GameRoundView(stage).getScene());
        scenes.put(SceneName.GAME_ENDING, new GameEndView(stage).getScene());
        scenes.put(SceneName.RANKING, new RankingView(stage).getScene());

        // Start with the main scene
        stage.setScene(scenes.get(SceneName.MENU));
        stage.setTitle("Spot the Foreign Dish!");
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
                "1",
                "2",
                "Question placeholder",
                new String[]{"answer 1", "answer 2", "answer 3", "answer 4"},
                0
        );

        return question;
    }

    public static QuestionProperties getQuestionProperties() {
        String[] answers = question.getAnswers();

        QuestionProperties questionProperties = new QuestionProperties(
                question.getMainCountry(),
                "BE",
                question.getQuestion(),
                answers,
                question.getCorrectAnswerIndex()
        );

        return questionProperties;
    }
}
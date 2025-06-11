package com.maciejcrosswat.projekt.data;

import javafx.beans.Observable;
import javafx.beans.value.ObservableStringValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;

import java.util.Arrays;
import java.util.Random;

public class Question {
    private QuestionCategory category;
    private String mainCountry;
    private String oddCountry;
    private String question;
    private String[] answers;
    private int correctAnswerIndex;

    public Question(QuestionCategory category, String mainCountry, String oddCountry, String question, String[] answers, int correctAnswerIndex) {
        this.category = category;
        this.mainCountry = mainCountry;
        this.oddCountry = oddCountry;
        this.question = question;
        this.answers = answers;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestion() {
        return question;
    }

    public String getMainCountry() {
        return mainCountry;
    }

    public String[] getAnswers() {
        return answers;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}

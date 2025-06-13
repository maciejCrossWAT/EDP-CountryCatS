package com.maciejcrosswat.projekt.data;

import javafx.beans.property.*;

import java.util.ArrayList;
import java.util.List;

public class QuestionProperties {
    private StringProperty mainCountry;
    private StringProperty mainCountry2Letters;
    private StringProperty question;
    private List<StringProperty> answers;
    private IntegerProperty correctAnswerIndex;

    public StringProperty getMainCountry() {
        return mainCountry;
    }

    public void setMainCountry(String mainCountry) {
        this.mainCountry.set(mainCountry);
    }

    public StringProperty getMainCountry2Letters() {
        return mainCountry2Letters;
    }

    public void setMainCountry2Letters(String mainCountry2Letters) {
        this.mainCountry2Letters.set(mainCountry2Letters);
    }

    public StringProperty getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question.set(question);
    }

    public StringProperty getAnswer(int i) {
        return this.answers.get(i);
    }

    public void setAnswer(String answer, int index) {
        this.answers.set(index, new SimpleStringProperty(answer));
    }

    public IntegerProperty getCorrectAnswerIndex() {
        return this.correctAnswerIndex;
    }

    public void setCorrectAnswerIndex(int index) {
        this.correctAnswerIndex.set(index);
    }

    public QuestionProperties(String mainCountry, String mainCountry2letters, String question, String[] answers, int correctAnswerIndex) {
        this.mainCountry = new SimpleStringProperty(mainCountry);
        this.mainCountry2Letters = new SimpleStringProperty(mainCountry2letters);
        this.question = new SimpleStringProperty(question);
        this.answers = new ArrayList<>();
        for (String answer : answers) {
            this.answers.add(new SimpleStringProperty(answer));
        }
        this.correctAnswerIndex = new SimpleIntegerProperty(correctAnswerIndex);
    }
}

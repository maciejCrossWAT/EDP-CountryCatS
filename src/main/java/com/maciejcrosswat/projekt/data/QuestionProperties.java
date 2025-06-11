package com.maciejcrosswat.projekt.data;

import javafx.beans.property.*;

import java.util.ArrayList;
import java.util.List;

public class QuestionProperties {
    private StringProperty mainCountry;
    private StringProperty question;
    private List<StringProperty> answers;
    private IntegerProperty correctAnswerIndex;

    public StringProperty getMainCountry() {
        return mainCountry;
    }

    public void setMainCountry(String mainCountry) {
        this.mainCountry.set(mainCountry);
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

    public QuestionProperties(String mainCountry, String question, String[] answers, int correctAnswerIndex) {
        this.mainCountry = new SimpleStringProperty(mainCountry);
        this.question = new SimpleStringProperty(question);
        this.answers = new ArrayList<>();
        for (String answer : answers) {
            this.answers.add(new SimpleStringProperty(answer));
        }
        this.correctAnswerIndex = new SimpleIntegerProperty(correctAnswerIndex);
    }
}

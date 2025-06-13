package com.maciejcrosswat.projekt.data;

import javafx.beans.property.*;

import java.util.List;

public class QuestionProperty {
    private StringProperty mainCountry;
    private StringProperty mainCountry2Letters;
    private StringProperty question;
    private List<StringProperty> answers;
    private StringProperty answer0;
    private StringProperty answer1;
    private StringProperty answer2;
    private StringProperty answer3;
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

    public StringProperty getAnswer0() {
        return answer0;
    }

    public StringProperty getAnswer1() {
        return answer1;
    }

    public StringProperty getAnswer2() {
        return answer2;
    }

    public StringProperty getAnswer3() {
        return answer3;
    }

    public void setAnswer0(String answer) {
        this.answer0.set(answer);
    }

    public void setAnswer1(String answer) {
        this.answer1.set(answer);
    }

    public void setAnswer2(String answer) {
        this.answer2.set(answer);
    }

    public void setAnswer3(String answer) {
        this.answer3.set(answer);
    }

    public IntegerProperty getCorrectAnswerIndex() {
        return this.correctAnswerIndex;
    }

    public void setCorrectAnswerIndex(int index) {
        this.correctAnswerIndex.set(index);
    }

    public QuestionProperty(String mainCountry, String mainCountry2letters, String question, String[] answers, int correctAnswerIndex) {
        this.mainCountry = new SimpleStringProperty(mainCountry);
        this.mainCountry2Letters = new SimpleStringProperty(mainCountry2letters);
        this.question = new SimpleStringProperty(question);
        this.answer0 = new SimpleStringProperty(answers[0]);
        this.answer1 = new SimpleStringProperty(answers[1]);
        this.answer2 = new SimpleStringProperty(answers[2]);
        this.answer3 = new SimpleStringProperty(answers[3]);
        this.correctAnswerIndex = new SimpleIntegerProperty(correctAnswerIndex);
    }
}

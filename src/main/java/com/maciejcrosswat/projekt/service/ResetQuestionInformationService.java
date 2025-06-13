package com.maciejcrosswat.projekt.service;

import com.maciejcrosswat.projekt.Main;
import com.maciejcrosswat.projekt.data.Question;
import com.maciejcrosswat.projekt.data.QuestionCategory;
import com.maciejcrosswat.projekt.data.QuestionProperties;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class ResetQuestionInformationService extends Service<QuestionProperties> {

    @Override
    protected Task<QuestionProperties> createTask() {
        return new Task<>() {
            protected QuestionProperties call() {
                // reset pytania
                Question question = new Question(
                        QuestionCategory.FOOD,
                        "1",
                        "2",
                        "Question Placeholder",
                        new String[]{"Answer 1", "Answer 2", "Answer 3", "Answer 4"},
                        0
                );
                int i = 0;
                String[] answers = new String[4];
                for (String answer : question.getAnswers()) {
                    answers[i] = answer;
                    i++;
                }
                QuestionProperties questionProperties = new QuestionProperties(
                        question.getMainCountry(),
                        "",
                        question.getQuestion(),
                        answers,
                        question.getCorrectAnswerIndex()
                );

                return questionProperties;
            }
        };
    }
}

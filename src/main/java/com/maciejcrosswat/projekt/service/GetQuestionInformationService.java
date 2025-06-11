package com.maciejcrosswat.projekt.service;

import com.maciejcrosswat.projekt.Main;
import com.maciejcrosswat.projekt.data.Question;
import com.maciejcrosswat.projekt.data.QuestionCategory;
import com.maciejcrosswat.projekt.data.QuestionProperties;
import javafx.beans.InvalidationListener;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.util.Locale;
import java.util.Random;

public class GetQuestionInformationService extends Service<QuestionProperties> {

    private String clientID = "3d7df620701058d20455dd005953cb86";
    private String clientSecret = "3ed3f86fe7dcd379a49e382898f289bc1bd7846a";
    private String accessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIzZDdkZjYyMDcwMTA1OGQyMDQ1NWRkMDA1OTUzY2I4NiIsImp0aSI6ImM5M2ZiZmJiZTgxNzFjN2UzOWExYzc0Njc3ZmVjMjE3MzRjZTI4NDZiYzUxNjg2MDlkYjliYmIwZTcyMDc2NWU0ZTU0Yjg0ZDM3YjAxYjUzIiwiaWF0IjoxNzQ5NTY0NzkyLjMzNjYxNSwibmJmIjoxNzQ5NTY0NzkyLjMzNjYxNiwiZXhwIjozMzMwNjQ3MzU5Mi4zMzI2ODcsInN1YiI6Ijc4NDk3MjUzIiwiaXNzIjoiaHR0cHM6Ly9tZXRhLndpa2ltZWRpYS5vcmciLCJyYXRlbGltaXQiOnsicmVxdWVzdHNfcGVyX3VuaXQiOjUwMDAsInVuaXQiOiJIT1VSIn0sInNjb3BlcyI6WyJiYXNpYyJdfQ.blDH5wP-aN7UnebZNmarFOQLOrKqATPChk-rhtsQw1Fdkem2svzGeemUNRKkRb2slvVK1doIyL3R6j5gNIRRkCX8FZuJPk_RzUf_ukjmNYXAYg61w9kOauIx33uyFbwjtlxjGgI_OLdU_gMM30kQ68gqLNRPETKgzSDvt3X5nvdufjMkaXrqavvfj0X76bgpCFgpKmoPN7roHqrslU1QaqGCsITOOOv8DHA17EJusX7AOlk7slefA1EzJM6KltfV25wfRBcpjqZ2V75zvBKwgXPtxDTlsMlaWxUdcMcXOc7CJq-n7lozQT5Wx_HCXV4BIxbZuyafwSElHdJKTXuCCsEWn6OBfP075vkJctlFA59ufoUTu5rTpWuD_CMmp68H4NzImMUn1pr-D36NQ2XTSUySbVSGGfsUURzTUWv7jaVQxTBPo2FYIMdQa951hfuz3mxCX250Q8rWiQxdgIQIeuX6k3NLcGEDRmbnq621M9ISt_QM3SAmMl5LnyHZUeBRJsoIaTUcn1VU3TnH49B4LQnXJL8Hmn5FtAFDbkEKXsGhLc9-KBsi1NXQoWwJsVPK4sgo7Pc3PObvDWA2_GhnN645hWSvGRo3xIO8ez4JKdhnXgqWmMdV9J_G8-B-Hy5oImL5worU6iQn-rcsFuQIE8_YdT0dao_OQqaDZUxNnCQ";

    @Override
    protected Task<QuestionProperties> createTask() {
        return new Task<>() {
            protected QuestionProperties call() throws InterruptedException {
                Question question = new Question(
                        QuestionCategory.FOOD,
                        "Finland",
                        "Sweden",
                        "Which of these foods are not from Finland?" + new Random().nextBoolean(),
                        new String[]{"swedish meatballs", "finnish food 1", "finnish food 2", "finnish food 3"},
                        3
                );

                System.out.println("Changed question");

                int i = 0;
                String[] answers = new String[4];
                for (String answer : question.getAnswers()) {
                    answers[i] = answer;
                    i++;
                }
                QuestionProperties questionProperties = new QuestionProperties(
                        question.getMainCountry(),
                        question.getQuestion(),
                        answers,
                        question.getCorrectAnswerIndex()
                );

                System.out.println("Changed question properties");

                if (isCancelled()) {
                    return null;
                }
                updateValue(questionProperties);

                return questionProperties;
            }
        };
    }
}

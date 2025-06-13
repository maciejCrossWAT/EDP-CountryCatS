package com.maciejcrosswat.projekt.service;

import com.maciejcrosswat.projekt.data.Question;
import com.maciejcrosswat.projekt.data.QuestionCategory;
import com.maciejcrosswat.projekt.data.QuestionProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class ResetQuestionInformationService extends Service<QuestionProperty> {

    @Override
    protected Task<QuestionProperty> createTask() {
        return new Task<>() {
            protected QuestionProperty call() {
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
                QuestionProperty questionProperties = new QuestionProperty(
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

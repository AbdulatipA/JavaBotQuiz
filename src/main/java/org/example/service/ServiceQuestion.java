package org.example.service;

import org.example.dataBase.TableQuestion;
import org.example.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceQuestion {

    @Autowired
    private TableQuestion tableQuestion;

    public Question getQuestionById(int questionId){
        TableQuestion tableQuestion = new TableQuestion();
        List<Question> questionList = tableQuestion.getQuestions();

        Question question = questionList.stream()
                .filter(e-> e.getId() == questionId)
                .findFirst()
                .orElse(null);

        return question;
    }
}

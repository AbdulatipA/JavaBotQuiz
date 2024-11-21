package org.example.service;

import org.example.dataBase.TableQuestion;
import org.example.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuestionService {

    @Autowired
    private TableQuestion tableQuestion;


    //сравниваем id каждого вопроса с номеров вопроса, на котором остановился user
    public Question getQuestionById(Long numbQuestion){
        Question question = tableQuestion.getQuestions().stream()
                .filter(e -> e.getId().equals(numbQuestion))
                .findFirst()
                .orElse(null);

        return question;
    }
}

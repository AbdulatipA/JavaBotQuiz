package org.example.dataBase;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import org.example.model.Question;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("singleton")
public class TableQuestion {
    private List<Question> questions;

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public TableQuestion() {
        questions = new ArrayList<>();
    }
}

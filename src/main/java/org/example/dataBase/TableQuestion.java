package org.example.dataBase;

import lombok.Getter;
import lombok.Setter;
import org.example.model.Question;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("singleton")
@Setter
@Getter
public class TableQuestion {
    private List<Question> questions;


    //Принимаем объект вопроса и добавляем его в лист
    public void addQuestion(Question question) {
        questions.add(question);
    }

    public TableQuestion() {
        questions = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "TableQuestion: " + "questions = " + questions;
    }
}

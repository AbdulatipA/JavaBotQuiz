package org.example.dataBase;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.model.Question;
import org.example.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("singleton")
@Setter
@Getter
public class TableQuestion {
    private List<Question> questions;

    private FileService fileService;

    public TableQuestion(FileService fileService) {
        this.fileService = fileService;
        questions = fileService.questionList();
    }

    @Override
    public String toString() {
        return "TableQuestion: " + "questions = " + questions;
    }


    //Принимаем объект вопроса и добавляем его в лист
    public void addQuestion() {
        questions.addAll(fileService.questionList());
    }

    //сравниваем id каждого вопроса с номеров вопроса, на котором остановился user
    public Question currentQuestion(int questionId ){
        Question question = questions.stream()
                .filter(e -> e.getId() == questionId)
                .findFirst()
                .orElse(null);

        return question;
    }


//    //Принимаем объект вопроса и добавляем его в лист
//    public void addQuestion(Question question) {
//        questions.add(question);
//    }
}

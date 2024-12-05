package org.example.service;

import org.example.model.Question;
import org.springframework.stereotype.Service;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {


    //делаем из строки объек Question
   private Question createQuestionFromString(String line) {
       Question question = new Question();
        if(line.isEmpty()) return null;

        String[] split = line.split(",");
        question.setId(Integer.parseInt(split[0]));
        question.setTitle(split[1]);
        question.setAnswer(split[2]);

        return question;
    }

    //добавляем файл с вопросами в лист
    private List<String> readFileToLine(){
        File file = new File("src/main/resources/questions.csv");
        List<String> lines = new ArrayList<>();

        try {
           lines = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            System.out.println("Файл не найден");
        }
        return lines;
    }


    public List<Question> questionList(){
        List<String> questionString = readFileToLine();
        List<Question> list = questionString.stream()
                .map(line -> createQuestionFromString(line))
                .toList();

        return list;
    }
}

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
   public Question createQuestionFromString(String line) {
        if(line.isEmpty()) return null;

        Question question = new Question();

        String[] split = line.split(",");
        question.setId(Integer.parseInt(split[0]));
        question.setTitle(split[1]);
        question.setAnswer(split[2]);

        return question;
    }

    //добавляем файл с вопросами в лист
    public List<String> readFileToLine(){
        File file = new File("src/main/resources/questions.csv");
        List<String> lines = new ArrayList<>();

        try {
           lines = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            System.out.println("Файл не найден");
        }
        System.out.println(lines);
        return lines;
    }
}

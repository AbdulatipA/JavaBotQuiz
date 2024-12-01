package org.example.service;

import org.example.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private FileService fileService;


    public List<Question> questionList(){
        List<String> questionString = fileService.readFileToLine();
        List<Question> list = questionString.stream()
                .map(line -> fileService.createQuestionFromString(line))
                .toList();

        return list;
    }
}

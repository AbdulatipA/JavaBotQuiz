package org.example.service;

import org.example.model.Question;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileServiceTest {
    FileService fileService = new FileService();
    @Test
    void createQuestionFromString() {

       Question question = fileService.createQuestionFromString("1, int x = 10; ++x; System.out.println(x--); x = ?, 11");

        System.out.println(question);
    }

    @Test
    void readFileToLine() {
        fileService.readFileToLine();
    }
}
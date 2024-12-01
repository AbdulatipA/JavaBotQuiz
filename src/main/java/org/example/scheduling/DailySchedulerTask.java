package org.example.scheduling;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dataBase.TableQuestion;
import org.example.service.FileService;
import org.example.service.QuestionService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@AllArgsConstructor
public class DailySchedulerTask {


    private QuestionService questionService;
    private FileService fileService;
    private TableQuestion tableQuestion;


    @Scheduled(cron = "0 0 * * * *")
    public void taskUpdateListQuestion() {
        log.info("Scheduler: Список вопросов обновлен");
        tableQuestion.setQuestions(questionService.questionList());
    }
}

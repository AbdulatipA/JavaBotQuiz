package org.example.scheduling;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dataBase.TableQuestion;
import org.example.model.Question;
import org.example.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class DailySchedulerTask {

    @Autowired
    private FileService fileService;

    @Autowired
    private TableQuestion tableQuestion;

    @Scheduled(cron = "* * 15,20 * * ?")
    public void taskUpdateListQuestion() {
        log.info("Scheduler: Updating list question ");

        List<String> questionString = fileService.readFileToLine();
        List<Question> list = questionString.stream()
                .map(line -> fileService.createQuestionFromString(line))
                .toList();

        tableQuestion.setQuestions(list);

    }
}
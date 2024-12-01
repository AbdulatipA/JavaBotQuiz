package org.example.service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.example.bot.Bot;
import org.example.dataBase.TableQuestion;
import org.example.dataBase.TableUsers;
import org.example.model.Question;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
@Slf4j
@Getter
@Setter
@Service
public class MainService {
    User currentUser;
    Question currentQuestion;
    int score;
    boolean switchQuestion;

    @Autowired
    private Bot bot;
    @Autowired
    private TableQuestion tableQuestion;
    @Autowired
    private TableUsers tableUsers;
    @Autowired
    private UserService userService;


    public void processingMessage(Update update, String textMessage){
        Long chatId= update.getMessage().getChatId();
        currentUser = userService.userCheckForNull(Integer.parseInt(String.valueOf(chatId)), update);

        if (switchQuestion && textMessage.startsWith("/start")) {
            bot.sendMessage(update, "Игра завершена, вопросов больше нет");
            return;
        }

            // запуск квиза
        if (!currentUser.isGameStarted() && textMessage.startsWith("/start")) {
            start(update);
            return;
        }

        if (currentUser.isGameStarted()) {
            point(update, textMessage);
            return;
        }

        //Статистика
        if(textMessage.startsWith("Статистика")){
            userStatistics(update);
            return;
        }
        // если введено что-то другое
        bot.sendMessage(update, "Введите /start, чтобы начать игру");
    }


    //старт
    public void start(Update update) {
            currentUser.setNumbQuestion(1);
            currentQuestion = tableQuestion.currentQuestion(1);
            currentUser.setGameStarted(true);
            bot.sendMessage(update, currentQuestion.getTitle());
    }


    public void point(Update update, String textMessage) {
        if (textMessage.trim().equalsIgnoreCase(currentQuestion.getAnswer().trim())) {
            currentUser.setPoint(currentUser.getPoint() + 1);
            bot.sendMessage(update, "Правильный ответ, ваш балл: " + currentUser.getPoint());
        } else {
            currentUser.setPoint(currentUser.getPoint() - 1);
            bot.sendMessage(update, "Неверный ответ, ваш балл: " + currentUser.getPoint());
        }

        //увеличиваем текущий вопрос на 1 у юзера, и след.вопроса
        currentUser.setNumbQuestion(currentUser.getNumbQuestion() + 1);
        Question nexQuestion = currentQuestion = tableQuestion.currentQuestion(currentUser.getNumbQuestion());


        //проверка на null конце игре
        if (nexQuestion == null) {
            bot.sendMessage(update, "Игра окончена! Ваш итоговый балл: " + currentUser.getPoint());
            score = currentUser.getPoint();
            currentUser.setPoint(0);
            currentUser.setGameStarted(false);
            return;
        }

        switchQuestion = true;
        bot.sendMessage(update, nexQuestion.getTitle());
    }


    //статистика
    public void userStatistics(Update update) {
        currentUser.setPoint(score);
        List<String> list = tableUsers.getUsers().stream()
                .map(user -> "логин:  " + user.getName() + "\n" + "баллы:  " + user.getPoint())
                .toList();

        // убираем квадратные скобки
        String result = String.join("\n\n", list);
        bot.sendMessage(update, result);
    }
}
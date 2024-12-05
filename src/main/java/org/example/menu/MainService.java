package org.example.menu;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.example.bot.Bot;
import org.example.dataBase.TableQuestion;
import org.example.dataBase.TableUsers;
import org.example.model.Question;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
@Slf4j
@Getter
@Setter
@Service
@RequiredArgsConstructor
public class MainService {
    User currentUser;
    int score;
    boolean endGameSwitchQuestion;

    private final Bot bot;
    private final TableQuestion tableQuestion;
    private final TableUsers tableUsers;
    private final UserService userService;

    @Autowired
    private Question currentQuestion;



    // главный метод
    public void processingMessage(Update update, String textMessage){
        Long chatId= update.getMessage().getChatId();
        currentUser = userService.userCheckForNull(Integer.parseInt(String.valueOf(chatId)), update);

        // Статистика
        if(textMessage.startsWith("Статистика")){
            userStatistics(update);
            return;
        }

        if (currentUser.getNumbQuestion() >= tableQuestion.getQuestions().size()) {
            bot.sendMessage(update, "Игра завершена, вопросов больше нет");
            return;
        }

        // запуск игры
        if (!currentUser.isGameStarted() && textMessage.startsWith("/start")) {
            start(update);
            return;
        }

        // если игра запущена, пристумает к подсчету очков и след. вопросу
        if (currentUser.isGameStarted()) {
            point(update, textMessage);
            return;
        }


        // если введено что-то другое
        bot.sendMessage(update, "Введите /start, чтобы начать игру");
    }


    // старт
    public void start(Update update) {
        currentUser.setNumbQuestion(1);
        currentQuestion = tableQuestion.currentQuestion(1);
        currentUser.setGameStarted(true);
        bot.sendMessage(update, currentQuestion.getTitle());
    }


    // подсчет очков
    public void point(Update update, String textMessage) {
        if (textMessage.trim().equalsIgnoreCase(currentQuestion.getAnswer().trim())) {
            currentUser.setPoint(currentUser.getPoint() + 1);
            bot.sendMessage(update, "Правильный ответ, ваш балл: " + currentUser.getPoint());
        } else {
            currentUser.setPoint(currentUser.getPoint() - 1);
            bot.sendMessage(update, "Неверный ответ, ваш балл: " + currentUser.getPoint());
        }

        // увеличиваем текущий вопрос юзера на 1, и задаем след. вопрос
        currentUser.setNumbQuestion(currentUser.getNumbQuestion() + 1);
        Question nexQuestion = currentQuestion = tableQuestion.currentQuestion(currentUser.getNumbQuestion());


        // проверка на null конце игре, чтобы не выпадала ошибка, обнуляем баллы юзера
        if (nexQuestion == null) {
            bot.sendMessage(update, "Игра окончена! Ваш итоговый балл: " + currentUser.getPoint());
            score = currentUser.getPoint();
            currentUser.setPoint(0);
            currentUser.setGameStarted(false);
            return;
        }

        endGameSwitchQuestion = true;
        bot.sendMessage(update, nexQuestion.getTitle());
    }


    // статистика
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
package org.example.bot;


import lombok.extern.slf4j.Slf4j;
import org.example.dataBase.TableUsers;
import org.example.model.Question;
import org.example.model.User;
import org.example.service.QuestionService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
public class Bot extends TelegramLongPollingBot {

    @Autowired
    TableUsers tableUsers;
    QuestionService questionService;
    UserService userService;

    public Bot(@Value("7517662529:AAG38sk_epw8tnOAc9pdi3GmDIUygVWnT2Y") String botToken) {
        super(botToken);
    }

    @Override
    public void onUpdateReceived(Update update) {
      if (!update.hasMessage() && !update.getMessage().hasText()) {
          return;
      }


      //возвращаем текущий объект User, чей индекс равен chatID
      Long chatId= update.getMessage().getChatId();
      User currentUser = userService.userCheckForNull(chatId, update);


      //объект, чей номер вопроса равен id вопроса
      Question questionById = questionService.getQuestionById((long) currentUser.getNumbQuestion());
        currentUser.setFlag(true);


        //отправка вопроса юзеру и получени ответа и его проверка
        //String textMessage = update.getMessage().getText();
//        if (textMessage.startsWith("/start")) {
//            if(tableUsers.) {
//
//            }
//        }
    }

    @Override
    public String getBotUsername() {
        return "JabaBotQuiz_bot";
    }

    void sendMessage(Update update) {
        SendMessage sendMessage = new SendMessage();
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}






//проверка пользователя (вытаскиваем объект чей id совпадает с chatId)
//        User currentUserOrNull = tableUsers.getUsers().stream()
//                .filter(user -> user.getId() == chatId)
//                .findFirst()
//                .orElse(null);

//если объект User == null, добавляем его в tableUsers
//      if (currentUserOrNull == null) {
//          User newUser = new User(chatId, update.getMessage().getFrom().getUserName(), 0, 0);
//          tableUsers.getUsers().add(newUser);
//      }


//        User currentUser = tableUsers.getUsers().stream()
//                .filter(user -> user.getId() == chatId)
//                .findFirst()
//                .orElse(null);


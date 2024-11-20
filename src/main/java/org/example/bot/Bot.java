package org.example.bot;


import lombok.extern.slf4j.Slf4j;
import org.example.dataBase.TableUsers;
import org.example.model.Question;
import org.example.model.User;
import org.example.service.ServiceQuestion;
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
    ServiceQuestion serviceQuestion;

    public Bot(@Value("345345345345345t4gtgr") String botToken) {
        super(botToken);
    }

    @Override
    public void onUpdateReceived(Update update) {
//        log.info(update.getMessage().getText());
      if (!update.hasMessage() && !update.getMessage().hasText()) {
          return;
      }


      String textMessage = update.getMessage().getText();

      Long chatId= update.getMessage().getChatId();
        User currentUserOrNull = tableUsers.getUsers().stream()
                .filter(user -> user.getId() == chatId)
                .findFirst()
                .orElse(null);

      if (currentUserOrNull == null) {
          User newUser = new User();
          newUser.setId(chatId);
          newUser.setName(update.getMessage().getFrom().getUserName());
          newUser.setPoint(0);
          newUser.setNumbQuestion(0);
          tableUsers.getUsers().add(newUser);
      }

        User currentUser = tableUsers.getUsers().stream()
                .filter(user -> user.getId() == chatId)
                .findFirst()
                .orElse(null);


        Question questionById = serviceQuestion.getQuestionById(currentUser.getNumbQuestion());
        currentUser.setFlag(true);


        //отправка вопроса юзеру и получени ответа и его проверка


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

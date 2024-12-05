package org.example.bot;


import lombok.extern.slf4j.Slf4j;
import org.example.menu.MainService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Slf4j
@Component
public class Bot extends TelegramLongPollingBot {
    MainService mainService;

    public Bot(@Value("${bot.token}") String botToken,
               @Lazy MainService appserver) {
        super(botToken);
        this.mainService = appserver;
    }


    @Override
    public String getBotUsername() {
        return "JabaBotQuiz_bot";
    }

    @Override
    public void onUpdateReceived(Update update) {

        // текст юзера
        String textMessage = update.getMessage().getText();

        // основная логика
        mainService.processingMessage(update, textMessage);
    }

    public void sendMessage(Update update, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}



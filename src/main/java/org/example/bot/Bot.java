package org.example.bot;

import lombok.extern.slf4j.Slf4j;
import org.example.dataBase.TableQuestion;
import org.example.model.Question;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.logging.Logger;
@Slf4j
public class Bot extends TelegramLongPollingBot {
    TableQuestion tableQuestion = new TableQuestion();

    public Bot(String botToken) {
        super(botToken);
    }

    @Override
    public void onUpdateReceived(Update update) {
        log.info(update.getMessage().getText());
    }

    @Override
    public String getBotUsername() {
        return "JabaBotQuiz_bot";
    }
}

package org.example;

import org.example.bot.Bot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


@SpringBootApplication
@EnableScheduling
public class App {

    public static void main( String[] args ) {
        SpringApplication.run(App.class, args);

        String token = "7517662529:AAG38sk_epw8tnOAc9pdi3GmDIUygVWnT2Y";
        Bot bot = new Bot(token);
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(bot);

        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}

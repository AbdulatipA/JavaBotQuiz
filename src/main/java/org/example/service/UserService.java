package org.example.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.dataBase.TableUsers;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Service
public class UserService {

    @Autowired
    private TableUsers tableUsers;


   // проверка пользователя (вытаскиваем объект, чей id совпадает с chatId)
   public User userCheckForNull(int chatId, Update update) {
        User currentUser = tableUsers.getUsers().stream()
        .filter(user -> user.getId() == chatId)
        .findFirst()
        .orElse(null);


       String userName = getUserNameSafe(update);

       // регистрируем текущего пользователя, если null
       if(currentUser == null) {
           User newUser = new User(chatId, userName, 0, 0);
           tableUsers.getUsers().add(newUser);
           return newUser;
       }

        return currentUser;
    }

    // проверяем имя пользлвателя на null
    private String getUserNameSafe(Update update) {
        if (update == null || update.getMessage() == null || update.getMessage().getFrom() == null) {
            return "Unknown";
        }
        return update.getMessage().getFrom().getUserName();
    }
}
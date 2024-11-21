package org.example.service;

import org.example.dataBase.TableUsers;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Comparator;
import java.util.List;

@Component
public class UserService {

    @Autowired
    private TableUsers tableUsers;

    //добавляем Chatid и name в значение свойств объекта User
//    public User getUser(Long  Chatid, String name){
//        User user = new User();
//        user.setId(Chatid);
//        user.setName(name);
//        return user;
//    }


    //сортируем лист объектов User по поинтам
    List<User> sortedByPoint(List<User> usersList) {
        List<User> ListSortedByPoint = usersList.stream()
                .sorted(Comparator.comparing(User::getPoint))
                .toList();

        return ListSortedByPoint;
    }


    //получаем нужный объект User из листа, по выбранному id
    User getId(List<User> usersList,  int id) {
        User findFirst = usersList.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found"));

        return findFirst;
    }


    //проверка пользователя (вытаскиваем объект, чей id совпадает с chatId)
   public User userCheckForNull(Long chatId, Update update) {
        User currentUser = tableUsers.getUsers().stream()
        .filter(user -> user.getId() == chatId)
        .findFirst()
        .orElse(null);

        //регистрируем текущего пользователя, если null
        if(currentUser == null) {
            User newUser = new User(chatId, update.getMessage().getFrom().getUserName(), 0, 0);
            tableUsers.getUsers().add(newUser);
        }
        return currentUser;
    }
}
package org.example.service;

import org.example.model.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserService {

    //добавляем Chatid и name в значение свойств объекта User
    public User getUser(String  Chatid, String name){
        User user = new User();
        user.setId(Integer.parseInt(Chatid));
        user.setName(name);
        return user;
    }


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
}
package org.example.dataBase;

import lombok.Getter;
import lombok.Setter;
import org.example.model.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Component
@Scope("singleton")
public class TableUsers {
    private List<User> users;

    //Добавляем объект user в лист users
    void addUser(User user) {
        users.add(user);
    }

    // при создании объекта TableUsers создает пустой лист users
    public TableUsers() {
        users = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "TableUsers: " + "users = " + users;
    }
}

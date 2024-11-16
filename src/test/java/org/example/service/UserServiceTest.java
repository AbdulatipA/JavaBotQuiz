package org.example.service;

import org.example.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private UserService userService;
    private List<User> users;

    @Test
    void getUser() {
        userService.getUser("1", "Jack");
    }


    @Test
    void sortedByPoint() {
        userService.sortedByPoint(users);
        System.out.println(users);
    }

    @Test
    void getId() {
        User user = userService.getId(users, 3);

        Assertions.assertEquals(user.getId(), 3);
        Assertions.assertEquals(user.getName(),"Alex");
        Assertions.assertEquals(user.getPoint(),9);
    }

    @BeforeEach
    void setUp() {
        userService = new UserService();
        users = new ArrayList<>();
        users.add(new User(1, "Alex", 5, 1));
        users.add(new User(2, "Alex", 4, 7));
        users.add(new User(3, "Alex", 9, 2));
    }
}
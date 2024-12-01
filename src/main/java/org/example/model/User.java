package org.example.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class User {
    private int id;
    private String name;
    private int point;
    private long numbQuestion;
    private boolean isGameStarted;

    @Override
    public String toString() {
        return "User" + "\n" +
                "id: " + id + "\n" +
                "name: " + name + "\n" +
                "point: " + point + "\n" +
                "numbQuestion: " + numbQuestion + "\n" +
                "isGameStarted: " + isGameStarted;
    }


    public User(int id, String name, int point, long numbQuestion) {
        this.id = id;
        this.name = name;
        this.point = point;
        this.numbQuestion = numbQuestion;
    }
}

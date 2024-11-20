package org.example.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private long id;
    private String name;
    private int point;
    private int numbQuestion;
    private boolean flag;

    @Override
    public String toString() {
        return "User" + "\n" +
                "id: " + id + "\n" +
                "name: " + name + "\n" +
                "point: " + point + "\n" +
                "numbQuestion: " + numbQuestion + "\n" +
                "flag: " + flag;
    }


    public User(int id, String name, int point, int numbQuestion) {
        this.id = id;
        this.name = name;
        this.point = point;
        this.numbQuestion = numbQuestion;
    }
}

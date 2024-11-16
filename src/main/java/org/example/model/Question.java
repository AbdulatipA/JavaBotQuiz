package org.example.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    private int id;
    private String title;
    private String answer;

    @Override
    public String toString() {
        return "ВОПРОС" + "\n" + "id: " + id + "\n" + "title: " + title + "\n" +
                "answer: " + answer + "\n";
    }
}

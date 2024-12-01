package org.example.model;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
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

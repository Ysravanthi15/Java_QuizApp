import java.util.*;

public class QuizService {
    private List<Question> questions;
    private int index = 0;
    private int score = 0;

    public QuizService() {
        questions = new ArrayList<>();

        questions.add(new Question(
            "Java is a?",
            new String[]{"Programming Language", "OS", "Browser", "Database"},
            "Programming Language"
        ));

        questions.add(new Question(
            "JVM stands for?",
            new String[]{"Java Variable Machine", "Java Virtual Machine", "Java Verified Machine", "None"},
            "Java Virtual Machine"
        ));

        questions.add(new Question(
            "Which is not Java feature?",
            new String[]{"Object Oriented", "Portable", "Pointer", "Secure"},
            "Pointer"
        ));
    }

    public Question getCurrentQuestion() {
        return questions.get(index);
    }

    public void checkAnswer(String selected) {
        if (selected != null && selected.equals(getCurrentQuestion().answer)) {
            score++;
        }
        index++;
    }

    public boolean hasNext() {
        return index < questions.size();
    }

    public int getScore() {
        return score;
    }
}
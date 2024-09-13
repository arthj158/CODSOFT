import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Quiz {

    static class Question {
        String text;
        String[] choices;
        int correctIndex;

        Question(String text, String[] choices, int correctIndex) {
            this.text = text;
            this.choices = choices;
            this.correctIndex = correctIndex;
        }
    }
    private static Question[] questions = {
        new Question("First President of India?", new String[]{"1. Dr. Rajendra Prasad", "2. Sonia Gandhi", "3. APJ Abdul Kalam", "4. Amit Shah"}, 1),
        new Question("How many bones in adult human body?", new String[]{"1. 312 ", "2. 206", "3. 98", "4. 101"}, 2),
        new Question("Capital of India?", new String[]{"1. Rajasthan", "2. Delhi", "3. Himachal Prades", "4. Maharashtra"}, 2)
    };
    private static int score = 0;
    private static final int TIME_LIMIT = 30;
    private static boolean answered = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quiz--");
        System.out.println("you have " + TIME_LIMIT + " seconds per question");

        for (int i = 0; i < questions.length; i++) {
            answered = false;
            printQuestion(questions[i]);

            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                public void run() {
                    if (!answered) {
                        System.out.println("\nNo time left.");
                        answered = true;
                    }
                    timer.cancel();
                }
            };
            timer.schedule(task, TIME_LIMIT * 1000);
          
            while (!answered) {
                try {
                    System.out.print("Answer: ");
                    int userAnswer = scanner.nextInt();
                    if (userAnswer < 1 || userAnswer > 4) {
                        System.out.println("Number b/w 1 to 4");
                        continue;
                    }
                    verifyAnswer(userAnswer, questions[i].correctIndex);
                    answered = true;
                    timer.cancel();
                } catch (Exception e) {
                    System.out.println("Wrong input.");
                    scanner.next();
                }
            }
        }

        showResults();
        scanner.close();
    }

    private static void printQuestion(Question question) {
        System.out.println("\n" + question.text);
        for (String choice : question.choices) {
            System.out.println(choice);
        }
        System.out.println("pick option");
    }
  
    private static void verifyAnswer(int userAnswer, int correctAnswer) {
        if (userAnswer == correctAnswer + 1) {
            System.out.println("correct.");
            score++;
        } else {
            System.out.println("wrong answer, the correct answer is : " + (correctAnswer + 1));
        }
    }

    private static void showResults() {
        System.out.println("\nquiz end.");
       System.out.println("your score is " + score + " out of " + questions.length + ".");
        if (score == questions.length) {
            System.out.println("all right.");
        } else if (score > 0) {
            System.out.println("not perfect");
        } else {
            System.out.println("all wrong");
        }
    }
}

import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter marks scored by you in each subject out of 100 :");
        int Mathematics = getValidMarks(scanner, "Mathematics");
        int Physics = getValidMarks(scanner, "Physics");
        int Chemistry = getValidMarks(scanner, "Chemistry");
        int English = getValidMarks(scanner, "English");

        int Subjects = 4;
        int TotalMarks = 400;
        int Sum = (Mathematics + Physics + Chemistry + English);
        double percentage = (Sum) / (double) Subjects;
        char grade = 'F';
        if (percentage >= 91 && percentage <= 100) {
            grade = 'A';
        } else if (percentage >= 81 && percentage <= 90) {
            grade = 'B';
        } else if (percentage >= 71 && percentage <= 80) {
            grade = 'C';
        } else if (percentage >= 61 && percentage <= 70) {
            grade = 'D';
        } else if (percentage >= 51 && percentage <= 60) {
            grade = 'E';
        }
        System.out.println("Total Marks : " + TotalMarks);
        System.out.println("Obtained Marks : " + Sum);
        System.out.println("Percentage : " + percentage);
        System.out.println("Grade : " + grade);
        if (grade != 'F') {
            System.out.println("Congratulations! You are promoted to the next class.");
        } else {
            System.out.println("You failed in the exams, Better Luck Next Time.");
        }
    }
        public static int getValidMarks(Scanner scanner, String subject) {
            int marks;
            do {
                System.out.println(subject + ": ");
                marks = scanner.nextInt();
                if (marks < 0 || marks > 100) {
                    System.out.println("Error! Marks should be between 0 to 100.");
                }
            }
                while (marks<0 || marks> 100) ;
            return marks;
                }

    }


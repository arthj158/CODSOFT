import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Course {
    String courseCode;
    String title;
    String description;
    int capacity;
    int availableSlots;
    String schedule;

    Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.availableSlots = capacity;
        this.schedule = schedule;
    }

    public boolean isAvailable() {
        return availableSlots > 0;
    }

    public void registerStudent() {
        if (availableSlots > 0) {
            availableSlots--;
        }
    }

    public void removeStudent() {
        if (availableSlots < capacity) {
            availableSlots++;
        }
    }

    @Override
    public String toString() {
        return "c ourse Code: " + courseCode + "\nTitle: " + title + "\nDescription: " + description +
                "\nCapacity: " + capacity + "\nAvailable Slots: " + availableSlots + "\nSchedule: " + schedule + "\n";
    }
}

class Student {
    String studentID;
    String name;
    ArrayList<Course> registeredCourses = new ArrayList<>();

    Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
    }

    public void registerCourse(Course course) {
        if (!registeredCourses.contains(course) && course.isAvailable()) {
            registeredCourses.add(course);
            course.registerStudent();
            System.out.println("Registered for course: " + course.title);
        } else {
            System.out.println("cannot register for course: " + course.title);
        }
    }

    public void removeCourse(Course course) {
        if (registeredCourses.contains(course)) {
            registeredCourses.remove(course);
            course.removeStudent();
            System.out.println("removed course: " + course.title);
        } else {
            System.out.println("Not registered for course: " + course.title);
        }
    }

    public void listRegisteredCourses() {
        System.out.println("Courses registered for " + name + ":");
        for (Course course : registeredCourses) {
            System.out.println(course.title);
        }
    }
}

public class CourseRegistrationSystem {
    static HashMap<String, Course> courseDatabase = new HashMap<>();
    static HashMap<String, Student> studentDatabase = new HashMap<>();

    public static void main(String[] args) {
        setupCourses();
        setupStudents();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Course Registration System!");

        while (true) {
            System.out.println("\n1.list courses\n2. register to course\n3. remove course\n4. list of courses registered to\n5. exit");
            System.out.print("submit your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    listCourses();
                    break;
                case 2:
                    System.out.print("Enter your id: ");
                    String studentID = scanner.nextLine();
                    System.out.print("course code to register: ");
                    String courseCode = scanner.nextLine();
                    registerForCourse(studentID, courseCode);
                    break;
                case 3:
                    System.out.print("Enter your id: ");
                    studentID = scanner.nextLine();
                    System.out.print("course code to drop: ");
                    courseCode = scanner.nextLine();
                    dropCourse(studentID, courseCode);
                    break;
                case 4:
                    System.out.print("enter your id: ");
                    studentID = scanner.nextLine();
                    listRegisteredCourses(studentID);
                    break;
                case 5:
                    System.out.println("exiting");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("invalid ");
            }
        }
    }

    public static void setupCourses() {
        courseDatabase.put("c1", new Course("c1", "computer science", "basic", 30, "mon-wed 10:00 am"));
        courseDatabase.put("c2", new Course("c2", "DSA", "dsa basic", 25, "tue-thu 11:00 am"));
        courseDatabase.put("c3", new Course("c3", "algorithm", "analysis", 20, "fri 12:00 pm"));
    }

    public static void setupStudents() {
        studentDatabase.put("1", new Student("1", "Anil"));
        studentDatabase.put("2", new Student("2", "Sunil"));
        studentDatabase.put("3", new Student("3", "Manish"));
    }

    public static void listCourses() {
        System.out.println("Available Courses:");
        for (Course course : courseDatabase.values()) {
            System.out.println(course);
        }
    }

    public static void registerForCourse(String studentID, String courseCode) {
        Student student = studentDatabase.get(studentID);
        Course course = courseDatabase.get(courseCode);
        if (student != null && course != null) {
            student.registerCourse(course);
        } else {
            System.out.println("Invalid  ID or course  code.");
        }
    }

    public static void dropCourse(String studentID, String courseCode) {
        Student student = studentDatabase.get(studentID);
        Course course = courseDatabase.get(courseCode);
        if (student != null && course != null) {
            student.removeCourse(course);
        } else {
            System.out.println("Invalid id or cours code.");
        }
    }

    public static void listRegisteredCourses(String studentID) {
        Student student = studentDatabase.get(studentID);
        if (student != null) {
            student.listRegisteredCourses();
        } else {
            System.out.println("Invalid id.");
        }
    }
}

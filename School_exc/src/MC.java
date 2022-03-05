import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MC {
    public static void main(String[] args) {
        List<Course> courses = new ArrayList<>();
        Course history = new Course("History 101", LocalDate.of(2022, Month.JANUARY, 15), LocalDate.of(2022, Month.JUNE, 15));
        Course arithmetics = new Course("Arithmetics", LocalDate.of(2022, Month.MARCH, 9), LocalDate.of(2022, Month.JUNE, 15));
        Student Aurelio = new Student("Aurelio");
        Student Laura = new Student("Laura");
        history.addStudentAndScore(Aurelio, 85.4);
        history.addStudentAndScore(Laura, 95.1);
        history.addStudentAndScore(new Student("Omar"), 75.4);

        courses.add(history);
        arithmetics.addStudentAndScore(new Student("Julio"), 6.5);
        arithmetics.addStudentAndScore(new Student("Esteban"), 8.5);
        arithmetics.addStudentAndScore(new Student("Miguel"), 9.5);

        courses.add(arithmetics);
        System.out.println("Students: ");
        courses.forEach( course -> {
            System.out.println("Course: " + course.getCourse_name());
            course.getEnrolled_students().entrySet().forEach( (student) -> {
                System.out.println("Student Name: " + student.getKey().getName() +  " avg score: " + student.getValue());
            });
        });
        courses.stream().filter(course -> course.getStart_date().isAfter(LocalDate.now())).collect(Collectors.toList()).forEach(course -> {
            System.out.println("Course name: " + course.getCourse_name() + " Start date: " + course.getStart_date() + " end date: " + course.getEnd_date());
        });

    }
}

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class Course {
    private String course_name;
    private LocalDate start_date;
    private LocalDate end_date;
    private Map<Student, Double> enrolled_students = new HashMap<>();
    public Course(String course_name, LocalDate start_date, LocalDate end_date) {
        this.course_name = course_name;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public void addStudentAndScore(Student s, Double score) {
        this.enrolled_students.put(s, score);
    }
    public Map<Student, Double> getEnrolled_students() {
        return enrolled_students;
    }

}

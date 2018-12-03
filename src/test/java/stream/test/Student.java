package stream.test;

import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Data
public class Student implements Comparable<Student> {
    public enum Gender {MALE, FEMALE};
    public enum City {Seoul, Busan, Daegu};

    private String name;
    private int score;
    private Gender gender;
    private City city;

    public Student(String name, int score, Gender gender) {
        this.name = name;
        this.score = score;
        this.gender = gender;
    }

    public Student(String name, int score, Gender gender, City city) {
        this.name = name;
        this.score = score;
        this.gender = gender;
        this.city = city;
    }

    @Override
    public int compareTo(Student o) {
        return Integer.compare(score, o.score);
    }

    public int compareByName(Student o) {
        return name.compareTo(o.name);
    }

    /* 테스트를 위한 스트림 생성 */
    private static List<Student> getStudentsList() {
        return Arrays.asList(
                new Student("Adam", 95, Gender.MALE, City.Seoul),
                new Student("Sunny", 85, Gender.FEMALE, City.Seoul),
                new Student("Justin", 80, Gender.MALE, City.Busan),
                new Student("Irene", 90, Gender.FEMALE, City.Busan),
                new Student("Tom", 70, Gender.MALE, City.Daegu),
                new Student("Jenny", 75, Gender.FEMALE, City.Daegu),
                new Student("Johan", 60, Gender.MALE, City.Busan),
                new Student("Joy", 87, Gender.FEMALE, City.Busan),
                new Student("Anna", 92, Gender.MALE, City.Seoul),
                new Student("Franky", 70, Gender.MALE, City.Daegu),
                new Student("May", 75, Gender.FEMALE, City.Daegu),
                new Student("Brandon", 60, Gender.MALE, City.Busan),
                new Student("Johanson", 87, Gender.FEMALE, City.Seoul)
        );
    }

    public static Stream<Student> getStudentsStream() {
        List<Student> students = getStudentsList();

        return students.stream();
    }


    public static Stream<Student> getStudentsParallelStream() {
        List<Student> students = getStudentsList();

        return students.parallelStream();
    }

}

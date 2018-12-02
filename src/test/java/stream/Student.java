package stream;

import lombok.Data;

import java.util.Arrays;
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

    /*
테스트를 위한 스트림 생성
 */
    public static Stream<Student> getStudentsStream() {
        return Arrays.asList(
                new Student("Adam", 95, Student.Gender.MALE, Student.City.Seoul),
                new Student("Sunny", 85, Student.Gender.FEMALE, Student.City.Seoul),
                new Student("Justin", 80, Student.Gender.MALE, Student.City.Busan),
                new Student("Irene", 90, Student.Gender.FEMALE, Student.City.Busan),
                new Student("Tom", 70, Student.Gender.MALE, Student.City.Daegu),
                new Student("Jenny", 75, Student.Gender.FEMALE, Student.City.Daegu),
                new Student("Johan", 60, Student.Gender.MALE, Student.City.Busan),
                new Student("Joy", 87, Student.Gender.FEMALE, Student.City.Busan),
                new Student("Anna", 92, Student.Gender.MALE, Student.City.Seoul),
                new Student("Franky", 70, Student.Gender.MALE, Student.City.Daegu),
                new Student("May", 75, Student.Gender.FEMALE, Student.City.Daegu),
                new Student("Brandon", 60, Student.Gender.MALE, Student.City.Busan),
                new Student("Johanson", 87, Student.Gender.FEMALE, City.Seoul)
        ).stream();
    }

    public static Stream<Student> getStudentsParallelStream() {
        return Arrays.asList(
                new Student("Adam", 95, Student.Gender.MALE, Student.City.Seoul),
                new Student("Sunny", 85, Student.Gender.FEMALE, Student.City.Seoul),
                new Student("Justin", 80, Student.Gender.MALE, Student.City.Busan),
                new Student("Irene", 90, Student.Gender.FEMALE, Student.City.Busan),
                new Student("Tom", 70, Student.Gender.MALE, Student.City.Daegu),
                new Student("Jenny", 75, Student.Gender.FEMALE, Student.City.Daegu),
                new Student("Johan", 60, Student.Gender.MALE, Student.City.Busan),
                new Student("Joy", 87, Student.Gender.FEMALE, Student.City.Busan),
                new Student("Anna", 92, Student.Gender.MALE, Student.City.Seoul),
                new Student("Franky", 70, Student.Gender.MALE, Student.City.Daegu),
                new Student("May", 75, Student.Gender.FEMALE, Student.City.Daegu),
                new Student("Brandon", 60, Student.Gender.MALE, Student.City.Busan),
                new Student("Johanson", 87, Student.Gender.FEMALE, City.Seoul)
                ).parallelStream();
    }

}

package stream;

import lombok.Data;

@Data
public class Student implements Comparable<Student> {
    public enum Gender {MALE, FEMALE};
    public enum City {Seoul, Busan};

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

}

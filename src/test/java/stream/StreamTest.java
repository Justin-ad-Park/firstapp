package stream;

import java.text.Collator;
import java.util.Comparator;
import java.util.List;

import com.oracle.tools.packager.Log;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;


import static junit.framework.TestCase.assertEquals;

@Slf4j
public class StreamTest {

    @Test
    public void testStream() {
        String a = "a";

        assertEquals(a, "a");

        /*
        Practice with Stream
        */
        List<String> lang = Arrays.asList("Java", "Scala","Groovy","Python","Go","Swift");

        //정렬
        List<String> sortLang = lang.stream()
                                    .sorted()
                                    .collect(Collectors.toList());

        log.debug(sortLang.toString());

        //반대순 정렬
        List<String> reverseLang = lang.stream()
                                       .sorted(Comparator.reverseOrder())
                                       .collect(Collectors.toList());

        log.debug(reverseLang.toString());

        //Sort
        List<String> sortedByLength = lang.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());
        log.debug("Sorted by length" + sortedByLength.toString());

        //Sort by formula
        List<String> sortedByReverseLength = lang.stream()
                .sorted((a1, a2) -> a2.length() - a1.length())
                .collect(Collectors.toList());
        log.debug("Sorted by reverse length :" + sortedByReverseLength.toString());


    }

    @Test
    public void streamExample() {
        List<String> list = Arrays.asList(
                "This is a java book",
                "Lambda Expressions",
                "Java8 supports lambda expressions"
        );

        list.stream()
                .filter(s->s.toLowerCase().indexOf("java")>=0)
                .forEach(s->System.out.println(s));

        double avg = Student.getStudentsStream()
                .filter(s->s.getScore()>70)
                .sorted(Comparator.reverseOrder())
                .peek(s->System.out.println("Name : " +  s.getName() + ", Score : " + s.getScore()))
                .mapToInt(Student::getScore)
                .average().getAsDouble();

        System.out.println("Average : " + avg);
    }

    @Test
    public void streamExample4Map() {
        List<Student> list = Student.getStudentsStream()
                .filter(s->s.getGender() == Student.Gender.MALE)
                .peek(s->System.out.println(s.getName() +  " " + s.getGender()))
                .collect(Collectors.toList());

        System.out.println(list);
    }

    @Test
    public void steamExample4GroupingBy() {
        Map<Student.Gender, List<Student>> groupingMap = Student.getStudentsStream()
                .collect(Collectors.groupingBy(Student::getGender));

        groupingMap.forEach((gender, list)-> System.out.println(gender.name() + " " + list) );

        System.out.println("\n[MALE]");
        groupingMap.get(Student.Gender.MALE).stream().forEach(s->System.out.print(s.getName() + ", "));
        System.out.println("\n[FEMALE]");
        groupingMap.get(Student.Gender.FEMALE).stream().forEach(s->System.out.print(s.getName() + ", "));

    }



}

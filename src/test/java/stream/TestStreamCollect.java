package stream;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class TestStreamCollect {

    @Test
    public void testCollect1() {

        //Collect Method 1
        Supplier<MaleStudent> supplier = ()->new MaleStudent();
        BiConsumer<MaleStudent, Student> accumulator = (ms, s)->ms.accumulate(s);
        BiConsumer<MaleStudent, MaleStudent> combiner = (ms1, ms2)->ms1.combine(ms2);

        MaleStudent maleStudent = getStudentsSteam()
                .filter(s->s.getGender() == Student.Gender.MALE)
                .collect(supplier, accumulator, combiner);
        printLn("testCollect1", maleStudent);


        //Collect Method 2
        maleStudent = getStudentsSteam()
                .filter(s->s.getGender() == Student.Gender.MALE)
                .collect(
                        ()->new MaleStudent()
                        , (r, t)->r.accumulate(t)
                        , (r1, r2)->r1.combine(r2));

        printLn("testCollect2", maleStudent);


        //Collect Method 3
        maleStudent = getStudentsSteam()
                .filter(s -> s.getGender() == Student.Gender.MALE)
                .collect(MaleStudent::new, MaleStudent::accumulate, MaleStudent::combine);

        printLn("testCollect3", maleStudent);

    }

    @Test
    public void groupingBy() {
        //Group by gender
        Map<Student.Gender, List<Student>> mapBygender = getStudentsSteam()
                .collect(Collectors.groupingBy(Student::getGender));

        System.out.println("Group By gender :");
        mapBygender.forEach((gender, students) -> System.out.println("\t[" + gender + "] : " + students));

        Map<Student.City, List<String>> mapByCity = getStudentsSteam()
                .collect(
                        Collectors.groupingBy(
                                Student::getCity,
                                Collectors.mapping(Student::getName, Collectors.toList())
                        )
                );

        System.out.println("\nGroup By City :");
        mapByCity.forEach( (city, nameList)
                -> System.out.println("\t[" + city + "] : " + nameList));


    }

    @Test
    public void averagingDouble() {
        //성별 평균 점수
        Map<Student.Gender, Double> averageBygender = getStudentsSteam()
                .collect(
                        Collectors.groupingBy(
                                Student::getGender,
                                Collectors.averagingDouble(Student::getScore)
                        )
                );

        System.out.println("\n[AveragingDouble]Group By gender :");
        averageBygender.forEach( (gender, average)
                -> System.out.println("\t[" + gender + "] : " + average));


        //성별로 모은 이름
        Map<Student.Gender, String> nameBygender = getStudentsSteam()
                .collect(
                        Collectors.groupingBy(
                                Student::getGender,
                                Collectors.mapping(
                                        Student::getName,
                                        Collectors.joining(",")
                                )
                        )
                );

        System.out.println("\nGroup By City :");
        nameBygender.forEach( (city, names)
                -> System.out.println("\t[" + city + "] : " + names));
    }

    @Test
    public void sortingListsAfterGroupingBy() {
        Map<Student.Gender , List<Student>> sortedListsByGender = getStudentsSteam()
                .collect(Collectors.groupingBy(Student::getGender));

        System.out.println("\nAfter grouping by city");
        sortedListsByGender.forEach( (city, names)
                -> System.out.println("\t[" + city + "] : " + names));


        sortedListsByGender.values()
                .forEach(list -> Collections.sort(list, Student::compareByName));


        System.out.println("\nSorting lists by names after grouping by city :");
        sortedListsByGender.forEach( (city, names)
                -> System.out.println("\t[" + city + "] : " + names));

    }

    static <T> Collector<T,?,List<T>> toSortedList(Comparator<? super T> c) {
        return Collectors.collectingAndThen(
                Collectors.toCollection(ArrayList::new), l->{ l.sort(c); return l; } );
    }

    private void printLn(String methodName, MaleStudent maleStudent) {
        System.out.println("===" + methodName + "===");
        boolean result = maleStudent.getList().stream()
                .peek(s->System.out.print(s.getName() + " "))
                .allMatch(a->a.getGender() == Student.Gender.MALE);

        System.out.println("\nAre they all males? " + result + "\n");
    }

    /*
    테스트를 위한 스트림 생성
     */
    private Stream<Student> getStudentsSteam() {
        return Arrays.asList(
                new Student("Adam", 95, Student.Gender.MALE, Student.City.Seoul),
                    new Student("Sunny", 85, Student.Gender.FEMALE, Student.City.Seoul),
                    new Student("Justin", 80, Student.Gender.MALE, Student.City.Busan),
                    new Student("Irene", 90, Student.Gender.FEMALE, Student.City.Busan)

            ).stream();
    }

}

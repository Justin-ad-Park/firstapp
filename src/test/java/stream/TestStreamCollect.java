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

        MaleStudent maleStudent = Student.getStudentsStream()
                .filter(s->s.getGender() == Student.Gender.MALE)
                .collect(supplier, accumulator, combiner);
        printLn("testCollect1", maleStudent);


        //Collect Method 2
        maleStudent = Student.getStudentsStream()
                .filter(s->s.getGender() == Student.Gender.MALE)
                .collect(
                        ()->new MaleStudent()
                        , (r, t)->r.accumulate(t)
                        , (r1, r2)->r1.combine(r2));

        printLn("testCollect2", maleStudent);


        //Collect Method 3
        maleStudent = Student.getStudentsStream()
                .filter(s -> s.getGender() == Student.Gender.MALE)
                .collect(MaleStudent::new, MaleStudent::accumulate, MaleStudent::combine);

        printLn("testCollect3", maleStudent);

    }

    @Test
    public void groupingBy() {
        //Group by gender
        Map<Student.Gender, List<Student>> mapBygender = Student.getStudentsStream()
                .collect(Collectors.groupingBy(Student::getGender));

        System.out.println("Group By gender :");
        mapBygender.forEach((gender, students) -> System.out.println("\t[" + gender + "] : " + students));

        Map<Student.City, List<String>> mapByCity = Student.getStudentsStream()
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
        Map<Student.Gender, Double> averageBygender = Student.getStudentsStream()
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
        Map<Student.Gender, String> nameBygender = Student.getStudentsStream()
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

    /*
        그룹핑 및 그룹핑된 결과에서 각 항목 정렬
     */
    @Test
    public void sortingListsAfterGroupingBy() {
        //성별을 키로 학생 리스트를 그룹핑
        Map<Student.Gender , List<Student>> sortedListsByGender = Student.getStudentsStream()
                .collect(Collectors.groupingBy(Student::getGender));

        System.out.println("\nAfter grouping by city");
        sortedListsByGender.forEach( (city, names)
                -> System.out.println("\t[" + city + "] : " + names));

        //위에서 그룹핑된 맵의 각 리스트를 이름으로 정렬
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



}

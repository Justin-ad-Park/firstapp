package stream;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
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
                .filter(s->s.getSex() == Student.Sex.MALE)
                .collect(supplier, accumulator, combiner);
        printLn("testCollect1", maleStudent);


        //Collect Method 2
        maleStudent = getStudentsSteam()
                .filter(s->s.getSex() == Student.Sex.MALE)
                .collect(
                        ()->new MaleStudent()
                        , (r, t)->r.accumulate(t)
                        , (r1, r2)->r1.combine(r2));

        printLn("testCollect2", maleStudent);


        //Collect Method 3
        maleStudent = getStudentsSteam()
                .filter(s -> s.getSex() == Student.Sex.MALE)
                .collect(MaleStudent::new, MaleStudent::accumulate, MaleStudent::combine);

        printLn("testCollect3", maleStudent);

    }

    @Test
    public void groupingBy() {
        //Group by Sex
        Map<Student.Sex, List<Student>> mapBySex = getStudentsSteam()
                .collect(Collectors.groupingBy(Student::getSex));

        System.out.println("Group By Sex :");
        mapBySex.forEach((sex, students) -> System.out.println("\t[" + sex + "] : " + students));

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


    private void printLn(String methodName, MaleStudent maleStudent) {
        System.out.println("===" + methodName + "===");
        boolean result = maleStudent.getList().stream()
                .peek(s->System.out.print(s.getName() + " "))
                .allMatch(a->a.getSex() == Student.Sex.MALE);

        System.out.println("\nAre they all males? " + result + "\n");
    }

    /*
    테스트를 위한 스트림 생성
     */
    private Stream<Student> getStudentsSteam() {
        return Arrays.asList(
                new Student("Adam", 12, Student.Sex.MALE, Student.City.Seoul),
                    new Student("Sunny", 17, Student.Sex.FEMALE, Student.City.Seoul),
                    new Student("Justin", 13, Student.Sex.MALE, Student.City.Busan),
                    new Student("Irene", 8, Student.Sex.FEMALE, Student.City.Busan)

            ).stream();
    }

}

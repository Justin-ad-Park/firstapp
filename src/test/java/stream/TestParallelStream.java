package stream;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class TestParallelStream {

    @Test
    public void ParallelCollection() {
        MaleStudent maleStudent = Student.getStudentsParallelStream()
                .filter(s->s.getGender() == Student.Gender.MALE)
                .collect(MaleStudent::new, MaleStudent::accumulate, MaleStudent::combine);
    }

    @Test
    public void SingleProcessCollection() {
        MaleStudent maleStudent = Student.getStudentsStream()
                .filter(s->s.getGender() == Student.Gender.MALE)
                .collect(MaleStudent::new, MaleStudent::accumulate, MaleStudent::combine);
    }

    @Test
    public void ParallelCollection2() {
        long start = System.nanoTime();
        Student.getStudentsStream()
                .forEach((a)->TestParallelStream.work(a));
        long end = System.nanoTime();
        long runTime = end - start;

        System.out.println("Single Stream untime    : " + runTime);

        start = System.nanoTime();
        Student.getStudentsParallelStream()
                .forEach((a)->TestParallelStream.work(a));
        end = System.nanoTime();
        runTime = end - start;

        System.out.println("Parallel Stream Runtime : " + runTime);


    }

    @Test
    public void ParallelCollection3() {
    }


    private static void work(Student s) {
        try { Thread.sleep(100); }
        catch (InterruptedException e) {}
    }

}

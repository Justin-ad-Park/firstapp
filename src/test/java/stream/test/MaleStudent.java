package stream.test;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MaleStudent {
    private List<Student> list;

    public MaleStudent() {
        list = new ArrayList<Student>();
        log.debug("[" + Thread.currentThread().getName() + "] MailStudent() Constructor");
    }

    public void accumulate(Student student) {
        list.add(student);
        log.debug("[" + Thread.currentThread().getName() + "] accumulate()");
    }

    public void combine(MaleStudent other) {
        list.addAll(other.getList());
        log.debug("[" + Thread.currentThread().getName() + "] combile()");
    }

    public List<Student> getList() {
        return list;
    }

}

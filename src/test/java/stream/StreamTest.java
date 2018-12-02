package stream;

import java.util.Comparator;
import java.util.List;

import com.oracle.tools.packager.Log;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.util.Arrays;
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

}

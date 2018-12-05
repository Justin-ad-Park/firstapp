package lambda.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class LambdaBasicTest {
    public String outterField = "Outter Field";

    @Test
    public void myFunctionalInterface() {
        MyFunctionalInterface fi;

        fi = () -> {
            String str = "Tethod call1";
            log.debug(str);
        };

        fi.method();

        fi = () -> { log.debug("method call2");};
        fi.method();;

        fi = ()->log.debug("method call3");
        fi.method();


    }

    @Test
    public void myFunctionalInterface2() {
        MyFunctionalInterface2 fi2;

        fi2 = (x) -> {
            int result = x * 5;
            log.debug("Result : " + result);
        };

        fi2.method(2);

        fi2 = (x) -> { log.debug("Result : " + x*6); };
        fi2.method(2);

        fi2 = x -> log.debug("Result : " + x*7);
        fi2.method(2);
    }

    @Test
    public void myFunctionInterface3() {
        MyFunctionalInterface3 fi = (x, y) -> {return x + y;};
        log.debug("Result1 : " + fi.method(2,5));

        fi = (x, y) -> x + y;
        log.debug("Result2 : " + fi.method(2,5));

        fi = (x, y) -> sum(x, y);
        log.debug("Result3 : " + fi.method(2, 5));

    }

    public static int sum(int x, int y) {
        return x + y;
    }

    @Test
    public void testUsingThis() {
        LambdaBasicTest usingThis = new LambdaBasicTest();
        LambdaBasicTest.Inner inner = usingThis.new Inner();
        inner.method1();

    }

    class Inner {
        String innerField = "Inner Field";

        public void method1() {
            MyFunctionalInterface fi = () -> {
                log.debug("Field = " + outterField);
                log.debug("Field = " + LambdaBasicTest.this.outterField);

                log.debug("InnerField : " +  innerField);
                log.debug("InnerField : " +  this.innerField);
            };

            fi.method();
        }

    }
}

package lambda.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import stream.test.Student;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.IntSupplier;
import java.util.function.ObjIntConsumer;
import java.util.function.Function;

@Slf4j
public class LambdaFunctionTest {

    @Test
    public void comsumerExample() {
        Consumer<String> consumer = t -> log.debug("Consumer : " + t);
        consumer.accept("java");

        BiConsumer<String, String> biConsumer = (t, u) -> log.debug("BiConsumer : " + t + " - " + u);
        biConsumer.accept("Just", "An");

        ObjIntConsumer<String> objIntConsumer = (t, i) -> log.debug("ObjIntConsumer : " + t + " - " + i);
        objIntConsumer.accept("Object", 11);

    }

    @Test
    public void supplierExample() {
        IntSupplier intSupplier = () -> {
            int num = (int) (Math.random() * 6) + 1;
            return num;
        };

        log.debug("int : " + intSupplier.getAsInt());

    }


}

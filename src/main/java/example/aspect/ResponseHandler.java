package example.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

@Aspect
@Component
@Slf4j
public class ResponseHandler {

    /*
    @Before("execution(* *..*Controller.*(..))")
    public void startLog(JoinPoint jp) {
        log.debug("[AOP Test]Before method : " + jp.getSignature());
    }
    */

    /**
     *
     * Test URL : http://localhost:8080/aoptest
     * @param jp
     */
    @Before("@annotation(example.aspect.annotation.NoCache)")
    public void setHeaderNoCache(JoinPoint jp) {
//        Object targetObject = jp.getTarget();
//        log.debug("[AOP Object info : " + targetObject.getClass().getName());
//
//        Object thisObject = jp.getThis();
//        log.debug("[AOP getThis info : " + thisObject.getClass().getName());

        Object[] args = jp.getArgs();

        for(Object arg : args)
        {
            if(arg instanceof HttpServletResponse) {
                HttpServletResponse response = (HttpServletResponse) arg;

                response.setHeader("Cache-Control", "no-cache");
                response.addHeader("Cache-Control","no-store");
                response.setHeader("Pragma","no-cache");
                response.setDateHeader("Expires",1L);

                return;
            }
        }
    }
}

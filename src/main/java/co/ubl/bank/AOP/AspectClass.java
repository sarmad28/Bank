package co.ubl.bank.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AspectClass {

    /*public static final Logger log = LoggerFactory.getLogger(AspectClass.class);

    @Around(value = "execution(* co.ubl.bank..*(..))")
    public void logAroundMethod(JoinPoint joinPoint) {
        log.info("Method Executed:: " + joinPoint.getSignature().getName());
    }*/

    private static final Logger logger = LoggerFactory.getLogger(AspectClass.class);

    @Around(value = "execution(* co.ubl.bank..*(..))")
    public Object logMethodExecution(JoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        logger.info("Method {} is starting...", joinPoint.getSignature());

        Object result = joinPoint.getSignature().getName(); // Execute the method

        long elapsedTime = System.currentTimeMillis() - start;
        logger.info("Method {} executed in {} ms", joinPoint.getSignature(), elapsedTime);

        return result;
    }
}

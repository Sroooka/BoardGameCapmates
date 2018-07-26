package com.capgemini.jstk.BoardGameCapmates.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutionTimeAspect {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExecutionTimeAspect.class);

	@Around("execution(* com.capgemini.jstk.BoardGameCapmates.*.*.*(..))")
	public Object profile(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.currentTimeMillis();
		LOGGER.info("Going to call the method: " + pjp.getSignature().getName());
		Object output = pjp.proceed();
		LOGGER.info("Method " + pjp.getSignature().getName() + " execution completed.");
		long elapsedTime = System.currentTimeMillis() - start;
		LOGGER.info("Execution time of method: " + elapsedTime + "[ms].");
		return output;
	}
}
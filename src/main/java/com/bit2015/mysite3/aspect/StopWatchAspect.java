package com.bit2015.mysite3.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class StopWatchAspect {
	@Around("execution(* *..service.*.*(..))")
	public Object aroundMethod(ProceedingJoinPoint pjp) throws Throwable {
		String taskName = pjp.getTarget().getClass() + "."
				+ pjp.getSignature().getName();
		// 시간측정 시작
		StopWatch stopWatch = new StopWatch();
		stopWatch.start(taskName);

		Object result = pjp.proceed();

		// 시간측정 끝
		stopWatch.stop();
		System.out.println("[실행시간][" + taskName + "] : "
				+ stopWatch.getTotalTimeMillis() + "millis");

		return result;
	}
}

package com.aspect;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ProcessTimeAspect {
	@Around("execution(* com.service.*.*(..))")
	public Object recordTime(ProceedingJoinPoint joinpoint) throws Throwable {
		long starttime = System.currentTimeMillis();
		System.out.println("=====任务开始执行时间: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(starttime) + "=====");
		Object object = joinpoint.proceed();
		long endtime = System.currentTimeMillis();
		System.out.println("=====任务结束处理时间: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endtime) + "=====");
		System.out.println("=====处理耗时: " + (endtime - starttime) / 1000 + "s=====");
		return object;
	}
}

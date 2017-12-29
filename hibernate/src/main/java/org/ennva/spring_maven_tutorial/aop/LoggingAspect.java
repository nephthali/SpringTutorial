package org.ennva.spring_maven_tutorial.aop;

import org.aspectj.lang.JoinPoint; //JoinPoint is a point during execution of a program such as execution of a method or handling exception
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.ennva.spring_maven_tutorial.model.Circle;

@Aspect
public class LoggingAspect {

	// This is advice
	// @Before("allCircleMethods()")
	public void loggingAdvice(JoinPoint joinpoint) {
		System.out.println("Advice run. Get method is called");
		System.out.println(joinpoint.toString());
		Circle c = (Circle) joinpoint.getTarget();
		System.out.println(c.getName());
	}

	@Before("getNamePointcut()")
	public void allGetNameMethods(JoinPoint joinpoint) {
		System.out.println("GetNameMethod: " + joinpoint.toString());
	}

	// @Before("args(name)")
	// public void allStringMethodArguments(String name) {
	// System.out.println("A setter method has been executed............"+name);
	// }

	// @AfterReturning(pointcut = "args(name)", returning = "returnString")
	public void allStringMethodArguments(String name, String returnString) {
		System.out.println("A setter method has been executed............" + name);
	}

	// @AfterThrowing(pointcut = "args(name)", throwing = "ex")
	public void exceptionAdvice(String name, Exception ex) {
		System.out.println("Exception is thrown ............" + ex);
	}

	@After("args(String)")
	public void afterAdvice() {
		System.out.println("After Advice is executed...........");
	}

	@Pointcut("execution(* get*())")
	public void allGetters() {
	}

	@Around("allGetters()")
	public Object myAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
		Object returnValue = null;
		try {
			System.out.println("Before method Executed");
			returnValue = proceedingJoinPoint.proceed();
			System.out.println("After Method Executed....");
		} catch (Throwable e) {
			System.out.println("Around Advice throws exception");
		}
		System.out.println("After Finally executed........");

		return returnValue;
	}

	@Pointcut("execution(public String getName())")
	public void getNamePointcut() {
	}

	// This is poincut: apply advice on all methods of class into model
	@Pointcut("within(org.ennva.spring_maven_tutorial.model.*)")
	public void allCircleMethods() {
	}

}

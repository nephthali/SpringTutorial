package org.ennva.spring_maven_tutorial;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author nephthali
 *
 */
public class Rectangle implements ApplicationContextAware, BeanNameAware {

	private Point pointA;
	private Point pointB;
	private Point pointC;
	private Point pointD;
	private ApplicationContext context = null;

	public Point getPointA() {
		return pointA;
	}

	public void setPointA(Point pointA) {
		this.pointA = pointA;
	}

	public Point getPointB() {
		return pointB;
	}

	public void setPointB(Point pointB) {
		this.pointB = pointB;
	}

	public Point getPointC() {
		return pointC;
	}

	public void setPointC(Point pointC) {
		this.pointC = pointC;
	}

	public Point getPointD() {
		return pointD;
	}

	public void setPointD(Point pointD) {
		this.pointD = pointD;
	}

	public void draw() {
		System.out.println("PointA is (" + pointA.getX() + ", " + pointA.getY() + ")");
		System.out.println("PointB is (" + pointB.getX() + ", " + pointB.getY() + ")");
		System.out.println("PointC is (" + pointC.getX() + ", " + pointC.getY() + ")");
		System.out.println("PointD is (" + pointD.getX() + ", " + pointD.getY() + ")");
	}

	public void setApplicationContext(ApplicationContext context) throws BeansException {
		// Check information about Current IoC Container or Context
		System.out.println("Number of beans: " + context.getBeanDefinitionCount()); //Return the number of beans defined in the factory/container.
		System.out.println("Context Id: " + context.getId()); //Return the unique id of this application context.
		System.out.println("Names of beans: " + context.getBeanDefinitionNames()); //Return the names of all beans defined in this factory.
		this.context = context;
	}

	public void setBeanName(String beanName) {
		System.out.println("Bean name is: " + beanName);
	}

}

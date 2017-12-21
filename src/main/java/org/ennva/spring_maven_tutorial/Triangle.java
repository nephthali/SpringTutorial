package org.ennva.spring_maven_tutorial;

public class Triangle {

	private String type;
	private Point pointA;
	private Point pointB;
	private Point pointC;

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

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

	public void draw() {
		System.out.println(type + " Triangle Drawn");
		System.out.println("PointA is (" + pointA.getX() + ", " + pointA.getY() + ")");
		System.out.println("PointB is (" + pointB.getX() + ", " + pointB.getY() + ")");
		System.out.println("PointC is (" + pointC.getX() + ", " + pointC.getY() + ")");
	}

	/*
	 * Dimonstration of Bean LifeCycle Manage by Spring beanFactory Container Theses
	 * method control Initialization and destruction of my Bean class and need to be
	 * add to metadata xml file. In this case beans.xml
	 */
	public void myInit() {
		System.out.println("-- Object Initialized --");
	}

	public void myDestroy() {
		System.out.println("-- Object Destroyed --");
	}

}

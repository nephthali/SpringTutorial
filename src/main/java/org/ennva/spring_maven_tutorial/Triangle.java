package org.ennva.spring_maven_tutorial;

public class Triangle {

	private String type;
	private Point pointA;
	private Point pointB;
	private Point pointC;

	public Triangle() {
		System.out.println("-- Triangle Bean constructor --");
	}

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

	/* Initialization callbacks method */
	public void myInit() {
		// System.out.println("-- My init method is called for Triangle --");
	}

	/* Destruction callbacks method */
	public void cleanUp() {
		// System.out.println("-- cleanUp method is called for Triangle --");
	}

}

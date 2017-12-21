package org.ennva.spring_maven_tutorial;

/**
 * @author nephthali
 *
 */
public class Circle {

	private Point center;
	
	
	/**
	 * @param center
	 */
	public Circle(Point center) {
		this.center = center;
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public void draw() {
		System.out.println("Center of circle is (" + center.getX() + ", " + center.getY() + ")");
	}

}

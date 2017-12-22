package org.ennva.spring_maven_tutorial;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author nephthali
 *
 */
public class Circle {

	// using autowired annotation with property
	// @Autowired
	private Point center;
	private String type;

	public Circle() {
		System.out.println("-- Circle constructor --");
	}

	/**
	 * @param center
	 */
	// using autowired annotation with constructor
	@Autowired(required=false)
	public Circle(Point center) {
		this.center = center;
	}

	public Point getCenter() {
		return center;
	}

	// using autowired annotation with setter method
	// @Autowired
	public void setCenter(Point center) {
		this.center = center;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void draw() {
		System.out.println("Shape type: " + type);
		System.out.println("Center of " + type + " is: (" + center.getX() + ", " + center.getY() + ")");
	}

}

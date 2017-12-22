package org.ennva.spring_maven_tutorial;

/**
 * @author nephthali
 *
 */
public class Circle implements Shape {

	private Point center;
	private String type;
	
	public Circle() {
		System.out.println("-- Circle constructor --");
	}

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
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public void draw() {
		System.out.println("Shape type: "+ type);
		System.out.println("Center of " + type + " is (" + center.getX() + ", " + center.getY() + ")");
	}

}

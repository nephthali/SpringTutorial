package org.ennva.spring_maven_tutorial.model;

/**
 * @author nephthali
 *
 */
public class Circle {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		System.out.println("Setter method is called.....");
		throw (new RuntimeException());
	}

	public String setNameAndReturning(String name) {
		this.name = name;
		System.out.println("Setter Method and return the value method executed....");
		return name;
	}

}

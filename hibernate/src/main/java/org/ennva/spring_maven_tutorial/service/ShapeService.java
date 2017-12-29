package org.ennva.spring_maven_tutorial.service;

import org.ennva.spring_maven_tutorial.model.Circle;
import org.ennva.spring_maven_tutorial.model.Triangle;

public class ShapeService {
	
	private Circle circle;
	private Triangle triangle;
	
	
	
	public ShapeService() {
		//System.out.println("-- costructor --");
	}

	public Circle getCircle() {
		return this.circle;
	}
	
	public void setCircle(Circle circle) {
		this.circle = circle;
	}
	
	public Triangle getTriangle() {
		return this.triangle;
	}
	
	public void setTriangle(Triangle triangle) {
		this.triangle = triangle;
	}

}

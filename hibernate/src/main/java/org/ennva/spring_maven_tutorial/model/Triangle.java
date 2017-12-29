package org.ennva.spring_maven_tutorial.model;


public class Triangle {

	private String name;
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/* Initialization callbacks method */
	public void myInit() {
		 System.out.println("-- My init method is called for Triangle --");
	}

	/* Destruction callbacks method */
	public void cleanUp() {
		 System.out.println("-- cleanUp method is called for Triangle --");
	}

}

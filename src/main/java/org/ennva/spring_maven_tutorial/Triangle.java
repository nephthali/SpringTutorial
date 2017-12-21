package org.ennva.spring_maven_tutorial;

public class Triangle {
	
	private int height;
	private String type;
	
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void draw()
	{
		System.out.println("Drawing Triangle");
	}
	
	@Override
	public String toString() {
		return "Triangle [height=" + height + ", type=" + type + "]";
	}

	/*
	 * Dimonstration of Bean LifeCycle Manage by Spring beanFactory Container
	 * Theses method control Initialization and destruction of my Bean class and
	 * need to be add to metadata xml file. In this case beans.xml
	 * */
	public void myInit() {
		System.out.println("-- Object Initialized --");
	}
	
	public void myDestroy() {
		System.out.println("-- Object Destroyed --");
	}

}

package org.ennva.spring_maven_tutorial;

public class Triangle {
	
	public void draw()
	 {
	     System.out.println("Drawing Triangle");
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

package org.ennva.spring_maven_tutorial.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Employee")
public class Employee {

	@Id
	@Column(name="empid")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int empid;
	
	@Column(name="name")
	private String name;
	
	@Column(name="age")
	private int age;
	
	@Column(name="salary")
	private long salary;
	
	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}
	
	@Override
	public String toString() {
		return "Employee [empid=" + empid + ", name=" + name + ", age=" + age + ", salary=" + salary + "]";
	}

	/*
	 * Dimonstration of Bean LifeCycle Manage by Spring Core Layer or Container
	 * Theses method control Initialization and destruction of my Bean class and
	 * need to be add to metadata xml file .
	 */
	// public void myInit() {
	// System.out.println("-- Object Initialized --");
	// }
	//
	// public void myDestroy() {
	// System.out.println("-- Object Destroyed --");
	// }

}

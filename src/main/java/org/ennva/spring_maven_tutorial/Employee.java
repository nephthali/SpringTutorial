package org.ennva.spring_maven_tutorial;

import com.ennva.Address;

//Bean or Model or POJO(The least restricted class)
public class Employee {

	int eid;
	String ename;
	Address eaddress;
//	String eaddress;
	
	public Employee() {
		System.out.println("--Employee Object Constructed--");
	}
	
	// Constructor Dependency Injection
	public Employee(Address address) {
		this.eaddress = address;
	}
	
	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}


	public String getEname() {
		return ename;
	}



	public void setEname(String ename) {
		this.ename = ename;
	}


	public Address getEaddress() {
		return eaddress;
	}

	// Setter Dependency Injection
	public void setEaddress(Address eaddress) {
		this.eaddress = eaddress;
	}

	@Override
	public String toString() {
		return "Employee [eid=" + eid + ", ename=" + ename + ", eaddress=" + eaddress + "]";
	}

	/*
	 * Dimonstration of Bean LifeCycle Manage by Spring Core Layer or Container
	 * Theses method control Initialization and destruction of my Bean class and
	 * need to be add to metadata xml file .
	 * */
	public void myInit() {
		System.out.println("-- Object Initialized --");
	}
	
	public void myDestroy() {
		System.out.println("-- Object Destroyed --");
	}
	
}


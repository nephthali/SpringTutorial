package org.ennva.spring_maven_tutorial;

public class Address {
	
	String city;
	State state;
	State commonstate;
	//String state;
	int zipcode;
	
	public Address() {
		System.out.println("--Address bean constructed-- \n");
	}

	// Constructor Dependency Injection
	public Address(State state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public State getState() {
		return state;
	}
	
	// Setter Dependency Injection
	public void setState(State state) {
		this.state = state;
	}
	
	public State getCommonstate() {
		return commonstate;
	}
	
	public void setCommonstate(State commonstate) {
		this.commonstate = commonstate;
	}
	
	public int getZipcode() {
		return zipcode;
	}
	
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	
	@Override
	public String toString() {
		return "Address [city=" + city + ", state=" + state + ", commonstate=" + commonstate + ", zipcode=" + zipcode
				+ "]";
	}

}

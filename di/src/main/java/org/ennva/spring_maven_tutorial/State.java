package org.ennva.spring_maven_tutorial;

public class State {
	
	String continent;
	int population;
	
	public State() {
		System.out.println("-- State ben constructed -- \n");
	}
	
	public String getContinent() {
		return continent;
	}
	public void setContinent(String continent) {
		this.continent = continent;
	}
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}

	@Override
	public String toString() {
		return "State [continent=" + continent + ", population=" + population + "]";
	}
	
}

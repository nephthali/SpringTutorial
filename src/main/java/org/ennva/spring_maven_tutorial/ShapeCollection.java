package org.ennva.spring_maven_tutorial;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class ShapeCollection {
	
	private List<Triangle> shapeOfList;
//	private List<String> shapeOfList;
	private Set<String> shapeOfSet;
	private Map<String, String> shapeOfMap;
	private Properties shapeOfProperties;
	
	public ShapeCollection() { 
		System.out.print("--Shape collection constructed-- \n");
		System.out.println("-- scope of class Triangle -- : " + shapeOfList );
	}
	
	

	public List<Triangle> getShapeOfList() {
		return shapeOfList;
	}



	public void setShapeOfList(List<Triangle> shapeOfList) {
		this.shapeOfList = shapeOfList;
	}



	public Set<String> getShapeOfSet() {
		return shapeOfSet;
	}

	public void setShapeOfSet(Set<String> shapeOfSet) {
		this.shapeOfSet = shapeOfSet;
	}

	public Map<String, String> getShapeOfMap() {
		return shapeOfMap;
	}

	public void setShapeOfMap(Map<String, String> shapeOfMap) {
		this.shapeOfMap = shapeOfMap;
	}

	public Properties getShapeOfProperties() {
		return shapeOfProperties;
	}

	public void setShapeOfProperties(Properties shapeOfProperties) {
		this.shapeOfProperties = shapeOfProperties;
	}
	
	

	@Override
	public String toString() {
		return "ShapeCollection [shapeOfList=" + shapeOfList + ", shapeOfSet=" + shapeOfSet + ", shapeOfMap="
				+ shapeOfMap + ", shapeOfProperties=" + shapeOfProperties + "]";
	}
	
	
}

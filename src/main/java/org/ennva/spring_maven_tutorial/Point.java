package org.ennva.spring_maven_tutorial;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * @author nephthali
 *
 */
@Service
//@Scope("singleton") The defaut
@Scope("prototype")
public class Point {
	
	private int x;
	private int y;
	
	
	/**
	 * @return
	 */
	public int getX() {
		return x;
	}
	
	
	/**
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * @return
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}

}

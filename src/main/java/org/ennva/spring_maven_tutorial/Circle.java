package org.ennva.spring_maven_tutorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

/**
 * @author nephthali
 *
 */
@Component
public class Circle {

	@Autowired
	private Point center;
	
	@Autowired
	private MessageSource messageSource;
	
	

	public Circle() {
		
	}

	/**
	 * @param center
	 */
	public Circle(Point center) {
		this.center = center;
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public void draw() {
		//System.out.println("Center of circle is (" + center.getX() + ", " + center.getY() + ")");
		System.out.println(this.messageSource.getMessage("drawing.circle", null, "Default Drawing Circle", null));
		// passing aray of object to MessageSource
		System.out.println(this.messageSource.getMessage("drawing.point", new Object[]{center.getX(), center.getY()}, "Default Drawing Circle", null));
	}

}

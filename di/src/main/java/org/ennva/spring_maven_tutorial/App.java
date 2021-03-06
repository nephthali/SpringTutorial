package org.ennva.spring_maven_tutorial;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        /**
         * Types of Dependency Injection:
		 * 1- By Constructor (<constructor-arg> subelement of bean is used for constructor injection)
		 * 2- By Setter method (<property> subelement of bean is usedfor setter injection)
		 * 
		 * See the POJO Employee.java on how Address is injected.
		 * 
		 * */
 
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		Employee emp = context.getBean("emp",Employee.class);
		
		/* Collection injection */
		ShapeCollection shapeCollection = (ShapeCollection) context.getBean("shapeCollection");
		System.out.println(shapeCollection);
		
		System.out.println("\n Employee Details" + emp);

        // Destroy the Bean Object :
        //You have to reference the ClassPathXmlApplicationContext
        ClassPathXmlApplicationContext ctx = (ClassPathXmlApplicationContext)context;
        // And close the container
        ctx.close();
    }
}

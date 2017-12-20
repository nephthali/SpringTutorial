package org.ennva.spring_maven_tutorial;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Client Application!
 *
 */
public class App
{
    public static void main( String[] args )
    {
    	/*
    	 * Traditional method without spring.
    	 * You have to construct yourself a Triangle class
    	 * */
//    	Triangle triangle = new Triangle();
    	
        /**
         * With Spring beanFactory container, class will be construct for you.
        **/
    	Resource resource = new ClassPathResource("beans.xml");
    	BeanFactory factory = new XmlBeanFactory(resource);
    	Triangle triangle = (Triangle) factory.getBean("triangle");
    	triangle.draw();
    	


        // Destroy the Bean Object :
        //You have to reference the ClassPathXmlApplicationContext
        //ClassPathXmlApplicationContext ctx = (ClassPathXmlApplicationContext)context;
        // And close the container
        //ctx.close();
    }
}

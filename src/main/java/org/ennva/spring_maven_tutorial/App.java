package org.ennva.spring_maven_tutorial;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


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
         * With Spring ApplicationContext container, class will be construct for you.
         * ApplicationContext is build on top of beanFactory and provide more
         * Functionality than beanFactory
         * 
         * ClassPathXmlApplicationContext taking the context definition files from the class path, 
         * interpreting plain paths as class path resource names that include the package path 
         * (e.g. "mypackage/myresource.txt")
         * 
         * NB:The bean definitions will be loaded from the classpath, as a ClassPathResource will be used
         * 
         **/
    	ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
//    	Triangle triangle = (Triangle) context.getBean("triangle");
//    	triangle.draw();
    	
    	/**
         * FileSystemXmlApplicationContext taking the context definition files from the file system 
         * or from URLs, 
         * interpreting plain paths as relative file system locations (e.g. "mydir/myfile.txt")
         * NB:The bean definition will be loaded from a filesystem location, 
         * 	in this case relative to the current working directory
         **/
//    	ApplicationContext context1 = new FileSystemXmlApplicationContext("src/main/resources/beans.xml");
//    	Triangle triangle1 = (Triangle) context1.getBean("triangle");
//    	triangle1.draw();
    	
    	/**
         * FileSystemXmlApplicationContext:
         * Note that the use of the special classpath prefix or a standard URL prefix on the location path will override the 
         * default type of Resource created to load the definition
         * 
         **/
//    	ApplicationContext context2 =
//    		    new FileSystemXmlApplicationContext("src/main/resources/*.xml");
//    	Triangle triangle2 = (Triangle) context2.getBean("triangle");
//    	triangle2.draw();
    	
    	/**
         * FileSystemXmlApplicationContext:
         * Wildcard like Ant-Style like /WEB-INF/*-context.xml
         * can also be use other that classpath*: FileSystemXmlApplicationContext("classpath*:beans.xml");
         * 
         **/
//    	ApplicationContext context3 =
//    		    new FileSystemXmlApplicationContext("classpath:*.xml");
////    	ApplicationContext context3 =
////    		    new FileSystemXmlApplicationContext("src/main/resources/*.xml");
//    	Triangle triangle3 = (Triangle) context2.getBean("triangle");
//    	triangle3.draw();
    	
    	/**
    	 * Autowiring by type test
    	 */
//    	Circle circle = (Circle) context.getBean("circle");
//    	circle.draw();
    	
    	/**
    	 * Bean Scope Test: Singleton and Prototype
    	 */
		// Point point1 = (Point) context.getBean("zeroPoint");
		// Point point2 = (Point) context.getBean("zeroPoint");
    	Point point1 = (Point) context.getBean("point");
    	Point point2 = (Point) context.getBean("point");
    	if(point1 == point2)
    	 System.out.println("In Singleton Demo >> Both are same objects values are.. \n" + "n1. point1= "+point1+"\nn2. point2= "+point2);
    	else
    	  System.out.println("In Prototype Demo >> Both are different objects values are.. \n" + "n1. point1= "+point1+"\nn2. point2= "+point2);
    	 
    	
    	/** In order to Destroy the Bean Object, in this case a singleton scope bean
    	 *  sigleton: When a bean is a singleton, only one shared instance of the bean will 
    	 *  be managed and all requests for beans with an id or ids matching that bean 
    	 *  definition will result in that one specific bean instance being returned
    	 *  
    	 *  Detruction mean remove bean on JVM. I need ApplicationContext that enable destruction of 
    	 *  a single bean that has a destroy-method
    	 * 
    	 */
    	//ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"beans.xml"});
    	ClassPathXmlApplicationContext ctx = (ClassPathXmlApplicationContext)context;
    	ctx.close();

    }
}

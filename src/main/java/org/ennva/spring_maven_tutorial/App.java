package org.ennva.spring_maven_tutorial;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
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
//    	Resource resource = new ClassPathResource("beans.xml");
//    	BeanFactory factory = new XmlBeanFactory(resource);
//    	Triangle triangle = (Triangle) factory.getBean("triangle");
//    	triangle.draw();
    	
    	/**
    	 * XmlBeanFactory is deprecated as on spring 3.1 in favour
    	 * of DefaultListableBeanFactory. So
    	 * */
    	Resource resource = new ClassPathResource("beans.xml");
    	DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
    	BeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
    	reader.loadBeanDefinitions(resource);
    	
    	Triangle triangle = beanFactory.getBean("triangle", Triangle.class);
    	triangle.draw();
    	
    	/** In order to Destroy the Bean Object, in this case a singleton scope bean
    	 *  sigleton: When a bean is a singleton, only one shared instance of the bean will 
    	 *  be managed and all requests for beans with an id or ids matching that bean 
    	 *  definition will result in that one specific bean instance being returned
    	 *  
    	 *  Detruction mean remove bean on JVM. I need ApplicationContext that enable destruction of 
    	 *  a single bean that has a destroy-method
    	 * 
    	 */
    	ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"beans.xml"});
    	context.close();

        
        //You have to reference the ClassPathXmlApplicationContext
        //ClassPathXmlApplicationContext ctx = (ClassPathXmlApplicationContext)context;
        // And close the container
        //ctx.close();
    }
}

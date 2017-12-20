# SpringTutorial – Spring BeanFactory Implementation
A Spring BeanFactory is like a factory class that contains a collection of beans.
The Spring BeanFactory holds Bean Definitions of multiple beans within itself and then instantiates
the bean whenever asked for by clients.

* Spring BeanFactory is able to create associations between collaborating objects as they are instantiated. This removes the burden of configuration from bean itself and the beans client.

* Spring  BeanFactory also takes part in the life cycle of a bean, making calls to custom initialization and destruction methods

* 
	```
	This is the simplest container providing basic support for DI and defined by the org.springframework.beans.factory.BeanFactory interface. The BeanFactory and related interfaces, such as BeanFactoryAware, InitializingBean, DisposableBean, are still present in Spring for the purposes of backward compatibility with the large number of third-party frameworks that integrate with Spring
	```
*Interesting definition of beanFactory can be found [here](https://spring.io/blog/2011/08/09/what-s-a-factorybean) at https://spring.io/blog/2011/08/09/what-s-a-factorybean.
<br /><br />
![image](https://i1.wp.com/www.dineshonjava.com/wp-content/uploads/2012/06/beanfactory.jpg?resize=530%2C331&ssl=1)
<br /><br />

### Triangle.java
```
package org.ennva.spring_maven_tutorial;

public class Triangle 
{
 public void draw()
 {
     System.out.println("Drawing Triangle");
 }
}
```

### beans.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.1.xsd">
	
	<bean class="org.ennva.spring_maven_tutorial.Triangle" id="triangle" />

</beans>
```

There are following two important points to note about the main program:

    1. First step is to create factory object where we used framework API XmlBeanFactory() to create the factory bean and ClassPathResource() API to load the bean configuration file available in CLASSPATH. The XmlBeanFactory() API takes care of creating and initializing all the objects ie. beans mentioned in the configuration file.
    2. Second step is used to get required bean using getBean() method of the created bean factory object. This method uses bean ID to return a generic object which finally can be casted to actual object. Once you have object, you can use this object to call any class method.
    
After run the App class we will get following Output on console. <br /><br />
<div style="background-color: #ff99cc; border-width: thin; border: solid;">
<div style="color: blue;"><b>Output:</b></div>
<div style="color: red;">Jun 17, 2012 3:47:36 PM org.springframework.beans.factory.xml.XmlBeanDefinitionReader loadBeanDefinitions</div>
<div style="color: red;">INFO: Loading XML bean definitions from file [F:my workspacespring03BeanFactoryDemospring.xml]</div>
<p><b>Drawing Triangle</b></p>
</div> 

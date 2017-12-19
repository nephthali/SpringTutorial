# SpringTutorial – Learn Spring Framework step by step
This Spring Tutorial Core Container use Eclipse IDE and Maven tool to build and manage depencencies of 
the project. So you have to:

1. Check [Maven repositry](https://mvnrepository.com/artifact/org.springframework/spring-core) to have a right spring dependency. For this tutorial we will use <b>spring 4.3.13 release</b>. It woulf look like this:
```
	<!-- https://mvnrepository.com/artifact/org.springframework/spring-core -->
	<dependency>
	    <groupId>org.springframework</groupId>
	    <artifactId>spring-core</artifactId>
	    <version>4.3.13.RELEASE</version>
	</dependency>
	<dependency>
	    <groupId>org.springframework</groupId>
	    <artifactId>spring-context</artifactId>
	    <version>4.3.13.RELEASE</version>
	</dependency>
	<dependency>
	    <groupId>org.springframework</groupId>
	    <artifactId>spring-beans</artifactId>
	    <version>4.3.13.RELEASE</version>
	</dependency>
```

2. Add your Spring config file under the classpath src/main/java. I called it beans.xml. It describe beans class or POJO. 
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
	<bean id="emp1" class="com.ennva.Employee" init-method="myInit" destroy-method="myDestroy">
		<property name="eid" value="101" />
		<property name="ename" value="pippo1" />
		<property name="eaddress" value="Via pippo 1" />
	</bean>
	<bean id="emp2" class="com.ennva.Employee">
		<property name="eid" value="102" />
		<property name="ename" value="pippo2" />
		<property
	</bean>
</beans>
```

3. Test your code into App.java main class
	
## Part 1: The Core Container
The Core Container has Beans, Core, Context and Expression Language modules.

Core and Beans are the basic parts of the framework with Dependency Injection and IoC features. The BeanFactory is a sophisticated implementation of the factory pattern. There is no need of programmatic singletons and they also allow decoupling the specification and configuration of dependencies from the actual program logic.

Context is another module which is built on the solid base by the Core and Beans. This module is a way to access the objects in a framework-style and is similar to a JNDI registry. It has the features from the Bean modules and supports internationalization, resource-loading, event-propagation and the transparent creation. EJB, JMX and basic remoting are also supported by Java EE in this module. The Context module has a focal point which is known as the ApplicationContext.

The most powerful expression language used for manipulation and querying an object graph at runtime is said to be in the Expression Language. It acts as an extension of the unified expression language in the JSP 2.1 specification. This language helps in setting and getting property values, method invocation, and access of context in arrays, property assignment, logical and arithmetic operators and retrieval of objects.

Spring framework can be described as a light weight container, as it does not involve installation, configuration, start and stop activities associated with a container. It is just a simple collection of few Java ARchive (JAR) files that need to be added to the classpath. The Spring Container takes the classes in the application, creates objects, and manages the life cycle of those objects.

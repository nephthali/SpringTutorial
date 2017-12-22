# SpringTutorial – Coding To Interfaces in Spring

```
In our enterprise application the coding to interface very necessary & useful. We are using DAO & Service layers as interfaces in the enterprise application but its business logic implement these interfaces. If in future you want to change or adding new business logic of enterprise application you does not need to change in the view layer(DAO & Service layers). You just need add one more class that implements that interface and rewired with spring dependency injection.
```

we have discussed about Dependency Injection using an Example of Drawing Class and Shape class. As given below in picture-

![image](https://i2.wp.com/www.dineshonjava.com/wp-content/uploads/2012/07/dependencies-injection.jpg?w=530&ssl=1)<br /><br />


In above picture we will see that second part name Drawing Class Dependent on Shape Class Actually shape is not a class Its an interface which implemented by three classes as Triangle, Circle & Rectangle.

In our Drawing Application class(App.java) we are using draw() method of the Triangle class. As below-
```
ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
Triangle triangle = (Triangle) context.getBean("triangle");
triangle.draw();
```

Here Drawing Application knows that it using draw() method of Triangle bean. If we want to use the draw() method of the circle class then we have to write the following code in the Drawing Application-
```
ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
Circle circle= (Circle) context.getBean("circle");
circle.draw();
```

If want to use the true power of spring framework then we have to use the coding to interface technique. In coding to interface Drawing Application(DrawingApp.java) does not care about that the draw() method of which classes is called. Both classes just implements the Shape interface with one method draw(). And after that Drawing Class have the following code-

```
ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
Shape shape = (Shape) context.getBean("shape");
shape.draw();
```

### shape.java
```
package <You classpath Here >;

public interface Shape
{
   void draw();
}
```

### beans.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<beans default-destroy-method="cleanUp" default-init-method="myInit"
	xmlns="http://www.springframework.org/schema/beans" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.1.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.1.xsd
		http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-4.1.xsd">
	bean class="< Your classpath here >.Triangle" id="triangle">
	 <property name="pointA" ref="pointA"></property>
	 <property name="pointB" ref="pointB"></property>
	 <property name="pointC" ref="pointC"></property>
	</bean>
	  
	<bean class="< Your classpath here >.Circle" id="circle">
	 <property name="center" ref="center"></property>
	</bean>
	  
	<bean class="< Your classpath here >.Point" id="pointA">
	 <property name="x" value="0"></property>
	 <property name="y" value="0"></property>
	</bean>
	  
	<bean class="< Your classpath here >.Point" id="pointB">
	 <property name="x" value="-20"></property>
	 <property name="y" value="0"></property>
	</bean>
	  
	<bean class="< Your classpath here >.Point" id="pointC">
	 <property name="x" value="20"></property>
	 <property name="y" value="0"></property>
	</bean>
	  
	<bean class="< Your classpath here >.Point" id="center">
	 <property name="x" value="10"></property>
	 <property name="y" value="10"></property>
	</bean>
</beans>
```

Now To draw a Triangle. From our drawing class App.java:
```
ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
Shape shape = (Shape) context.getBean("triangle");
shape.draw();
```

Now To draw a Circle. From our drawing class App.java:
```
ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
Shape shape = (Shape) context.getBean("circle");
shape.draw();
```
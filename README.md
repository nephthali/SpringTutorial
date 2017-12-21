# SpringTutorial – Understanding Spring bean life cycle
The Spring Framework provides several marker interfaces to change the behavior of your bean in the container; they include <b>InitializingBean</b> and <b>DisposableBean</b>. Implementing these interfaces will result in the container calling <b>afterPropertiesSet()</b> for the former and <b>destroy()</b> for the latter to allow the bean to perform certain actions upon initialization and destruction.

When a bean is instantiated, it may be required to perform some initialization to get it into a usable state. Similarly, when the bean is no longer required and is removed from the container, some cleanup may be required.

Though, there is lists of the activities that take place behind the scenes between the time of bean Instantiation and its destruction, but this chapter will discuss only <b>two important bean life cycle callback methods</b> which are required at the time of bean initialization and its destruction.

Beans can be notified after creation and all properties are set, and before they are destroyed and removed from the bean container. This involves specifying the callback method to be invoked by the container. This is done in XML by specifying attributes <b>init-method=”myinit”</b>, for the initialization callback, and <b>destroy-method=”mydestroy”</b>, for the destroy callback. “myinit” and “cleanUp” are names of instance methods in the bean class.

## Initialization callbacks
Implementing the <b>org.springframework.beans.factory.InitializingBean</b> interface allows a bean to perform initialization work after all necessary properties on the bean are set by the container. The InitializingBean interface specifies exactly one method:
org.springframework.beans.factory.InitializingBean interface  provide Initialization callbacks method as given below..

```
void afterPropertiesSet() throws Exception
```

Now we can implements above interface and do some initialization functionality with in this method. As below..

```
public class Triangle implements InitializingBean
{
     @Override
     public void afterPropertiesSet() throws Exception
     {
       //To do some initialization works here
       System.out.println("InitializingBean init method is called for Triangle");
     }
}
```


Generally, the use of the InitializingBean interface can be avoided (and is discouraged since it unnecessarily couples the code to Spring). A bean definition provides support for a generic initialization method to be specified. In the case of XML-based configuration metadata, this is done using the ‘init-method’ attribute. For example, the following definition:
In the case of XML-based configuration metadata, we can use the init-method attribute to specify the name of the method that has a void no-argument signature. For example:

```
<bean class="< Your classpath go here >.Triangle" id="triangle" init-method="myInit"></bean>
```

Now following has myInit method in class.

```
public class Triangle
{
  public void myInit()
  {
     //To do some initialization works here
     System.out.println("My init method is called for Triangle");
  }
}
```

Now using Java annotations can also be used to declare bean life cycle callbacks.

```
public class Triangle
{
  //init callback
  @PostConstruct
  public void myInit()
  {
     //To do some initialization works here
     System.out.println("My init method is called for Triangle");
  }
}
```

## Destruction callbacks
Implementing the <b>org.springframework.beans.factory.DisposableBean</b> interface allows a bean to get a callback when the container containing it is destroyed. The DisposableBean interface specifies one method:

```
void destroy() throws Exception
```

Now we can implements above interface and do some Destruction functionality with in this method. As below..

```
public class Triangle implements DisposableBean 
{
     @Override
     public void destroy() throws Exception
     {
       //To do some Destruction works here
       System.out.println("DisposableBean destroy method is called for Triangle");
     }
}
```

Generally, the use of the DisposableBean marker interface can be avoided (and is discouraged since it unnecessarily couples the code to Spring). A bean definition provides support for a generic destroy method to be specified. When using XML-based configuration metadata this is done via the ‘destroy-method’ attribute on the . For example, the following definition:
In the case of XML-based configuration metadata, we can use the destroy-method attribute to specify the name of the method that has a void no-argument signature. For example:

```
<bean class="<Your classPath here >.Triangle" destroy-method="cleanUp" id="triangle"></bean>
```

Now following has cleanUp method in class.

```
public class Triangle
{
  public void cleanUp()
  {
     //To do some Destruction works here
     System.out.println("cleanUp method is called for Triangle");
  }
}
```

Now using Java annotations can also be used to declare bean life cycle callbacks.


```
public class Triangle
{
  //destroy callback
  @PreDestroy
  public void myInit()
  {
     //To do some Destruction works here
     System.out.println("cleanUp method is called for Triangle");
  }
}
```



## Notes

1. If you are using Spring’s IoC container in a non-web application environment; for example, in a rich client desktop environment; you register a shutdown hook with the JVM. Doing so ensures a graceful shutdown and calls the relevant destroy methods on your singleton beans so that all resources are released.

2. It is recommended that you do not use the InitializingBean or DisposableBean callbacks, because XML configuration gives much flexibility in terms of naming your method.

## Default initialization and destroy methods

If you have too many beans having initialization and or destroy methods with the same name, you don’t need to declare init-method and destroy-method on each individual bean. Instead framework provides the flexibility to configure such situation using default-init-method and default-destroy-method attributes on the <beans> element as follows

### beans.xml

```
<beans default-destroy-method="cleanUp" default-init-method="myInit" xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.1.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.1.xsd">
  
	<bean autowire="byName" class="com.dineshonjava.sdnext.callbackLifecycle.tutorial.Triangle" id="triangle">
	</bean>
  
	<bean class="com.dineshonjava.sdnext.callbackLifecycle.tutorial.Point" id="pointA">
  		<property name="x" value="0"></property>
  		<property name="y" value="0"></property>
	</bean>
	<bean class="com.dineshonjava.sdnext.callbackLifecycle.tutorial.Point" id="pointB">
  		<property name="x" value="-20"></property>
  		<property name="y" value="0"></property>
	</bean>
	<bean class="com.dineshonjava.sdnext.callbackLifecycle.tutorial.Point" id="pointC">
  		<property name="x" value="20"></property>
  		<property name="y" value="0"></property>
	</bean>
</beans>
```
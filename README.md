# SpringTutorial – ApplicationContext in Spring and Implementations
In previous we used Bean Factory container and discussed with example. Now we will discuss about the ApplicationContext and Using with in Example.<br />

 ApplicationContext like Bean Factory‘s Big Brother with some additional functionality such as <b>AOP concept<b>, <b>event notification</b> and it adds more enterprise-specific functionality such as the <b>ability to resolve textual messages from a properties file</b> and the <b>ability to publish application events to interested event listeners</b>. 
 This container is defined by the <b>org.springframework.context.ApplicationContext</b> interface.
 
```
The ApplicationContext includes all functionality of the BeanFactory, it is generally recommended over the BeanFactory. BeanFactory can still be used for light weight applications like mobile devices or applet based applications.
```

## ApplicationContext Implementations
The most commonly used ApplicationContext implementations are:

* <b> FileSystemXmlApplicationContext </b>
	This container loads the definitions of the beans from an XML file. 
	Here you need to provide the full path of the XML bean configuration file to the constructor.
	```
	FileSystemXmlApplicationContext context = 
		new FileSystemXmlApplicationContext("F:/my workspace/springAppDemo/src/spring
	```
	
* <b> ClassPathXmlApplicationContext</b>
	This container loads the definitions of the beans from an XML file. 
	Here you do not need to provide the full path of the XML file but you need 
	to set CLASSPATH properly because this container will look bean configuration XML file in CLASSPATH.
	```
	ApplicationContext context = 
		new ClassPathXmlApplicationContext("classpath*:com/dineshonjava/**/springConfig/spring.xml");
	```
* <b> WebXmlApplicationContext</b>
	This container loads the XML file with definitions of all beans from within a web application.
	```
	ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
	```
## Example on App.java
Running your example will output something like that:<br /><br />
<div style="background-color: #ff99cc; border-width: thin; border: solid;">
<div style="color: blue;"><b>Output:</b></div>
<div style="color: red;">Jun 18, 2012 11:40:04 AM org.springframework.context.support.AbstractApplicationContext prepareRefresh</div>
<div style="color: red;">INFO: Refreshing org.springframework.context.support.ClassPathXmlApplicationContext@145d068: startup date [Mon Jun 18 11:40:04 IST 2012]; root of context hierarchy</div>
<div style="color: red;">Jun 18, 2012 11:40:04 AM org.springframework.beans.factory.xml.XmlBeanDefinitionReader loadBeanDefinitions</div>
<div style="color: red;">INFO: Loading XML bean definitions from class path resource [spring.xml]</div>
<div style="color: red;">Jun 18, 2012 11:40:05 AM org.springframework.beans.factory.support.DefaultListableBeanFactory preInstantiateSingletons</div>
<div style="color: red;">INFO: Pre-instantiating singletons in org.springframework.beans.factory.support.DefaultListableBeanFactory@14c1103: defining beans [triangle]; root of factory hierarchy</div>
<p><b>Equilateral Triangle Drawn</b></p>
</div>

## Using ApplicationContextAware in Spring

### How you can access your Spring-ApplicationContext from everywhere in your Application ?

Imagine we have an application (e.g. a web or swing-application) which we now want to be Spring-enabled. Ok we add the Spring libraries(spring.jar) and the Configuration-file(spring.xml) and create our Spring-beans. But there are still some old class-files which you can’t use in this way. These files still need access to the Spring-Honeypot where all the goodies exists and you don’t want to redesign your application.

First create the class <b>“ApplicationContextProvider (Triangle class)”</b>. This class implements the <b>ApplicationContextAware</b>. A bean which implements the ApplicationContextAware-interface and is deployed into the context, will be called back on creation of the bean, using the interface’s <b>setApplicationContext(…)</b> method, and provided with a reference to the context, which may be stored for later interaction with the context.

The ApplicationContextAware is used when an object required. See The <b>(Rectangle.java)</b> class.
We’ll provide our beans with access to the ApplicationContext object by implementing the ApplicationContextAware interface. We’ll also use BeanNameAware interface to get the name of the bean configured in the Spring XML.

## Spring Bean Definition Inheritance Example

A bean definition potentially contains a large amount of configuration information, including container specific information and constructor arguments and property values. A child bean definition inherits configuration data from a parent definition. The child definition can override some values, or add others, as needed. Using parent and child bean definitions can save a lot of typing. Effectively, this is a form of templating.

<p color="red">
Spring Bean definition inheritance has nothing to do with Java class inheritance but inheritance concept is same. You can define a parent bean definition as a template and other child beans can inherit required configuration from the parent bean.
</p>

Following is the configuration file spring.xml where we defined “parentTriangle” bean which has one property “pointA”. Next there are two beans “triangle1” bean and “triangle2” bean has been defined as a child of “parentTriangle” bean by using parent attribute. The child beans inherits “pointA” property as is, and overrides “pointA” property and introduces two more properties “pointB” and “pointC.

### Spring.xml

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.1.xsd">
	
	<bean class="< Your classPath Here >.Triangle" id="parentTriangle">
   		<property name="pointA" ref="pointA"></property>
	</bean>
	<bean class="< Your classPath Here >.Triangle" id="triangle1" parent="parentTriangle">
	   <property name="pointB" ref="pointB"></property>
	   <property name="pointC" ref="pointC"></property>
	</bean>
	  
	<bean class="< Your classPath Here >.Triangle" id="triangle2" parent="parentTriangle">
	   <property name="pointB" ref="pointB"></property>
	</bean>
	  
	<bean class="< Your classPath Here >.Point" id="pointA">
	  <property name="x" value="0"></property>
	  <property name="y" value="0"></property>
	</bean>
	  
	<bean class="< Your classPath Here >.Point" id="pointB">
	  <property name="x" value="-20"></property>
	  <property name="y" value="0"></property>
	</bean>
	  
	<bean class="< Your classPath Here >.Point" id="pointC">
	  <property name="x" value="20"></property>
	  <property name="y" value="0"></property>
	</bean>

</beans>
```

### App.java

```
package < Your classPath Here >;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Nephthali
 *
 */
public class DrawingApp 
{
 /**
  * @param args
  */
 public static void main(String[] args) 
 {
ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
Triangle triangle = (Triangle) context.getBean("triangle1");
triangle.draw();
 }
}
```



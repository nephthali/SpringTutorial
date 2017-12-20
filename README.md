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

# SpringTutorial – BeanPostProcessor in Spring
BeanPostProcessor is interface that tells Spring to do some processing after initialization some beans.
This allows you to add some custom logic before and after spring bean creation.

![image](https://i2.wp.com/www.dineshonjava.com/wp-content/uploads/2012/07/going-beyond-dependency-injection.jpg?w=530&ssl=1) <br /><br />
@ImageSource-SlideShare.net
![image](https://i1.wp.com/www.dineshonjava.com/wp-content/uploads/2012/07/beanpostprocessor-in-spring.jpg?w=530&ssl=1)<br /><br />
@ImageSource-SlideShare.net

Many processes in the IoC container were made to be extensible. A specific extensible process can be referred to as an extension point. One extension point, the <b>BeanPostProcessor</b> interface, allows the modification of a bean instance before and after the properties are set. Another extension point is the <b>BeanFactoryPostProcessor</b> interface which allows direct modification of bean definitions before a bean is instantiated.

An ApplicationContext will automatically register and process a bean that implements either of these interfaces (BeanPostProcessor , BeanFactoryPostProcessor ), but a BeanFactory would have to have a BeanPostProcessor or BeanFactoryPostProcessor registered with it programatically as given below.

```
.....
// create BeanFactory
ConfigurableBeanFactory  factory = new XmlBeanFactory(new FileSystemResource("spring.xml"));
// now register some beans
// now register any needed BeanPostProcessors
DisplayNameBeanPostProcessor postProcessor = new DisplayNameBeanPostProcessor(); 
factory.addBeanPostProcessor(postProcessor);
.....
// now start using the factory
```

Since this manual registration step is not convenient, and ApplictionContexts are functionally supersets of BeanFactories, it is generally recommended that ApplicationContext variants are used when bean post-processors are needed.

A <b>BeanPostProcessor</b> gives you a chance to process an instance of a bean created by the IoC container after it’s instantiation and then again after the initialization life cycle event has occurred on the instance. You could use this to process fields that were set, perform validation on a bean, or even look up values from a remote resource to set on the bean as defaults.

```
Spring’s different AOP proxies for caching, transactions, etc. are all applied by BeanPostProcessor .
```

Lets see the following example which describe the BeanPostProcessor activity in <b>DisplayNameBeanPostProcessor.java</b>

### DisplayNameBeanPostProcessor.java

```
package < Your classPath Here >;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class DisplayNameBeanPostProcessor implements BeanPostProcessor 
{  
@Override
public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException 
{
System.out.println("In After bean Initialization method. Bean name is "+beanName);
return bean;
}

@Override
public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException 
{
System.out.println("In Before bean Initialization method. Bean name is "+beanName);
return bean;
}
}
```

DisplayNameBeanPostProcessor bean in the Spring IoC Container is automatically called when before and after any beans (triangle, pointA, pointB, pointC) creation. In the below configuration file has the DisplayNameBeanPostProcessor bean

### beans.xml

```
<bean autowire="byName" class="< Your classPath here >.Triangle" id="triangle"></bean>
  
<bean class="< Your classPath here >" id="pointA">
  <property name="x" value="0"></property>
  <property name="y" value="0"></property>
</bean>
  
<bean class="< Your classPath here >" id="pointB">
  <property name="x" value="-20"></property>
  <property name="y" value="0"></property>
</bean>
  
<bean class="< Your classPath here >" id="pointC">
   <property name="x" value="20"></property>
   <property name="y" value="0"></property>
</bean>
  
<bean class="< Your classPath here >.DisplayNameBeanPostProcessor"></bean>
</beans>
```

### Notes on example output
There are four beans (triangle, pointA, pointB, pointC) in the Spring IoC Container and both methods of the BeanPostProcessor interface is executes four times.

## BeanFactoryPostProcessor in Spring

BeanFactoryPostProcessor works on the bean definitions or configuration meta data of the bean before beans are actually created. Spring provides multiple BeanFactoryPostProcessor beans, so it invoked to resolve run time dependencies such reading value from external property files. In Spring application, BeanFactoryPostProcessor can modify the definition of any bean.

## Customizing bean factories with BeanPostProcessors Vs BeanFactoryPostProcessors
1. A <b>bean post-processor</b> is   a    java   class   which   implements    the org.springframework.beans.factory.config.BeanPostProcessor interface, which consists of two callback methods
(postProcessAfterInitialization(Object bean, String beanName) & postProcessBeforeInitialization(Object bean, String beanName)).

When such a class is registered as a post-processor with the BeanFactory, for each bean instance that is created by the BeanFactory, the post-processor will get a callback from the BeanFactory before any initialization methods (afterPropertiesSet and any declared init method) are called, and also afterwords.

2. A <b>bean factory post-processor</b> is a java class which implements the org.springframework.beans.factory.config.BeanFactoryPostProcessor interface. It is executed manually (in the case of the BeanFactory) or automatically (in the case of the ApplicationContext) to apply changes of some sort to an entire BeanFactory, after it has been constructed.
 Spring includes a number of pre-existing bean factory post-processors, such as given below

<b>PropertyResourceConfigurer</b> and <b>PropertyPlaceHolderConfigurer</b> – implemented as a bean factory post-processor, is used to externalize some property values from a BeanFactory definition, into another separate file in Java Properties format. This is useful to allow to developer to declare some key property as properties file. As given below example show the database connection related information in the following property file.

<b>databaseConfig.properties</b>
```
jdbc.driverClassName=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://davproductionDB:3306
jdbc.username=root
jdbc.password=root
```

Now in the bean configuration file has the <b>dataSource</b> bean as below.

```
<bean class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close" id="dataSource">
  <property name="driverClassName" value="${jdbc.driverClassName}">
  <property name="url" value="${jdbc.url}">
  <property name="username" value="${jdbc.username}">
  <property name="password" value="${jdbc.password}">
</property></property></property></property></bean>
```

To use this with a BeanFactory, the bean factory post-processor is manually executed on it:

```
XmlBeanFactory factory = new XmlBeanFactory(new FileSystemResource("spring.xml"));
PropertyPlaceholderConfigurer cfg = new PropertyPlaceholderConfigurer();
cfg.setLocation(new FileSystemResource("databaseConfig.properties"));
cfg.postProcessBeanFactory(factory);
```

To use this with a ApplicationContext, the bean factory post-processor is automatically executed on it:

```
ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
```

###  BeanFactoryPPDemo.java
```
package < Your classPath Here>;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

public class BeanFactoryPPDemo implements BeanFactoryPostProcessor  
{
@Override
public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException
 {
 PropertyPlaceholderConfigurer cfg = new PropertyPlaceholderConfigurer();  
 cfg.setLocation(new FileSystemResource("shape.properties"));  
 cfg.postProcessBeanFactory(beanFactory); 
 System.out.println("Bean factory post processor is initialized");
 }
}
```

### For PropertyResourceConfigurer example look at 
[https://www.programcreek.com/java-api-examples/index.php?source_dir=winlet-master/dao/src/main/java/com/aggrepoint/dao/DaoScannerConfigurer.java](https://www.programcreek.com/java-api-examples/index.php?source_dir=winlet-master/dao/src/main/java/com/aggrepoint/dao/DaoScannerConfigurer.java)

1. DaoScannerConfigurer.java
2. MapperScannerConfigurer.java
3. SpringRestClientScannerConfigurer.java

## Using properties files in Spring with MessageSource
ApplicationContext has some extra functionalities in Spring Framework which Internatiolization, Messaging, Properties file etc.

1. Using org.springframework.context.MessageSource:
Its using read message in the class files and display message as the output, below given example show how to use properties files with org.springframework.context.MessageSource.

**myMessage.properties**

```
greeting=Hello Dinesh!
drawing.circle=Circle is Drawn!
```

Now to read this properties file in the spring application we have to use the a class name **“org.springframework.context.support.ResourceBundleMessageSource”** this help to pick the properties files into to the application, this define as bean in the spring configuration file(spring.xml). As follows


**spring.xml**

```
<bean class="org.springframework.context.support.ResourceBundleMessageSource" id="messageSource">
 <property name="basenames">
  <list>
      <value>myMessages</value>
  </list>
 </property>
</bean>
```

Now **“messageSource”** using as property of the bean circle as follows:

```
/**
 * @author Dinesh Rajput
 *
 */
@Component
public class Circle
{
 @Autowired
 private Point center;
 @Autowired
 private MessageSource messageSource;

 /**
  * @param messageSource the messageSource to set
  */
 public void setMessageSource(MessageSource messageSource) 
 {
  this.messageSource = messageSource;
 }

 /**
  * @param center the center to set
  */
 public void setCenter(Point center)
 {
  this.center = center;
 }

 public void draw()
 {
System.out.println(this.messageSource.getMessage("drawing.circle", null, "Default Drawing Greeting", null));
 }
}
```

In the above class file two property “center” and “messageSource” which autowired with the bean name “circle“. We get the message of the properties file in the draw() method by using following method


## Using properties files in Spring with PropertyPlaceholderConfigurer
If you need to read properties file in your Spring application all you need is to configure a PropertyPlaceholderConfigurer bean in your application context.
Following example shows how to read property values from a properties file named myMessage.properties. This file needs to be in your classpath so Spring can find it.

**myMessage.properties**

```
X-axis=20
Y-axis=0
```

**beans.xml**

```
<bean class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer" id="placeholderConfig">    
	<property name="location" value="classpath:myMessage.properties"></property>
</bean>  
<bean class="com.dineshonjava.sdnext.tutorial.property.Point" id="center">
 	<property name="x" value="${X-axis}"></property>
 	<property name="y" value="${Y-axis}"></property>
</bean>
```



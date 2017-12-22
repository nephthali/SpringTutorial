# SpringTutorial – Annotations in Spring and Based Configuration
In Spring 2.0 introduced support for various annotations for configuration purposes, such as @Transactional, @Required and @PersistenceContext /@PersistenceUnit. Spring 2.5 introduces support for a complete set of configuration annotations: @Autowired in combination with support for the JSR-250 annotations @Resource, @PostConstruct and @PreDestroy .

As of Spring 2.5, it is now possible to follow that same general approach to drive Spring’s dependency injection.Starting from Spring 2.5 it became possible to configure the dependency injection using annotations. So instead of using XML to describe a bean wiring, you can move the bean configuration into the component class itself by using annotations on the relevant class, method, or field declaration.

```
Annotation injection is performed before XML injection, 
thus the latter configuration will override the former 
for properties wired through both approaches.
```

Annotation wiring is not turned on in the Spring container by default. So, before we can use annotation-based wiring, we will need to enable it in our Spring configuration file. So consider to have following configuration file in case you want to use any annotation in your Spring application.

```
<beans default-destroy-method="cleanUp" default-init-method="myInit"
	xmlns="http://www.springframework.org/schema/beans" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.1.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.1.xsd
		http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-4.1.xsd">

	<!-- Annotation wiring -->
	<context:annotation-config></context:annotation-config>
	<context:component-scan base-package="org.ennva.spring_maven_tutorial"></context:component-scan>
	<aop:aspectj-autoproxy></aop:aspectj-autoproxy>
	<context:property-placeholder location="classpath:jdbcConfig.properties"></context:property-placeholder>
</beans>
```

### beans.xml file explanation
1. The <b><context:annotation-config/></b> : element is used to automatically register all of Spring’s standard post-processors for annotation-based configuration <br />

#### Element : annotation-config

Activates various annotations to be detected in bean classes: Spring’s @Required and @Autowired, as well as JSR  250’s @PostConstruct, @PreDestroy and @Resource (if available), JAX-WS’s @WebServiceRef (if available), EJB3’s  @EJB (if available), and JPA’s @PersistenceContext and @PersistenceUnit (if available). Alternatively, you may choose to activate the individual BeanPostProcessors for those annotations.

<b>Note:</b> This tag does not activate processing of Spring’s @Transactional or EJB3’s @TransactionAttribute annotation. Consider the use of the <tx:annotation-driven> tag for that purpose. 

2. The <b><context:component-scan base-package=”org.ennva.spring_maven_tutorial”/></b> element is used to enable autodetection of the stereotyped classes in the package “org.ennva.spring_maven_tutorial”. AutowiredAnnotationBeanPostProcessor and CommonAnnotationBeanPostProcessor are both included implicitly when using the component-scan element, the <context:annotation-config/> element can be omitted

The properties for the data source are taken from the <b>jdbcConfig.properties</b> file in the classpath. The property placeholders are configured with the <context:property-placeholder/> element.

The <b><aop:aspectj-autoproxy/></b> element is used to enable @AspectJ support in Spring.

Element<context:load-time-weaver/> Activates a Spring LoadTimeWeaver for this application context, available as a bean with the name “loadTimeWeaver“. Any bean that implements the LoadTimeWeaverAware interface will then receive the LoadTimeWeaver reference automatically; for example, Spring’s JPA bootstrap support. The default weaver is determined automatically. As of Spring 2.5: detecting Sun’s GlassFish, Oracle’s OC4J, Spring’s VM agent and any ClassLoader supported by pring’s ReflectiveLoadTimeWeaver (for example, the TomcatInstrumentableClassLoader). The activation of AspectJ load-time weaving is specified via a simple flag (the ‘aspectj-weaving’ attribute), with the AspectJ class transformer registered through Spring’s LoadTimeWeaver. AspectJ weaving will be activated by default if a “META-INF/aop.xml” resource is present in the classpath. This also activates the current application context for applying dependency injection to non-managed classes that are instantiated outside of the Spring bean factory (typically classes annotated with the @Configurable annotation). This will only happen if the AnnotationBeanConfigurerAspect is on the classpath (i.e. spring-aspects.jar), effectively activating “spring-configured” by default.

Let us see few important annotations to understand how they work:

### Annotation @Required:
**Target**:
        bean property setter methods
**Desrcription**- 
The @Required annotation is used to specify that the value of a bean property is required to be dependency injected. That means, an error is caused if a value is not specified for that property.

### Annotation @Autowired:
**Target**:
        bean property setter methods, non-setter methods, constructor and properties
**Desrcription**- 

@Autowired may also be used for well-known “resolvable dependencies“: the BeanFactory interface, the ApplicationContext interface, the ResourceLoader interface, the ApplicationEventPublisher interface and the MessageSource interface.

**Note**:
The @Autowired annotation is auto wire the bean by matching data type if spring container find more than one beans same data type then it find by name.

**@Autowired Annotation on Setter Method**:
You can use @Autowired annotation on setter methods to get ref id of the <property> element in XML configuration file(spring.xml). When Spring finds an @Autowired annotation used with setter methods, it tries to perform byType autowiring on the method.
See following full example to demonstrate the use of @Autowired on **Circle.java**.

**@Autowired Annotation on Properties**:
You can use @Autowired annotation on properties to get ref id of the setter methods. When you will pass values of autowired properties using Spring will automatically assign those properties with the passed values or references.

**@Autowired Annotation on Constructors**:
You can apply @Autowired to constructors as well. A constructor @Autowired annotation indicates that the constructor should be autowired when creating the bean, even if no <constructor-arg/> elements are used while configuring the bean in XML file.

**@Autowired Annotation with (required=false) option**: 
By default, the @Autowired annotation has the required dependency but we can set the required dependency to false as argument of @Autowired: @Autowired(required=true)

### Annotation @Qualifier:
**Target**:
        bean property setter methods
**Desrcription**- 
There may be a situation when you create more than one bean of the same type and want to wire only one of them with a property, in such case you can use @Qualifier annotation along with @Autowired to remove the confusion by specifying which exact bean will be wired. Below is an example to show the use of @Qualifier annotation.

### Annotation @Aspect:
**Target**:
        on class marks
**Desrcription**- 
To use @AspectJ aspects in a Spring configuration you need to enable Spring support for configuring Spring AOP based on @AspectJ aspects, and autoproxying beans based on whether or not they are advised by those aspects. By autoproxying we mean that if Spring determines that a bean is advised by one or more aspects, it will automatically generate a proxy for that bean to intercept method invocations and ensure that advice is executed as needed.

### JSR-250 Annotations:
**Target**:
        setter and non setter methods
**Desrcription**- 
Spring supports JSR-250 based annotations which include @Resource, @PostConstruct and @PreDestroy annotations. JSR 250, as a Java Specification Request, has the objective to define a set of annotations that address common semantic concepts and therefore can be used by many Java EE and Java SE components. This is to avoid redundant annotations across those components. JSR 250 was released on the 11th 05 2006. As Declarative annotation-driven configuration is more and more used in Java frameworks and applications, e.g. Spring makes more components of its framework configurable via annotations, the importance of JSR 250 is likely to increase in the future

**JSR 250**, as a **Java Specification Request**, has the objective to define a set of annotations that address common semantic concepts and therefore can be used by many Java EE and Java SE components. This is to avoid redundant annotations across those components. JSR-250 Annotations were released on the **11th 05 2006**. As Declarative annotation-driven configuration is more and more used in Java frameworks and applications, e.g. Spring makes more components of its framework configurable via annotations, the importance of JSR 250 is likely to increase in the future.

Spring also provides support for JSR-250 annotations which include @PostConstruct, @PreDestroy and @Resource annotations. JSR-250 aka Common Annotations for the Java Platform was introduced as part of Java EE 5 an is usually used to annotated EJB3s.

* **@PostContruct** – This annotation is applied to a method to indicate that it should be invoked after all dependency injection is complete.
* **@PreDestroy** – This is applied to a method to indicate that it should be invoked before the bean is removed from the Spring context, i.e. just before it’s destroyed.
* **@Resource** – This duplicates the functionality of **@Autowired** combined with **@Qualifier** as you get the additional benefit of being able to name which bean you’re injecting, without the need for two annotations.

#### Annotation @Resource: 
**Target**: component class, or to fields or methods of the component class.
**Description**:
The @Resource annotation marks a resource that is needed by the application. This annotation may be applied to an application component class, or to fields or methods of the component class. When the annotation is applied to a field or method, the container will inject an instance of the requested resource into the application component when the component is initialized. If the annotation is applied to the component class, the annotation declares a resource that the application will look up at runtime.

@Resource takes a “name” attribute, and by default Spring will interpret that value as the bean name to be injected. In other words, it follows by-name semantics as demonstrated in this example:
```
@Resource(name="pointB")
public void setCenter(Point center) 
{
	this.center = center;
}
```

If no name is specified explicitly, then the default name will be derived from the name of the field or setter method: In case of a field, it will simply be equivalent to the field name; in case of a setter method, it will be equivalent to the bean property name. So the following example is going to have the bean with name “center” injected into its setter method:

	```
	public class Circle
	{ 
	 private Point center;
	 
	 @Resource
	 public void setCenter(Point center) 
	 {
	      this.center = center;
	 }
	}
	```
The name provided with the annotation will be resolved as a bean name by the BeanFactory of which the CommonAnnotationBeanPostProcessor is aware.

#### Annotation @PostContruct :
**Target**: methods of the component class.
**Description**:

The @PostConstruct annotation is used on a method that needs to be executed after dependency injection is done to perform any initialization. This method MUST be invoked before the class is put into service. This annotation MUST be supported on all classes that support dependency injection. The method annotated with @PostConstruct MUST be invoked even if the class does not request any resources to be injected. Only one method can be annotated with this annotation. The method on which the @PostConstruct annotation is applied MUST fulfill all of the following criteria – – The method MUST NOT have any parameters except in the case of EJB interceptors in which case it takes an InvocationC ontext object as defined by the EJB specification. – The return type of the method MUST be void. – The method MUST NOT throw a checked exception. – The method on which @PostConstruct is applied MAY be public, protected, package private or private. – The method MUST NOT be static except for the application client. – The method MAY be final. – If the method throws an unchecked exception the class MUST NOT be put into service except in the case of EJBs where the EJB can handle exceptions and even recover from them.

	```
	public class Circle
	{
	   @PostConstruct
	   public void initializeCircle()
	   {
	      //populates the circle data cache upon initialization...
	      System.out.println("Init of Circle");
	   }
	} 
	```

#### Annotation @PreDestroy :
**Target**: methods of the component class.
**Description**:
The @PreDestroy annotation is used on methods as a callback notification to signal that the instance is in the process of being removed by the container. The method annotated with @PreDestroy is typically used to release resources that it has been holding. This annotation MUST be supported by all container managed objects that support @PostConstruct except the application client container in Java EE 5. The method on which the @PreDestroy annotation is applied MUST fulfill all of the following criteria – – The method MUST NOT have any parameters except in the case of EJB interceptors in which case it takes an InvocationContext object as defined by the EJB specification. – The return type of the method MUST be void. – The method MUST NOT throw a checked exception. – The method on which PreDestroy is applied MAY be public, protected, package private or private. – The method MUST NOT be static. – The method MAY be final. – If the method throws an unchecked exception it is ignored except in the case of EJBs where the EJB can handle exceptions.

	```
	public class Circle
	{
	   @PreDestroy
	   public void destroyCircle()
	   {
	        //clears the circle related cache upon destruction..
	        System.out.println("Destroy of Circle");
	   }
	} 
	```

### JSR-330 Annotations (Dependency Injection):
Spring provides support for JSR 330 annotations since Spring 3.0. As spring annotations JSR 330 annotations are also working in the spring bean container. You just need to have the relevant jars in your classpath.

In **pom.xml** file add
	
	```
	<dependency>
	    <groupId>javax.inject</groupId>
	    <artifactId>javax.inject</artifactId>
	    <version>1</version>
	</dependency>
	```
Dependency Injection with **@Inject** and **@Named**
Instead of @Autowired, @javax.inject.Inject may be used as follows:
	
	```
	import javax.inject.Inject;
	import javax.inject.Named;
	@Named
	public class TransferServiceImpl implements TransferService{
	
	    @Inject
	    public void TransferServiceImpl(@Named("accountRepository") AccountRepository accountRepository ) {
	        this.accountRepository = accountRepository ;
	    }
	
	 }
	
	
	import javax.inject.Named;
	
	@Named("accountRepository")
	public class JdbcAccountRepository implements AccountRepository {
	//...
	}
	```
	
	
* As with @Autowired, it is possible to use @Inject at the field level, method level and constructor-argument level.
* As with @Component, it is possible to use @Named at the class level for component scanning by @ComponentScan
* If you would like to use a qualified name for the dependency that should be injected, you should use the @Named annotation as with @Qualifier.
	
**Note**: In contrast to @Component, the JSR-330 @Named annotation are not composable. Please use Spring’s stereotype model for building custom component annotations.

**From @Autowired to @Inject**
<table border="1" summary="Spring component model elements vs. JSR-330 variants">
<thead>
<tr bgcolor="#006600">
<th align="center" valign="top">Spring</th>
<th align="center" valign="top">JSR 330</th>
<th align="center" valign="top">Comments</th>
</tr>
</thead>
<tbody>
<tr bgcolor="#999999">
<td align="left" valign="top">
<div style="color: #990000; font-weight: bold;">@Autowired</div>
</td>
<td align="left" valign="top">
<div style="color: blue; font-weight: bold;">@Inject</div>
</td>
<td align="left" valign="top">@Inject always mandatory, has no ‘required’ attribute.</td>
</tr>
<tr>
<td align="left" valign="top">
<div style="color: #990000; font-weight: bold;">@Component</div>
</td>
<td align="left" valign="top">
<div style="color: blue; font-weight: bold;">@Named</div>
</td>
<td align="left" valign="top">Spring also scan for @Named .</td>
</tr>
<tr bgcolor="#999999">
<td align="left" valign="top">@Scope</td>
<td align="left" valign="top">@Scope</td>
<td align="left" valign="top">JSR 330 Scope for @ annotation and injection point only</td>
</tr>
<tr>
<td align="left" valign="top">
<div style="color: #990000; font-weight: bold;">@Scope(“singleton”)</div>
</td>
<td align="left" valign="top">
<div style="color: #0000cc; font-weight: bold;">@Singleton</div>
</td>
<td align="left" valign="top">JSR-330 default scope is like Spring’s prototype.</td>
</tr>
<tr bgcolor="#999999">
<td align="left" valign="top">
<div style="color: #990000; font-weight: bold;">@Qualifier</div>
</td>
<td align="left" valign="top">
<div style="color: blue; font-weight: bold;">@Named</div>
</td>
<td align="left" valign="top"></td>
</tr>
<tr>
<td align="left" valign="top">
<div style="color: #990000; font-weight: bold;">@Value</div>
</td>
<td align="left" valign="top">No equivalent</td>
<td align="left" valign="top">SpEL specific</td>
</tr>
<tr bgcolor="#999999">
<td align="left" valign="top">
<div style="color: #990000; font-weight: bold;">@Required</div>
</td>
<td align="left" valign="top">Redundant</td>
<td align="left" valign="top">@Inject always required</td>
</tr>
<tr>
<td align="left" valign="top">
<div style="color: #990000; font-weight: bold;">@Lazy</div>
</td>
<td align="left" valign="top">No equivalent</td>
<td align="left" valign="top">Useful when needed, often abused</td>
</tr>
</tbody>
</table>


## Stereotype Annotations:
**Target**: Classes marked with stereotype annotations

**Desrcription**- 

Classes marked with stereotype annotations are candidates for auto-detection by Spring when using annotation-based configuration and classpath scanning.
The **@Component** annotation is the main stereotype that indicates that an annotated class is a “component”.
The **@Service** stereotype annotation used to decorate the Service implementation related class is a specialized form of the **@Component** annotation. It is appropriate to annotate the service-layer classes with @Service to facilitate processing by tools or anticipating any future service-specific capabilities that may be added to this annotation.
The **@Repository** annotation is yet another stereotype that was introduced in Spring 2.0 itself. A class that serves in the persistence layer of the application as a data access object (DAO), otherwise known as a repository in some other technologies.
**@Controller** A controller component in the presentation layer of the application, as it relates to a MVC-designed application

In the previous version **Spring 2.0** introduce the first Stereotype Annotations name as @Repository. The @Component annotations introduced in **Spring 2.5** are really just a continuation of the “stereotype” annotations introduced in Spring 2.0. Stereotype annotations are markers for any class that fulfills a role within an application. This helps remove, or at least greatly reduce, the Spring XML configuration required for these components.

These annotations are used to stereotype classes with regard to the application tier that they belong to. Classes that are annotated with one of these annotations will automatically be registered in the Spring application context if <context:component-scan> is in the Spring XML configuration(spring.xml). 

The Four Types of Spring Stereotype Components and Their Purposes:
<pre class="lang-java prettyprint"><b style="color: blue;">| </b><b style="color: blue;">Annotation | Meaning &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; |</b>
+------------+-----------------------------------------------------+
| <a href="https://www.dineshonjava.com/spring-component-annotation/"><b style="color: #0c343d;">@Component</b></a> | <b>generic stereotype </b><b>for any Spring-</b><b>managed component</b> |
| <b style="color: #0c343d;">@Repository</b>|<b style="color: #274e13;"> stereotype for</b><b style="color: #274e13;"> persistence layer</b> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;|
| <b>@Service &nbsp; </b>| <b style="color: #274e13;">stereotype </b><b style="color: #274e13;">for</b><b style="color: #274e13;"> service layer</b> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;|
| <b style="color: #0c343d;">@Controller|</b> <b style="color: #274e13;">stereotype </b><b style="color: #274e13;">for presentation layer (spring-mvc)</b> &nbsp; &nbsp; &nbsp;|
</pre>

#### Annotation @Component:
**Target**: Class

**Description**:
@Component is a generic stereotype for any Spring-managed component. @Repository, @Service, and @Controller are specializations of @Component for more specific use cases, for example, in the persistence, service, and presentation layers, respectively.
<br /><br />
![image](https://i0.wp.com/www.dineshonjava.com/wp-content/uploads/2012/07/SpringComponentAnnotations-1.jpg?w=530&ssl=1)

```
@Component
public class Circle
{
    private Point center;
    ----
}
```

#### Annotation @Repository:
**Target**: Class
**Description**:
In Spring 2.0 and later, the @Repository annotation is a marker for any class that fulfills the role or stereotype (also known as Data Access Object or DAO) of a repository. Among the uses of this marker is the automatic translation of exceptions.
A class that serves in the persistence layer of the application as a data access object (DAO), otherwise known as a repository in some other technologies. Annotate all your DAO classes with @Repository. All your database access logic should be in DAO classes. 

```
@Repository
public class CircleDaoImpl implements CircleDao
{
    private Point center;
    ----
}
```

#### Annotation @Service:
**Target**: Class
**Description**: Annotate all your service classes with @Service. All your business logic should be in Service classes.

```
@Service
public class CircleServiceImpl implements CircleService
{
    private Point center;
    ----
}
```

#### Annotation @Controller:
**Target**: Class
**Description**:
The @Controller is a class level annotation, which indicates that the annotated class is a Spring component of type “controller“.
The @Controller annotation indicates that a particular class serves the role of a controller. Spring does not require you to extend any controller base class or reference the Servlet API. However, you can still reference Servlet-specific features if you need to. In Spring MVC you can make controller class very easily by prefixing @Controller before any class declaration.

```
@Controller
public class CircleController
{
    private Point center;
    ----
}
```

Enable component scanning
```
<context:component-scan base-package="org.ennva.spring_maven_tutorial.service" />
<context:component-scan base-package="org.ennva.spring_maven_tutorial.dao" />
<context:component-scan base-package="org.ennva.spring_maven_tutorial.controller" />
```

### Where to use stereotype annotations?
Always use these annotations over concrete classes; not over interfaces.

* @Controller annotation is for a class as a Spring Web MVC controller. It is a meta annotation of @Component, so beans annotated with it are automatically imported into the Spring container. If you add the @Controller annotation to a class then you can use handler mappling annotation i.e. @RequestMapping; to map URLs to instance methods of a class.
* @Service annotation is for a class as a Service of application.
* @Repository annotation is more suitable annotation that provides additional benefits specifically for DAOs. The @Repository annotation is a meta annotation of the @Component annotation with similar use and functionality. In addition to importing the DAOs into the DI container, it also makes the unchecked exceptions eligible for translation into Spring DataAccessException.
* @Component should be used when your class does not fall into either of three categories i.e. Controllers, Services and DAOs

## @Autowired Annotation in Spring Example
In last Spring Bean Autowiring in Spring in XML example, it will autowired the matched property of any bean in current Spring container. In most cases, you may need autowired property in a particular bean only.
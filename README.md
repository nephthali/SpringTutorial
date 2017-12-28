# SpringTutorial – Spring AOP (Aspect Oriented Programming)

“AOP“, Aspect Oriented Programming is a feature Spring provide in the Dependency Injection, Actually Aspect Oriented Programming  is not only feature but also its different style of programming just like as Object Oriented Programming.

AOP it’s just an interceptor to intercept some processes, for example, when a method is execute, Spring AOP can hijack the executing method, and add extra functionality before or after the method execution.

Now let talk about some other programming before Aspect Oriented Programming…

### 1.Functional Programming:
In older programming language like C, we have used functional programming style like below in figure.

![image](https://i1.wp.com/www.dineshonjava.com/wp-content/uploads/2012/07/functionalprogramming.jpg?w=530&ssl=1)
<br /><br />
As above see In this style of programming writing code into couple of functions and each function perform unit task and each function call another function as see above and after last function execution then program is completed. But in this style of programming the main problem is complexity, it is very messy style of coding to write big project programming.

### 2. Object Oriented Programming:
In this style of programming we would not think about function when we trying to solve problem by writing code, we would think as individual entities as object when writing the program as below Object A, Object B & Object C…

![image](https://i1.wp.com/www.dineshonjava.com/wp-content/uploads/2012/07/oop.jpg?resize=530%2C181&ssl=1)
<br /><br />

Here each object contain member variables and methods to perform individual tasks of each individual entity so this is fine but here is also a problem that not all rectify common procedure in all of the objects as **common logging procedure** in all as **logMessage()** method in all.

![image](https://i0.wp.com/www.dineshonjava.com/wp-content/uploads/2012/07/commonprocedure.jpg?resize=530%2C172&ssl=1)
<br /><br />

In above see that logMessage() method in each objects no matter how many objects are there so this is not good design of programming each object has repeating method. So to solve this type problem we write the separate entity for logger and called in each objects where we want to add read log message as below.

![image](https://i2.wp.com/www.dineshonjava.com/wp-content/uploads/2012/07/separatelog.jpg?resize=530%2C156&ssl=1)

For using this logger entity in each object we have to make dependency injection with each beans of business classes or we have use inheritance for accessing logger method of Logger class that is good but couple of problems are also there:

First Problem is doing the design this type of style there too many dependencies with non business object because  logger object does not have any business logic in the project its using just for logging with each objects in the project.

PROBLEMS:

* To many relationships with the crosscutting objects.
* Code is still required in the all methods
* Cannot all be changed at once

CROSS CUTTING CONCERNS: Means non business idea or non business logic its not part of our main problem it is related to below…

* Security
* Logging
* Transaction

To solve the above problems in the Object Oriented Programming we can using Aspect Oriented Programming.
### 3.Aspects Oriented Programming:

In this style of code we are make Aspects means Aspects are also specific classes which some special methods for particular tasks like logging, security and transactions etc.

**Aspect-Oriented Programming (AOP)** complements **Object-Oriented Programming (OOP)** by providing another way of thinking about program structure. The key unit of modularity in OOP is the class, whereas in AOP the unit of modularity is the aspect. Aspects enable the modularization of concerns such as transaction management that cut across multiple types and objects. (Such concerns are often termed crosscutting concerns in AOP literature.)

**Aspect-oriented programming** entails breaking down program logic into distinct parts (so-called concerns, cohesive areas of functionality). All programming paradigms support some level of grouping and encapsulation of concerns into separate, independent entities by providing abstractions (e.g., procedures, modules, classes, methods) that can be used for implementing, abstracting, and composing these concerns. But some concerns defy these forms of implementation, and are called crosscutting concerns because they “cut across” multiple abstractions in a program.

First we make different Aspects…

 a. Logging Aspect
 
 b. Transaction Aspect
 
 c. Security Aspect

etc… and configure with each of objects as per as our requirement with Aspect Configuration file as see below in figure…

![image](https://i0.wp.com/www.dineshonjava.com/wp-content/uploads/2012/07/aspect.jpg?w=530&ssl=1)


Aspect Configuration file: Its responsible for configuration for all Aspects with the all object where we want to use. Its configure suppose Logging Aspect for a method in Object A Before or After execution of that method,

its just like the…..

–Servlet Filter in Servlet Configuration<br />
	-Trigger in Database <br  />
      -Interceptors in Struts or in Spring MVC.<br />
      
Aspect Configuration tells which aspect apply which method of which class. Aspect Configuration solve our three problems of Object Oriented Programming


* To many relationships with the crosscutting objects -> Only single configuration required for every object where we want to use the crosscutting object like Logging Aspect with using Aspect Configuration file
* Code is still required in the all methods -> No need to code required in all method just put that method on the Aspect Configuration file then code automatically associated with that method and execute Before or After execution of Target Method.
* Cannot all be changed at once -> We can all be changed at once by using Aspect Configuration file.

There are only two Steps for using Aspects:
1. **Write Aspects**
2. **Configure Aspects where the aspects apply**

## AOP Terminologies:

* **Aspect**: A modularization of a concern that cuts across multiple objects. Transaction management is a good example of a crosscutting concern in J2EE applications. In Spring AOP, aspects are implemented using regular classes (**the schema-based approach**) or regular classes annotated with the @Aspect annotation (**@AspectJ style**).

* **Join point**: A point during the execution of a program, such as the execution of a method or the handling of an exception. In Spring AOP, a join point always represents a method execution. Join point information is available in advice bodies by declaring a parameter of type **org.aspectj.lang.JoinPoint**.

* **Advice**: Action taken by an aspect at a particular join point. Different types of advice include “around,” “before” and “after” advice. Advice types are discussed below. Many AOP frameworks, including Spring, model an advice as an interceptor, maintaining a chain of interceptors “around” the join point.

* **Pointcut**: A predicate that matches join points. Advice is associated with a pointcut expression and runs at any join point matched by the pointcut (for example, the execution of a method with a certain name). The concept of join points as matched by pointcut expressions is central to AOP: Spring uses the AspectJ pointcut language by default.

* **Introduction**: (Also known as an inter-type declaration). Declaring additional methods or fields on behalf of a type. Spring AOP allows you to introduce new interfaces (and a corresponding implementation) to any proxied object. For example, you could use an introduction to make a bean implement an IsModified interface, to simplify caching.

* **Target object**: Object being advised by one or more aspects. Also referred to as the advised object. Since Spring AOP is implemented using runtime proxies, this object will always be a proxied object.

* **AOP proxy**: An object created by the AOP framework in order to implement the aspect contracts (advise method executions and so on). In the Spring Framework, an AOP proxy will be a JDK dynamic proxy or a CGLIB proxy. Proxy creation is transparent to users of the schema-based and @AspectJ styles of aspect declaration introduced in Spring 2.0.

* **Weaving**: Linking aspects with other application types or objects to create an advised object. This can be done at compile time (using the AspectJ compiler, for example), load time, or at runtime. Spring AOP, like other pure Java AOP frameworks, performs weaving at runtime.

## Types of advice:

* **Before advice**: Advice that executes before a join point, but which does not have the ability to prevent execution flow proceeding to the join point (unless it throws an exception).

* **After returning advice**: Advice to be executed after a join point completes normally: for example, if a method returns without throwing an exception.

* **After throwing advice**: Advice to be executed if a method exits by throwing an exception.

* **After (finally) advice**: Advice to be executed regardless of the means by which a join point exits (normal or exceptional return).

* **Around advice**: Advice that surrounds a join point such as a method invocation. This is the most powerful kind of advice. Around advice can perform custom behavior before and after the method invocation. It is also responsible for choosing whether to proceed to the join point or to shortcut the advised method execution by returning its own return value or throwing an exception.
 
## Setting Up AOP Dependencies
* For setting up AOP dependencies in spring we have to add AOP related libraries (
    **aspectjrt.jar**
    **aspectjweaver.jar**
    **asm-x.x.x.jar**
    **aopalliance.jar**
)

* Writing our Logging Aspect:
	
	```
	@Aspect
	public class LoggingAspect
	{
	  public void loggingAdvice()
	  {
	    System.out.println("Advice run. Get method is called");
	  }
	}
	```

* Configuring Spring AOP and @AspectJ support:

	```
	<beans>
	.....
	  <bean class="com.dineshonjava.sdnext.aop.tutorial.aspectJ.LoggingAspect" name="loggingAspect"></bean>
		.....
		<aop:aspectj-autoproxy></aop:aspectj-autoproxy>
		.....
		<aop:config>
		   <aop:aspect id="loggingAspect" ref="loggingAspect">
		        .....
		        <aop:after method="..." pointcut="..."></aop:after>
		        .....
		    </aop:aspect>
		</aop:config>
		....
	</beans>
	```


Spring supports the **@AspectJ annotation style** approach and the schema-based approach to implement custom aspects. These two approaches have been explained in detail in the following.

1. **XML Schema based**: Aspects are implemented using regular classes along with XML based configuration.

2. **@AspectJ based** : @AspectJ refers to a style of declaring aspects as regular Java classes annotated with Java 5 annotations.

## Declaring Pointcut Expressions
A pointcut declaration has four parts as below

* Matching Method Signature Patterns
* Matching Type Signature Patterns
* Matching Bean Name Patterns
* Combining Pointcut Expressions

#### Supported Pointcut Designators by Spring AOP

* **execution** – pointcut expression for matching method execution join points.
* **within** – pointcut expression for matching to join points within certain types
* **this** – pointcut expression for matching to join points where the bean reference is an instance of the given type
* **target** – pointcut expression for matching to join points where the target object is an instance of the given type
* **args** – pointcut expression for matching to join points where the arguments are instances of the given types
* **@target** – pointcut expression for matching to join points where the class of the executing object has an annotation of the given type
* **@args** – pointcut expression for matching to join points where the runtime type of the actual arguments passed have annotations of the given type
* **@within** – pointcut expression for matching to join points within types that have the given annotation
* **@annotation** – pointcut expression for matching to join points where the subject of the join point has the given annotation

![image](https://i2.wp.com/www.dineshonjava.com/wp-content/uploads/2012/11/pointcuts-expression.png?w=748&ssl=1)

<br /><br />

### Declaring pointcut Expressions Examples

#### 1. Matching Method Signature Patterns

**Matching all public methods in TransferService**
Use public keyword in start, use * to match any return type.

```
@Pointcut("execution(public * com.doj.app.service.TransferService.*(..))")
public void anyPublicMethodOfTrasferService();
```

**Matching any public methods**
Use public keyword in start, use * to match any return type and use another * to match any method name.

```
@Pointcut("execution(public * *(..))")
public void anyPublicMethod();
```

**Matching any method defined in the service package**
Use * in start to match any return type, use second * to match any class name and use another * to match any method name.

```
@Pointcut("execution(* com.doj.app.service.*.*(..))")
public void anyMethodInServicePackage();
```

**Matching any method defined in the service package or it’s sub-packages**
Use * in start to match any return type, use two dots after service package means it’s include sub-packages as well, use second * to match any class name and use another * to match any method name.

```
@Pointcut("execution(* com.doj.app.service..*.*(..))")
public void anyMethodInServicePackageAndSubPackage();
```

**Matching all public methods in TransferService with return type Account**
Use public keyword in start and use Account as a return type.

```
@Pointcut("execution(public Account com.doj.app.service.TransferService.*(..))")
public void allPublicMethodOfTransferServiceReturnTypeAccount();
```

**Matching all methods in TransferService with return type void and first parameter as Account**
Use void keyword in start and use Account as a argument type for first parameter.

```
@Pointcut("execution(void com.doj.app.service.TransferService.*(Account account, ..))")
public void allMethodOfTransferServiceVoidReturnTypeFirstArgumentAccount();
```

**Matching all public methods in any class of the service package with any return type and method name should be transfer with taking two parameters of Account types**
Use public keyword in start and use * as any return type, use another * for any class in the service package, use transfer method name with two parameters of Account Types.

```
@Pointcut("execution(public * com.doj.app.service.*.tranfer(Account account1, Account account2))")
public void allTranferMethodsInServicePackageWithTwoArgumentsOfAccountType();
```

#### 2. Matching Type Signature Patterns
It provides narrowed to matching all method executions within the certain types only.

**Matching all methods defined in classes inside package com.doj.app.service**

```
@Pointcut("within(com.doj.app.service.*)")
public void allMethodsInServicePackage();
```

**Matching all methods defined in classes inside package com.doj.app.service and it’s sub-packages. sub-packages use two dots.**

```
@Pointcut("within(com.doj.app.service..*)")
public void allMethodsInServicePackageAndSubPackages();
```

**Matching all methods with a TransferService class**

```
@Pointcut("within(com.doj.app.service.TransferService)")
public void allMethodsOfTransferService();
```

**Matching all methods within all implementing classes of TransferService interface**
Use + (plus) sign to match all implementations of an interface.

```
@Pointcut("within(com.doj.app.service.TransferService+)")
public void allMethodsOfTransferServiceImpl();
```

**Matching all methods where the proxy implements the TransferService interface**

```
@Pointcut("this(com.doj.app.service.TransferService)")
public void allMethodsProxyImplmentTransferService();
```

**Matching all methods where the target object implements the TransferService interface**

```
@Pointcut("target(com.doj.app.service.TransferService)")
public void allMethodsTargetObjectImplmentTransferService();
```
#### 3. Matching Bean Name Patterns

Matching Bean Name Patterns is only supported in Spring AOP – and not in native AspectJ weaving.

**Matching all methods on a Spring bean named transferService**

```
@Pointcut("bean(transferService)")
public void allMethodsOfTransferServiceBean();
```

**Matching all methods on Spring beans having names that match the wildcard expression *Service**

```
@Pointcut("bean(*Service)")
public void allMethodsOfBeanNameAsTransferService();
```

#### 4. Combining Pointcut Expressions

Pointcut expressions can be combined using ‘&&’, ‘||’ and ‘!’.

**Matching any public methods within service module**

```
@Pointcut("execution(public * *(..))")
private void anyPublicOperation() {}

@Pointcut("within(com.doj.app.service..*)")
private void inService() {}

@Pointcut("anyPublicOperation() && inService()")
private void serviceOperation() {}
```
## Spring AOP JoinPoint and Advice Arguments Examples
In Spring AOP, a join point always represents a method execution.

**Join point**: A point during the execution of a program, such as the execution of a method or the handling of an exception. 

In Spring AOP, a join point always represents a method execution 
Join point information is available in advice bodies by declaring a parameter of type org.aspectj.lang.JoinPoint.

![image](https://i1.wp.com/www.dineshonjava.com/wp-content/uploads/2012/12/AOP-concepts.png?w=595&ssl=1)


#### Example of JoinPoint and Advice Arguments

1- **Add aop libraries into your classpath**

If your using maven:

```
		<!-- https://mvnrepository.com/artifact/aspectj/aspectjrt -->
		<dependency>
			<groupId>org.aspectj</groupId>
			<artifactId>aspectjrt</artifactId>
			<version>${aspectj.version}</version>
			<scope>compile</scope>
		</dependency>

		<!-- https://mvnrepository.com/artifact/org.aspectj/aspectjweaver -->
		<dependency>
			<groupId>org.aspectj</groupId>
			<artifactId>aspectjweaver</artifactId>
			<version>1.8.13</version>
			<scope>compile</scope>
		</dependency>

		<!-- https://mvnrepository.com/artifact/org.ow2.asm/asm -->
		<dependency>
			<groupId>org.ow2.asm</groupId>
			<artifactId>asm</artifactId>
			<version>6.0</version>
		</dependency>

		<!-- https://mvnrepository.com/artifact/aopalliance/aopalliance -->
		<dependency>
			<groupId>aopalliance</groupId>
			<artifactId>aopalliance</artifactId>
			<version>1.0</version>
		</dependency>

		<!-- https://mvnrepository.com/artifact/cglib/cglib-nodep -->
		<dependency>
			<groupId>cglib</groupId>
			<artifactId>cglib-nodep</artifactId>
			<version>3.2.5</version>
		</dependency>
```

2- **Declaring your beans**

For our beans I have defined **Circle.java** and **Triangle.java**

3- **Declaring your beans into spring configuration XML file**

In my beans.xml file I have added this

```
<bean name="triangle" class="org.ennva.spring_maven_tutorial.model.Triangle">
		<property name="name" value="triangle name" />
	</bean>

	<bean name="circle" class="org.ennva.spring_maven_tutorial.model.Circle">
		<property name="name" value="circle name" />
	</bean>
```

4- ** Declaring your Aspect class **

For this example look at **LoggingAspect.java** with annotation **Aspect**. the body of class contains methods for **Advice** and **pointcut**

5- To have aop annotations work add this **<aop:aspectj-autoproxy />** tag and aop namespaces into your spring configuration

If your want to use aop schema-based:

```
<aop:config>
		<aop:aspect id="loggingAspect" ref="loggingAspect">
			<aop:before method="loggingAdvice"
				pointcut="within(org.ennva.spring_maven_tutorial.model.*)"></aop:before>
			<!-- <aop:before method="allStringMethodArguments" pointcut="args(String)"></aop:before> -->
		</aop:aspect>
	</aop:config>
```

## Spring AOP After and Around Advice Type

**Advice**:
Action taken by an aspect at a particular join point. Different types of advice include **“around,” “before”** and **“after”** advice. Many AOP frameworks, including Spring, model an advice as an interceptor, maintaining a chain of interceptors “around” the join point.

**These are advices into LoggingAspect.java **

```
@AfterReturning(pointcut="args(name)",returning="returnString")
    public void allStringMethodArguments(String name, String returnString){
    System.out.println("A setter method has been executed............"+name);
    }
   
    @AfterThrowing(pointcut="args(name)",throwing="ex")
    public void exceptionAdvice(String name, Exception ex){
    System.out.println("Exception is thrown ............"+ex);
    }
   
    @After("args(String)")
    public void afterAdvice(){
    System.out.println("After Advice is executed...........");
    }
```
## Spring AOP Proxy

Spring AOP uses either **JDK dynamic proxies** or **CGLIB()**(Byte **Code Generation Library** is high level API to generate and transform JAVA byte code. It is used by AOP, testing, data access frameworks to generate dynamic proxy objects and intercept field access. CGLIB is required to process **@Configuration** classes.
**Hibernate** uses cglib to transparently implement Proxy behavior in classes that need to be persistent at run time.) to create the proxy for a given target object. (JDK dynamic proxies are preferred whenever you have a choice).

If the target object to be proxied implements at least one interface then a JDK dynamic proxy will be used. All of the interfaces implemented by the target type will be proxied. If the target object does not implement any interfaces then a CGLIB proxy will be created.

If you want to force the use of CGLIB proxying (for example, to proxy every method defined for the target object, not just those implemented by its interfaces) you can do so. However, there are some issues to consider:

* final methods cannot be advised, as they cannot be overriden.
* You will need the CGLIB 2 binaries on your classpath, whereas dynamic proxies are available with the JDK. Spring will automatically warn you when it needs CGLIB and the CGLIB library classes are not found on the classpath.
* The constructor of your proxied object will be called twice. This is a natural consequence of the CGLIB proxy model whereby a subclass is generated for each proxied object. For each proxied instance, two objects are created: the actual proxied object and an instance of the subclass that implements the advice. This behavior is not exhibited when using JDK proxies. Usually, calling the constructor of the proxied type twice, is not an issue, as there are usually only assignments taking place and no real logic is implemented in the constructor.

To force the use of CGLIB proxies set the value of the proxy-target-class attribute of the <aop:config> element to true:

```
<aop:config proxy-target-class="true">
    <!-- other beans defined here... -->
</aop:config>
```
To force CGLIB proxying when using the @AspectJ autoproxy support, set the ‘proxy-target-class’ attribute of the element to true:

```
<aop:aspectj-autoproxy proxy-target-class="true">
  <!-- other beans defined here... -->
</aop:aspectj-autoproxy>
```

**Spring AOP is proxy-based.**

Consider first the scenario where you have a plain-vanilla, un-proxied, nothing-special-about-it, straight object reference, as illustrated by the following codesnippet.

```
public class SimplePojo implements Pojo {

   public void foo() {
      // this next method invocation is a direct call on the 'this' reference
      this.bar();
   }
   
   public void bar() {
      // some logic...
   }
}
```

If you invoke a method on an object reference, the method is invoked directly on that object reference, as can be seen below.

![image](https://i0.wp.com/www.dineshonjava.com/wp-content/uploads/2012/12/aop-proxy-plain-pojo-call.png)

Things change slightly when the reference that client code has is a proxy. Consider the following diagram and code snippet.

![image](https://i0.wp.com/www.dineshonjava.com/wp-content/uploads/2012/12/aop-proxy-call.png)

```
public class Main {

   public static void main(String[] args) {
   
      ProxyFactory factory = new ProxyFactory(new SimplePojo());
      factory.addInterface(Pojo.class);
      factory.addAdvice(new RetryAdvice());

      Pojo pojo = (Pojo) factory.getProxy();
      
      // this is a method call on the proxy!
      pojo.foo();
   }
}
```

The key thing to understand here is that the client code inside the main(..) of the Main class has a reference to the proxy. This means that method calls on that object reference will be calls on the proxy, and as such the proxy will be able to delegate to all of the interceptors (advice) that are relevant to that particular method call. However, once the call has finally reached the target object, the SimplePojo reference in this case, any method calls that it may make on itself, such as this.bar() or this.foo(), are going to be invoked against the this reference, and not the proxy. This has important implications. It means that self-invocation is not going to result in the advice associated with a method invocation getting a chance to execute.

Okay, so what is to be done about this? The best approach (the term best is used loosely here) is to refactor your code such that the self-invocation does not happen. For sure, this does entail some work on your part, but it is the best, least-invasive approach. The next approach is absolutely horrendous, and I am almost reticent to point it out precisely because it is so horrendous. You can (choke!) totally tie the logic within your class to Spring AOP by doing this:

```
public class SimplePojo implements Pojo {

   public void foo() {
      // this works, but... gah!
      ((Pojo) AopContext.currentProxy()).bar();
   }
   
   public void bar() {
      // some logic...
   }
}
```

This totally couples your code to Spring AOP, and it makes the class itself aware of the fact that it is being used in an AOP context, which flies in the face of AOP. It also requires some additional configuration when the proxy is being created:

```
public class Main {

   public static void main(String[] args) {
   
      ProxyFactory factory = new ProxyFactory(new SimplePojo());
      factory.adddInterface(Pojo.class);
      factory.addAdvice(new RetryAdvice());
      factory.setExposeProxy(true);

      Pojo pojo = (Pojo) factory.getProxy();

      // this is a method call on the proxy!
      pojo.foo();
   }
}
```

Finally, **it must be noted that AspectJ does not have this self-invocation issue because it is not a proxy-based AOP framework.**

As an example, say you have a service bean that invokes a call to the saveCustomer( ) method on a DAO.

![image](https://i0.wp.com/www.dineshonjava.com/wp-content/uploads/2012/12/image_thumb.png)

Now say you want to have some logging (a cross cutting concern) occur when a call to any save method occurs on a DAO. Spring detects your need to call on a logging aspect through your AOP configuration or annotations. When it does, it builds a proxy (called CustomerDaoProxy for example sake here) around the “target” object – in this case the DAO

![image](https://i0.wp.com/www.dineshonjava.com/wp-content/uploads/2012/12/image_thumb_3.png)

Now, on a call to a save method in the DAO, the proxy intercepts the call and routes it appropriately to the appropriate advice method in the aspect class.

![image](https://i0.wp.com/www.dineshonjava.com/wp-content/uploads/2012/12/image_thumb_4-1024x545.png)

In Spring AOP, it is not possible to have aspects themselves be the target of advice from other aspects.

![image](https://i0.wp.com/www.dineshonjava.com/wp-content/uploads/2012/12/image_thumb_5-1024x651.png)


# SpringTutorial – Learn Spring Framework step by step
This Spring Tutorial provides basic and advanced concepts of Spring Framework. It's designed for beginners and professionals and it’s provides many step by step examples and explanations. The [Spring framework](http://www.springsource.org/) , created by <b>Rod Johnson</b>, is an extremely powerful Inversion of control(IoC) framework to helps decouple your project components’ dependencies.

## Background of Spring and the Spring Framework
In this Spring Tutorial first let’s discuss about some history of the Spring Framework as below: <br />
On <b style="color:red">Oct 2002</b>– The first version released on book (Expert One-on-One J2EE Design and Development) by Rod Johnson. <br />
In <b>2003</b>, Spring came into existence as a solution to the complexity of the early J2EE specifications. It is complementary to Java EE. The Spring programming model integrates only with selected individual specifications such as Servlet API, JPA, JMS, Bean Validation, JSON Binding API, Concurrency Utilities, WebSocket API and JTA/JCA setups for transaction coordination. Let’s see the following list: <br />
On <b>Jun 2003</b>– Take license from Apache and First released as a Framework.<br />
<b>Jan 2006</b>- Won the two awards Jolt productivity award and JAX Innovation Award.<br />
On <b>Oct 2006</b>– Spring 2.0 released.<br />
On <b>Nov 2007</b>– Spring 2.5 released.<br />
On <b>Dec 2009</b> – Spring 3.0 released.<br />
On <b>Dec 2011</b>– Spring 3.1 released.<br />
On <b>Jun 2012</b>– Spring 3.2 released.<br />
On <b>December 2013</b>– Spring 4.0 released.<br />
On <b>31 July 2015</b>– Spring 4.2 released.<br />
On <b>10 June 2016</b>– Spring 4.3 released.<br />
Next, Spring 5 is announced to be built upon Reactive Streams compatible Reactor Core. 
For Spring Framework 5.0, Spring must have Java EE 7 level as a minimum requirement. It is fully compatibles with Tomcat 8/9, JBOss EAP 7 and WebSphere 9.<br />
## Spring 5 Design Pattern
For this purpose I will relate the course to [PacktPublishing repository](https://github.com/PacktPublishing/Spring5-Design-Patterns). This available book on the [Amazon](https://www.amazon.in/Spring-Design-Patterns-Dinesh-Rajput/dp/1788299450/ref=sr_1_1?ie=UTF8&qid=1507925340&sr=8-1&keywords=spring+5+design+pattern) and [Packt](https://www.packtpub.com/application-development/spring-5-design-patterns) publisher website is also usefull.

## What is Spring?
A Java application framework which allows user to create Java enterprise application with inversion of control container is known as Spring Framework. The Spring Framework is open source with extensions for building web applications on the top of the Java EE (Enterprise Edition) platform.

Plain Old Java Objects (POJOs) can be built from Spring with enterprise services non-invasively. Following are some examples to the Java SE programming model:

    A Java method can be created in database without the interaction of APIs
    A remote procedure of local Java without involving remove APIs
    A Java method for a message handler without JMS (Java Messaging Service) APIs
    A management operation by a local java method without JMX (Java Management Extensions) APIs

Spring is great framework for development of Enterprise grade applications. It is a light-weight framework for the development of enterprise-ready applications. It provides very simple and rich facilities to integrate various frameworks, technologies, and services in the applications. One of the main reason for using the Spring framework is to keep code as simple as possible. It push the way to develop enterprise applications with loosely coupled simple java beans. For this reason, the spring framework can also be called a Plain Old Java Object (POJO) framework.

## Modules in Spring Framework
The Spring Framework has been categorized into modules. All modules will not be used in an application, so every module is dependent on the type of application. There are 20 modules organized in Spring Framework. <b>Core Container, Data Access/Integration, Web, AOP (Aspect Oriented Programming), Instrumentation and Test are the groups </b>.

A configuration model and a dependency injection mechanism belong to the core container located in the heart of framework. Beyond that, there are different application architectures such as transactional data, messaging, persistence and web for the foundational support. Servlet-based Spring MVC web-framework is also there in parallel with the Spring WebFlux reactive web framework. <br /><br />
![image](https://i2.wp.com/www.dineshonjava.com/wp-content/uploads/2013/03/Spring-modules.png?resize=530%2C409&ssl=1)

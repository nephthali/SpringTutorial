# SpringTutorial – Learn Spring Framework step by step
This Spring Tutorial provides basic and advanced concepts of Spring Framework. It's designed for beginners and professionals and it’s provides many step by step examples and explanations. The [Spring framework](http://www.springsource.org/) , created by <b>Rod Johnson</b>, is an extremely powerful Inversion of control(IoC) framework to helps decouple your project components’ dependencies.

## Part 1: The Core Container
The Core Container has Beans, Core, Context and Expression Language modules.

Core and Beans are the basic parts of the framework with Dependency Injection and IoC features. The BeanFactory is a sophisticated implementation of the factory pattern. There is no need of programmatic singletons and they also allow decoupling the specification and configuration of dependencies from the actual program logic.

Context is another module which is built on the solid base by the Core and Beans. This module is a way to access the objects in a framework-style and is similar to a JNDI registry. It has the features from the Bean modules and supports internationalization, resource-loading, event-propagation and the transparent creation. EJB, JMX and basic remoting are also supported by Java EE in this module. The Context module has a focal point which is known as the ApplicationContext.

The most powerful expression language used for manipulation and querying an object graph at runtime is said to be in the Expression Language. It acts as an extension of the unified expression language in the JSP 2.1 specification. This language helps in setting and getting property values, method invocation, and access of context in arrays, property assignment, logical and arithmetic operators and retrieval of objects.

Spring framework can be described as a light weight container, as it does not involve installation, configuration, start and stop activities associated with a container. It is just a simple collection of few Java ARchive (JAR) files that need to be added to the classpath. The Spring Container takes the classes in the application, creates objects, and manages the life cycle of those objects.

# SpringTutorial – Spring Bean Scopes in IoC Container
In Spring application, we get the bean from the Spring container with some default configuration. Default behavior is that every beans in the Spring container are initialized when bean configuration file loaded to the JVM.  Whenever getBean is called container recognized the bean by given bean id and return that bean to the caller. One more default behavior is that every beans has only one instance in the spring container.

Spring Bean Scopes means which is used to decide which type of bean instance should be return from Spring container back to the caller. 

Basic Spring Bean Scopes are only two types:
## 1. Singleton:(Default)
Scopes a single bean definition to a single object instance per Spring IoC container. This is the default behavior of the spring container.

When a bean is a singleton, only one shared instance of the bean will be managed, and all requests for beans with an id or ids matching that bean definition will result in that one specific bean instance being returned by the Spring container.

We can say another way, <b>when you define a bean definition and it is scoped as a singleton</b>, then the Spring IoC container will create <b>exactly one instance</b> of the object defined by that bean definition. This single instance will be stored in a cache of such singleton beans, and <b>all subsequent requests and references</b> for that named bean will result in the <b>cached object being returned</b>.

To define a singleton <b>scope</b>, you can set the scope property to <b>singleton</b> in the bean configuration file, as shown below:
```
<bean class="< Your classpath here >.Point" id="zeroPoint" scope="singleton">
  <property name="x" value="0"></property>
  <property name="y" value="0"></property>
</bean>
```

### Using Annotation for Bean Scope:
```
@Service
@Scope("singleton")
public class Point
{
   private int x;
   private int y;
   
   public void setX(int x){
      this.x = x;
   }

   public void setY(int y){
      this.y = y;
   }
}
```
Add the following configuration into beans.xml
```
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.1.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.1.xsd">
	<context:component-scan base-package="< Your package path here>">
	</context:component-scan>
</beans>
```
Here <b>component-scan</b> element has attribute ‘base-package‘. base-package contain the base package of the Bean class.

### Annotation @Service:
Target : Class
Description:
Here <b>@Service</b> is stereotype annotation which declared above the class name that means this class is a bean of the spring container.
In Spring container this bean recognized by the id “point” means that same as the class name except first letter of the class name is the small letter.
for example: class ‘Point‘ get as ‘point‘ bean
                    class ‘Circle‘ get as ‘circle‘ bean

### Annotation @Scope:
Target : Class
Description:
Here <b>@Scope</b> is annotation which declared above the class name that define the scope the bean

 @Scope(“singleton”)
 @Scope(“prototype”)
 
### NOTE : 
This singleton is differ from the singleton pattern in Java Class. Single pattern in java mean you can create the only one instance of a that class in JVM. But In spring singleton bean scope means every container can create only single bean in the Spring IoC Container but a JVM can have multiple Spring IoC Container so JVM can multiple beans rather than bean singleton bean scope.

## 2. Prototype:
Scopes a single bean definition to any number of object instances.

If scope is set to prototype, the Spring IoC container creates new bean instance of the object every time a request for that specific bean is made. 
```
As a rule, use the prototype scope for all state-full beans and the singleton scope for stateless beans.
```

To define a prototype scope, you can set the scope property to prototype in the bean configuration file, as shown below:
```
<bean class="<Your class path here>.Point" id="zeroPoint" scope="prototype">
	<property name="x" value="0"></property>
  	<property name="y" value="0"></property>
</bean>
```

But at web environment spring have three more aware bean scopes as following: 
## 3. Request Scope:
This scopes a bean definition to an HTTP request. Only valid in the context of a web-aware Spring ApplicationContext.
```
<bean class="<Your class path here>.Point" id="point" scope="request"></bean>
```

## 4. Session Scope:
This scopes a bean definition to an HTTP session. Only valid in the context of a web-aware Spring ApplicationContext.
```
<bean class="<Your class path here>.Point" id="point" scope="session"></bean>
```

## 5. Global Session:
Scopes a single bean definition to the lifecycle of a global HTTP Session.
```
<bean class="<Your class path here>.Point" id="point" scope="globalSession"></bean>
```

The global session scope is similar to the standard HTTP Session scope (described immediately above), and really only makes sense in the context of portlet-based web applications. The portlet specification defines the notion of a global Session that is shared amongst all of the various portlets that make up a single portlet web application. Beans defined at the global session scope are scoped (or bound) to the lifetime of the global portlet Session.

Please note that if you are writing a standard Servlet-based web application and you define one or more beans as having global session scope, the standard HTTP Session scope will be used, and no error will be raised.




We can control not only the various dependencies and configuration values that are to be plugged into an object that is created from a particular bean definition, but also the scope of the objects created from a particular bean definition. This approach is very powerful and gives you the flexibility to choose the scope of the objects you create through configuration.





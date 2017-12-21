# SpringTutorial – Bean Autowiring in Spring Application

There are many collaborating bean in Spring for develop an application, the autowire help in making the relationship between them. Bean Autowiring reduces the effort of writing properties or constructor arguments. Bean Autowiring is the feature provided by the spring framework to skip the some of configuration in XML file. The bean autowiring in specified at the autowire attribute inside <bean></bean> element.

```
<bean autowire="byName" class="< your classpath here >.Triangle" id="triangle"></bean>
```

## Bean Autowiring Modes
There are five bean autowiring modes which can be used to instruct Spring container to use bean autowiring for dependency injection. In above syntax we have used the autowire attribute of the element to specify autowire mode for a bean definition.


<table border="2">
<tbody>
<tr>
<td style="background-color: #6aa84f; color: blue; width: 114px;"><b>&nbsp;Mode</b></td>
<td style="background-color: #6aa84f; color: blue;"><b>Explanation</b></td>
</tr>
<tr>
<td style="background-color: #b6d7a8; color: #274e13; width: 114px;"><i><b>no</b></i></td>
<td style="color: #274e13;">It is default which define no autowiring.</td>
</tr>
<tr>
<td style="background-color: #b6d7a8; color: #274e13; width: 114px;"><a href="https://www.dineshonjava.com/spring-autowiring-by-name/"><i><b>byName</b></i></a></td>
<td style="color: #274e13;">Autowiring is done by property name. <a href="https://www.dineshonjava.com/spring-ioc-container/">Spring container</a> looks at the properties of the beans on which <i>autowire</i> attribute is set to <a href="https://www.dineshonjava.com/spring-autowiring-by-name/"><i>byName</i> </a>in the XML configuration file. It then tries to match and wire its properties with the beans defined by the same names in the configuration file.</td>
</tr>
<tr>
<td style="background-color: #b6d7a8; color: #274e13; width: 114px;"><a href="https://www.dineshonjava.com/spring-autowiring-by-type/"><i><b>byType</b></i></a></td>
<td style="color: #274e13;">Autowiring is done by matching data type of property name. <a href="https://www.dineshonjava.com/spring-ioc-container/">Spring container</a> looks at the properties of the beans on which <i>autowire</i> attribute is set to <a href="https://www.dineshonjava.com/spring-autowiring-by-type/"><i>byType</i></a> in the XML configuration file. It then tries to match and wire a property if its <b>type</b> matches with exactly one of the beans name in configuration file. If more than one such beans exists, a fatal exception is thrown.</td>
</tr>
<tr>
<td style="background-color: #b6d7a8; color: #274e13; width: 114px;"><a href="https://www.dineshonjava.com/spring-autowiring-by-constructor/"><i><b>constructor</b></i></a></td>
<td style="color: #274e13;">Autowiring is done by matching data type of property name with the property constructor argument. i.e. Similar to <a href="https://www.dineshonjava.com/spring-autowiring-by-type/"><i>byType</i></a>, but type applies to constructor arguments. If there is not exactly one bean of the constructor argument type in the container, a fatal error is raised.</td>
</tr>
<tr>
<td style="background-color: #b6d7a8; color: #274e13; width: 114px;"><a href="https://www.dineshonjava.com/spring-autowiring-by-autodetect/"><i><b>autodetect</b></i></a></td>
<td style="color: #274e13;">When default constructor with no argument, it auto-wire by data type or it auto-wire by constructor means that Spring first tries to wire using <b><i>autowire </i></b>by <a href="https://www.dineshonjava.com/spring-autowiring-by-constructor/"><i>constructor</i></a>, if it does not work, Spring tries to <b><i>autowire </i></b>by <a href="https://www.dineshonjava.com/spring-autowiring-by-type/"><i>byType</i></a>..</td>
</tr>
</tbody>
</table>

## 1. Auto-Wiring “no”
This is the default mode, you need to wire your bean via ‘ref’ attribute.

```
<bean class="< your classpath here >.Circle" id="circle">
   <property name="center" ref="center">
	</property>
</bean>
<bean class="< your classpath here >.Point" id="center"></bean>
```

## 2. Auto-Wiring “byName”
Auto-wire a bean by property name. In this case, since the name of “center” bean is same with the name of the “circle” bean’s property (“center”), so, Spring will auto wired it via setter method – “setCenter(Point center)”. With autowire by name enabled, you do not need to declares the property tag anymore.

```
<bean autowire="byName" class="< your classpath here >.Circle" id="circle"></bean>
<bean class="< your classpath here >.Point" id="center"></bean>
```
### For Example look Triangle.java, Point.java, and beans.xml

### beans.xml
```
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.1.xsd">

	<bean autowire="byName" class="< your classpath here >.Triangle" id="triangle">
  	</bean>
 	<bean class="< your classpath here >.Point" id="pointA">
  		<property name="x" value="0"></property>
  		<property name="y" value="0"></property>
 	</bean>
  
 	<bean class="< your classpath here >.Point" id="pointB">
  		<property name="x" value="-20"></property>
  		<property name="y" value="0"></property>
 	</bean>
  
 	<bean class="< your classpath here >.Point" id="pointC">
  		<property name="x" value="20"></property>
  		<property name="y" value="0"></property>
 	</bean>
</beans>
```

## 3. Auto-Wiring “byType”
Auto-wire a bean by property data type. In this case the data type of “center” bean is same with the data type of the “circle” bean’s property (“center”), so, Spring will auto wired it via setter method – “setCenter(Point center)”. With autowire by type enabled, you do not need to declares the property tag anymore
```
<bean autowire="byType" class="com.dineshonjava.sdnext.autowiring.Circle" id="circle"></bean>
<bean class="com.dineshonjava.sdnext.autowiring.Point" id="center"></bean>
```
### For Example look Circle.java, Point.java, and beans.xml

### beans.xml
```
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.1.xsd">

	<bean autowire="byType" class="< your classpath here >.Circle" id="circle">
  	</bean>
 	 
 	<bean class="< your classpath here >.Point" id="center">
  		<property name="x" value="0"></property>
  		<property name="y" value="0"></property>
 	</bean>
</beans>
```

## 4. Auto-Wiring “constructor”
Auto-wire a bean by property data type in constructor argument. In this case, since the data type of “center” bean is same as the constructor argument data type in “circle” bean’s property (Point center), so, Spring auto wired it via constructor method – “public Circle(Point center)”.

```
<bean autowire="constructor" class="< your classpath her >.Circle" id="circle"></bean>
<bean class="< your classpath her >.Point" id="center"></bean>
```

## 5. Auto-Wiring “autodetect”
If a default constructor is found, uses “constructor”; Otherwise, uses “byType“. In this case, since there is a default constructor in “Circle” class, so, Spring auto wired it via constructor method – “public Circle(Point center)”.

```
```
<bean autowire="autodetect" class="< your classpath her >.Circle" id="circle"></bean>
<bean class="< your classpath her >.Point" id="center"></bean>
```

## Notes 

Autowiring works best when it is used consistently across a project. If autowiring is not used in general, it might be confusing to developers to use it to wire only one or two bean definitions. Though, autowiring can significantly reduce the need to specify properties or constructor arguments but you should consider the limitations and disadvantages of autowiring before using them. Following limitations are


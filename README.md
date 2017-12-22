# SpringTutorial – Event Handling
Spring Framework also provide an another feature to promote loose coupling ,which is Application Event handling

Using Events, an Event publisher object can communicate with other objects without even knowing which object is listen.and event listener can work to event without knowing which object publish the events.

**Publisher Object** that object who publish the event or call the event and **Listener** always listen the events that are occurs.

To make a custom event extends the class with ApplicationEvent class.The ApplicationEventPublisher has contain publishEvent() method that enable to publish ApplicationEvents (Custom Events).Any ApplicationListener that is registered with the onApplicationEvent() method in application,listen the events.

**Circle.java**

```
/**
 * @author Dinesh Rajput
 *
 */
@Controller
public class Circle implements ApplicationEventPublisherAware
{
 private Point             center;
 private ApplicationEventPublisher publisher;
 
 /**
  * @param center the center to set
  */
 @Resource(name="pointB")
 public void setCenter(Point center)
 {
  this.center = center;
 }

 public void draw()
 {
             System.out.println("Circle is Drawn");
             DrawEvent drawEvent = new DrawEvent(this);
             publisher.publishEvent(drawEvent);
 }

 @Override
 public void setApplicationEventPublisher(ApplicationEventPublisher publisher)
 {
  this.publisher = publisher;
 }
}
```

**MyEventListener.java**

```
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MyEventListener implements ApplicationListener
{

 @Override
 public void onApplicationEvent(ApplicationEvent event) 
 {
  System.out.println(event.toString());
 }

}
```


**DrawEvent.java**

```
import org.springframework.context.ApplicationEvent;

public class DrawEvent extends ApplicationEvent
{

 /**
  * Dinesh Rajput
  */
 private static final long serialVersionUID = 6973014356268900607L;

 public DrawEvent(Object source)
 {
  super(source);
 }
 
 public String toString()
 {
  return "Draw event occurred";
  //return "DrawEvent [source=" + source + "]";
 }
}
```

**spring.xml**

```
<bean class="corg.ennva.spring_maven_tutorial.Point" id="pointB">
    <property name="x" value="10"></property>
    <property name="y" value="20"></property>
</bean>

<context:component-scan base-package="org.ennva.spring_maven_tutorial"></context:component-scan>
```

**App.java**

```
public class App
{

 /**
  * @param args
  */
 public static void main(String[] args)
 {
ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
Circle circle = (Circle)context.getBean("circle");
circle.draw();
 }
}
```


**

<div style="background-color: #ff99cc; border-width: thin; border: solid;">
<div style="color: blue;"><b>Output:</b></div>
<p><b>Circle is Drawn</b><br>
<b>Draw event occurred</b></p>
</div>

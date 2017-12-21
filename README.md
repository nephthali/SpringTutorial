# SpringTutorial – Learn Spring Framework step by step
This Spring Tutorial Dependency Injection use Eclipse IDE and Maven tool to build and manage depencencies of
the project. So you have to download all dependencies as in a previous step 1.


## Part 2: Dependency Injection (DI) in Spring, What is Dependency Injection?
Dependency Injection means injecting the dependency between two
object as per as our requirement in our application,
this help to reducing the dependency to each other and more beneficiary to unit testing of every
objects independently.

Every java based application has a few objects that work
together to present what the end-user sees as a working application.
When writing a complex Java application, application classes should be as independent as possible of other
Java classes to increase the possibility to reuse these classes and to test them independently of other
classes while doing unit testing

## Advantages of Dependency Injection
1. Reducing the dependency to each other of objects in application.
2. Every objects in application could individually unit testing with different mock implementation.
3. Loosely couple or it promotes decoupling to application
4. Promotes re-usability of code or objects in different applications
5. Promotes logical abstraction of components.

## More into App.java class
Spring framework has two types of dependency of injection:
1. <b> Dependency with Constructor Injection</b>
2. <b> Dependency with Setter Injection </b>

## Real Life Based Introduction to Dependency Injection
<b>Scenario 1</b>: You work in an organization where you and your colleagues tend to travel a lot.
Generally you travel by air and every time you need to catch a flight, you arrange for a pickup by a cab.
You are aware of the airline agency who does the flight bookings, and the cab agency which arranges for
the cab to drop you off at the airport. You know the phone numbers of the agencies, you are aware of
the typical conversational activities to conduct the necessary bookings. Thus your typical travel
planning routine might look like the following :

    Decide the destination, and desired arrival date and time
    Call up the airline agency and convey the necessary information to obtain a flight booking.
    Call up the cab agency, request for a cab to be able to catch a particular flight from say
    your residence     (the cab agency in turn might need to communicate with the airline agency
    to obtain the flight departure schedule,the airport, compute the distance between your
    residence and the airport and compute the appropriate time at which to have the cab reach
    your residence) Pickup the tickets, catch the cab and be on your way

Now if your company suddenly changed the preferred agencies and their contact mechanisms,
you would be subject to the following relearning scenarios

    The new agencies, and their new contact mechanisms
    (say the new agencies offer internet based services and the way to do the bookings is over
    the internet instead of over the phone) The typical conversational sequence through which
    the necessary bookings get done (Data instead of voice).

Its not just you, but probably many of your colleagues would need to adjust themselves to the
new scenario. This could lead to a substantial amount of time getting spent in the readjustment
process.

<b>Scenario 2</b>: Now lets say the protocol is a little bit different.
You have an administration department. Whenever you needed to travel an administration department
interactive telephony system simply calls you up (which in turn is hooked up to the agencies).
Over the phone you simply state the destination, desired arrival date and time by responding to
a programmed set of questions. The flight reservations are made for you, the cab gets scheduled
for the appropriate time, and the tickets get delivered to you. Now if the preferred agencies were
changed, the administration department would become aware of a change, would perhaps readjust its
workflow to be able to communicate with the agencies. The interactive telephony system could be
reprogrammed to communicate with the agencies over the internet. However you and your colleagues
would have no relearning required. You still continue to follow exactly the same protocol as earlier
(since the administration department did all the necessary adaptation in a manner that you do not
need to do anything differently). Dependency Injection ? In both the scenarios, you are the client
and you are dependent upon the services provided by the agencies.

However Scenario 2 has a few differences.

    You don’t need to know the contact numbers / contact points of the agencies – the administration department
    calls you when necessary. You don’t need to know the exact conversational sequence by which they conduct
    their activities (Voice / Data etc.) (though you are aware of a particular standardized conversational
    sequence with the administration department) The services you are dependent upon are provided to you
    in a manner that you do not need to readjust should the service providers change.

Thats dependency injection in “real life”.
This may not seem like a lot since you imagine a cost to yourself as a single person – but if you imagine
a large organization the savings are likely to be substantial.

## Injecting Inner Beans in Spring with Example
Injecting Inner Beans in the Spring means beans that are defined within the scope of another bean whenever a bean is used for only one particular property. It’s advise to declare it as an inner bean.It supported in setter injection and constructor injection.

Inner Bean means bean with in the bean

Let See an Example with Employe.java, Adreess.java and State.java beans

## Injecting Collections in Spring Application
Injecting Collections in Spring-We can inject collection values by constructor in spring framework. Spring Collections (List, Set, Map, and Properties) example. Spring examples to show you how to inject values into collections type (List, Set, Map, and Properties).
<br />
![image](https://i1.wp.com/www.dineshonjava.com/wp-content/uploads/2012/06/injecting-collections-in-spring.jpg?w=530&ssl=1)
<br />
You have seen how to configure primitive data type using value attribute and object references using ref attribute of the tag in your Bean configuration file(spring.xml). Both the cases deal with passing singular value to a bean. Now we will discuss about passing multiple value of data like List, Set, Collection, Map, Properties etc. To handle the situation, Spring offers four types of collection configuration element.

So There are different tags are using by these different Collection elements as follows:

* List  — using– <list> – </list>
* Set   — using– <set> – </set>
* Map — using– <map> – </map>
* Properties –using– <props> – </props>

## Using List
Lets see that code snip sett how to use the List in the spring bean configuration file.
In this code snipset list contain all values tags only.
```
<property name="listOfShape">
   <list>
      <value>Triangle</value>
      <value>Circle</value>
      <value>Rectangle</value>
      <value>Square</value>
   </list>
</property>
```

In this code snipset list contain values and beans and inner bean tags.
```
<property name="listOfShape">
   <list>
      <value>Triangle</value>
      <value>Circle</value>
      <ref bean="rectangle"></ref>      
      <bean class="com.dineshonjava.sdnext.Square">
          <property name="width" value="20"></property>
      </bean>
    </list>
</property>
```

## Using Set
Lets see that code snip sett how to use the Set in the spring bean configuration file.
In this code snipset set contain all values tags only.
```
<property name="setOfShape">
   <set>
      <value>Triangle</value>
      <value>Circle</value>
      <value>Rectangle</value>
      <value>Square</value>
   </set>
</property>
```

In this code snipset set contain values and beans and inner bean tags.
```
<property name="setOfShape">
   <set>
      <value>Triangle</value>
      <value>Circle</value>
      <ref bean="rectangle"></ref>      
      <bean class="com.dineshonjava.sdnext.Square">
          <property name="width" value="20"></property>
      </bean>
   </set>
</property>
```

## Using Map
Lets see that code snip sett how to use the Map in the spring bean configuration file.
In this code snipset mapcontain all values tags only.
```
<property name="mapOfShape">
   <map>
     <entry key="1" value="Triangle">
     <entry key="2" value="Circle">
     <entry key="3" value="Rectangle">
     <entry key="4" value="Square">
     </entry></entry></entry></entry>
   </map>
</property>
```

In this code snipset map contain values and beans and inner bean tags.
```
<property name="mapOfShape">
   <map>
     <entry key="1" value="Triangle"></entry>
     <entry key="2" value="Circle"></entry>
     <entry key="3" value-ref="rectangle"></entry>
     <entry key="4">
        <bean class="com.dineshonjava.sdnext.Square">
          <property name="width" value="20"></property>
        </bean>
     </entry>
   </map>
</property>
```

## Using Properties file
Lets see that code snip sett how to use the Properties file in the spring bean configuration file.
```
<property name="proprtyOfShape">
   <props>
     <prop key="triangle">Triangle</prop>
     <prop key="circle">Circle</prop>
     <prop key="rectangle">Rectangle</prop>
     <prop key="sqare">Square</prop>
   </props>
</property>
```



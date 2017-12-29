# SpringTutorial – Spring Hibernate Integration
**Hibernate** is a powerful technology for persisting data in any kind of Application. Spring, on the other hand is a dependency injection framework that supports IOC. The beauty of Spring is that it can integrates well with most of the prevailing popular technologies.

## Spring and Hibernate:
As a pre-requisite, let us understand the need for such integration before we actually get into the integration between these two technologies. It is well known that Hibernate is a powerful **ORM tool** that lies between Application and Database. It enables Application to access data from any database in a platform-independent manner. There is no need for the Application to depend on the low-level JDBC details like managing connection, dealing with statements and result sets. All the necessary details for accessing a particular data source is easily configurable in Xml files. Another good thing is that Hibernate can be coupled well with both J2SE and J2EE Applications.

One of the problem with using Hibernate is that the client Application that accesses the database using **Hibernate Framework** has to depend on the Hibernate APIs like Configuration, SessionFactory and Session. These objects will continue to get scattered across the code throughout the Application. Moreover, the Application code has to manually maintain and manage these objects. In the case of Spring, the business objects can be highly configurable with the help of IOC Container. In simple words, the state of an object can be externalized from the Application code. It means that now it is possible to use the Hibernate objects as Spring Beans and they can enjoy all the facilities that Spring provides.

## Integration Spring and Hibernate

We can simply integrate Hibernate configuration with the Spring than struts 2 , In hibernate framework file **hibernate.cfg.xml** using for the hibernate configuration with the application but in case of Spring there are no need to use this file we can simply configure with in the **Spring.xml** or **applicationContext.xml** configuration file. The following sections cover the various steps involved in the Spring-Hiberante integration along with a detailed explanation.

### Using Hibernate Mapping file with Spring

1. Creating Database and Table(**Employee Table**)
2. Creating Employee Class(**Employee.java**)
3. Creating the Hibernate Mapping file for Employee table(**Employee.hbm.xml**)
4. Creating the Spring Configuration File (**spring.xml**)
5. Creating the DAO class (**EmployeeDao.java**)
6. Creating Application Class for using this

### We can also use the Annotation for using this example with following steps.

1. Creating Database and Table(**Employee Table**)
2. Creating Employee Class with using **@Entity** annotation(**Employee.java**)
3. Creating the Spring Configuration File (**spring.xml**) but **creating bean with using annotation**
4. Creating the DAO class (**EmployeeDao.java**)
5. Creating Application Class for using this

### Example Of Hibernate integration using annotation

**Step 1. Creating Database and Table(Employee Table)**

```
CREATE TABLE Employee(
   EMPID   INT NOT NULL AUTO_INCREMENT,
   NAME VARCHAR(20) NOT NULL,
   AGE  INT NOT NULL,
   SALARY BIGINT NOT NULL,
   PRIMARY KEY (ID)
);
```

**Step 2: Creating Employee Class**<br />
See **Employee.java**

**Step 3: Creating the Spring Configuration File **<br />
See **beans.xml**

**Step 4: Creating the DAO class** <br />
See **(EmployeeDao.java)**, **SuperHibernateDaoSupport.java** and **EmployeeDaoImpl.java**

**Step 5: Creating Application Class for using this**<br />
See **App.java**



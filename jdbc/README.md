# SpringTutorial – Spring JDBC Framework

 In old JDBC API, when we working with database using **old JDBC framework** then we have to take care lots of the nonsense responsibilities or it becomes cumbersome to write unnecessary code to handle exceptions, opening and closing database connections etc. But **Spring JDBC Framework** takes care of all the low-level details starting from opening the connection, prepare and execute the SQL statement, process exceptions, handle transactions and finally close the connection.
 
So what you have do is just define connection parameters and specify the SQL statement to be executed and do the required work for each iteration while fetching data from the database.

Spring provides JDBC Templates to access the data from the database with ease. <br />
Spring provides **org.springframework.jdbc.core.JdbcTemplate** which basically take cares about all your
connection establishment to the database post query work eg closing statement and connections.

## The package hierarchy
The Spring Framework’s JDBC abstraction framework consists of four different packages, namely **core, dataSource, object, and support.**

* The **org.springframework.jdbc.core** package contains the JdbcTemplate class and its various callback interfaces, plus a variety of related classes.

* The **org.springframework.jdbc.datasource** package contains a utility class for easy DataSource access, and various simple DataSource implementations that can be used for testing and running unmodified JDBC code outside of a J2EE container. The utility class provides static methods to obtain connections from JNDI and to close connections if necessary. It has support for thread-bound connections, e.g. for use with DataSourceTransactionManager.

* The **org.springframework.jdbc.object** package contains classes that represent RDBMS queries, updates, and stored procedures as thread safe, reusable objects. This approach is modeled by JDO, although of course objects returned by queries are “disconnected” from the database. This higher level of JDBC abstraction depends on the lower-level abstraction in the org.springframework.jdbc.core package.

* The **org.springframework.jdbc.support** package is where you find the SQLException translation functionality and some utility classes.

Exceptions thrown during JDBC processing are translated to exceptions defined in the **org.springframework.dao** package. This means that code using the Spring JDBC abstraction layer does not need to implement JDBC or RDBMS-specific error handling. All translated exceptions are unchecked giving you the option of catching the exceptions that you can recover from while allowing other exceptions to be propagated to the caller.

### JdbcTemplate
The JdbcTemplate class is the central class in the JDBC core package. It simplifies the use of JDBC since <u>**it handles the creation and release of resources**</u>. This helps to avoid common errors such as forgetting to always close the connection. <u>**It executes the core JDBC workflow like statement creation and execution, leaving application code to provide SQL and extract results**</u>. This class executes SQL queries, update statements or stored procedure calls, imitating iteration over ResultSets and extraction of returned parameter values. <u>**It also catches JDBC exceptions and translates them to the generic, more informative, exception hierarchy defined in the org.springframework.dao package**</u>.

Code using the JdbcTemplate only need to implement callback interfaces, giving them a clearly defined contract. The PreparedStatementCreator callback interface creates a prepared statement given a Connection provided by this class, providing SQL and any necessary parameters. The same is true for the CallableStatementCreator interface which creates callable statement. The RowCallbackHandler interface extracts values from each row of a ResultSet.

The JdbcTemplate can be used within a DAO implementation via direct instantiation with a DataSource reference, or be configured in a Spring IOC container and given to DAOs as a bean reference.

**Note**: The DataSource should always be configured as a bean in the Spring IoC container, in the first case given to the service directly, in the second case to the prepared template.

**Note**: Instances of the JdbcTemplate class are threadsafe once configured. So you can configure a single instance of a JdbcTemplate and then safely inject this shared reference into multiple DAOs.

### NamedParameterJdbcTemplate
NamedParameterJdbcTemplate wraps a JdbcTemplate to provide named parameters instead of the traditional JDBC “?” placeholders. This approach provides better documentation and ease of use when you have multiple parameters for an SQL statement.Named parameters improves readability and are easier to maintain. See the full Example…

```
public void create(String name, Integer age, Long salary) {
      String SQL = "INSERT INTO Employee (name, age, salary) VALUES (:name, :age, :salary)";
      Map namedParameters = new HashMap();   
      namedParameters.put("name", name);   
      namedParameters.put("age", age);
      namedParameters.put("salary", salary);
      namedParameterJdbcTemplate.update(SQL, namedParameters);
System.out.println("Created Record Name = " + name + " Age = " + age+ " Salary = " + salary);
}
```

### SimpleJdbcTemplate

The SimpleJdbcTemplate combines the most frequently used operations of **JdbcTemplate and NamedParameterJdbcTemplate**. See the full Example…

The SimpleJdbcTemplate **has all the features of old JdbcTemplate** and also support some features of Java 5 i.e **varargs and autoboxing**. It best suited when you need not to access all the feature of JdbcTemplate. It has a simpler API and basically construct to support java 5.Thats why it has more method to exploit varargs.

The **getJdbcOperations()** method is used to access those methods which are defined in JdbcTemplate. You have to call these method on SimpleJdbcTemplate . **The main drawback is that you need to cast these methods as the methods on JdbcOperations interface are not generic**.

Here are few examples to show how to use SimpleJdbcTemplate query() methods to query or extract data from database. **In JdbcTemplate query(), you need to manually cast the returned result to desire object type, and pass an Object array as parameters. In SimpleJdbcTemplate, it is more user friendly and simple**.

**Note**: The SimpleJdbcTemplate isn’t a replacement for JdbcTemplate, it’s just a java5-friendly supplement to it. 

**JdbcTemplate Style:-**

```
// classic JdbcTemplate-style...
private JdbcTemplate jdbcTemplate;

public void setDataSource(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
}

public Actor findActor(String specialty, int age) {

    String sql = "select id, first_name, last_name from T_ACTOR" + 
            " where specialty = ? and age = ?";
    
    RowMapper mapper = new RowMapper() {
        public Actor mapRow(ResultSet rs, int rowNum) throws SQLException {
            Actor actor = new Actor();
            actor.setId(rs.getLong("id"));
            actor.setFirstName(rs.getString("first_name"));
            actor.setLastName(rs.getString("last_name"));
            return actor;
        }
    };

    
    // notice the wrapping up of the argumenta in an array
    return (Actor) jdbcTemplate.queryForObject(sql, new Object[] {specialty, age}, mapper);
}
```

**Same thing in the form of SimplrJdbcTemplate style:-**

```
// SimpleJdbcTemplate-style...
private SimpleJdbcTemplate simpleJdbcTemplate;

public void setDataSource(DataSource dataSource) {
    this.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
}

public Actor findActor(String specialty, int age) {

    String sql = "select id, first_name, last_name from T_ACTOR" + 
            " where specialty = ? and age = ?";
    RowMapper mapper = new RowMapper() {  
        public Actor mapRow(ResultSet rs, int rowNum) throws SQLException {
            Actor actor = new Actor();
            actor.setId(rs.getLong("id"));
            actor.setFirstName(rs.getString("first_name"));
            actor.setLastName(rs.getString("last_name"));
            return actor;
        }
    };

    // notice the use of varargs since the parameter values now come 
    // after the RowMapper parameter
    return this.simpleJdbcTemplate.queryForObject(sql, mapper, specialty, age);
}
```

**Note**:
The SimpleJdbcTemplate class only offers a subset of the methods exposed on the JdbcTemplate class. If you need to use a method from the JdbcTemplate that is not defined on the SimpleJdbcTemplate, you can always access the underlying JdbcTemplate by calling the **getJdbcOperations()** method on the SimpleJdbcTemplate, which then allows you to invoke the method that you want. The only downside is that the methods on the JdbcOperations interface are not generic, so you are back to casting and so on.

### SimpleJdbcInsert and SimpleJdbcCall

SimpleJdbcInsert and SimpleJdbcCall **optimize database metadata to limit the amount of necessary configuration**. This approach simplifies coding so that you only need to provide the name of the table or procedure and provide a map of parameters matching the column names. **This only works if the database provides adequate metadata**. If the database doesn’t provide this metadata, you will have to provide explicit configuration of the parameters.

**RDBMS Objects including MappingSqlQuery, SqlUpdate and StoredProcedure** requires you to create reusable and thread-safe objects during initialization of your data access layer. This approach is modeled after JDO Query wherein you define your query string, declare parameters, and compile the query. Once you do that, execute methods can be called multiple times with various parameter values passed in.

### Configuring DataSource

**Spring obtains a connection to the database through a DataSource**. A DataSource is part of the JDBC specification and is a generalized connection factory. It allows a container or a framework to hide connection pooling and transaction management issues from the application code. **As a developer, you need not know details about how to connect to the database; that is the responsibility of the administrator that sets up the datasource. You most likely fill both roles as you develop and test code, but you do not necessarily have to know how the production data source is configured**.

When using Spring’s JDBC layer, you obtain a data source from JNDI or you configure your own with a connection pool implementation provided by a third party. Popular implementations are **Apache Jakarta Commons DBCP and C3P0**. Implementations in the Spring distribution are meant only for testing purposes and do not provide pooling.

### Data Access Object (DAO)

DAO stands for data access object which is **commonly used for database interaction**. DAOs exist to provide a means to read and write data to the database and they should expose this functionality through an interface by which the rest of the application will access them.

The Data Access Object (DAO) support in Spring makes it easy to work with data access technologies like **JDBC, Hibernate, JPA or JDO** in a consistent way.

**DataSourceUtils**:<br />
The DataSourceUtils class is a convenient and powerful helper class that provides static methods to obtain connections from JNDI and close connections if necessary. It supports thread-bound connections with, for example, **DataSourceTransactionManager**.

**SmartDataSource**:<br />
The SmartDataSource interface should be implemented by classes that can provide a connection to a relational database. It extends the DataSource interface to allow classes using it to query whether the connection should be closed after a given operation. This usage is efficient when you know that you will reuse a connection.

**AbstractDataSource**:<br />
AbstractDataSource is an abstract base class for Spring’s DataSource implementations that implements code that is common to all DataSource implementations. **You extend the AbstractDataSource class if you are writing your own DataSource implementation**.

**SingleConnectionDataSource**:<br />
The SingleConnectionDataSource class is an implementation of the SmartDataSource interface that wraps a single Connection that is not closed after each use. Obviously, **this is not multi-threading capable**.

If any client code calls close in the assumption of a pooled connection, as when using persistence tools, set the suppressClose property to true. This setting returns a close-suppressing proxy wrapping the physical connection. Be aware that you will not be able to cast this to a native Oracle Connection or the like anymore.

This is primarily a test class. For example, **it enables easy testing of code outside an application server**, in conjunction with a simple JNDI environment. In contrast to DriverManagerDataSource, it reuses the same connection all the time, avoiding excessive creation of physical connections.

**DriverManagerDataSource**:
The DriverManagerDataSource class is an implementation of the standard DataSource interface that configures a plain JDBC driver through bean properties, and returns a new Connection every time.

This implementation **is useful for test and stand-alone environments outside of a Java EE container**, either as a DataSource bean in a Spring IoC container, or in conjunction with a simple JNDI environment. Pool-assuming Connection.close() calls will simply close the connection, so any DataSource-aware persistence code should work. However, using JavaBean-style connection pools such as commons-dbcp is so easy, even in a test environment, that it is almost always preferable to use such a connection pool over DriverManagerDataSource.

**DataSourceTransactionManager**:<br />
The DataSourceTransactionManager class is a PlatformTransactionManager implementation for single JDBC datasources. It binds a JDBC connection from the specified data source to the currently executing thread, potentially allowing for one thread connection per data source.

Application code is required to retrieve the JDBC connection through DataSourceUtils.getConnection(DataSource) instead of Java EE’s standard DataSource.getConnection. It throws unchecked org.springframework.dao exceptions instead of checked SQLExceptions. All framework classes like JdbcTemplate use this strategy implicitly. If not used with this transaction manager, the lookup strategy behaves exactly like the common one – it can thus be used in any case.

The DataSourceTransactionManager class supports custom isolation levels, and timeouts that get applied as appropriate JDBC statement query timeouts. To support the latter, application code must either use JdbcTemplate or call the DataSourceUtils.applyTransactionTimeout(..) method for each created statement.

This implementation can be used instead of JtaTransactionManager in the single resource case, as it does not require the container to support JTA. Switching between both is just a matter of configuration, if you stick to the required connection lookup pattern. JTA does not support custom isolation levels!

### Example of connection to database NEPHDB
Let see how to create a database table Employee in our database DAVDB. we assume you are working with MySQL database, if you work with any other database then you can change your DDL and SQL queries accordingly

```
CREATE TABLE Employee(
   EMPID   INT NOT NULL AUTO_INCREMENT,
   NAME VARCHAR(20) NOT NULL,
   AGE  INT NOT NULL,
   SALARY BIGINT NOT NULL,
   PRIMARY KEY (ID)
);
```

You obtain a connection with **DriverManagerDataSource** as you typically obtain a JDBC connection. Specify the fully qualified classname of the JDBC driver so that the **DriverManager** can load the driver class.<br />
**Note**: Only use the **DriverManagerDataSource** class should only be used for testing purposes since it does not provide pooling and will perform poorly when multiple requests for a connection are made.<br />
Now we need to supply a **DataSource to the JdbcTemplate** so it can configure itself to get database access. Here is an example of how to configure a DriverManagerDataSource in Java code.

```
DriverManagerDataSource dataSource = new DriverManagerDataSource();
dataSource.setDriverClassName("com.mysql.jdbc.Driver");
dataSource.setUrl("jdbc:mysql://localhost:3306/NEPHDB");
dataSource.setUsername("root");
dataSource.setPassword("password");
```

Here is the corresponding XML configuration:

```
<bean class="org.springframework.jdbc.datasource.DriverManagerDataSource" id="dataSource">
   <property name="driverClassName" value="com.mysql.jdbc.Driver"></property>
   <property name="url" value="jdbc:mysql://localhost:3306/NEPHDB"></property>
   <property name="username" value="root"></property>
   <property name="password" value="password"></property>
</bean>
```

The following examples show the basic connectivity and configuration for **DBCP** and **C3P0**. To learn about more options that help control the pooling features, see the product documentation for the respective connection pooling implementations.

**DBCP configuration:**

```
<bean class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close" id="dataSource">
    <property name="driverClassName" value="${jdbc.driverClassName}"></property>
    <property name="url" value="${jdbc.url}"></property>
    <property name="username" value="${jdbc.username}"></property>
    <property name="password" value="${jdbc.password}"></property>
</bean>

<context:property-placeholder location="jdbc.properties"></context:property-placeholder>
```
**C3P0 configuration:**

```
<bean class="com.mchange.v2.c3p0.ComboPooledDataSource" destroy-method="close" id="dataSource">
    <property name="driverClass" value="${jdbc.driverClassName}"></property>
    <property name="jdbcUrl" value="${jdbc.url}"></property>
    <property name="user" value="${jdbc.username}"></property>
    <property name="password" value="${jdbc.password}"></property>
</bean>

<context:property-placeholder location="jdbc.properties"></context:property-placeholder>
```
##### Step 1: Create your database as follows, Here I am using MySQL as database.

```
mysql> create database NEPHDB;
```

##### Step 2: Create the Java Project and add Spring framework jar library to it.
![image](https://i1.wp.com/www.dineshonjava.com/wp-content/uploads/2012/12/jdbcDemo.jpg?w=530&ssl=1)


##### Step 3: Create Employee class for table ’employee’ in the ‘DAVDB’ database.
See **Employee.java**

##### Step 4: Create Employee DAO interface for the abstarct functionality with employee table.
See **EmployeeDao**

##### Step 6: Create EmployeeMapper class which map the row of table ( the Employee class ), which implements the org.springframework.jdbc.core.RowMapper interface of the jdbc core API.
See **EmployeeMapper.java**

##### Step 7:Create the configuration file for the bean configuration
See **beans.xml**

##### Step 8: Finally we have to create main class for execution this application
See **App.java**

### Implementing RowMapper(An interface used by JdbcTemplate for mapping rows of a ResultSet on a per-row basis.)
```
public interface RowMapper<T> {
T mapRow(ResultSet rs, int rowNum)
throws SQLException;
}
```
**1.Custom RowMapper**:
In general, It’s always recommended to implement the RowMapper interface to create a custom RowMapper to suit your needs..

```
public class EmployeeMapper implements RowMapper {  
 public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {  
  Employee employee = new Employee();  
  employee.setEmpid(rs.getInt("empid"));  
  employee.setName(rs.getString("name"));  
  employee.setAge(rs.getInt("age"));  
  employee.setSalary(rs.getLong("salary"));  
  return employee;  
 }  
} 
```

Pass it to **queryForObject()** method, the returned result will call your custom mapRow() method to match the value into the property.

```
public Employee getEmployee(Integer empid) {
   String SQL = "SELECT * FROM Employee WHERE empid = ?";
   Employee employee = (Employee) jdbcTemplateObject.queryForObject(SQL, new Object[]{empid}, new EmployeeMapper());
    return employee;
}
```

Without a custum RowMapper, **BeanPropertyRowMapper** can be use

```
public Employee getEmployee(Integer empid) {
   String SQL = "SELECT * FROM Employee WHERE empid = ?";
   Employee employee = (Employee) jdbcTemplateObject.queryForObject(SQL, new Object[]{empid}, new BeanPropertyRowMapper(Employee.class));
    return employee;
}
```

### DAO Support Classes in Spring

To make it easier to work with a variety of data access technologies such as **JDBC, JDO and Hibernate** in a consistent way, Spring provides a set of abstract DAO classes that one can extend

* **JdbcDaoSupport –**

It is superclass for JDBC data access objects. Requires a DataSource to be provided; in turn, this class provides a JdbcTemplate instance initialized from the supplied DataSource to subclasses.

* **HibernateDaoSupport –**

It is superclass for Hibernate data access objects. Requires a SessionFactory to be provided; in turn, this class provides a HibernateTemplate instance initialized from the supplied SessionFactory to subclasses. Can alternatively be initialized directly via a HibernateTemplate, to reuse the latters settings like SessionFactory, flush mode, exception translator, and so forth.

* ** JdoDaoSupport –**

It is super class for JDO data access objects. Requires a PersistenceManagerFactory to be provided; in turn, this class provides a JdoTemplate instance initialized from the supplied PersistenceManagerFactory to subclasses.

* ** JpaDaoSupport –**

It is super class for JPA data access objects. Requires a EntityManagerFactory to be provided; in turn, this class provides a JpaTemplate

In Spring JDBC Framework there are many DAO support classes which help to reduce the configuration of **JdbcTemplate, SimpleJdbcTemplate and NamedParamJdbcTemplate** with dataSource object.

For **JdbcTemplate:**<br />
org.springframework.jdbc.core.support.JdbcDaoSupport 

For **SimpleJdbcTemplate:**<br />
org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport

For **NamedParamJdbcTemplate:**<br />
org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport 
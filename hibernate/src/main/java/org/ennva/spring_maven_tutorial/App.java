package org.ennva.spring_maven_tutorial;

import java.util.List;

import org.ennva.spring_maven_tutorial.dao.EmployeeDao;
import org.ennva.spring_maven_tutorial.dao.impl.EmployeeDaoImpl;
import org.ennva.spring_maven_tutorial.model.Circle;
import org.ennva.spring_maven_tutorial.model.Employee;
import org.ennva.spring_maven_tutorial.service.ShapeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Client Application!
 *
 */
public class App {
	public static void main(String[] args) {
		/*
		 * Traditional method without spring. You have to construct yourself a Triangle
		 * class
		 */
		// Triangle triangle = new Triangle();

		/**
		 * With Spring ApplicationContext container, class will be construct for you.
		 * ApplicationContext is build on top of beanFactory and provide more
		 * Functionality than beanFactory
		 * 
		 * ClassPathXmlApplicationContext taking the context definition files from the
		 * class path, interpreting plain paths as class path resource names that
		 * include the package path (e.g. "mypackage/myresource.txt")
		 * 
		 * NB:The bean definitions will be loaded from the classpath, as a
		 * ClassPathResource will be used
		 * 
		 **/
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		// Triangle triangle = (Triangle) context.getBean("triangle");
		// triangle.draw();

		/**
		 * FileSystemXmlApplicationContext taking the context definition files from the
		 * file system or from URLs, interpreting plain paths as relative file system
		 * locations (e.g. "mydir/myfile.txt") NB:The bean definition will be loaded
		 * from a filesystem location, in this case relative to the current working
		 * directory
		 **/
		// ApplicationContext context1 = new
		// FileSystemXmlApplicationContext("src/main/resources/beans.xml");
		// Triangle triangle1 = (Triangle) context1.getBean("triangle");
		// triangle1.draw();

		/**
		 * FileSystemXmlApplicationContext: Note that the use of the special classpath
		 * prefix or a standard URL prefix on the location path will override the
		 * default type of Resource created to load the definition
		 * 
		 **/
		// ApplicationContext context2 =
		// new FileSystemXmlApplicationContext("src/main/resources/*.xml");
		// Triangle triangle2 = (Triangle) context2.getBean("triangle");
		// triangle2.draw();

		/**
		 * FileSystemXmlApplicationContext: Wildcard like Ant-Style like
		 * /WEB-INF/*-context.xml can also be use other that classpath*:
		 * FileSystemXmlApplicationContext("classpath*:beans.xml");
		 * 
		 **/
		// ApplicationContext context3 =
		// new FileSystemXmlApplicationContext("classpath:*.xml");
		//// ApplicationContext context3 =
		//// new FileSystemXmlApplicationContext("src/main/resources/*.xml");
		// Triangle triangle3 = (Triangle) context2.getBean("triangle");
		// triangle3.draw();

		/**
		 * Autowiring by type test
		 */
		// Circle circle = (Circle) context.getBean("circle");
		// circle.draw();

		/**
		 * BeanPostProcessor Test
		 */
		// Triangle triangle = (Triangle) context.getBean("triangle");
		// triangle.draw();

		// Shape shape = (Shape) context.getBean("triangle");
		// Circle circle = context.getBean("circle", Circle.class);
		// circle.setName("My circle");
		// System.out.println(circle.getName());
		// circle.draw();

		// ShapeService
		// ShapeService service = context.getBean("shapeService", ShapeService.class);
		// service.getCircle().setName("Dummy Circle");
		// service.getCircle().setNameAndReturning("Dummy circle return");
		// String name = service.getCircle().setNameAndReturning("Dummy circle return");
		// System.out.println(service.getCircle().getName());
		// System.out.println(service.getTriangle().getName());
		// System.out.println("name: "+name);

		/**
		 * Dao test
		 */
		EmployeeDao empDao = context.getBean("employeeDaoImpl", EmployeeDaoImpl.class);
		// System.out.println("------Records Creation--------");
		// empDao.create("Dinesh", 25, 50000l);
		// empDao.create("Anamika", 23, 30000l);
		// empDao.create("Nimmo", 24, 30020l);
		// empDao.create("Adesh", 24, 30011l);
		// empDao.create("Vinesh", 22, 20011l);
		//
		// System.out.println("------Listing Multiple Records--------");
		// List<Employee> employees = empDao.listEmployees();
		// for (Employee employee : employees) {
		// System.out.print(employee + "\n");
		// }
		//
		// System.out.println("----Updating Record with EMPID = 2 -----");
		// empDao.update(2, 20);
		// int id = 6;
		// System.out.println("----Listing Record with ID = "+ id +" -----");
		// Employee employee = empDao.getEmployee(id);
		// System.out.print(employee + "\n");

		/**
		 * Test Spring Hibernate
		 */
		Employee employee = new Employee();
		employee.setName("Dinesh");
		employee.setAge(25);
		employee.setSalary(50000l);
		System.out.println("------Records Creation--------\n");
		empDao.createEmployee(employee);
		
		// System.out.println("------Listing Multiple Records--------\n");
		// List<Employee> employees = empDao.listEmployees();
		// for (Employee emp : employees) {
		// System.out.print(emp);
		// }
		// int id = 3;
		// System.out.println("------find one Records--------Employee: "+id+"\n");
		// Employee employeeGot = empDao.getEmployee(id);
		// System.out.print(employeeGot);
		// System.out.println("------Delete one Records--------");
		// empDao.delete(employeeGot);

		/**
		 * In order to Destroy the Bean Object, in this case a singleton scope bean
		 * sigleton: When a bean is a singleton, only one shared instance of the bean
		 * will be managed and all requests for beans with an id or ids matching that
		 * bean definition will result in that one specific bean instance being returned
		 * 
		 * Detruction mean remove bean on JVM. I need ApplicationContext that enable
		 * destruction of a single bean that has a destroy-method
		 * 
		 */
		// ConfigurableApplicationContext context = new
		// ClassPathXmlApplicationContext(new String[] {"beans.xml"});
		ClassPathXmlApplicationContext ctx = (ClassPathXmlApplicationContext) context;
		ctx.close();

	}
}

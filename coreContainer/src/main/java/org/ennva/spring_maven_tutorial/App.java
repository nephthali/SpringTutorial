package org.ennva.spring_maven_tutorial;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        /**
         * Spring Have 2 type of container:
         * 1- First Spring Container (BeanFactory)
         *      This first one have a method XmlBeanFactory deprecated as of Spring 3.1
         *      in in favor of DefaultListableBeanFactory and XmlBeanDefinitionReader
         *
         * 2- Second Spring container (ApplicationContext)
         *      This is a second That is build on top of BeanFactory
         *      and Most use as of Spring 3.1 **/
//      Resource resource = new ClassPathResource("beans.xml");
//      BeanFactory factory = new XmlBeanFactory(resource);
//      Employee emp1 = (Employee)factory.getBean("emp1");
//      Employee emp2 = factory.getBean("emp2",Employee.class);

        // Second Spring Container
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Employee emp1 = (Employee)context.getBean("emp1");
        Employee emp2 = context.getBean("emp2",Employee.class);

        System.out.println("Employee Details" + emp1);
        System.out.println("Employee Details" + emp2);

        // Destroy the Bean Object :
        //You have to reference the ClassPathXmlApplicationContext
        ClassPathXmlApplicationContext ctx = (ClassPathXmlApplicationContext)context;
        // And close the container
        ctx.close();
    }
}

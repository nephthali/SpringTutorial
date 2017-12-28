package org.ennva.spring_maven_tutorial.dao.impl;

import java.util.List;

import org.ennva.spring_maven_tutorial.dao.EmployeeDao;
import org.ennva.spring_maven_tutorial.model.Employee;
import org.ennva.spring_maven_tutorial.utils.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDaoImpl extends JdbcDaoSupport implements EmployeeDao {
	
	// @Autowired
	// private JdbcTemplate jdbcTemplateObject;
	//
	// public void setJdbcTemplateObject(JdbcTemplate jdbcTemplateObject) {
	// this.jdbcTemplateObject = jdbcTemplateObject;
	// }

	// public void create(String name, Integer age, Long salary) {
	// String SQL = "INSERT INTO Employee (name, age, salary) VALUES (?, ?, ?)";
	// jdbcTemplateObject.update(SQL, new Object[] { name, age, salary });
	// System.out.println("Created Record Name = " + name + " Age = " + age + "
	// Salary = " + salary);
	// }
	
	//Test with Dao Support Class JdbcDaoSupport
	public Employee getEmployee(Integer empid) {
		String SQL = "SELECT * FROM Employee WHERE empid = ?";
		//Here we see that there is one method name getJdbcTemplate() associated with JdbcTemplate and DataSource. There are no need to configure manually.
		Employee employee = (Employee) getJdbcTemplate().queryForObject(SQL, new Object[] { empid },
				new EmployeeMapper());
		return employee;
	}

	// public Employee getEmployee(Integer empid) {
	// String SQL = "SELECT * FROM Employee WHERE empid = ?";
	// Employee employee = (Employee) jdbcTemplateObject.queryForObject(SQL, new
	// Object[] { empid },
	// new EmployeeMapper());
	// return employee;
	// }

	// public List listEmployees() {
	// String SQL = "SELECT * FROM Employee";
	// List employees = (List) jdbcTemplateObject.query(SQL, new EmployeeMapper());
	// return employees;
	// }
	//
	// public void delete(Integer empid) {
	// String SQL = "DELETE FROM Employee WHERE empid = ?";
	// jdbcTemplateObject.update(SQL, new Object[] { empid });
	// System.out.println("Deleted Record with EMPID = " + empid);
	// }
	//
	// public void update(Integer empid, Integer age) {
	// String SQL = "UPDATE Employee SET age = ? WHERE empid = ?";
	// jdbcTemplateObject.update(SQL, new Object[] { age, empid });
	// System.out.println("Updated Record with EMPID = " + empid);
	// }

}

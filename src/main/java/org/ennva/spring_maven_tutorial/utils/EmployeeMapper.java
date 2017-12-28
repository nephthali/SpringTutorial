package org.ennva.spring_maven_tutorial.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.ennva.spring_maven_tutorial.model.Employee;
import org.springframework.jdbc.core.RowMapper;

public class EmployeeMapper implements RowMapper<Employee> {

	/**
	 * Spring provides a RowMapper interface for mapping a single row of a ResultSet to an object. 
	 * It can be used for both single and multiple row queries. It is parameterized as of Spring 3.0.
	 */
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee employee = new Employee();
		employee.setEmpid(rs.getInt("empid"));
		employee.setName(rs.getString("name"));
		employee.setAge(rs.getInt("age"));
		employee.setSalary(rs.getLong("salary"));
		return employee;
	}

}

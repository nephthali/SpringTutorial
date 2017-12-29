package org.ennva.spring_maven_tutorial.dao;

import java.util.List;

import org.ennva.spring_maven_tutorial.model.Employee;

public interface EmployeeDao {

	/**
	 * This is the method to be used to create a record in the Employee table.
	 */
	void createEmployee(Employee employee);

	/**
	 * This is the method to be used to list down a record from the Employee table
	 * corresponding to a passed Employee id.
	 */
	Employee getEmployee(Integer empid);

	/**
	 * This is the method to be used to list down all the records from the Employee
	 * table.
	 */
	List<Employee> listEmployees();

	/**
	 * This is the method to be used to delete a record from the Employee table
	 * corresponding to a passed Employee id.
	 */
	void delete(Employee employee);

	/**
	 * This is the method to be used to update a record into the Employee table.
	 */
	void update(Employee employee);

}

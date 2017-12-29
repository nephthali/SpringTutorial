package org.ennva.spring_maven_tutorial.dao.impl;

import java.util.List;

import org.ennva.spring_maven_tutorial.dao.EmployeeDao;
import org.ennva.spring_maven_tutorial.model.Employee;
import org.ennva.spring_maven_tutorial.utils.SuperHibernateDaoSupport;
import org.springframework.stereotype.Repository;


@Repository
public class EmployeeDaoImpl extends SuperHibernateDaoSupport implements EmployeeDao {

	public void createEmployee(Employee employee) {
		getHibernateTemplate().setCheckWriteOperations(false);
		//getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		getHibernateTemplate().saveOrUpdate(employee);
	}

	public Employee getEmployee(Integer empid) {
		return (Employee) getHibernateTemplate().get(Employee.class, empid);
	}

	/**
	 * This is the method to be used to list down all the records from the Employee
	 * table.
	 */
	public List<Employee> listEmployees(){
		return (List<Employee>) getHibernateTemplate().find("FROM Employee");
	}

	/**
	 * This is the method to be used to delete a record from the Employee table
	 * corresponding to a passed Employee id.
	 */
	public void delete(Employee employee) {
		getHibernateTemplate().delete(employee);
	}

	/**
	 * This is the method to be used to update a record into the Employee table.
	 */
	public void update(Employee employee) {
		getHibernateTemplate().saveOrUpdate(employee);
	}
}

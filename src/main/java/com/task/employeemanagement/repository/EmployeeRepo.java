
package com.task.employeemanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.employeemanagement.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer>{
	List<Employee> findByManagerId(Integer id);
	List<Employee> findByManagerIdOrderByFirstNameAsc(Integer id);

	Employee findByManagerIdAndEmpId(Integer managerid, Integer empid);

	Employee findByEmpId(Integer empid);
}

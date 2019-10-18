
package com.task.employeemanagement.repository;

import com.task.employeemanagement.entity.Employee;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
	List<Employee> findByManagerId(Integer id);
	List<Employee> findByManagerIdOrderByFirstNameDesc(Integer id);

	Employee findByManagerIdAndEmpId(Integer managerid, Integer empid);

	Employee findByEmpId(Integer empid);
}

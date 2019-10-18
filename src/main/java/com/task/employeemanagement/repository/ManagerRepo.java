package com.task.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.task.employeemanagement.entity.Manager;

public interface ManagerRepo extends JpaRepository<Manager, Integer> {
	
	public Manager findByEmail(String email);
	public Manager findByEmailAndPassword(String email,String password);

}
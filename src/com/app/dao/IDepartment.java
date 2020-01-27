package com.app.dao;

import java.util.List;

import com.app.pojos.Department;


public interface IDepartment 
{
	public Integer registerDepartment(Department department);
	
	Department getDepartmentDetails(int id);
	
	Department updateDepartmentDetails(Department department);
	
	public List<Department> getAllDepartment();
}

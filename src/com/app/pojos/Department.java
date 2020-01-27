package com.app.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "t_departments")
public class Department 
{
	private Integer id;
	
	private String department_name;
	
	@JsonIgnore
	List<User> user = new ArrayList<>();
	
	public Department() 
	{
	}

	public Department(String department_name) 
	{
		super();
		this.department_name = department_name;
	}

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id", length = 8, unique = true, nullable = false)
	public Integer getId() 
	{
		return id;
	}

	public void setId(Integer id) 
	{
		this.id = id;
	}

	@Column(name = "department_name")
	public String getDepartment_name() 
	{
		return department_name;
	}

	public void setDepartment_name(String department_name) 
	{
		this.department_name = department_name;
	}

	@OneToMany(mappedBy = "department",cascade = CascadeType.ALL,orphanRemoval = true)
	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

	@Override
	public String toString() 
	{
		return "Department [id=" + id + ", department_name=" + department_name + "]";
	}
	
}

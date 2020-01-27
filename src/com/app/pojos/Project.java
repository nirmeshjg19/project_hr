package com.app.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
@Table(name = "t_project")
public class Project 
{
	private Integer id;
	private String project_name;
	private PStatus status;
	
	@JsonBackReference
	List<User> users = new ArrayList<>();
	
	public Project() 
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public Project(String project_name, PStatus status) {
		super();
		this.project_name = project_name;
		this.status = status;
	}

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id", length = 8, unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "project_name")
	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	public PStatus getStatus() {
		return status;
	}

	public void setStatus(PStatus status) {
		this.status = status;
	}

	@OneToMany(mappedBy = "project",cascade = CascadeType.ALL,orphanRemoval = true)
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", project_name=" + project_name + ", status=" + status + "]";
	}
	
	
}

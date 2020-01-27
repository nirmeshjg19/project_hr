package com.app.dao;

import java.util.List;

import com.app.pojos.Project;
import com.app.pojos.User;


public interface IProject {
	
	public Integer registerProject(Project proj);
	
	public List<Project> getAllProjects();

	public Project updateProject(Project p);
	
	Project getProjectDetails(int id);
}

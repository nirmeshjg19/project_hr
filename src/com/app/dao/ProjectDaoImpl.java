package com.app.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.Project;

@Repository
@Transactional
public class ProjectDaoImpl implements IProject

{
	@Autowired
	private SessionFactory sf;

	@Override
	public Integer registerProject(Project proj) 
	{
		return (Integer)sf.getCurrentSession().save(proj);
	}

	@Override
	public List<Project> getAllProjects()
	{
		String jpql="select p from Project p";
		
		return sf.getCurrentSession().createQuery(jpql, Project.class).getResultList();
		
	}

	@Override
	public Project updateProject(Project p) {
		
		
		Session hs=sf.getCurrentSession();
		hs.clear();
		hs.update(p);
		
		return p;
	}

	@Override
	public Project getProjectDetails(int id) 
	{
		String jpql = "select p from Project p where p.id =:id";
		return sf.getCurrentSession().createQuery(jpql, Project.class).setParameter("id", id).getSingleResult();
		
	}
	
	
	
	

}

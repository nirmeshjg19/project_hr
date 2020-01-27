package com.app.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.Department;


@Service
@Transactional
public class DepartmentDaoImpl implements IDepartment
{

	@Autowired
	private SessionFactory sf;
	
	@Override
	public Integer registerDepartment(Department department)
	{
		return (Integer)sf.getCurrentSession().save(department);
	}

	@Override
	public Department getDepartmentDetails(int id) 
	{
		String jpql = "select d from Department d where d.id =:id";
		return sf.getCurrentSession().createQuery(jpql, Department.class).setParameter("id", id).getSingleResult();
	}

	@Override
	public Department updateDepartmentDetails(Department d)
	{
		Session hs=sf.getCurrentSession();
		hs.clear();
		hs.update(d);
		return d;
	}

	@Override
	public List<Department> getAllDepartment() 
	{
		String jpql = "select d from Department d";
		return sf.getCurrentSession().createQuery(jpql, Department.class).getResultList();
	}

}

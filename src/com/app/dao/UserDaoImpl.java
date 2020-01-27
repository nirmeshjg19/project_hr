package com.app.dao;

import java.util.List;
import java.util.Random;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.app.pojos.Department;
import com.app.pojos.Otp;
import com.app.pojos.Project;
import com.app.pojos.User;

@Service
@Transactional
public class UserDaoImpl implements IUserDao 
{
		@Autowired
		private SessionFactory sf;
	
		@Override
		public Integer registerUser(User user, int project_id,int department_id) 
		{
		    int id = (Integer)sf.getCurrentSession().save(user);
			User u=sf.getCurrentSession().get(User.class,id);
			
			Project p=sf.getCurrentSession().get(Project.class,project_id);
			u.setProject(p);
			
			Department d=sf.getCurrentSession().get(Department.class,department_id);
			u.setDepartment(d);
			
			return 1;
		}
	
		@Override
		public User login(User user)
		{
			String jpql="select u from User u left outer join fetch u.department where u.email_id=:email_id and u.password=:password";
			return sf.getCurrentSession().createQuery(jpql,User.class).setParameter("email_id",user.getEmail_id()).setParameter("password",user.getPassword()).getSingleResult();
		}
	
		@Override
		public List<User> getAllUsers() 
		{
			String jpql = "select u from User u join fetch u.project";
			return sf.getCurrentSession().createQuery(jpql, User.class).getResultList();
		}
	
		@Override
		public User getUserDetails(int id) 
		{
			String jpql = "select u from User u join fetch u.project where u.id=:id";
			return sf.getCurrentSession().createQuery(jpql, User.class).setParameter("id", id).getSingleResult();
		}
	
		@Override
		public User updateDetails(User u) 
		{
				Session hs=sf.getCurrentSession();
				hs.clear();
				hs.update(u);
				return u;
		}

		
		@Override
		public User getUserByemail(String email_id)
		{
			String jpql="Select u from User u join fetch u.project where email_id=:email_id";
			return sf.getCurrentSession().createQuery(jpql, User.class).setParameter("email_id", email_id).getSingleResult();
		
		}
		
		@Override
		public User findByEmail(String email) 
		{
			String str = "select u from User u where u.email_id =:em";
			return sf.getCurrentSession().createQuery(str,User.class).setParameter("em",email).getSingleResult();
					
		}

		
		@Override
		public void setPass(String pass, String email) {
			System.out.println("Nirmesh validation");
			System.out.println(email);
			String str = "select u from User u where u.email_id=:em";
		User u= sf.getCurrentSession().createQuery(str,User.class).setParameter("em",pass).
					getSingleResult();
		System.out.println(u);
		
			u.setPassword(email);
		
			
		}
		
}


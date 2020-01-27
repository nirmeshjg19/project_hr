package com.app.dao;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.Department;
import com.app.pojos.Leave;
import com.app.pojos.Project;
import com.app.pojos.User;
import com.fasterxml.jackson.databind.ser.std.StdArraySerializers.DoubleArraySerializer;

@Service
@Transactional
public class LeaveDaoImpl implements ILeaveDao
{

	@Autowired
	private SessionFactory sf;
	
	@Override
	public List<Leave> getAllLeaves() 
	{
		String jpql = "select l from Leave l join fetch l.user";
		return sf.getCurrentSession().createQuery(jpql, Leave.class).getResultList();
	}

	@Override
	public Integer applyLeave(Leave leave, int u_id) 
	{	
		long diffInMillies = Math.abs(leave.getFromDate().getTime() - leave.getToDate().getTime());
		
	    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	    
	    int duration = (int) diff;
	    
		System.out.println(duration);
		
		leave.setDuration(duration + 1);
		leave.setActive(true);
		
		int id = (Integer)sf.getCurrentSession().save(leave);
		
		Leave l=sf.getCurrentSession().get(Leave.class,id);
		
		User u=sf.getCurrentSession().get(User.class,u_id);
		
		l.setUser(u);
		
		return u.getId();
	}

	@Override
	public List<Leave> getAllActiveLeaves()
	{
		String jpql = "select l from Leave l left outer join fetch l.user where l.active=1";
		return sf.getCurrentSession().createQuery(jpql, Leave.class).getResultList();
		
	}

	@Override
	public List<Leave> getAllLeavesOfUser(int id) 
	{
		
		String jpql = "select l from Leave l join fetch l.user where l.user.id =:id";
		return sf.getCurrentSession().createQuery(jpql, Leave.class).setParameter("id",id).getResultList();
	}
	
	@Override
	public Leave getLeaveById(int u_id) 
	{
		String jpql = "select l from Leave l join fetch l.user where user.u_id =:u_id";
		return sf.getCurrentSession().createQuery(jpql, Leave.class).setParameter("u_id",u_id).getSingleResult();
	}
	
	@Override
	public Leave approveLeave(Leave leave)
	{
		Session hs=sf.getCurrentSession();
		hs.clear();
		    
			    leave.setAcceptRejectFlag(true);
				leave.setActive(false);
				hs.update(leave);
			return leave;
	}

	
	@Override
	public Leave disapproveLeave(Leave leave)
	{
		Session hs=sf.getCurrentSession();
		hs.clear();
		
		leave.setAcceptRejectFlag(true);
		leave.setActive(false);
		hs.update(leave);
		
		return leave;
	}
	
	
}

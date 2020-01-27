package com.app.dao;

import java.util.List;

import com.app.pojos.Leave;
import com.app.pojos.User;

public interface ILeaveDao
{
	public List<Leave> getAllLeaves();	
	
	
	
	public List<Leave> getAllActiveLeaves();
	
	public List<Leave> getAllLeavesOfUser(int u_id);

	public Leave getLeaveById(int u_id); 
	
	
	
	
	public Integer applyLeave(Leave leave,int u_id);
	
	
	
	public Leave approveLeave(Leave leave);
	
	public Leave disapproveLeave(Leave leave);
    
}

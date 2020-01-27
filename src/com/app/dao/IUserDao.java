package com.app.dao;

import java.util.List;

import com.app.pojos.Otp;
import com.app.pojos.User;

public interface IUserDao 
{
	public User login(User user);

	void setPass(String pass, String email);
	
	public User findByEmail(String email);
	
	public Integer registerUser(User user,int project_id,int department_id);
	
	public List<User> getAllUsers();
	
	public User getUserDetails(int id);
	
	public User updateDetails(User u);

	public User getUserByemail(String email_id);
	
	
	
}
